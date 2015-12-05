package game;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

/**
 * 
 * @author Anshul Kumar
 *
 */
public class Revealed extends Tile {

	public Revealed(boolean isMine, boolean isHidden) {
		// Always set hidden to false as its the revealed tile.
		super(isMine, false);
	}

	public int getm_NearByMines() {
		return m_nearbyMines;
	}

	private int m_nearbyMines;

	public void render(Graphics g, int x, int y) {

		g.setColor(Color.GREEN);
		g.drawRect(x * WIDTH, y * HEIGHT, WIDTH, HEIGHT);

		if (m_nearbyMines > 0) {
			g.drawString(Integer.toString(m_nearbyMines), x * WIDTH, y * HEIGHT);
		}
	}

	/**
	 * calculates the number of mines arround a tile
	 * 
	 * @param tile
	 *            have the tile in center
	 * @param tileArround
	 *            have the tile arround it
	 */
	private int calculateNearbyMines(Tile tile, ArrayList<Tile> tileArround) {
		if (tile.isMine) {
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
	 * it the method which gives all the tiles arround the tile on i,j position
	 * 
	 * @param board
	 *            contains the board so the tile arround the i,j position can be
	 *            worked out
	 * @param i
	 *            the current row which has been clicked
	 * @param j
	 *            the current coloumb which has been clicked
	 * @return
	 */
	private ArrayList<Tile> getTileArround(ArrayList<ArrayList<Tile>> board, int i, int j) {

		int prevrow = i - 1;
		int prevrcol = j - 1;
		int nextrow = i + 1;
		int nextcol = j + 1;
		ArrayList<Tile> t2 = new ArrayList<Tile>();
		for (int k = prevrow; k <= nextrow; ++k) {
			for (int m = prevrcol; m <= nextcol; ++m) {
				if (!(k < 0 || m < 0 || k >= board.size() || m >= board.get(0).size())) {
					t2.add(board.get(k).get(m));
				}
			}
		}
		return t2;
	}

	/**
	 * a recursion method the set the tile as a revealed tile if the clicked
	 * tile is a zero then it will reveal all tile arround it
	 * 
	 * @param board
	 *            contains all the tile
	 * @param i
	 *            have the row of the current tile
	 * @param j
	 *            have the coloumb of the current tile
	 */
	public void revealPosition(ArrayList<ArrayList<Tile>> board, int i, int j) {
		if (i < 0 || j < 0 || i >= board.size() || j >= board.get(0).size() || !(board.get(i).get(j).isHidden)) {
			return;
		}
		ArrayList<Tile> tileArround = getTileArround(board, i, j);
		if (calculateNearbyMines(board.get(i).get(j), tileArround) == 0) {
			board.get(i).remove(j);
			board.get(i).add(j, new Revealed(false, false));
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
			board.get(i).add(j, new Revealed(false, false));
			Revealed r = (Revealed) board.get(i).get(j);
			r.calculateNearbyMines(r, getTileArround(board, i, j));
		}
	}

}
