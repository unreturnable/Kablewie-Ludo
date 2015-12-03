/**
 * 
 * @author Thomas Phelps
 *
 */

package main;

import java.util.ArrayList;

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
		frame = new JFrame("Main Menu");
		frame.setBounds(100, 100, 480, 560);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		

		frame.getContentPane().setLayout(null);
		
		//Show it.
		frame.setVisible(true);
		
		MainMenu menu = new MainMenu(frame);
	}
	
	public void startGame(Board board, ArrayList<Player> players){
		new GameController(board, players, frame);
	}

}
