package game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.ImageObserver;
import java.util.ArrayList;

import javax.swing.ImageIcon;

/**
 * It has the revealed algorithm 
 * which reveal a Tile and Draws it 
 * @author Anshul Kumar
 *
 */
public class Revealed extends Tile implements ImageObserver {
/**
 * Revealed Constructor
 * @param isMine - is the current Tile is mine or not
 * @param isHidden - is the current Tile is hidden or not
 * @param isDefused - is the current Tile is Defused or not
 */
	public Revealed(boolean isMine, boolean isHidden, boolean isDefused) {
		// Always set hidden to false as its the revealed tile.
		super(isMine, isHidden, isDefused);

		m_revealedImage = new ImageIcon("revealed.png");
	}
	/**
	 * 
	 * @return the mines near by the tile calls it
	 */
	public int getm_NearByMines() {
		return m_nearbyMines;
	}

	private int m_nearbyMines;
	private final ImageIcon m_revealedImage;
	/**
	 * draw the number of mines near by the tile which is clicked
	 */
	public void render(Graphics g, int x, int y) {

		g.drawImage(m_revealedImage.getImage(), 
				x * super.WIDTH, y * super.HEIGHT, this);
		if (m_nearbyMines > 0) {
			/*
			 * using Spacing so that the number is drawn in the center
			 */
			int spacingX=10;
			int spacingY=19;
			int fontSize=15;
			g.setFont(new Font("Time new roman", Font.BOLD, fontSize));
			g.drawString(Integer.toString(m_nearbyMines), 
					x * super.WIDTH + spacingX, y * super.HEIGHT + spacingY);
		}
	}

	/**
	 * calculates the number of mines around a tile
	 * 
	 * @param tile
	 *            have the tile in centre
	 * @param tileArround
	 *            have the tile around it
	 */
	private int calculateNearbyMines(Tile tile, ArrayList<Tile> tileArround) {
		if (tile.m_isMine) {
			m_nearbyMines = -1;
		} else {
			int nearbyMine = 0;
			for (int i = 0; i < tileArround.size(); i++) {
				if (tileArround.get(i).isMine()) {
					nearbyMine++;
				}
			}
			m_nearbyMines = nearbyMine;
		}
		return m_nearbyMines;
	}

	/**
	 * it the method which gives all the tiles around the tile on i,j position
	 * 
	 * @param board
	 *            contains the board so the tile around the i,j position can be
	 *            worked out
	 * @param i
	 *            the current row which has been clicked
	 * @param j
	 *            the current column which has been clicked
	 * @return
	 */
	private ArrayList<Tile> getTileArround(ArrayList<ArrayList<Tile>> board, 
			int i, int j) {

		int prevrow = i - 1;
		int prevrcol = j - 1;
		int nextrow = i + 1;
		int nextcol = j + 1;
		//it run a for loop all around the tile i,j
		ArrayList<Tile> t2 = new ArrayList<Tile>();
		for (int k = prevrow; k <= nextrow; ++k) {
			for (int m = prevrcol; m <= nextcol; ++m) {
				if (!(k < 0 || m < 0 || k >= board.size() 
						|| m >= board.get(0).size())) {
					/*
					 * before adding it the condition makes sure that it
					 * is not out of bound of the board
					 */
					t2.add(board.get(k).get(m));
				}
			}
		}
		return t2;
	}

	/**
	 * a recursion method the set the tile as a revealed tile if the clicked
	 * tile is a zero then it will reveal all tile around it
	 * the base case is if the tile is not hidden or if i,j
	 * are out of the bound then stop and 
	 * go back to the previous call 
	 * 
	 * @param board
	 *            contains all the tile
	 * @param i
	 *            have the row of the current tile
	 * @param j
	 *            have the column of the current tile
	 */
	public void revealPosition(ArrayList<ArrayList<Tile>> board, 
			int i, int j) {
		if (i < 0 || j < 0 || 
				i >= board.size() || j >= board.get(0).size() 
				|| !(board.get(i).get(j).m_isHidden)) {
			return;
		}
		ArrayList<Tile> tileArround = getTileArround(board, i, j);
		if (calculateNearbyMines(board.get(i).get(j), tileArround) == 0) {
			board.get(i).remove(j);
			board.get(i).add(j, new Revealed(false, false, false));
			Revealed r = (Revealed) board.get(i).get(j);
			r.calculateNearbyMines(r, getTileArround(board, i, j));
			revealPosition(board, i - 1, j - 1);
			revealPosition(board, i - 1, j);
			revealPosition(board, i - 1, j + 1);
			revealPosition(board, i, j - 1);
			revealPosition(board, i, j + 1);
			revealPosition(board, i + 1, j - 1);
			revealPosition(board, i + 1, j);
			revealPosition(board, i + 1, j + 1);
		} else {
			board.get(i).remove(j);
			board.get(i).add(j, new Revealed(false, false, false));
			Revealed r = (Revealed) board.get(i).get(j);
			r.calculateNearbyMines(r, getTileArround(board, i, j));
		}
	}

	public boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height) {
		return false;
	}

}
