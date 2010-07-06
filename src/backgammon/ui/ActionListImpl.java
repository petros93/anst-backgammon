package backgammon.ui;

import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ActionListImpl implements ActionListener {

	private Frame frame;

	public ActionListImpl(Frame frame) {
		this.frame = frame;
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		String action = ae.getActionCommand();

		if (action.equals("exitButton")) {
			frame.dispose();
			System.out.println("Exiting.");
			System.exit(0);
		} else if (action.equals("startGame")) {
			System.out.println("startGame.");
			startGame();
		} else if (action.equals("previousGame")) {

		} else {
			System.out.println(action);
		}
	}

	private void startGame() {
		
	}

}
