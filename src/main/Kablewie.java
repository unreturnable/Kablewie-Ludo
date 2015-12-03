/**
 * 
 * @author Thomas Phelps
 *
 */

package main;

import java.awt.BorderLayout;

import javax.swing.JFrame;

import game.Board;
import game.GameController;


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
		
		Board board = new Board(5,5,5);
		
		GameController gameController = new GameController(board, null);
		
	}

}
