package backgammon.ui;

import javax.swing.JButton;
import javax.swing.JFrame;

/**
 * @author Andrei Penchev
 *
 */
public class BtnStartCommand extends JButton implements Command {

	private JFrame frame;

	public BtnStartCommand(String str, JFrame frame) {
		super(str);
		this.frame = frame;
	}

	@Override
	public void executeAction() {
		Thread th = new Thread(new StartThread(frame));
		th.start();
	}

}
