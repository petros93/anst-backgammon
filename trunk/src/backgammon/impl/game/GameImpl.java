package backgammon.impl.game;

import backgammon.impl.logger.GameLoggerFactory;
import backgammon.game.BackgammonBoard;
import backgammon.game.Dice;
import backgammon.game.Game;
import backgammon.game.GameOverStatus;
import backgammon.game.Player;
import backgammon.game.PlayerMove;
import backgammon.logger.GameLogger;
import backgammon.util.BackgammonConfig;
import backgammon.util.Debug;

/**
 * Represents implementation of the {@link Game} interface. An instance of this
 * class is created for each game between two players. It is used for
 * realization of the rules of backgammon and manages the game. With each
 * instance of the GameImpl class a {@link BackgammonBoard} object is
 * associated. Class GameImpl uses class {@link MoveValidator} to validate the
 * players' moves. Each instance of this class is associated with a {@link Dice}
 * implementation that represent the dice.
 */
final class GameImpl implements Game {

	/**
	 * Timeout in milliseconds to wait for a single move. If the time elapses
	 * and the player has not yet returned a move, the game is over and the
	 * player loses.
	 */
	private static long MOVE_TIMEOUT = BackgammonConfig.getProperty(
			"backgammonator.game.moveTimeout", 2000);

	private Player whitePlayer;
	private Player blackPlayer;

	private Player winner = null;
	private volatile boolean running = false;

	private GameLogger logger;
	private boolean logMoves;

	private MoverRunnable mover;
	private Thread moverThread;

	private PlayerMove currentMove = null;
	private Throwable throwable = null;
	private BackgammonBoardImpl board;
	private DiceImpl dice;

	/**
	 * Constructs a game between two AI players.
	 */
	GameImpl(Player whitePlayer, Player blackPlayer, boolean logMoves) {
		this.whitePlayer = whitePlayer;
		this.blackPlayer = blackPlayer;
		dice = new DiceImpl();
		this.logMoves = logMoves;
		board = new BackgammonBoardImpl();
		if (logMoves) {
			logger = GameLoggerFactory.getLogger();
		}
	}

	/**
	 * @see Game#start()
	 */
	public GameOverStatus start(BackgammonBoard initBoard) {
		if (running)
			throw new IllegalStateException("Game is not over");
		running = true;
		Debug.getInstance().info(
				"Starting game: " + whitePlayer.getName() + " vs "
						+ blackPlayer.getName(), Debug.GAME_LOGIC);
		try {
			winner = null;
			if (initBoard != null) {
				board = (BackgammonBoardImpl) initBoard;
			} else {
				board.reset();
			}
			if (logMoves)
				logger.startGame(whitePlayer, blackPlayer);
			GameOverStatus status;

			startNewMoverThread(false);

			while (true) {
				status = makeMove(whitePlayer, blackPlayer);
				if (status != null)
					break;
				status = makeMove(blackPlayer, whitePlayer);
				if (status != null)
					break;
			}

			if (logMoves)
				logger.endGame(status,
						status.isNormal() ? board.getCurrentPlayerColor()
								: board.getCurrentPlayerColor().opposite());
			return status;

		} finally {
			Debug.getInstance().info(
					"Game over: " + winner.getName() + " wins",
					Debug.GAME_LOGIC);
			running = false;
		}
	}

	/**
	 * @see Game#getFilename()
	 */
	@Override
	public String getFilename() {
		return logMoves ? logger.getFilename() : "";
	}

	/**
	 * @see Game#getWinner()
	 */
	@Override
	public Player getWinner() {
		return winner;
	}

	private void startNewMoverThread(boolean kill) {
		if (kill) {
			mover.stop();
			kill(moverThread, true, 500);
		}
		mover = new MoverRunnable();
		moverThread = new Thread(mover, "[Game " + whitePlayer.getName()
				+ " vs " + blackPlayer.getName() + "] Mover Thread");
		moverThread.start();
	}

	/**
	 * Return the end game status if the game is over, on null otherwise
	 */
	private GameOverStatus makeMove(Player currentPlayer, Player other) {
		board.switchPlayer();
		dice.generateNext();

		try {
			mover.makeMove(currentPlayer);
			if (throwable != null) {
				Debug.getInstance().error(
						"Exception thrown while performing move for player "
								+ currentPlayer.getName(), Debug.GAME_LOGIC,
						throwable);
				mover.gameOver(currentPlayer, false, GameOverStatus.EXCEPTION);
				board.switchPlayer();
				mover.gameOver(other, true, GameOverStatus.EXCEPTION);
				mover.stop();
				board.switchPlayer();
				winner = other;
				return GameOverStatus.EXCEPTION;
			}

			if (!mover.notified()) {
				Debug.getInstance().error(
						"Move timeout for player " + currentPlayer.getName(),
						Debug.GAME_LOGIC, null);

				startNewMoverThread(true);
				mover.gameOver(currentPlayer, false, GameOverStatus.TIMEDOUT);
				board.switchPlayer();
				mover.gameOver(other, true, GameOverStatus.TIMEDOUT);
				mover.stop();
				board.switchPlayer();
				winner = other;
				return GameOverStatus.TIMEDOUT;
			}

			boolean invalid = false;

			if (currentMove == null || !board.makeMove(currentMove, dice)) {
				Debug.getInstance().error(
						"Invalid move for player " + currentPlayer.getName(),
						Debug.GAME_LOGIC, null);
				mover.gameOver(currentPlayer, false,
						GameOverStatus.INVALID_MOVE);
				board.switchPlayer();
				mover.gameOver(other, true, GameOverStatus.INVALID_MOVE);
				mover.stop();
				board.switchPlayer();
				winner = other;
				invalid = true;
			}

			if (logMoves && currentMove != null) {
				logger.logMove(currentMove, dice, invalid, board);
			}

			if (invalid)
				return GameOverStatus.INVALID_MOVE;

			if (board.getBornOff(board.getCurrentPlayerColor()) == 15) {
				GameOverStatus status = board.getWinStatus();
				mover.gameOver(currentPlayer, true, status);
				board.switchPlayer();
				mover.gameOver(other, false, status);
				mover.stop();
				board.switchPlayer();
				winner = currentPlayer;
				return status;
			}
		} catch (Exception e) {
			Debug.getInstance().error(
					"Exception thrown while performing move for player "
							+ currentPlayer.getName(), Debug.GAME_LOGIC, e);
			mover.gameOver(currentPlayer, false, GameOverStatus.EXCEPTION);
			board.switchPlayer();
			mover.gameOver(other, true, GameOverStatus.EXCEPTION);
			mover.stop();
			board.switchPlayer();
			winner = other;
			return GameOverStatus.EXCEPTION;
		}

		return null;
	}

	/**
	 * An utility method to safely stop a thread. Initially it tries to
	 * interrupt the thread. It it is still alive, it stops it. And if stop
	 * doesn't help, it calls destroy.
	 * 
	 * @param thread
	 *            the thread to stop
	 * @param callStop
	 *            <code>true</code> to call stop and destroy.
	 * @param joinTime
	 *            the time to wait for the thread to die.
	 * @return <code>true</code> if the thread is stopped
	 */
	@SuppressWarnings("deprecation")
	private static final boolean kill(Thread thread, boolean callStop,
			int joinTime) {
		boolean alive = thread.isAlive();
		if (alive) {
			try {
				thread.interrupt();
			} catch (Throwable t) {
			}
			if (callStop && thread.isAlive())
				try {
					thread.join(joinTime);
					if (thread.isAlive()) {
						thread.stop();
					}
					if (thread.isAlive()) {
						thread.join(joinTime);
					}
					if (thread.isAlive()) {
						thread.destroy();
					}
				} catch (Throwable t) {
				}
			alive = thread.isAlive();
		}
		return !alive;
	}

	/**
	 * For each game an instance of this class is created and it is used to
	 * initialize a Thread object. Every player's move is executed in this
	 * thread in order to prevent hanging of the main thread.
	 */
	@SuppressWarnings("synthetic-access")
	class MoverRunnable implements Runnable {

		private Player player;

		private int operation = 0;
		private GameOverStatus status = null;
		private boolean wins;

		private boolean suspended = true;
		private boolean notified = false;
		private boolean stopped = false;

		private Object synch = new Object();
		private int waiting = 0;

		@Override
		public void run() {
			while (true) {
				synchronized (synch) {
					if (suspended && !stopped) {
						try {
							waiting++;
							synch.wait();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						waiting--;
					}
					if (stopped)
						return;
				}
				try {
					switch (operation) {
					case 1: // make move
						currentMove = player.getMove(board, dice);
						break;
					case 2: // game over
						player.gameOver(board, wins, status);
					default:
						break;
					}

				} catch (Exception e) {
					throwable = e;
				}
				notify0();
			}

		}

		/**
		 * Makes the move for the specified player on the board.
		 * 
		 * @param player
		 *            the player to move
		 */
		public void makeMove(Player player) {
			resume(player, 1, false, null);
			waitForMoveDone(MOVE_TIMEOUT);
		}

		/**
		 * Ends the game for the specified player.
		 * 
		 * @param player
		 *            the player which game is to be ended
		 */
		public void gameOver(Player player, boolean wins, GameOverStatus status) {
			resume(player, 2, wins, status);
			waitForMoveDone(MOVE_TIMEOUT * 2);
		}

		/**
		 * Causes the Runnable object to exit its run method.
		 */
		public void stop() {
			synchronized (synch) {
				if (!stopped) {
					stopped = true;
					if (waiting > 0)
						synch.notify();
				}
			}
		}

		/**
		 * Returns <code>true</code> if the thread has been notified.
		 */
		public boolean notified() {
			return notified;
		}

		private boolean waitForMoveDone(long timeout) {
			synchronized (synch) {
				if (!notified) {
					try {
						waiting++;
						synch.wait(timeout);
					} catch (InterruptedException e) {
						// TODO handle
						e.printStackTrace();
					}
					waiting--;
				}
			}
			if (!notified)
				return false;
			return true;
		}

		private void resume(Player player, int operation, boolean wins,
				GameOverStatus status) {
			synchronized (synch) {

				this.player = player;
				this.operation = operation;
				this.status = status;
				this.wins = wins;

				notified = false;
				suspended = false;
				throwable = null;
				if (waiting > 0)
					synch.notify();
			}
		}

		private void notify0() {
			synchronized (synch) {
				player = null;
				status = null;

				notified = true;
				suspended = true;
				if (waiting > 0)
					synch.notify();
			}
		}
	}
}
