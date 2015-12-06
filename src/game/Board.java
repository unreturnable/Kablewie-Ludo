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

	private int m_boardPosition;
	private int m_rows;
	private int m_columns;
	private int m_mineCount;
	private Boolean m_gameWon;
	private Boolean m_gameLost;
	private ArrayList<ArrayList<Tile>> m_board = new ArrayList<ArrayList<Tile>>();
	private Revealed m_reveal; // made it so that you can call the reveal
								// methods

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


	public boolean getm_GameLost() {
		return m_gameLost;
	}

	public ArrayList<ArrayList<Tile>> getm_Board() {
		return m_board;
	}

	public boolean getm_GameWon() {
		return m_gameWon;
	}
	
	private void setBoardDimensions() {
		// Sets the dimensions HxW of the board that is to be created
		for (int i = 0; i < m_rows; i++) {
			m_board.add(new ArrayList<Tile>());

			for (int q = 0; q < m_columns; q++) {
				m_board.get(i).add(new Hidden(false, true, false));
			}
		}
	}
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
			} else {
				m_board.get(yPos).remove(xPos);
				m_board.get(yPos).add(xPos, new Hidden(isMine, true, false));
			}
		}
		}
	}
	public boolean inLimit(int x,int y) {
		if(x>=m_board.size() 
				|| y>=m_board.get(0).size() 
				|| x<0 ||y<0) {
			return false;
		} else {
			return true;
		}
	}
	public void render(Graphics g) {
		// This will be responsible for creating the graphics of the board
		for (int y = 0; y < m_board.size(); y++) {
			for (int x = 0; x < m_board.get(y).size(); x++) {
				m_board.get(y).get(x).render(g, x, y);
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
		g.drawString("Hidden Square : " + this.getHiddenTile(), x, y);
		x = x + 200;
		g.drawString("Revealed Square : " + this.getRevealedTile(), x, y);
	}

	public void haveWon() {
		if (getRevealedTile() + getDefusedTile() == m_board.size() * m_board.size()) {
			m_gameLost = false;
			m_gameWon = true;
		}
	}


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

	public void reset() {
		m_board.clear();
		setBoardDimensions();
		placeMines();
		m_gameLost = false;
		m_gameWon = false;
	}
}
