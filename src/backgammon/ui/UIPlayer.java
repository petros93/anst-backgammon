package backgammon.ui;

import javax.swing.JFrame;

import backgammon.game.BackgammonBoard;
import backgammon.game.CheckerMove;
import backgammon.game.Dice;
import backgammon.game.GameOverStatus;
import backgammon.game.Player;
import backgammon.game.PlayerMove;
import backgammon.util.ObjectPool;

public class UIPlayer implements Player {

	private JFrame mainFrame;
	private ObjectPool objPool;
	private CheckerMove allMoves[];

	public UIPlayer(JFrame panel) {
		this.mainFrame = panel;
		Utils.creatCoordinates();
		objPool = new ObjectPool(5, CheckerMove.class);
		System.out.println(objPool);
	}

	@Override
	public void gameOver(BackgammonBoard board, boolean wins,
			GameOverStatus status) {

	}

	Object sync = new Object();

	@Override
	public PlayerMove getMove(BackgammonBoard board, Dice dice) throws Exception {
		PlayerInterface plInterface = new PlayerInterface(mainFrame, board, dice,
				sync);
		plInterface.run();
		synchronized (sync) {
			sync.wait(3600000);
		}

		returnBorrowedObjects();
		return makePlayer(dice);
	}

	private void returnBorrowedObjects() {
		if (allMoves != null) {
			for (int p = 0; p < 4; p++) {
				if (allMoves[p] != null) {
					objPool.returnObject(allMoves[p]);
				}
			}
		}
	}

	private PlayerMove makePlayer(Dice dice) {
		int i = dice.isDouble() ? 4 : 2;
		allMoves = new CheckerMove[i];
		for (int j = 0; j < i; j++) {
			allMoves[j] = ((CheckerMove) objPool.borrowObject());
			allMoves[j].setStartPoint(PlayerInterface.postions[j]);
			allMoves[j].setDie(PlayerInterface.moves[j]);
		}

		PlayerMove playerMove = new PlayerMove(allMoves);
		return playerMove;
	}

	@Override
	public String getName() {
		return "UIPlayer";
	}
}
