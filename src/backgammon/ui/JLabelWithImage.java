package backgammon.ui;

import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 * @author Andrei Penchev
 */
public class JLabelWithImage extends JLabel {

	public JLabelWithImage(String url, PointImpl size, PointImpl position,
			PointImpl pictureDesiredScale) {
		super();
		
		ImageIcon icon = new ImageIcon(getClass().getResource(url));
		if (pictureDesiredScale != null) {
			Image img = icon.getImage();
			Image newImg = img.getScaledInstance(pictureDesiredScale.getX(),
					pictureDesiredScale.getY(), java.awt.Image.SCALE_SMOOTH);
			icon = new ImageIcon(newImg);
		}
		setIcon(icon);
		setSize(size.getX(), size.getY());
		setLocation(position.getX(), position.getY());
	}
}
