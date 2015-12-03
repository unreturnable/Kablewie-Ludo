package game;
import java.awt.*;

/**
 * 
 * @author Ethan Davies
 *
 */

public class Tile {
	
	public static int WIDTH = 25;
	public static int HEIGHT = 25;

	protected Boolean isClicked;
	protected Boolean mineType;
	protected Boolean hiddenType;
	protected Boolean revealedType;
	protected Boolean defusedType;
	protected Image tileCover;
	
	public static void main(String[] args) {
		
	}

	public Tile(boolean mineType){
		this.mineType=mineType;
	}
	
	public void createTile() {
		
	}
	
	public boolean getMineType() {
		return mineType;
	}
	
	public void changeTileType() {
		// This will change the type of the tile if required
	}
	
	public void render(Graphics g, int x, int y) {
		// This will render the tile 
	}

}
