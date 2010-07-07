package backgammon.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameActionListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent ae) {
		Command cmd = (Command) ae.getSource();
		cmd.executeAction();
	}

}
