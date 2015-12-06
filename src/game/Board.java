package game;

import java.awt.*;
import java.util.*;

import javax.swing.JLabel;
import javax.swing.JPanel;

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
	private Revealed reveal; // made it so that you can call the reveal methods

	public Board(int bRows, int bColumns, int numMines) {
		this.mineCount = numMines;
		this.rows = bRows;
		this.columns = bColumns;
		reveal = new Revealed(false, false, false);
		setBoardDimensions();
		placeMines();
		gameLost = false;
		gameWon = false;
	}

	private void setBoardDimensions() {
		// Sets the dimensions HxW of the board that is to be created
		for (int i = 0; i < rows; i++) {
			board.add(new ArrayList<Tile>());

			for (int q = 0; q < columns; q++) {
				board.get(i).add(new Hidden(false, true, false));
			}
		}
	}

	public boolean getGameLost() {
		return gameLost;
	}
	public ArrayList<ArrayList<Tile>> getBoard() {
		return board;
	}
	private void placeMines() {
		// This places the mines in random areas on the board(In the array)
		Random rnd = new Random();
		int mineCount = this.mineCount;
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
		if (!(board.get(yPos).get(xPos).isDefused)) {
			if (board.get(yPos).get(xPos).isHidden && !(board.get(yPos).get(xPos).isMine)) {
				/*
				 * board.get(xPos).get(yPos).setTileType(false, false);
				 * board.get(xPos).remove(yPos); board.get(xPos).add(yPos, new
				 * Revealed(false, false));
				 * board.get(xPos).get(yPos).revealPosition(board,xPos, yPos);
				 */
				reveal.revealPosition(board, yPos, xPos);
				haveWon();
			} else if (board.get(yPos).get(xPos).isMine) {
				this.gameWon = false;
				this.gameLost = true;
				board.get(yPos).remove(xPos); // create a mine tile
				board.get(yPos).add(xPos, new Mine(true, true, false, "mineX.jpg"));
				for (int i = 0; i < board.size(); ++i) {
					for (int j = 0; j < board.get(0).size(); ++j) {
						if (board.get(i).get(j).isMine && !(i == yPos && j == xPos)) {
							board.get(i).remove(j); // create a mine tile
							board.get(i).add(j, new Mine(true, true, false, "mine.png"));
						}
					}
				}
			}
		}

	}
	
	public void defusedTile(int x, int y) {
		int xPos = (int) Math.floor(x / Tile.WIDTH);
		int yPos = (int) Math.floor(y / Tile.HEIGHT);
		boolean isMine = board.get(yPos).get(xPos).isMine;
		boolean isDefused = board.get(yPos).get(xPos).isDefused;
		boolean isHidden = board.get(yPos).get(xPos).isHidden;
		if (isHidden) {
			if (!(isDefused)) {
				board.get(yPos).remove(xPos);
				board.get(yPos).add(xPos, new Defused(isMine, true, true));
			} else {
				board.get(yPos).remove(xPos);
				board.get(yPos).add(xPos, new Hidden(isMine, true, false));
			}
		}
	}

	public void render(Graphics g) {
		// This will be responsible for creating the graphics of the board
		for (int y = 0; y < board.size(); y++) {
			for (int x = 0; x < board.get(y).size(); x++) {
				board.get(y).get(x).render(g, x, y);
			}
		}
	}

	public void renderInfo(Graphics g, Player player, String timePassed) {
		Font timeNewRoman = new Font("Time new roman", Font.BOLD, 12);
		int x = 1;
		int y = 10;
		g.setFont(timeNewRoman);
		g.setColor(Color.RED);
		g.drawString("Name : " + player.getUsername(), x, y);
		x = x + 200;
		if (timePassed == null) {
			g.drawString("Time : 00:00:00" , x, y);
		} else {
			g.drawString("Time :" + timePassed, x, y);
		}
		x = 1;
		y = 27;
		g.setColor(Color.BLUE);
		g.drawString("Defused Mine : " + getDefusedTile(), x, y);
		x = x + 200;
		g.drawString("Mines Present : " + mineCount, x, y);
		y = 48;
		x = 1;
		g.drawString("Hidden Square : " + this.getHiddenTile(), x, y);
		x = x + 200;
		g.drawString("Revealed Square : " + this.getRevealedTile(), x, y);
	}
	public void haveWon() {
		if(getRevealedTile()+getDefusedTile()==board.size()*board.size()) {
			gameLost = false;
			gameWon = true;
		}
	}
	public boolean getGameWon() {
		return gameWon;
	}
	private int getRevealedTile() {
		int revealedTile = 0;
		for (int i = 0; i < board.size(); ++i) {
			for (int j = 0; j < board.get(0).size(); ++j) {
				if (!(board.get(i).get(j).isHidden)) {
					++revealedTile;
				}
			}
		}
		return revealedTile;
	}

	private int getHiddenTile() {
		int hiddenTile = 0;
		for (int i = 0; i < board.size(); ++i) {
			for (int j = 0; j < board.get(0).size(); ++j) {
				if (board.get(i).get(j).isHidden) {
					++hiddenTile;
				}
			}
		}
		return hiddenTile;
	}

	private int getDefusedTile() {
		int defusedTile = 0;
		for (int i = 0; i < board.size(); ++i) {
			for (int j = 0; j < board.get(0).size(); ++j) {
				if (board.get(i).get(j).isDefused && board.get(i).get(j).isMine) {
					++defusedTile;
				}
			}
		}
		return defusedTile;
	}
	public void reset() {
		board.clear();
		setBoardDimensions();
		placeMines();
		gameLost = false;
		gameWon = false;
	}
}
