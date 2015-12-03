package game;
import java.awt.*;

/**
 * 
 * @author Ethan Davies
 *
 */

public class Tile {
	
	public static final int WIDTH = 25;
	public static final int HEIGHT = 25;

	protected boolean isMine = false;
	protected boolean isHidden = false;

	public Tile(boolean isMine, boolean isHidden){
		this.isMine = isMine;
		this.isHidden = isHidden;
	}
	
	protected boolean isMine() {
		return isMine;
	}
	
	protected boolean isHidden() {
		return isHidden;
	}
	
	protected void setTileType(boolean isMine, boolean isHidden) {
		this.isMine = isMine;
		this.isHidden = isHidden;
	}
	
	public void render(Graphics g, int x, int y) {
		// This will render the tile 
	}

}
