package backgammon.impl.logger;

import backgammon.logger.GameLogger;
import backgammon.logger.GameLoggerType;

/**
 * Represents factory for getting the configured type of {@link GameLogger}
 * implementation.
 */
public final class GameLoggerFactory {

	/**
	 * Gets new instance of the configured Logger implementation.
	 * 
	 * @return new instance of the configured Logger implementation
	 */
	public static GameLogger getLogger(GameLoggerType type) {
		switch (type) {
		case HTML:
			return new HTMLGameLogger();
		default:
			return null;
		}
	}
}
