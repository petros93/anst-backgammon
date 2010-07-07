package backgammon.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;

/**
 * @author Andrei Penchev
 */
public class MainFrame extends JFrame {

	private static final long serialVersionUID = 4L;

	private BtnStartCommand startNewGameButton;
	private BtnLoadCommand previousGameButton;
	private BtnExitCommand exitButton;
	public static JLayeredPane mainPanel;

	/**
	 * Default constructor
	 */
	public MainFrame() {
		setSize(805, 535);
		setLayout(new BorderLayout());
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});

		mainPanel = new JLayeredPane();
		GameActionListener actionlist = new GameActionListener();
		mainPanel.setLayout(null);

		startNewGameButton = new BtnStartCommand("startGame", this);
		startNewGameButton.addActionListener(actionlist);
		startNewGameButton.setSize(new Dimension(130, 40));
		startNewGameButton.setLocation(380, 130);

		previousGameButton = new BtnLoadCommand("previousGame");
		previousGameButton.addActionListener(actionlist);
		previousGameButton.setSize(new Dimension(130, 40));
		previousGameButton.setLocation(380, 230);
		previousGameButton.setVisible(true);

		exitButton = new BtnExitCommand("exitButton");
		exitButton.addActionListener(actionlist);
		exitButton.setSize(new Dimension(130, 40));
		exitButton.setLocation(380, 330);

		JLabel backLabel = new JLabel();
		ImageIcon backGroungIcon = new ImageIcon(getClass().getResource(
		"/backgammon/ui/res/background.jpg"));
    Image img = backGroungIcon.getImage();
    Image newimg = img.getScaledInstance(800, 600, java.awt.Image.SCALE_SMOOTH);
    ImageIcon backIcon = new ImageIcon(newimg);
    backLabel.setIcon(backIcon);
    backLabel.setSize(800, 600);

		mainPanel.add(backLabel, new Integer(100));
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
