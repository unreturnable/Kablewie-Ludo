package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.ImageObserver;

import javax.swing.ImageIcon;

public class Hidden extends Tile implements ImageObserver {

	ImageIcon m_hidden;
	public Hidden(boolean isMine, boolean isHidden,boolean isDefused) {
		// Always set hidden to false as its the hidden tile.
		super(isMine, true,false);
		m_hidden=new ImageIcon("hidden.png");
	}
	
	public void render(Graphics g, int x, int y) {
	
		g.setColor(Color.BLUE);
		g.drawImage(m_hidden.getImage(), x * super.WIDTH, y * super.HEIGHT, this);
	}

	public boolean imageUpdate(Image arg0, int arg1, int arg2, int arg3, int arg4, int arg5) {
		// TODO Auto-generated method stub
		return false;
	}

}
