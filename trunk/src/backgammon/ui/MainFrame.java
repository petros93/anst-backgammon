package backgammon.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;

/**
 * @author Andrei Penchev
 */
public class MainFrame extends JFrame {

	private static final long serialVersionUID = 4L;

	private JButton startNewGameButton;
	private JButton previousGameButton;
	private JButton exitButton;
	public static BackgoundPanel1 mainPanel;

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

		mainPanel = new BackgoundPanel1();
		ActionListImpl actionlist = new ActionListImpl(this);
		mainPanel.setLayout(null);

		startNewGameButton = new JButton("startGame");
		startNewGameButton.addActionListener(actionlist);
		startNewGameButton.setSize(new Dimension(130, 40));
		startNewGameButton.setLocation(380, 130);

		previousGameButton = new JButton("previousGame");
		previousGameButton.addActionListener(actionlist);
		previousGameButton.setSize(new Dimension(130, 40));
		previousGameButton.setLocation(380, 230);
		previousGameButton.setVisible(true);

		exitButton = new JButton("exitButton");
		exitButton.addActionListener(actionlist);
		exitButton.setSize(new Dimension(130, 40));
		exitButton.setLocation(380, 330);

		mainPanel.add(startNewGameButton, new Integer(200));
		mainPanel.add(previousGameButton, new Integer(200));
		mainPanel.add(exitButton, new Integer(200));
		mainPanel.setVisible(true);
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
