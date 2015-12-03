package game;

import java.util.ArrayList;

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

	public GameController(Board board, ArrayList<Player> players) {
		this.board = board;
		this.players = players;
		
		startGame();
	}
	
	private void startGame() {
		gameStartTime = System.currentTimeMillis();
		
		// Loop until the game is over.
		while (gamePlaying) {
			
			for (Player player: players) {
				player.takeTurn();
				
			}
			
		}
	}
	
}
