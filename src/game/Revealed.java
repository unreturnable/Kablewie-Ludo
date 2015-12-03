package game;

import java.awt.Color;
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
		
		g.setColor(Color.GREEN);
		g.drawRect(x * WIDTH, y * HEIGHT, WIDTH, HEIGHT);
		
		if(m_nearbyMines>0) {
			g.drawString(""+m_nearbyMines, x * WIDTH, y * HEIGHT);
		}
	}
	
	/**
	 * calculates the number of mines arround a tile
	 * @param tile have the tile in center
	 * @param tileArround have the tile arround it
	 */
	public void calculateNearbyMines(Tile tile, Tile[] tileArround) {
		int nearbyMine = 0;
		for (int i = 0; i < tileArround.length; ++i) {
			if (tileArround[i].getMineType()) {
				++nearbyMine;
			}
		}
		m_nearbyMines=nearbyMine;
	}
}
