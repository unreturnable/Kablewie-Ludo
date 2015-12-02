package game;

import java.awt.*;
import java.util.*;
import java.awt.event.ActionListener;

import javax.swing.Icon;
import javax.swing.JButton;

/**
 * 
 * @author Ethan Davies
 *
 */

public class Tile{
	
	private Boolean isClicked;
	private Boolean mineType;
	private Boolean hiddenType;
	private Boolean revealedType;
	private Boolean defusedType;
	private Image tileCover;
	private JButton tile = new JButton();
	
	public static void main(String[] args) {
		
	}
	
	public void setTileImage(Image tileCover) {
		tile.setIcon((Icon) tileCover);
		// This will set the image of each tile to be displayed on the board
	}
	
	public void createTile() {
		tile = new JButton();
        tile.setPreferredSize(new Dimension(20,20));
        tile.setMargin(new Insets(0,0,0,0));
        setTileImage(tileCover);
			}
	
	public void changeTileType() {
		// This will change the type of the tile if required
	}
	
	public void render() {
		// This will render the tile 
	}
}
