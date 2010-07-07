package backgammon.ui;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JLayeredPane;

/**
 * @author Andrei Penchev
 */
public class BackgoundPanel extends JLayeredPane {

	private static String BOARD_PATH = "res" + File.separator + "background.jpg";

	@Override
	public void paint(Graphics g) {
		super.paintComponent(g);
		Image img = null;
		try {
			img = ImageIO.read(new File(BOARD_PATH));
		} catch (IOException e1) {
			e1.printStackTrace();
			// TODO debug
		}
		g.drawImage(img, 0, 0, 800, 500, null);
	}
}
