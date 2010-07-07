package backgammon.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

public class PlayerActionListener implements ActionListener {

	private JFrame frame;

	public PlayerActionListener(JFrame frame) {
		this.frame = frame;
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		String action = ae.getActionCommand();
		System.out.println("action: " + action);
	}

	private void startGame() {
		UIPlayer pl = new UIPlayer(frame);
		pl.start();
	}

}
