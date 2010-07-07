package backgammon.ui;

import javax.swing.JComponent;

/**
 * @author Andrei Penchev
 *
 */
public abstract class ComponentDecorator extends JComponent {

	public ComponentDecorator(JComponent comp) {
		super();
		setLayout(null);
		add("Center", comp);
	}
}
