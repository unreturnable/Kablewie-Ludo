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
	private Revealed reveal;	// made it so that you can call the reveal methods
	public Board(int bRows, int bColumns, int numMines) {
		this.mineCount = numMines;
		this.rows = bRows;
		this.columns = bColumns;
		reveal=new Revealed(false,false,false);
		setBoardDimensions();
		placeMines(numMines);
	}
	
	private void setBoardDimensions() {
		// Sets the dimensions HxW of the board that is to be created
		for (int i=0; i<rows; i++) {
			board.add(new ArrayList<Tile>());
			
			for (int q=0; q<columns; q++) {
				board.get(i).add(new Hidden(false, true,false));
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
		if(!(board.get(yPos).get(xPos).isDefused)){
		if (board.get(yPos).get(xPos).isHidden && !(board.get(yPos).get(xPos).isMine)) {
			/*board.get(xPos).get(yPos).setTileType(false, false);
			board.get(xPos).remove(yPos);
			board.get(xPos).add(yPos, new Revealed(false, false));
			board.get(xPos).get(yPos).revealPosition(board,xPos, yPos);
			*/
			reveal.revealPosition(board,yPos, xPos);
		} else if (board.get(yPos).get(xPos).isMine) {
			this.gameWon = false;
			this.gameLost = true;
			board.get(xPos).remove(yPos); //create a mine tile
			board.get(xPos).add(yPos,new Mine(true,true,false,""));
		}
		}
		
	}
	public void defusedTile(int x, int y) {
		int xPos = (int) Math.floor(x / Tile.WIDTH);
		int yPos = (int) Math.floor(y / Tile.HEIGHT);
		boolean isMine=board.get(yPos).get(xPos).isMine;
		boolean isDefused=board.get(yPos).get(xPos).isDefused;
		boolean isHidden=board.get(yPos).get(xPos).isHidden;
		if(isHidden){
		if(!(isDefused)) {
			board.get(yPos).remove(xPos);
			board.get(yPos).add(xPos, new Defused(isMine, true,true));
		}
		else {
			board.get(yPos).remove(xPos);
			board.get(yPos).add(xPos, new Hidden(isMine, true,false));
		}
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
