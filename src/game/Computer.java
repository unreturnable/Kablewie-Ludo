/**
 * @file Computer.java
 * @author Zongbo Xu
 * @date 5 December 2015
 *
 * A class for computer players.
 */ 

package game;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Computer extends Player {
	
	private boolean m_computerTurn;
	public void setComputerTurn(boolean computerTurn) {
		m_computerTurn=computerTurn;
	}
	public boolean getComputerTurn() {
		return m_computerTurn;
	}
	public Computer(String name) {
		super(name);
	}
	
	
	public void computerPlaysRandom(Board board) {
		if(m_computerTurn){
		int openR=0;
		int openC=0;
			do{
				Random rnd= new Random();
				 openR=rnd.nextInt(board.getm_Rows());
				 openC=rnd.nextInt(board.getm_Columns());
			}while(!(board.getm_Board().get(openC).get(openR).isHidden()));			
			board.revealTile(openR *Tile.WIDTH , openC*  Tile.HEIGHT);
			if(board.getm_GameLost() ){
				m_computerTurn=false;
			}
			
		
	}
	}
	
}
