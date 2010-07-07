package backgammon.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

public class GameActionListener implements ActionListener {

	private JFrame frame;

	public GameActionListener(JFrame frame) {
		this.frame = frame;
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		String action = ae.getActionCommand();

		if (action.equals("exitButton")) {
			System.exit(0);
		} else if (action.equals("startGame")) {
			startGame();
		} else if (action.equals("previousGame")) {

		} else {
			System.out.println(action);
		}
	}

	private void startGame() {
		Thread th = new Thread(new StartThread(frame));
		th.start();
	}

}
