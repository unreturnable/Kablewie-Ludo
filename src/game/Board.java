package game;

import java.awt.*;
import java.util.*;
import javax.swing.*;

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
	private int[][] board;
	
	public Board (int bRows, int bColumns, int numMines) {
		mineCount = numMines;
		rows = bRows;
		columns = bColumns;
		
		setBoardDimensions();
		placeMines(); // method not complete yet
	}
	
	public Boolean isHidden() {
		// Returns true or false is tile is hidden
		return false;
	}
	
	public Boolean isDefused() {
		// Returns true or false is tile is defused
		return false;
	}
	
	public Boolean isMine() {
		// Returns true or false if tile is a mine
		return false;
	}
	
	public Boolean isRevealed() {
		// Returns true or false if the tile is revealed
		return false;
	}

	public void placeMines() {
		// This places the mines in random areas on the board(In the array)
		Random rnd = new Random();
		int mineCount = 0;
		while (mineCount < numMines) {
			int row = rnd.nextInt(board.length);
			int column = rnd.nextInt(board[0].length);
			
			if (board[row][column] == // code that checks if tile is hidden) {
				board[row][column] = // code that changes the type of tile to mine
				mineCount++;
			}
		}
	}
	
	public void revealTile(int row, int column) {
		// This method is responsible for the revealing of a tile on the board
		if (board[row][column] == /*code that calls isMine method */) {
				gameWon = false;
				gameLost = true;
		} else if (board[row][column] == /* code that calls isHidden method */) {
			board[row][column] = // code that calls adjacent mines method
		}
	}
	
	private void paintBoard(Graphics g) {
		// This will be responsible for creating the graphics of the board
	}
	
	private void flagTile(int boardPosition) {
		// This method will be responsible for flagging a tile on the board
		if ()
	}
	
	public Boolean isGameLost() {
		// Checks the conditions to see if the game has been lost
		return false;
	}
	
	public Boolean isGameWon() {
		// Checks the conditions to see if the game has been won
		return false;
	}
	
	
	private void setBoardDimensions() {
		// Sets the dimensions HxW of the board that is to be created
		board = new int[rows][columns];
	} 
	
	private void resetBoard() {
		// This will reset the board to an empty board when executed
		for (int i = 0; i<board.length; i++) {
		    for (int j = 0; j<board.length; j++) {
		    	board[i][j] = // code that changes all the tiles on the board to hidden
		    }
		}
	}
	
	private void revealBoard() {
		
	}
	
	public void startNewGame() {
		// This will start a new game of Kablewie
	}
	
	public void populateBoard() {
		// This will populate the board based on the dimensions specified with mines and hidden tiles
	} 
}
	