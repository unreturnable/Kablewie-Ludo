package game;

import java.awt.Color;
import java.awt.Graphics;

public class Hidden extends Tile {

	public Hidden(boolean isMine, boolean isHidden) {
		// Always set hidden to false as its the hidden tile.
		super(isMine, true);
	}
	
	public void render(Graphics g, int x, int y) {
		
		g.setColor(Color.BLUE);
		g.fillRect(x * WIDTH, y * HEIGHT, WIDTH, HEIGHT);
	}

}
