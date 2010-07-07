package backgammon.impl.game;

import backgammon.game.BackgammonBoard;
import backgammon.game.GameType;

/**
 * Gives Initial BackgammonBoard Prototype. Singleton
 */
public final class InitStandardBackgammonBoardPrototype {

	private BackgammonBoardImpl initBoard = new BackgammonBoardImpl(GameType.STANDARD);

	static private InitStandardBackgammonBoardPrototype instance = null;

	/**
	 * Creates default board initiated with the default playing positions.
	 */
	private InitStandardBackgammonBoardPrototype() {
	}

	static public InitStandardBackgammonBoardPrototype getInstance() {
		if (instance == null)
			instance = new InitStandardBackgammonBoardPrototype();
		return instance;
	}

	public BackgammonBoard getInitBoard() {
		return (BackgammonBoard) initBoard.clone();
	}

}
