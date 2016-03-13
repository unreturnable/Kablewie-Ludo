/**
 * @file Secret.java
 * @author Thomas Williams
 * @date 7 December 2015
 *
 * A hidden Tile
 */ 

package game;

import java.awt.Graphics;
import javax.swing.ImageIcon;

public class Secret extends Tile {

	private ImageIcon m_hidden;
	
	/**
	 * Hidden Constructor
	 * @param isMine a boolean is mine or not
	 * @param isHidden a boolean is hidden or not
	 * @param isDefused a boolean is defused or not
	*/
	public Secret(boolean isMine, boolean isHidden,boolean isDefused) {
		// Always set hidden to false as its the hidden tile.
		super(isMine, true,false);
		m_hidden=new ImageIcon("images/hidden.png");
	}
	
	/**
	 *  render the tile
	 *  
	 * @param g a Graphics object used to render
	 * @param x the X coordinate to render at
	 * @param y the Y coordinate to render at
	 */
	public void render(Graphics g, int x, int y) {
		g.drawImage(m_hidden.getImage(), 
					x * Tile.WIDTH,
					y * Tile.HEIGHT,
					null);
	}
	
	/**
	 * Unit test for Secret. Testing functionality of Tile here too
	 * as tile is abstract and cannot be directly tested.
	 * 
	 */
	public static void main(String[] args){
		Tile t = new Secret(true, false, false);
		
		System.out.println("\nisMine() test | Expected output: true " 
				+ "| Actual output: " + t.isMine());
		
		System.out.println("\nisHidden() test | Expected output: true " 
				+ "| Actual output: " + t.isHidden());
		
		System.out.println("\nisDefused() test | Expected output: false " 
				+ "| Actual output: " + t.isDefused());
		
		System.out.println("\nsetTileType() test | Set mine to false and"
				+ " hidden to false.");
		t.setTileType(false, false);
		
		System.out.println("isMine test after change | Expected output: false" 
				+ " | Actual output: " + t.isMine());
		
		System.out.println("isHidden() test | Expected output: false " 
				+ "| Actual output: " + t.isDefused());
	}

}
