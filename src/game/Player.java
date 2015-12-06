package game;

/**
 * store the name of the current player
 * @author Ceri Westcott
 *
 */

public class Player {

	private String m_username;
	
	public Player(String username) {
		this.m_username = username;
	}
	
	public void takeTurn() {
		// Take turn
	}
	
	public String getUsername() {
		return m_username;
	}
	
}
