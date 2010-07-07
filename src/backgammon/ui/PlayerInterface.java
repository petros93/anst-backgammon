package backgammon.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;

import backgammon.game.BackgammonBoard;
import backgammon.game.Dice;
import backgammon.game.PlayerColor;
import backgammon.game.Point;

public class PlayerInterface implements ActionListener, PropertyChangeListener {

	private JFrame mainFrame;
	private BackgammonBoard board;
	private Dice dice;
	private Object sync;
	private static JLayeredPane backPanel;

	public PlayerInterface(JFrame mainFrame, BackgammonBoard board, Dice dice,
			Object sync) {
		this.mainFrame = mainFrame;
		this.board = board;
		this.dice = dice;
		this.sync = sync;
	}

	public void run() {
		mainFrame.remove(MainFrame.mainPanel);
		if (backPanel != null) {
			mainFrame.remove(backPanel);
		}
		// make backgroung panel
		backPanel = new JLayeredPane();
		backPanel.setLayout(null);
		backPanel.setSize(800, 500);
		backPanel.setVisible(true);
		backPanel.add(new JLabelWithImage("/backgammon/ui/res/board.png",
				new PointImpl(900, 500), new PointImpl(0, 0), new PointImpl(800, 500)),
				new Integer(100));

		// add borders
		visualBorders();

		// add dies
		visualDies();

		// add combo boxes & fields
		visualizeFields();

		// vizualize button
		JButton button = new JButton("give values");
		button.addActionListener(this);
		button.setLocation(350, 320);
		button.setSize(100, 40);
		backPanel.add(button, new Integer(301));

		mainFrame.add(backPanel);
		mainFrame.setVisible(true);
		System.out.println("tararar1");
	}

	private void visualDies() {
		backPanel.add(new JLabelWithImage("/backgammon/ui/res/" + dice.getDie1()
				+ ".png", new PointImpl(100, 100), new PointImpl(355, 120), null),
				new Integer(301));

		backPanel.add(new JLabelWithImage("/backgammon/ui/res/" + dice.getDie2()
				+ ".png", new PointImpl(100, 100), new PointImpl(410, 120), null),
				new Integer(301));
	}

	private void visualBorders() {
		for (int i = 1; i <= 24; i++) {
			Point p = board.getPoint(i);
			int t = p.getCount();
			if (t > 0 && p.getColor() == PlayerColor.WHITE) {
				for (int j = 1; j <= t; j++) {
					backPanel.add(new JLabelWithImage("/backgammon/ui/res/white.png",
							new PointImpl(40, 40), Utils.coord[i][j], new PointImpl(40, 40)),
							new Integer(301));
				}
			}

			if (t > 0 && p.getColor() == PlayerColor.BLACK) {
				for (int j = 1; j <= t; j++) {
					backPanel.add(new JLabelWithImage("/backgammon/ui/res/black.png",
							new PointImpl(40, 40), Utils.coord[i][j], new PointImpl(40, 40)),
							new Integer(301));
				}
			}
		}
	}

	private void visualizeFields() {
		System.out.println("2132");
		JComboBox combo1 = new JComboBox(Utils.petStrings);
		combo1.setLocation(355, 200);
		combo1.setSize(40, 20);
		combo1.setName("1");
		combo1.addActionListener(this);

		JFormattedTextField text1 = new JFormattedTextField(Utils
				.createFormatter("#"));
		text1.setLocation(410, 200);
		text1.setSize(30, 20);
		text1.setName("1");
		text1.addPropertyChangeListener("value", this);

		JComboBox combo2 = new JComboBox(Utils.petStrings);
		combo2.setLocation(355, 230);
		combo2.setSize(40, 20);
		combo2.setName("2");
		combo2.addActionListener(this);

		JFormattedTextField text2 = new JFormattedTextField(Utils
				.createFormatter("#"));
		text2.setLocation(410, 230);
		text2.setSize(30, 20);
		text2.setName("2");
		text2.addPropertyChangeListener("value", this);
		if (dice.isDouble()) {
			JComboBox combo3 = new JComboBox(Utils.petStrings);
			combo3.setLocation(355, 260);
			combo3.setSize(40, 20);
			combo3.setName("3");
			combo3.addActionListener(this);

			JFormattedTextField text3 = new JFormattedTextField(Utils
					.createFormatter("#"));
			text3.setLocation(410, 260);
			text3.setSize(30, 20);
			text3.setName("3");
			text3.addPropertyChangeListener("value", this);

			JComboBox combo4 = new JComboBox(Utils.petStrings);
			combo4.setLocation(355, 290);
			combo4.setSize(40, 20);
			combo4.setName("4");
			combo4.addActionListener(this);

			JFormattedTextField text4 = new JFormattedTextField(Utils
					.createFormatter("#"));
			text4.setLocation(410, 290);
			text4.setSize(30, 20);
			text4.setName("4");
			text4.addPropertyChangeListener("value", this);

			backPanel.add(combo3, new Integer(301));
			backPanel.add(text3, new Integer(301));
			backPanel.add(combo4, new Integer(301));
			backPanel.add(text4, new Integer(301));
		}
		backPanel.add(combo1, new Integer(301));
		backPanel.add(text1, new Integer(301));
		backPanel.add(combo2, new Integer(301));
		backPanel.add(text2, new Integer(301));
	}

	public static int[] postions = new int[5];
	public static int[] moves = new int[5];

	public void actionPerformed(ActionEvent ae) {
		String action = ae.getActionCommand();
		if (ae.getSource() instanceof JComboBox) {
			JComboBox com = (JComboBox) ae.getSource();
			postions[Integer.valueOf(com.getName()).intValue()] = Integer.valueOf(
					(String) com.getSelectedItem()).intValue();
		} else if (action.equals("give values")) {
			synchronized (sync) {
				sync.notifyAll();
			}
		}
	}

	@Override
	public void propertyChange(PropertyChangeEvent event) {
		if (event.getSource() instanceof JFormattedTextField) {
			JFormattedTextField textFiled = (JFormattedTextField) event.getSource();
			if (textFiled.getValue() != null) {
				moves[Integer.valueOf(textFiled.getName()).intValue()] = Integer
						.valueOf((String) textFiled.getValue()).intValue();
			}
		}
	}
}
