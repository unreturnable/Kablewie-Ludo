package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.ImageObserver;

import javax.swing.ImageIcon;

/**
 * 
 * @author Josh Townsend
 *
 */
public class Defused extends Tile implements ImageObserver {

	ImageIcon m_defused;
	/**
	 * Defused Constructor
	 * @param isMine - is the current Tile is mine or not
	 * @param isHidden - is the current Tile is hidden or not
	 * @param isDefused - is the current Tile is Defused or not
	*/
	public Defused(boolean isMine, boolean isHidden,boolean isDefused) {
		super(isMine, isHidden,isDefused);
		m_defused=new ImageIcon("defused.png");
	}
	/**
	 * draws a Tile when user does a right click to Defused a Mine
	 */
	public void render(Graphics g, int x, int y) {
		
		g.drawImage(m_defused.getImage(), 
				x * super.WIDTH, y * super.HEIGHT, this);
	}

	public boolean imageUpdate(Image img, int infoflags, int x, 
			int y, int width, int height) {
		return false;
	}

}
