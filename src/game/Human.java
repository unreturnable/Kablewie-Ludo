package game;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

/**
 * human is a player which store the
 * @author Zongbo Xu
 *
 */

public class Human extends Player  {

	private String m_username;
	private boolean m_playersTurn = true;

	public Human(String name) {
		super(name);
	}

	
	public void takeTurn() {
		m_playersTurn = true;
	}

	

}
