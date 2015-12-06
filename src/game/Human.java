package game;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

/**
 * 
 * @author Zongbo Xu
 *
 */

public class Human extends Player  {

	private String username;
	private boolean playersTurn = true;

	public Human(String name) {
		super(name);
	}

	
	public void takeTurn() {
		playersTurn = true;
	}

	

}
