package game;

import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JFrame;

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
		
		startGame();
	}
	
	private void startGame() {
		gameStartTime = System.currentTimeMillis();
		
		// Loop until the game is over
		while (gamePlaying) {
			
			for (Player player: players) {
				player.takeTurn();
				
				// Render
				Graphics g = frame.getGraphics();
				board.render(g);
			}
			
		}
	}
	
}
