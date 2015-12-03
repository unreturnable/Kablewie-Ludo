package game;

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

		startGame();
	}

	private void startGame() {
		gameStartTime = System.currentTimeMillis();

		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 640, 480);
		frame.getContentPane().add(panel);
		
		try {
			Human humanPlayer = (Human) player;
			humanPlayer.setMouseListener(panel);
		} catch(ClassCastException e) {
			// Player was not human.
		}

		frame.validate();
		frame.repaint();
		
		// Loop until the game is over
		while (gamePlaying) {

			// board.render(panel.getGraphics());
			//panel.repaint();
			
			player.takeTurn();
			gamePlaying = false;
			
		}
	}

}
