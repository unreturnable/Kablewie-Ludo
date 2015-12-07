package game;

import java.awt.*;
import java.util.*;

import javax.swing.JLabel;
import javax.swing.JPanel;

import java.lang.Math;

/**
 * Contain the Information of the current Board
 * In generally Board Contain the Information
 * which is displayed on the board
 * @author Ethan Davies
 *
 */

public class Board {

	private int m_boardPosition;
	private int m_rows;
	private int m_columns;
	private int m_mineCount;
	private Boolean m_gameWon;
	private Boolean m_gameLost;
	private ArrayList<ArrayList<Tile>> m_board = new ArrayList<ArrayList<Tile>>();
	private Revealed m_reveal; // made it so that you can call the reveal
								// methods
	/**
	 * Board Constructor it take all the counter 
	 * and the inputs from the MainMenu class as parameters
	 * @param bRows 
	 * 				number of rows
	 * @param bColumns
	 * 				number of columns
	 * @param numMines
	 * 				number of mines
	 */
	public Board(int bRows, int bColumns, int numMines) {
		this.m_mineCount = numMines;
		this.m_rows = bRows;
		this.m_columns = bColumns;
		m_reveal = new Revealed(false, false, false);
		setBoardDimensions();
		placeMines();
		m_gameLost = false;
		m_gameWon = false;
	}

	/**
	 * 
	 * @return the value of m_gameLost which is true if the game is lost
	 */
	public boolean getm_GameLost() {
		return m_gameLost;
	}
	/**
	 * 
	 * @return the board which has all the tile
	 */
	public ArrayList<ArrayList<Tile>> getm_Board() {
		return m_board;
	}
	/**
	 * 
	 * @return the value of m_gameWon which is true if the game is Won
	 */
	public boolean getm_GameWon() {
		return m_gameWon;
	}
	/**
	 * this set the Dimension of the board
	 * which is the size times size enter in MainMenu
	 */
	private void setBoardDimensions() {
		// Sets the dimensions HxW of the board that is to be created
		for (int i = 0; i < m_rows; i++) {
			m_board.add(new ArrayList<Tile>());

			for (int q = 0; q < m_columns; q++) {
				m_board.get(i).add(new Hidden(false, true, false));
			}
		}
	}
	/**
	 * this function places the mine at random 
	 * position and makes sure that two Mine are not place on the same tile
	 */
	private void placeMines() {
		// This places the mines in random areas on the board(In the array)
		Random rnd = new Random();
		int mineCount = this.m_mineCount;
		while (mineCount > 0) {
			int row = rnd.nextInt(m_board.size());
			int column = rnd.nextInt(m_board.get(row).size());

			if (m_board.get(row).get(column).m_isMine) {

			} else {
				m_board.get(row).get(column).setTileType(true, true);
				mineCount--;
			}
		}

	}
	/**
	 * the method is called with the x,y position to reveal a tile
	 * the method calls Revealed class if the Tile is not a mine and 
	 * is not defused 
	 * @param x 
	 * 			x position of the click on the board
	 * @param y
	 * 			y position of the click on the board
	 */
	public void revealTile(int x, int y) {
		// This method is responsible for the revealing of a tile on the board

		int xPos = (int) Math.floor(x / Tile.WIDTH);
		int yPos = (int) Math.floor(y / Tile.HEIGHT);
		if ( inLimit(yPos,xPos) && !(m_board.get(yPos).get(xPos).m_isDefused) ) {
			if (m_board.get(yPos).get(xPos).m_isHidden && !(m_board.get(yPos).get(xPos).m_isMine)) {
				m_reveal.revealPosition(m_board, yPos, xPos);
				haveWon();
			} else if (m_board.get(yPos).get(xPos).m_isMine) {
				this.m_gameWon = false;
				this.m_gameLost = true;
				m_board.get(yPos).remove(xPos); // create a mine tile
				m_board.get(yPos).add(xPos, new Mine(true, true, false, "mineX.jpg"));
				for (int i = 0; i < m_board.size(); ++i) {
					for (int j = 0; j < m_board.get(0).size(); ++j) {
						if (m_board.get(i).get(j).m_isMine && !(i == yPos && j == xPos)) {
							m_board.get(i).remove(j); // create a mine tile
							m_board.get(i).add(j, new Mine(true, true, false, "mine.png"));
						}
					}
				}
			}
		}

	}
	/**
	 * the method is called when the user does a right click
	 * which is for placing a flag on a tile(Defusing a tile)
	 * it make sure that if a flag is already there then it removes it
	 * @param x 
	 * 			x position of the click on the board
	 * @param y
	 * 			y position of the click on the board
	 */
	public void defusedTile(int x, int y) {
		int xPos = (int) Math.floor(x / Tile.WIDTH);
		int yPos = (int) Math.floor(y / Tile.HEIGHT);
		if(inLimit(yPos,xPos)){
		boolean isMine = m_board.get(yPos).get(xPos).m_isMine;
		boolean isDefused = m_board.get(yPos).get(xPos).m_isDefused;
		boolean isHidden = m_board.get(yPos).get(xPos).m_isHidden;
		if (isHidden) {
			if (!(isDefused)) {
				m_board.get(yPos).remove(xPos);
				m_board.get(yPos).add(xPos, new Defused(isMine, true, true));
				haveWon();
			} else {
				m_board.get(yPos).remove(xPos);
				m_board.get(yPos).add(xPos, new Hidden(isMine, true, false));
			}
		}
		}
	}
	/**
	 * it check if x,y are in the range of the board or not
	 * @param x
	 * 			row in Tile
	 * @param y
	 * 			column in Tile
	 * @return true if x, y are in range
	 */
	public boolean inLimit(int x,int y) {
		if(x>=m_board.size() 
				|| y>=m_board.get(0).size() 
				|| x<0 ||y<0) {
			return false;
		} else {
			return true;
		}
	}
	/**
	 * the method calls the render method in Tile 
	 * which draw the current status of the Board
	 * @param g
	 * 			graphics
	 */
	public void render(Graphics g) {
		// This will be responsible for creating the graphics of the board
		for (int y = 0; y < m_board.size(); y++) {
			for (int x = 0; x < m_board.get(y).size(); x++) {
				m_board.get(y).get(x).render(g, x, y);
			}
		}
	}
	/**
	 * it displays the information which need to be display
	 * according to the specification
	 * @param g
	 * 			graphics
	 * @param player
	 * 			Player Object
	 * @param timePassed
	 * 			time which has passed since the start of the game
	 */
	public void renderInfo(Graphics g, Player player, String timePassed) {
		Font timeNewRoman = new Font("Time new roman", Font.BOLD, 12);
		int x = 1;
		int y = 10;
		g.setFont(timeNewRoman);
		g.setColor(Color.RED);
		g.drawString("Name : " + player.getUsername(), x, y);
		x = x + 200;
		if (timePassed == null) {
			g.drawString("Time : 00:00:00", x, y);
		} else {
			g.drawString("Time :" + timePassed, x, y);
		}
		x = 1;
		y = 27;
		g.setColor(Color.BLUE);
		g.drawString("Defused Mine : " + getDefusedTile(), x, y);
		x = x + 200;
		g.drawString("Mines Present : " + m_mineCount, x, y);
		y = 48;
		x = 1;
		g.drawString("Hidden Square : " + getHiddenTile(), x, y);
		x = x + 200;
		g.drawString("Revealed Square : " + getRevealedTile(), x, y);
	}
	/**
	 *  checks if the game have been won or not
	 */
	public void haveWon() {
		if (getRevealedTile() + getDefusedTile() == m_board.size() * m_board.size()) {
			m_gameLost = false;
			m_gameWon = true;
		}
	}

	/**
	 * 
	 * @return the number of Revealed Tile
	 */
	private int getRevealedTile() {
		int revealedTile = 0;
		for (int i = 0; i < m_board.size(); ++i) {
			for (int j = 0; j < m_board.get(0).size(); ++j) {
				if (!(m_board.get(i).get(j).m_isHidden)) {
					++revealedTile;
				}
			}
		}
		return revealedTile;
	}
	/**
	 * 
	 * @return the number of Hidden Tile
	 */
	private int getHiddenTile() {
		int hiddenTile = 0;
		for (int i = 0; i < m_board.size(); ++i) {
			for (int j = 0; j < m_board.get(0).size(); ++j) {
				if (m_board.get(i).get(j).m_isHidden) {
					++hiddenTile;
				}
			}
		}
		return hiddenTile;
	}
	/**
	 * 
	 * @return the number of defused Tile
	 */
	private int getDefusedTile() {
		int defusedTile = 0;
		for (int i = 0; i < m_board.size(); ++i) {
			for (int j = 0; j < m_board.get(0).size(); ++j) {
				if (m_board.get(i).get(j).m_isDefused && m_board.get(i).get(j).m_isMine) {
					++defusedTile;
				}
			}
		}
		return defusedTile;
	}
	/**
	 * reset the game so that it can be played again
	 */
	public void reset() {
		m_board.clear();
		setBoardDimensions();
		placeMines();
		m_gameLost = false;
		m_gameWon = false;
	}
}
