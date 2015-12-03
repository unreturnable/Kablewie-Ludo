/**
 * 
 * @author Thomas Phelps
 *
 */

package main;

import javax.swing.JFrame;

import game.Board;
import game.GameController;
import game.Player;

public class Kablewie {
	
	private JFrame frame;
	

	public static void main(String[] args) {
		// Create a Kablewie instance to escape static scope.
		new Kablewie();
	}

	public Kablewie() {
		
		// Create the frame.
		frame = new JFrame("Kablewie");
		frame.setBounds(100, 100, 640, 480);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
		//Show it.
		frame.setVisible(true);
		
		MainMenu menu = new MainMenu(frame, this);
	}
	
	public void startGame(Board board, Player player) {
		frame.getContentPane().removeAll();
		new GameController(board, player, frame);
	}

}
