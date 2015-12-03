package game;

import java.awt.Color;
import java.awt.Graphics;

public class Mine extends Tile {

	public Mine(boolean mineType) {
		super(mineType);
	}
	
	public void render(Graphics g, int x, int y) {
		
		g.setColor(Color.RED);
		g.fillRect(x * WIDTH, y * HEIGHT, WIDTH, HEIGHT);
		
	}

}
