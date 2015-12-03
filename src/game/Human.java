package game;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

/**
 * 
 * @author Zongbo Xu
 *
 */

public class Human extends Player implements MouseListener {

	private String username;
	private boolean playersTurn = false;

	public Human(String name) {
		super(name);
	}

	public void setMouseListener(JPanel gameArea) {
		gameArea.addMouseListener(this);
		System.out.println("Stuff");
	}
	
	public void takeTurn() {
		playersTurn = true;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		System.out.println("Mouse event");
		
		if (playersTurn) {
			if (e.getButton() == MouseEvent.BUTTON1) {
				playersTurn = false;
				System.out.println("Turn over");
			} else if (e.getButton() == MouseEvent.BUTTON3) {
				
			}
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {	
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
	}

}
