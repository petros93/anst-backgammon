package backgammon.ui;

import javax.swing.JFrame;

import org.eclipse.swt.widgets.Display;


import backgammon.game.BackgammonBoard;
import backgammon.game.CheckerMove;
import backgammon.game.Dice;
import backgammon.game.GameFacade;
import backgammon.game.GameOverStatus;
import backgammon.game.Player;
import backgammon.game.PlayerMove;

public class UIPlayer implements Player {

	private JFrame mainFrame;
	private BackgammonBoard board;
	private Dice dice;

	public UIPlayer(JFrame panel) {
		this.mainFrame = panel;
		Utils.creatCoordinates();
	}

	@Override
	public void gameOver(BackgammonBoard board, boolean wins,
			GameOverStatus status) {

	}

	Object sync = new Object();

	@Override
	public PlayerMove getMove(BackgammonBoard board, Dice dice) throws Exception {
		System.out.println("getMove!");
		PlayerInterface plInterface = new PlayerInterface(mainFrame, board, dice,
				sync);
//		Display.getDefault().syncExec(plInterface);
		plInterface.run();
		synchronized (sync) {
			sync.wait(3600000);
		}

//		CheckerMove allMoves[] = new CheckerMove[4];
//		allMoves[0] = new CheckerMove(PlayerInterface.postions[1],
//				PlayerInterface.moves[1]);
//		allMoves[1] = new CheckerMove(PlayerInterface.postions[2],
//				PlayerInterface.moves[2]);
//		if (dice.isDouble()) {
//			allMoves[3] = new CheckerMove(PlayerInterface.postions[3],
//					PlayerInterface.moves[3]);
//			allMoves[4] = new CheckerMove(PlayerInterface.postions[4],
//					PlayerInterface.moves[4]);
//		}
//		PlayerMove playerMove = new PlayerMove(allMoves);
//
//		return playerMove;
		return null;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}
}
