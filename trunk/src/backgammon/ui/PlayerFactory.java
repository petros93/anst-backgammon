package backgammon.ui;

import backgammon.game.Player;

public class PlayerFactory {

	public static int SIMPLE_PLAYER = 1;
	public static int NORMAL_PLAYER = 2;
	public static int HARD_PLAYER = 3;

	public static Player getPlayer(int player) {
		if (player == SIMPLE_PLAYER) {
			return new SamplePlayer();
		} else {
			//
		}
		return null;
	}
}
