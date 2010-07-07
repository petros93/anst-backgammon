package backgammon.ui;

import java.awt.geom.Point2D;

/**
 *
 */
public class Utils {

	public static PointImpl[][] coord;

	public static void creatCoordinates() {
		coord = new PointImpl[25][6];
		for (int i = 13; i <= 18; i++) {
			for (int j = 1; j <= 5; j++) {
				coord[i][j] = new PointImpl(25 + ((i - 13) * 56), 15 + ((j - 1) * 40));
			}
		}

		for (int i = 19; i <= 24; i++) {
			for (int j = 1; j <= 5; j++) {
				coord[i][j] = new PointImpl(465 + ((i - 19) * 56), 15 + ((j - 1) * 40));
			}
		}

		for (int i = 12; i >= 7; i--) {
			for (int j = 1; j <= 5; j++) {
				coord[i][j] = new PointImpl(25 - ((i - 12) * 56), 440 - ((j - 1) * 40));
			}
		}
		
		for (int i = 6; i >= 1; i--) {
			for (int j = 1; j <= 5; j++) {
				coord[i][j] = new PointImpl(465 - ((i - 6) * 56), 440 - ((j - 1) * 40));
			}
		}
	}
}

class PointImpl {

	int x, y;

	public PointImpl(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

}