package backgammon.ui;

import java.awt.geom.Point2D;

/**
 *
 */
public class Utils {

	public static PointImpl[][] coord;

	public static void creatCoordinates() {
		coord = new PointImpl[25][5];
		for (int i = 0; i < 25; i++) {
			coord[i] = new PointImpl[5];
		}
		PointImpl p = new PointImpl();
	}
}

class PointImpl extends Point2D {

	private double x;
	private double y;

	@Override
	public double getX() {
		return x;
	}

	@Override
	public double getY() {
		return y;
	}

	@Override
	public void setLocation(double x, double y) {
		this.x = x;
		this.y = y;
	}

}