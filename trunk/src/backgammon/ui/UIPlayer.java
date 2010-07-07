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
	private BackgammonBoard board;
	private Dice dice;

	public UIPlayer(JFrame panel) {
		this.mainFrame = panel;
		Utils.creatCoordinates();
	}

	@Override
	public void gameOver(BackgammonBoard board, boolean wins,
			GameOverStatus status) {

	}

	@Override
	public PlayerMove getMove(BackgammonBoard board, Dice dice) throws Exception {
		this.board = board;
		this.dice = dice;
		visualizeLogic();
		return null;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

	public void visualizeLogic() {
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
		board1.setSize(900, 500);
		backPanel.add(board1, new Integer(100));

		// JLabel whiteChecker = new JLabel();
		ImageIcon whiteCheckIcon = new ImageIcon(getClass().getResource(
				"/backgammon/ui/white.png"));
		img = whiteCheckIcon.getImage();
		newimg = img.getScaledInstance(40, 40, java.awt.Image.SCALE_SMOOTH);
		ImageIcon whiteIcon = new ImageIcon(newimg);

		ImageIcon blackCheckerIcon = new ImageIcon(getClass().getResource(
				"/backgammon/ui/black.png"));
		img = blackCheckerIcon.getImage();
		newimg = img.getScaledInstance(40, 40, java.awt.Image.SCALE_SMOOTH);
		ImageIcon blackIcon = new ImageIcon(newimg);

		for (int i = 1; i <= 24; i++) {
			Point p = board.getPoint(i);
			int t = p.getCount();
			if (t > 0 && p.getColor() == PlayerColor.WHITE) {
				for (int j = 1; j <= t; j++) {
					JLabel whiteChecker = new JLabel();
					whiteChecker.setIcon(whiteIcon);
					whiteChecker.setSize(40, 40);
					PointImpl point2D = Utils.coord[i][j];
					whiteChecker.setLocation((int) point2D.getX(), (int) point2D.getY());
					backPanel.add(whiteChecker, new Integer(301));
				}
			}
			if (t > 0 && p.getColor() == PlayerColor.BLACK) {
				for (int j = 1; j <= t; j++) {
					JLabel blackChecker = new JLabel();
					blackChecker.setIcon(blackIcon);
					blackChecker.setSize(40, 40);
					PointImpl point2D = Utils.coord[i][j];
					blackChecker.setLocation((int) point2D.getX(), (int) point2D.getY());
					backPanel.add(blackChecker, new Integer(301));
				}
			}

			ImageIcon dice1Icon = new ImageIcon(getClass().getResource(
					"/backgammon/ui/res/" + dice.getDie1() + ".png"));
			JLabel dice1Label = new JLabel();
			dice1Label.setIcon(dice1Icon);
			dice1Label.setSize(100, 100);
			dice1Label.setLocation(355, 180);
			backPanel.add(dice1Label, new Integer(301));

			ImageIcon dice2Icon = new ImageIcon(getClass().getResource(
					"/backgammon/ui/res/" + dice.getDie1() + ".png"));
			JLabel dice2Label = new JLabel();
			dice2Label.setIcon(dice2Icon);
			dice2Label.setSize(100, 100);
			dice2Label.setLocation(410, 180);
			backPanel.add(dice2Label, new Integer(301));

			mainFrame.add(backPanel);
		}
	}

	public void start() {
		GameFacade gameFacade = new GameFacade();
		gameFacade.startGame(this, new SamplePlayer(), null, true);
		// visualizeSMT();
	}
}
