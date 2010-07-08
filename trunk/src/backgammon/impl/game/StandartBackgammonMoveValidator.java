package backgammon.impl.game;

import backgammon.game.BackgammonBoard;
import backgammon.game.CheckerMove;
import backgammon.game.Constants;
import backgammon.game.Dice;
import backgammon.game.MoveValidator;
import backgammon.game.PlayerColor;
import backgammon.game.PlayerMove;
import backgammon.game.Point;

/**
 * Validates the game play.
 */
public final class StandartBackgammonMoveValidator extends MoveValidator {

	public StandartBackgammonMoveValidator(BackgammonBoard board) {
		super(board);
	}

	public boolean validateMove(PlayerMove move, Dice dice) {
		if (move.isDouble() != dice.isDouble()) {
			return false;
		}
		CheckerMove m1 = move.getCheckerMove(0);
		CheckerMove m2 = move.getCheckerMove(1);
		if (!((m1.getDie() == dice.getDie1() && m2.getDie() == dice.getDie2()) || (m1
				.getDie() == dice.getDie2() && m2.getDie() == dice.getDie1()))) {
			return false;
		}

		if (move.isDouble()
				&& (move.getCheckerMove(2).getDie() != dice.getDie1() || move
						.getCheckerMove(3).getDie() != dice.getDie1())) {
			return false;
		}

		return true;
	}

	public boolean validateMove(CheckerMove move) {
		if (move.isUnavailableMove()) {
			if (!validateEmptyMoves(move)) {
				return false;
			}
			return true;
		}
		if (!validateStartAndEndPoints(move)) {
			return false;
		}
		if (!isReenteringValid(move)) {
			return false;
		}
		if (!isBearingOffValid(move)) {
			return false;
		}
		return true;
	}

	private boolean validateStartAndEndPoints(CheckerMove move) {
		PlayerColor color = board.getCurrentPlayerColor();
		if (move.isReenterHitChecker()) {
			if (board.getHits(color) <= 0) {
				return false;
			}
		} else {
			Point source = board.getPoint(move.getStartPoint());
			if (source.getColor() != color || source.getCount() <= 0) {
				return false;
			}
		}
		if (!move.isBearingOff()) {
			Point dest = board.getPoint(move.getEndPoint());
			if (dest.getCount() > 1 && dest.getColor() != color) {
				return false;
			}
		}
		return true;
	}

	private boolean isReenteringValid(CheckerMove move) {
		return !((board.getHits(board.getCurrentPlayerColor()) > 0 && !move
				.isReenterHitChecker()));
	}

	private boolean isBearingOffValid(CheckerMove move) {
		// TODO
		if (move.isBearingOff()) {
			for (int i = Constants.MAX_DIE + 1; i <= Constants.POINTS_COUNT; i++) {
				if (board.getPoint(i).getCount() > 0
						&& board.getPoint(i).getColor() == board
								.getCurrentPlayerColor()) {
					return false;
				}
			}
		}
		return true;
	}

	private boolean validateEmptyMoves(CheckerMove move) {
		if (!move.isUnavailableMove())
			return true;
		int die = move.getDie();
		PlayerColor color = board.getCurrentPlayerColor();

		if (board.getHits(color) > 0) {
			return board.getPoint(25 - die).getCount() > 1
					&& board.getPoint(25 - die).getColor() != color;
		}

		boolean isBearingOff = true;
		for (int i = Constants.MAX_DIE + 1; i <= Constants.POINTS_COUNT; i++) {
			if (board.getPoint(i).getCount() > 0
					&& board.getPoint(i).getColor() == board
							.getCurrentPlayerColor()) {
				isBearingOff = false;
				Point dest = board.getPoint(i + die);
				if (dest.getCount() < 2
						|| (dest.getCount() > 1 && dest.getColor() == color)) {
					return false;
				}
			}
		}

		for (int i = die + 1; i <= Constants.MAX_DIE; i++) {
			if (board.getPoint(i).getCount() > 0
					&& board.getPoint(i).getColor() == color) {
				// TODO
				return false;
			}
		}

		if (isBearingOff) {
			for (int i = die + 1; i <= Constants.MAX_DIE; i++) {
				if (board.getPoint(i).getCount() > 0
						&& board.getPoint(i).getColor() == color) {
					return false;
				}
			}
		}

		return true;
	}
}