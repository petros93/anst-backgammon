package backgammon.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

public class GameActionListener implements ActionListener {

	private JFrame frame;

	@Override
	public void actionPerformed(ActionEvent ae) {
		Command cmd = (Command) ae.getSource();
		cmd.executeAction();
	}

}
