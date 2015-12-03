package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.color.*;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * 
 * @author Peter Jenkins
 *
 */

public class GameController {

	private ArrayList<Player> players;
	private Board board;
	private long gameStartTime = 0;
	private boolean gamePlaying = true;
	private JFrame frame;

	public GameController(Board board, ArrayList<Player> players, JFrame frame) {
		this.board = board;
		this.players = players;

		frame = this.frame;

		JPanel panel = new JPanel() {
			@Override
			public void paintComponent(Graphics g) {
				super.paintComponent(g);
				g.setColor(Color.BLUE);
				g.fillRect(0, 0, 100, 100);
			}
		};
		frame.add(panel);

		frame.validate();
		frame.repaint();
		
		startGame();
	}

	private void startGame() {
		gameStartTime = System.currentTimeMillis();

		// Loop until the game is over
		while (gamePlaying) {

			for (Player player : players) {
				player.takeTurn();
			}

		}
	}

}
