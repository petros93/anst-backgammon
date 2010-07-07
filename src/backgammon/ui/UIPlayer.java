package backgammon.ui;

import javax.swing.JFrame;

import backgammon.game.BackgammonBoard;
import backgammon.game.CheckerMove;
import backgammon.game.Dice;
import backgammon.game.GameOverStatus;
import backgammon.game.Player;
import backgammon.game.PlayerMove;

public class UIPlayer implements Player {

	private JFrame mainFrame;

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
		plInterface.run();
		synchronized (sync) {
			sync.wait(3600000);
		}

		CheckerMove allMoves[];
		if (dice.isDouble()) {
			allMoves = new CheckerMove[4];
		} else {
			allMoves = new CheckerMove[2];
		}
		
		allMoves[0] = new CheckerMove(PlayerInterface.postions[1],
				PlayerInterface.moves[1]);
		allMoves[1] = new CheckerMove(PlayerInterface.postions[2],
				PlayerInterface.moves[2]);
		if (dice.isDouble()) {
			allMoves[3] = new CheckerMove(PlayerInterface.postions[3],
					PlayerInterface.moves[3]);
			allMoves[4] = new CheckerMove(PlayerInterface.postions[4],
					PlayerInterface.moves[4]);
		}
		System.out.println(PlayerInterface.postions[1]);
		System.out.println(PlayerInterface.moves[1]);
		System.out.println(PlayerInterface.postions[2]);
		System.out.println(PlayerInterface.moves[2]);
		PlayerMove playerMove = new PlayerMove(allMoves);

		return playerMove;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}
}
