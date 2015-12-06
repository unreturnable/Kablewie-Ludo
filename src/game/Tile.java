package game;
import java.awt.*;

/**
 * It is an abstract class which is extended by Hidden, 
 * Mine, Revealed and Defused
 * it has the basic information which each type of tile has
 * @author Ethan Davies
 *
 */

public abstract class Tile {
	
	public static final int WIDTH = 30;
	public static final int HEIGHT = 30;

	protected boolean m_isMine = false;
	protected boolean m_isHidden = false;
	protected boolean m_isDefused = false;
	/**
	 * Tile Constructor
	 * @param isMine 
	 * 				if its a mine then it is true
	 * @param isHidden
	 * 				if it is hidden then it is true
	 * @param isDefused
	 * 				if its defused then it is true
	 */
	public Tile(boolean isMine, boolean isHidden, boolean isDefused){
		this.m_isMine = isMine;
		this.m_isHidden = isHidden;
		this.m_isDefused=isDefused;
	}
	/**
	 * 
	 * @return isMine which is true if the tile is a mine 
	 */
	protected boolean isMine() {
		return m_isMine;
	}
	/**
	 * 
	 * @return isMine which is true if the tile is a mine 
	 */
	protected boolean isHidden() {
		return m_isHidden;
	}
	/**
	 * 
	 * @return isMine which is true if the tile is a mine 
	 */
	protected boolean isDefused() {
		return m_isDefused;
	}
	/**
	 * 
	 * @param isMine
	 * @param isHidden
	 */
	protected void setTileType(boolean isMine, boolean isHidden) {
		this.m_isMine = isMine;
		this.m_isHidden = isHidden;
	}
	/**
	 *  a draw Function to draw each 
	 *  type of tile in the system
	 * @param g 
	 * 			takes the graphics
	 * @param x
	 * 			takes the coordinates of current tile x(row)
	 * @param y
	 *			takes the coordinates of current tile y(column)
	 */
	public abstract void render(Graphics g, int x, int y);

}
