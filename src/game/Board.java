package game;

import java.awt.*;
import java.util.*;
import java.lang.Math;

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
	private ArrayList<ArrayList<Tile>> board = new ArrayList<ArrayList<Tile>>();

	public Board(int bRows, int bColumns, int numMines) {
		mineCount = numMines;
		rows = bRows;
		columns = bColumns;
		
		setBoardDimensions();
		placeMines(numMines); // method not complete yet
	}
	
	private void setBoardDimensions() {
		// Sets the dimensions HxW of the board that is to be created
		for (int i=0; i<rows; i++) {
			board.add(new ArrayList<Tile>());
			
			for (int q=0; q<columns; q++) {
				board.get(i).add(new Hidden(false));
			}
		}
	}

	private void placeMines(int mines) {
		// This places the mines in random areas on the board(In the array)
		 Random rnd = new Random();
		 while (mineCount < mines) {
			 int row = rnd.nextInt(board.size());
			 int column = rnd.nextInt(board.get(row).size()); 
			 board.get(row).set(column, new Mine(false));
			 mineCount++;
		 }
	} 

	public void revealTile(int x, int y) {
		// This method is responsible for the revealing of a tile on the board
		
		double xPos = Math.floor(x/50);
		double yPos = Math.floor(y/50);

		
//		if (board[row][column] == /*code that calls isMine method */) {
//				gameWon = false;
//				gameLost = true;
//		} else if (board[row][column] == /* code that calls isHidden method */) {
//			board[row][column] = // code that calls adjacent mines method
//		}
	}


	public void render(Graphics g) {
		// This will be responsible for creating the graphics of the board
		for (ArrayList<Tile> row : board) {
			for (Tile tile : row) {
				tile.render();
			}
		}
	}
}
