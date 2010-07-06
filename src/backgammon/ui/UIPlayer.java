package backgammon.ui;

import backgammon.game.BackgammonBoard;
import backgammon.game.Dice;
import backgammon.game.GameOverStatus;
import backgammon.game.Player;
import backgammon.game.PlayerMove;

public class UIPlayer implements Player{

	public UIPlayer() {
		
	}
	@Override
	public void gameOver(BackgammonBoard board, boolean wins,
			GameOverStatus status) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public PlayerMove getMove(BackgammonBoard board, Dice dice) throws Exception {
		
		return null;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

}
