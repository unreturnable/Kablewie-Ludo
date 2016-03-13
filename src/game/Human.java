/**
 * @file Human.java
 * @author Zongbo Xu, David Jones
 * @date 5 December 2015
 *
 * A class for Human players.
 */ 

package game;

public class Human extends Player  {
	private boolean m_playersTurn = true;

	/**
	 * Human Constructor
	 * @param name a string which is name of the player
	*/
	public Human(String name) {
		super(name);
	}
	
	
	public void takeTurn() {
		m_playersTurn = true;
	}
	
	/**
	 * @return if it is the players turn
	 */
	public boolean getm_playersTurn() {
		return m_playersTurn;
	}
	
	
	  /**
	    * This is the main method of the class.
	    * 
	    * Used for Unit testing
	    *
	    * @param args -input arguments are ignored in this example
	    */
	public static void main(String args[] ) {
		boolean test = true;
	    if (test) {
	    	System.out.println("Human::main() BEGIN");
	    }
	    
	    Human humanTest = new Human("Contructor unit test"); 
	    System.out.println(humanTest.getUsername());
	    
	    if (test) {
	       System.out.println("Human::main() END");
	    }
	    
	}

}
