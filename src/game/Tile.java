package game;
import java.awt.*;

/**
 * 
 * @author Ethan Davies
 *
 */

public class Tile {
	
	public static final int WIDTH = 30;
	public static final int HEIGHT = 30;

	protected boolean m_isMine = false;
	protected boolean m_isHidden = false;
	protected boolean m_isDefused = false;

	public Tile(boolean isMine, boolean isHidden, boolean isDefused){
		this.m_isMine = isMine;
		this.m_isHidden = isHidden;
		this.m_isDefused=isDefused;
	}
	
	protected boolean isMine() {
		return m_isMine;
	}
	
	protected boolean isHidden() {
		return m_isHidden;
	}
	protected boolean isDefused() {
		return m_isDefused;
	}
	
	protected void setTileType(boolean isMine, boolean isHidden) {
		this.m_isMine = isMine;
		this.m_isHidden = isHidden;
	}
	
	public void render(Graphics g, int x, int y) {
		// This will render the tile 
	}

}
