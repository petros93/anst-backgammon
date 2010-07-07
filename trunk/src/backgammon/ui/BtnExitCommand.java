package backgammon.ui;

import javax.swing.JButton;

/**
 * @author Andrei Penchev
 *
 */
public class BtnExitCommand extends JButton implements Command {

	public BtnExitCommand(String str) {
		super(str);
	}
	
	@Override
	public void executeAction() {
		System.exit(0);
	}

}
