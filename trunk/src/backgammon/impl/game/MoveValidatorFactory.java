package backgammon.impl.game;

import backgammon.game.BackgammonBoard;
import backgammon.game.GameType;
import backgammon.game.MoveValidator;

public final class MoveValidatorFactory {

	private static MoveValidatorFactory instance = null;

	private MoveValidatorFactory() {

	}

	public static MoveValidatorFactory getInstance() {
		if (instance == null)
			instance = new MoveValidatorFactory();
		return instance;
	}

	public MoveValidator getMoveValidator(GameType type, BackgammonBoard board) {
		switch (type) {
		case STANDARD:
			return new StandartBackgammonMoveValidator(board);
		default:
			return null;
		}
	}

}
