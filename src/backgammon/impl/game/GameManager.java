package backgammon.impl.game;

import backgammon.game.Game;
import backgammon.game.GameType;
import backgammon.game.Player;

/**
 * This class is used to create a new game between two AI players.
 */
public class GameManager {

	/**
	 * Creates a game between the two AI players given as arguments. The players
	 * are given as Player objects.
	 * 
	 * @param white
	 *            Player the first player
	 * @param black
	 *            Player the second player
	 * @param logMoves
	 *            true if the game logger should be enabled and false otherwise
	 * @return new instance of the {@link Game} interface implementation
	 */
	public static Game newGame(Player white, Player black, boolean logMoves,
			GameType gameType) {
		return new GameImpl(white, black, logMoves, gameType);
	}

}
