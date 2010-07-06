package backgammonator.interfacce.logger;

import backgammonator.lib.game.BackgammonBoard;
import backgammonator.lib.game.Dice;
import backgammonator.lib.game.GameOverStatus;
import backgammonator.lib.game.Player;
import backgammonator.lib.game.PlayerColor;
import backgammonator.lib.game.PlayerMove;

/**
 * Interface to provide format for the output of a single game. May be
 * implemented as writing plane text in a txt file, table in html document,
 * print on the console, etc.
 */
public interface GameLogger {

	/**
	 * Notifies the logger that a game has started between the specified
	 * players. The logger should prepare for logging.
	 * 
	 * @param whitePlayer the first player to move.
	 * @param blackPlayer the second player to move .
	 */
	void startGame(Player whitePlayer, Player blackPlayer);

	/**
	 * Logs a single move of the backgammon board.
	 * 
	 * @param move the move to log.
	 * @param dice the numbers on the dice.
	 * @param invalid true if the move to log is invalid, of false otherwise.
	 * @param board current move's board configuration
	 */
	void logMove(PlayerMove move, Dice dice, boolean invalid, BackgammonBoard board);

	/**
	 * Notifies the logger that the game has finished giving the color of the
	 * winning player and the exist status of the game. The value of the exit
	 * status can be {@link GameOverStatus#NORMAL},
	 * {@link GameOverStatus#INVALID_MOVE}, {@link GameOverStatus#TIMEDOUT}. or
	 * {@link GameOverStatus#EXCEPTION}.
	 * 
	 * @param status is the exit code of the game
	 * @param winner is the winner of the game
	 */
	void endGame(GameOverStatus status, PlayerColor winner);

	/**
	 * Returns the name of the output file or null when not available.
	 * 
	 * @return the name of the output file or null when not available.
	 */
	String getFilename();

	/**
	 * Returns the timestamp of the logged game or null when not available.
	 * 
	 * @return the timestamp of the logged game or null when not available.
	 */
	public String getGameTimestamp();

}
