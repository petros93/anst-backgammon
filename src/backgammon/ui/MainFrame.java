package backgammon.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * @author Andrei Penchev
 */
public class MainFrame extends JFrame {

	private static final long serialVersionUID = 4L;

	private JButton startNewGameButton;
	private JButton previousGameButton;
	private JButton exitButton;

	/**
	 * Default constructor
	 */
	public MainFrame() {
		setSize(800, 500);
		setLayout(new BorderLayout());
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		ActionListImpl actionlist = new ActionListImpl(this);

		BackgoundPanel mainPanel = new BackgoundPanel();
		mainPanel.setLayout(new FlowLayout());

		startNewGameButton = new JButton("startGame");
		startNewGameButton.addActionListener(actionlist);
		startNewGameButton.setVisible(true);
		startNewGameButton.setLocation(380, 200);

		previousGameButton = new JButton("previosGame");
		previousGameButton.addActionListener(actionlist);
		previousGameButton.setVisible(true);
		previousGameButton.setLocation(380, 250);

		exitButton = new JButton("exitButton");
		exitButton.addActionListener(actionlist);
		exitButton.setSize(exitButton.getPreferredSize());
		exitButton.setLocation(380, 300);

		mainPanel.add(startNewGameButton);
		mainPanel.add(previousGameButton);
		mainPanel.add(exitButton);
		add(mainPanel);
		mainPanel.setVisible(true);
	}

	/**
	 * Main method
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		MainFrame window = new MainFrame();
		window.setVisible(true);
	}
}
