package game;

import java.awt.Graphics;

/**
 * 
 * @author Anshul Kumar
 *
 */
public class Revealed extends Tile {
	public Revealed(boolean mineType) {
		super(mineType);
	}

	private int m_nearbyMines;

	public void render(Graphics g, int x, int y) {
		g.drawRect(x*width, y*height, width, height);
	}

	/*
	 * private int calculateNearbyMines(){ return 1; }
	 */
	public int calculateNearbyMines(Tile tile, Tile[] tileArround) {
		int nearbyMine = 0;
		for (int i = 0; i < tileArround.length; ++i) {
			if (tileArround[i].getMineType()) {
				++nearbyMine;
			}
		}
		return nearbyMine;
	}
}
