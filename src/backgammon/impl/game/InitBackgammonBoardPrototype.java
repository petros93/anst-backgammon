package backgammon.impl.game;

import backgammon.game.BackgammonBoard;

/**
 * Gives Initial BackgammonBoard Prototype. Singleton
 */
public final class InitBackgammonBoardPrototype {

	private BackgammonBoardImpl initBoard = new BackgammonBoardImpl();

	static private InitBackgammonBoardPrototype instance = null;

	/**
	 * Creates default board initiated with the default playing positions.
	 */
	private InitBackgammonBoardPrototype() {
	}

	static public InitBackgammonBoardPrototype getInstance() {
		if (instance == null)
			instance = new InitBackgammonBoardPrototype();
		return instance;
	}

	public BackgammonBoard getInitBoard() {
		return (BackgammonBoard) initBoard.clone();
	}

}
