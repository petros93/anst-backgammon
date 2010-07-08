package backgammon.impl.game;

import backgammon.game.GameFacade;
import backgammon.game.GameType;
import backgammon.ui.SamplePlayer;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		GameFacade gf = new GameFacade();
		gf.startGame(new SamplePlayer(), new SamplePlayer(), null, true,
				GameType.STANDARD);
	}

}
