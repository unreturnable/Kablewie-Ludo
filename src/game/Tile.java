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

	public static void main(String[] args) {
		Revealed r=new Revealed(true);
		Tile[] t=new Tile[8];
		t[0]=new Tile(false);t[1]=new Tile(false);t[2]=new Tile(false);t[3]=new Tile(true);
		t[7]=new Tile(true);t[6]=new Tile(true);t[5]=new Tile(true);t[4]=new Tile(false);
		Tile[] t2=new Tile[3];
		t2[0]=new Tile(true);t2[1]=new Tile(false);t2[2]=new Tile(false);
		
		System.out.println(r.calculateNearbyMines(t, t[0]));
		System.out.println(r.calculateNearbyMines(t2, t[0]));
	}
	public Tile(boolean mineType){
		this.mineType=mineType;
	}
	public void setTileImage(Image tileCover) {
		// This will set the image of each tile to be displayed on the board
	}
	
	public void createTile() {
		// This will create each individual tile required
	}
	
	public void changeTileType() {
		// This will change the type of the tile if required
	}
	
	public void render() {
		// This will render the tile 
	}
	public boolean getMineType(){
		return mineType;
	}
}