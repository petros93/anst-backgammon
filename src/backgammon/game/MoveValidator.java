package backgammon.game;

/**
 * Validates the game play.
 */
public abstract class MoveValidator {

	protected BackgammonBoard board;

	/**
	 * Creates a move validator associated to the specified board.
	 */
	public MoveValidator(BackgammonBoard board) {
		this.board = board;
	}

	/**
	 * Validates if the move is valid according the board and the dice.
	 * 
	 * @return true if the move is valid.
	 */
	public abstract boolean validateMove(PlayerMove move, Dice dice);

	/**
	 * Checks if the move is valid according to the board.
	 * 
	 * @return true if the move is valid according to the board.
	 */
	public abstract boolean validateMove(CheckerMove move);

}
