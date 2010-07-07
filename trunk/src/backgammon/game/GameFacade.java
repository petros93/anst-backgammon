package backgammon.game;

import backgammon.impl.game.GameManager;

public class GameFacade {

	public void startGame(Player whitePlayer, Player blackPlayer,
			String gameName, boolean logMoves, GameType gameType) {
		Game game = GameManager.newGame(whitePlayer, blackPlayer, logMoves,
				gameType);
		BackgammonBoard board = null;
		if (gameName != null) {
			board = BoardLoader.getInstance().load(gameName);
		}
		game.start(board);
	}

	public void saveGame(BackgammonBoard board, String gameName) {
		BoardLoader.getInstance().save(board, gameName);
	}

}
