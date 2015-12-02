package game;

import java.awt.*;
import java.util.*;

/**
 * 
 * @author Ethan Davies
 *
 */

public class Board {
	
	private int boardPosition;
	private int rows;
	private int columns;
	private int mineCount;
	private Boolean gameWon;
	private Boolean gameLost;
	private ArrayList<String> tiles = new ArrayList<String>();
	
	public static void main(String[] args) {
	
	}
	
	public Boolean isHidden() {
		// Returns true or false is tile is hidden
		return false;
	}
	
	public Boolean isDefused() {
		// Returns true or false is tile is defused
		return false;
	}
	
	public Boolean Mine() {
		// Returns true or false if tile is a mine
		return false;
	}
	
	public Boolean isRevealed() {
		// Returns true or false if the tile is revealed
		return false;
	}

	public void placeMines() {
		// This places the mines in random areas on the board(In the array list)
	}
	
	public void revealTile(int boardPosition) {
		// This method is responsible for the revealing of a tile on the board
	}
	
	private void paintBoard(Graphics g) {
		// This will be responsible for creating the graphics of the board
	}
	
	private void flagTile(int boardPosition) {
		// This method will be responsible for flagging a tile on the board
	}
	
	public Boolean isGameLost() {
		// Checks the conditions to see if the game has been lost
		return false;
	}
	
	public Boolean isGameWon() {
		// Checks the conditions to see if the game has been won
		return false;
	}
	
	private void setBoardDimensions(int rows, int columns) {
		// Sets the dimensions HxW of the board that is to be created
	}
	
	private void resetBoard() {
		// This will reset the board to an empty board when executed
	}
	
	public void startNewGame() {
		// This will start a new game of Kablewie
	}
	
	public void populateBoard() {
		// This will populate the board based on the dimensions specified with mines and hidden tiles
	} 
}
	