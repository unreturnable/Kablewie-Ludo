package game;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * 
 * @author Peter Jenkins
 *
 */

public class GameController {

	private Player player;
	private Board board;
	private long gameStartTime = 0;
	private boolean gamePlaying = true;
	private JFrame frame;

	public GameController(Board board, Player player, JFrame frame) {
		this.board = board;
		this.player = player;

		this.frame = frame;

		JPanel panel = new JPanel() {
			@Override
			public void paintComponent(Graphics g) {
				super.paintComponent(g);
				board.render(g);
			}
		};
		panel.setBounds(0, 0, 640, 480);
		frame.getContentPane().add(panel);

		frame.validate();
		frame.repaint();

		startGame();
	}

	private void startGame() {
		gameStartTime = System.currentTimeMillis();

		// Loop until the game is over
		while (gamePlaying) {

			player.takeTurn();
			gamePlaying = false;
		}
	}

}
