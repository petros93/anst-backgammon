package backgammon.impl.game;

import java.util.Random;

import backgammon.game.Dice;

/**
 * Represents implementation of the {@link Dice} interface.
 */
public final class DiceImpl implements Dice {

	private int[] die = new int[2];

	/**
	 * Creates dice with predefined values.
	 * 
	 * @param die1
	 *            the first die.
	 * @param die2
	 *            the second die.
	 */
	public DiceImpl(int die1, int die2) {
		die[0] = die1;
		die[1] = die2;
	}

	/**
	 * @see Dice#getDie1()
	 */
	public int getDie1() {
		return die[0];
	}

	/**
	 * @see Dice#getDie2()
	 */
	public int getDie2() {
		return die[1];
	}

	/**
	 * @see Dice#isDouble()
	 */
	public boolean isDouble() {
		return die[0] == die[1];
	}

	/**
	 * @see Object#toString()
	 */
	public String toString() {
		return "(" + die[0] + ", " + die[1] + ")";
	}
}