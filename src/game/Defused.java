package game;

import java.awt.Color;
import java.awt.Graphics;

public class Defused extends Tile {

	public Defused(boolean isMine, boolean isHidden) {
		super(isMine, isHidden);
	}
	
	public void render(Graphics g, int x, int y) {
		
		g.setColor(Color.ORANGE);
		g.fillRect(x * WIDTH, y * HEIGHT, WIDTH, HEIGHT);
	}

}
