package game;

import java.awt.Color;
import java.awt.Graphics;

public class Mine extends Tile {

	public Mine(boolean isMine, boolean isHidden) {
		// Always set hidden to false as Mine is a visible tile.
		// Always set mine to true as Mine is a mine.
		super(true, false);
	}
	
	public void render(Graphics g, int x, int y) {
		
		g.setColor(Color.RED);
		g.fillRect(x * WIDTH, y * HEIGHT, WIDTH, HEIGHT);
		
	}

}
