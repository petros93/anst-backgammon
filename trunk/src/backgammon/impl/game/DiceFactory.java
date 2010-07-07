package backgammon.impl.game;

import java.util.Random;

import backgammon.game.Dice;

/**
 * Singleton and Flyweight factory
 * 
 */
public final class DiceFactory {

	static private DiceFactory instance = null;

	private static final Random generator = new Random();

	private DiceImpl dice[][];

	static public DiceFactory getInstance() {
		if (instance == null)
			instance = new DiceFactory();
		return instance;
	}

	private DiceFactory() {
		dice = new DiceImpl[6][6];
		for (int i = 1; i <= 6; i++) {
			for (int j = 1; j <= 6; j++) {
				dice[i][j] = new DiceImpl(i, j);
			}
		}
	}

	public Dice getDice(int die1, int die2) {
		return dice[die1][die2];
	}

	public Dice getRandomDice() {
		return dice[generator.nextInt(6) + 1][generator.nextInt(6) + 1];
	}

}
