/**
 * @file Kablewie.java
 * @author Thomas Phelps
 * @date 4 December 2015
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

	/**
	 * Method for creating a JFrame for the mainMenu
	 * 
	 * @see MainMenu.java
	 */

	public Kablewie() {

		// Create the frame.
		frame = new JFrame("Kablewie");

		// Set frame boundaries.
		frame.setBounds(100, 100, 640, 480);

		// Set window to close when exited.
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Show the frame.
		frame.setVisible(true);

		MainMenu menu = new MainMenu(frame, this);
	}

	/**
	 * Simple method for passing board and player information to the game
	 * controller
	 * 
	 * @param board
	 * @param player
	 * @see GameController.java
	 * @return void
	 */

	public void startGame(Board board, Player player, MainMenu menu) {
		frame.getContentPane().removeAll();
		new GameController(board, player, frame, menu);
	}

}
