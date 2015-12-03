package game;
import java.awt.*;
import java.util.*;

/**
 * 
 * @author Ethan Davies
 *
 */

public class Tile {
	
	protected static int width = 50;
	protected static int height = 50;
	
	private Boolean isClicked;
	private Boolean mineType;
	private Boolean hiddenType;
	private Boolean revealedType;
	private Boolean defusedType;
	private Image tileCover;

	public Tile(boolean mineType){
		this.mineType=mineType;
	}
	
	public void createTile() {
		
	}
	
	public void changeTileType() {
		// This will change the type of the tile if required
	}
	
	public void render() {
		// This will render the tile 
	}

}
