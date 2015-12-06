package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.ImageObserver;

import javax.swing.ImageIcon;

public class Defused extends Tile implements ImageObserver {

	ImageIcon defused;
	public Defused(boolean isMine, boolean isHidden,boolean isDefused) {
		super(isMine, isHidden,isDefused);
		defused=new ImageIcon("defused.png");
	}
	
	public void render(Graphics g, int x, int y) {
		
		g.setColor(Color.ORANGE);
		g.drawImage(defused.getImage(), x * super.WIDTH, y * super.HEIGHT, this);
	}

	public boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height) {
		// TODO Auto-generated method stub
		return false;
	}

}
