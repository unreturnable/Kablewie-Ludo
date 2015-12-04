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
		this.mineCount = numMines;
		this.rows = bRows;
		this.columns = bColumns;

		setBoardDimensions();
		placeMines(numMines);
	}
	
	private void setBoardDimensions() {
		// Sets the dimensions HxW of the board that is to be created
		for (int i=0; i<rows; i++) {
			board.add(new ArrayList<Tile>());
			
			for (int q=0; q<columns; q++) {
				board.get(i).add(new Hidden(false, true));
			}
		}
	}

	private void placeMines(int mines) {
		// This places the mines in random areas on the board(In the array)
		 Random rnd = new Random();
		 
		 while (mineCount > 0) {
			 int row = rnd.nextInt(board.size());
			 int column = rnd.nextInt(board.get(row).size());
			 
			 if (board.get(row).get(column).isMine) {
				 
			 } else {
				 board.get(row).get(column).setTileType(true, true);
				 mineCount--;
			 }
		 }
		 
	} 

	public void revealTile(int x, int y) {
		// This method is responsible for the revealing of a tile on the board
		
		int xPos = (int) Math.floor(x / Tile.WIDTH);
		int yPos = (int) Math.floor(y / Tile.HEIGHT);

		if (board.get(xPos).get(yPos).isHidden) {
			board.get(xPos).get(yPos).setTileType(false, false);
		} else if (board.get(xPos).get(yPos).isMine) {
			this.gameWon = false;
			this.gameLost = true;
			board.get(xPos).get(yPos).setTileType(true, false);
		}
		
	}

	public void render(Graphics g) {
		// This will be responsible for creating the graphics of the board
		for (int y=0; y<board.size(); y++) {
			for (int x=0; x<board.get(y).size(); x++) {
				board.get(y).get(x).render(g, x, y);
			}
		}
	}
}
