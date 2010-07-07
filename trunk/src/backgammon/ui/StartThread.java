package backgammon.ui;

import javax.swing.JFrame;

import backgammon.game.GameFacade;
import backgammon.game.GameType;

public class StartThread implements Runnable {

	private JFrame mainPanel;

	public StartThread(JFrame mainPanel) {
		this.mainPanel = mainPanel;
	}

	@Override
	public void run() {
		GameFacade gameFacade = new GameFacade();
		gameFacade.startGame(new UIPlayer(mainPanel), PlayerFactory
				.getPlayer(PlayerFactory.SIMPLE_PLAYER), null, true, GameType.STANDARD);
	}
}
