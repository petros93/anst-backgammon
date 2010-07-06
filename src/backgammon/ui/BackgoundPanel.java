package backgammon.ui;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Panel;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 * @author Andrei
 */
public class BackgoundPanel extends JPanel {

	private static final long serialVersionUID = -1260929905428006244L;
	private static String BOARD_PATH = "res" + File.separator + "background.jpg";

	@Override
	public void paint(Graphics g) {
		System.out.println("ima ");
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
