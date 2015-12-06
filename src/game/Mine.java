package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.ImageObserver;

import javax.swing.ImageIcon;

public class Mine extends Tile implements ImageObserver {

	ImageIcon m_mineImage;
	/**
	 * Mine Constructor
	 * @param isMine - is the current Tile is mine or not
	 * @param isHidden - is the current Tile is hidden or not
	 * @param isDefused - is the current Tile is Defused or not
	*/
	public Mine(boolean isMine, boolean isHidden
			,boolean isDefused,String path) {
		// Always set hidden to false as Mine is a visible tile.
		// Always set mine to true as Mine is a mine.
		super(true, true,false);
		m_mineImage=new ImageIcon(path);
	}
	/**
	 * draws Mine on the tile after the player clicks on a mine
	 */
	public void render(Graphics g, int x, int y) {
		g.drawImage(m_mineImage.getImage(), 
				x * super.WIDTH, y * super.HEIGHT, this);
	}

	public boolean imageUpdate(Image arg0, int arg1, int arg2, 
			int arg3, int arg4, int arg5) {
		return false;
	}

}
