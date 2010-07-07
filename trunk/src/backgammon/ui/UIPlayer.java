package backgammon.ui;

import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;

import backgammon.game.BackgammonBoard;
import backgammon.game.Dice;
import backgammon.game.GameFacade;
import backgammon.game.GameOverStatus;
import backgammon.game.Player;
import backgammon.game.PlayerColor;
import backgammon.game.PlayerMove;
import backgammon.game.Point;

public class UIPlayer implements Player {

	private JFrame mainFrame;

	public UIPlayer(JFrame panel) {
		this.mainFrame = panel;
	}

	@Override
	public void gameOver(BackgammonBoard board, boolean wins,
			GameOverStatus status) {

	}

	@Override
	public PlayerMove getMove(BackgammonBoard board, Dice dice) throws Exception {

		mainFrame.remove(MainFrame.mainPanel);
		System.out.println("test1");
		JLayeredPane backPanel = new JLayeredPane();
		backPanel.setLayout(null);
		backPanel.setSize(800, 500);
		backPanel.setVisible(true);
		backPanel.repaint();
		JLabel board1 = new JLabel();
		ImageIcon icon = new ImageIcon(getClass().getResource(
				"/backgammon/ui/board.png"));
		Image img = icon.getImage();
		Image newimg = img.getScaledInstance(800, 500, java.awt.Image.SCALE_SMOOTH);
		ImageIcon newIcon = new ImageIcon(newimg);

		board1.setIcon(newIcon);
		board1.setSize(800, 500);
		backPanel.add(board1, new Integer(100));

		JLabel whiteChecker = new JLabel();
		ImageIcon whiteCheckIcon = new ImageIcon(getClass().getResource(
				"/backgammon/ui/white.png"));
		img = whiteCheckIcon.getImage();
		newimg = img.getScaledInstance(40, 40, java.awt.Image.SCALE_SMOOTH);
		newIcon = new ImageIcon(newimg);
		whiteChecker.setIcon(newIcon);
		whiteChecker.setSize(40, 40);

		JLabel blackChecker = new JLabel();
		ImageIcon blackCheckerIcon = new ImageIcon(getClass().getResource(
				"/backgammon/ui/white.png"));
		img = blackCheckerIcon.getImage();
		newimg = img.getScaledInstance(40, 40, java.awt.Image.SCALE_SMOOTH);
		newIcon = new ImageIcon(newimg);
		blackChecker.setIcon(newIcon);
		blackChecker.setSize(40, 40);

		Point point;
		int count;
		int x = 25 + 56;
		int y = 15;

//		if (board.getPoint(13).getColor() == PlayerColor.WHITE) {
//			System.out.println("sdgdf");
//			point = board.getPoint(13);
//			count = point.getCount();
//			System.out.println(x);
//			x = x + 2*56;
//			whiteChecker.setLocation(x, y);
//			backPanel.add(whiteChecker, new Integer(300));
//		}
//		for (int i = 1; i <= 24; i++) {
//			point = board.getPoint(i);
//			count = point.getCount();
//			if (point.getColor() == PlayerColor.WHITE) {
//				x = i * 20;
				System.out.println(x);
				whiteChecker.setLocation(x, y);
				backPanel.add(whiteChecker, new Integer(300));
				
				whiteChecker = new JLabel();
				whiteCheckIcon = new ImageIcon(getClass().getResource(
						"/backgammon/ui/white.png"));
				img = whiteCheckIcon.getImage();
				newimg = img.getScaledInstance(40, 40, java.awt.Image.SCALE_SMOOTH);
				newIcon = new ImageIcon(newimg);
				whiteChecker.setIcon(newIcon);
				whiteChecker.setSize(40, 40);
				y += 40;
				whiteChecker.setLocation(x, y);
				backPanel.add(whiteChecker, new Integer(301));
				
				whiteChecker = new JLabel();
				whiteCheckIcon = new ImageIcon(getClass().getResource(
						"/backgammon/ui/white.png"));
				img = whiteCheckIcon.getImage();
				newimg = img.getScaledInstance(40, 40, java.awt.Image.SCALE_SMOOTH);
				newIcon = new ImageIcon(newimg);
				whiteChecker.setIcon(newIcon);
				whiteChecker.setSize(40, 40);
				y += 40;
				whiteChecker.setLocation(x, y);
				backPanel.add(whiteChecker, new Integer(301));
				
				whiteChecker = new JLabel();
				whiteCheckIcon = new ImageIcon(getClass().getResource(
						"/backgammon/ui/white.png"));
				img = whiteCheckIcon.getImage();
				newimg = img.getScaledInstance(40, 40, java.awt.Image.SCALE_SMOOTH);
				newIcon = new ImageIcon(newimg);
				whiteChecker.setIcon(newIcon);
				whiteChecker.setSize(40, 40);
				y += 40;
				whiteChecker.setLocation(x, y);
				backPanel.add(whiteChecker, new Integer(301));
				
				whiteChecker = new JLabel();
				whiteCheckIcon = new ImageIcon(getClass().getResource(
						"/backgammon/ui/white.png"));
				img = whiteCheckIcon.getImage();
				newimg = img.getScaledInstance(40, 40, java.awt.Image.SCALE_SMOOTH);
				newIcon = new ImageIcon(newimg);
				whiteChecker.setIcon(newIcon);
				whiteChecker.setSize(40, 40);
				y += 40;
				whiteChecker.setLocation(x, y);
				backPanel.add(whiteChecker, new Integer(301));
//			}
//		}
		mainFrame.add(backPanel);
		mainFrame.setVisible(true);
		mainFrame.repaint();
		return null;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

	public void visualizeSMT() {
		mainFrame.remove(MainFrame.mainPanel);
		System.out.println("test1");
		JLayeredPane backPanel = new JLayeredPane();
		backPanel.setLayout(null);
		backPanel.setSize(800, 500);
		backPanel.setVisible(true);
		backPanel.repaint();

		JLabel board = new JLabel();
		ImageIcon icon = new ImageIcon(getClass().getResource(
				"/backgammon/ui/board.png"));
		Image img = icon.getImage();
		Image newimg = img.getScaledInstance(800, 500, java.awt.Image.SCALE_SMOOTH);
		ImageIcon newIcon = new ImageIcon(newimg);

		board.setIcon(newIcon);
		board.setSize(800, 500);
		backPanel.add(board, new Integer(100));

		JLabel checker = new JLabel();
		ImageIcon checkIcon = new ImageIcon(getClass().getResource(
				"/backgammon/ui/white.png"));
		img = checkIcon.getImage();
		newimg = img.getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);
		newIcon = new ImageIcon(newimg);
		checker.setIcon(newIcon);
		checker.setSize(50, 50);
		checker.setLocation(20, 10);
		backPanel.add(checker, new Integer(300));

		mainFrame.add(backPanel);
		mainFrame.setVisible(true);
		mainFrame.repaint();
	}

	public void start() {
		GameFacade gameFacade = new GameFacade();
		gameFacade.startGame(this, new SamplePlayer(), null, true);
		// visualizeSMT();
	}
}
