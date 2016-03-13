/**
 * @file Computer.java
 * @author Anshul Kumar
 * @date 12 March 2016
 * 
 * A class for computer AI.
 */

package game;

public class Position {

	private int m_xPos;
	private int m_yPos;
	private int m_mineCount;
	private boolean m_isMine;
	private boolean m_isSafeToOpen;
	private boolean m_flagIsThere;
	private boolean m_tileIsOpen;

	/**
	 * set the xPos to the number sent
	 * 
	 * @param integer
	 */
	public void setXPos(int xPos) {
		m_xPos = xPos;
	}

	/**
	 * get the value of xPos
	 * 
	 * @return the value of xPos
	 */
	public int getXPos() {
		return m_xPos;
	}

	/**
	 * set the yPos to the number sent
	 * 
	 * @param integer
	 */
	public void setYPos(int yPos) {
		m_yPos = yPos;
	}

	/**
	 * get the value of yPos
	 * 
	 * @return the value of yPos
	 */
	public int getYPos() {
		return m_yPos;
	}

	/**
	 * changes the mine count to the mineCount sent to the method
	 * 
	 * @param integer
	 */
	public void setMineCount(int mineCount) {
		m_mineCount = mineCount;
	}

	/**
	 * returns the value of the mine counts
	 * 
	 * @return the value of the mine count
	 */
	public int getMineCount() {
		return m_mineCount;
	}

	/**
	 * set the isMine to a boolean value which tells us that 
	 * if there is mine at this position or not
	 * 
	 * @param isMine
	 *            boolean
	 */
	public void setIsMine(boolean isMine) {
		m_isMine = isMine;
	}

	/**
	 * 
	 * @return true if there is mine otherwise returns false
	 */
	public boolean isMine() {
		return m_isMine;
	}

	/**
	 * 
	 * @return true is returns if it is safe to open otherwise it is false
	 */
	public boolean isSafeToOpen() {
		return m_isSafeToOpen;
	}

	/**
	 * set the isSafeToOpen to a boolean value which tells us that if there is
	 * mine at this position or not
	 * 
	 * @param isSafeToOpen
	 *            boolean
	 */
	public void setIsSafeToOpen(boolean isSafeToOpen) {
		m_isSafeToOpen = isSafeToOpen;
	}

	/**
	 * @param it
	 *            is true if flag is at this position otherwise it is false
	 */
	public void setFlagIsThere(boolean flagIsThere) {
		m_flagIsThere = flagIsThere;
	}

	/**
	 * 
	 * @return flagIsThere returns true if it there is flag otherwise it 
	 * 			returns false
	 */
	public boolean isFlagIsThere() {
		return m_flagIsThere;
	}

	/**
	 * 
	 * @return if the tile at this position is open then return true otherwise
	 *         set it to false
	 */
	public boolean isTileIsOpen() {
		return m_tileIsOpen;
	}

	/**
	 * if the tile at this position is open then it is true otherwise it is
	 * false
	 * 
	 * @param tileIsOpen
	 *            boolean
	 */
	public void setTileIsOpen(boolean tileIsOpen) {
		m_tileIsOpen = tileIsOpen;
	}

	/**
	 * Constructor of the class
	 * 
	 * @param x
	 *            x position of a board
	 * @param y
	 *            y position of a board
	 */
	public Position(int x, int y) {
		setXPos(x);
		setYPos(y);
		setMineCount(-1);
		setIsMine(false);
		setIsSafeToOpen(false);
	}

	/**
	 * checks if two objects of position are equal or not
	 * 
	 * @param position
	 *            it is a position which is check if it is equal to the called
	 *            object(this class)
	 * @return true if the current object and position have same location else
	 *         it is false
	 */
	public boolean equal(Position position) {
		return (getXPos() == position.getXPos() 
				&& getYPos() == position.getYPos());
	}

}
