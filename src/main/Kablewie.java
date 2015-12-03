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
	
	private String username;
	private int boardHeight;
	private int boardWidth;
	private int totalMines;
	private Object frame;
	

	public static void main(String[] args) {
		// Create a Kablewie instance to escape static scope.
		new Kablewie();
	}

	public Kablewie(){
		
		//1. Create the frame.
		JFrame frame = new JFrame("Main Menu");
		frame.setBounds(100, 100, 480, 560);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		

		frame.getContentPane().setLayout(null);
		
		//Show it.
		frame.setVisible(true);
		
		//MainMenu menu = new MainMenu(frame);
		
		
		
	}
	
	public void startGame(Board board, ArrayList<Player> players){
		GameController gameController = new GameController(board, null);
	}

}
