/**
 * @file Computer.java
 * @author Anshul Kumar
 * @date 12 March 2016
 * 
 * A class for computer AI.
 */

package game;

import java.util.LinkedList;

public class Mapping {

	private LinkedList<Position> m_position;
	private int numberOfMine;

	/**
	 * create a list of position by saying that it will point to where
	 * every the position parameter is pointing
	 * 
	 * @param position
	 *            position is a list of Position object
	 */
	public void setPosition(LinkedList<Position> position) {
		m_position = position;
	}

	/**
	 * 
	 * @return return the list of position
	 */
	public LinkedList<Position> getPosition() {
		return m_position;
	}

	/**
	 * 
	 * @return the size of the list
	 */
	public int getPostionSize() {
		return m_position.size();
	}

	/**
	 * it is the number of mine the list of position is map to this mean that
	 * the sum of mines in the list will give this number
	 * 
	 * @param numberOfMine
	 *            integer
	 */
	public void setNumberOfMine(int numberOfMine) {
		this.numberOfMine = numberOfMine;
	}

	/**
	 * the return value is the sum of mines in the list
	 * 
	 * @return the number of mine is return
	 */
	public int getNumberOfMine() {
		return numberOfMine;
	}

	/**
	 * Constructor of the class
	 */
	public Mapping() {
		m_position = new LinkedList<Position>();
	}

	/**
	 * it adds the sent position to the list
	 * 
	 * @param position
	 *            an object of Position class
	 * @see Position class
	 */
	public void add(Position position) {
		m_position.add(position);
	}

	/**
	 * check if two mapping object have all the position equal or not
	 * 
	 * @param mapping
	 *            the mapping which is checked if it is equal to the current
	 *            mapping or not
	 * @return true if the two mapping object have the equal position 
	 * 			otherwise it is false
	 */
	public boolean equal(Mapping mapping) {
		for (int i = 0; i < m_position.size(); ++i) {
			int j = 0;
			for (j = 0; j < mapping.m_position.size(); ++j) {
				if (m_position.get(i).equal(mapping.m_position.get(j))) {
					break;
				}
			}
			if (j == mapping.m_position.size()) {
				return false;
			}
		}
		return true;
	}// not a useful method

	/**
	 * Tells if a mapping is a subset of another mapping or not
	 * 
	 * @param mapping
	 *            object of Mapping class
	 * @return null is return if it is not a subset otherwise it return 
	 * 			the list of position which are the element in both mapping
	 */
	public LinkedList<Position> isSubset(Mapping mapping) {
		LinkedList<Position> mapRemove = new LinkedList<Position>();
		int i = 0;
		for (i = 0; i < m_position.size(); ++i) {
			Position p = m_position.get(i);
			boolean pointFound = false;
			for (int j = 0; j < mapping.m_position.size(); ++j) {
				Position p2 = mapping.m_position.get(j);
				if (p.equal(p2)) {
					pointFound = true;
					mapRemove.add(p2);
					break;
				}
			}
			if (!pointFound) {
				return null;
			}
		}
		return mapRemove;
	}

	/**
	 * Sets every position in mapping object to true for mine except the
	 * position which can be found in mapRemove
	 * 
	 * @param mapping
	 *            it is a mapping object
	 * @param mapRemove
	 *            list of position
	 */
	public void setMines(Mapping mapping, LinkedList<Position> mapRemove) {
		for (int j = 0; j < mapping.m_position.size(); ++j) {
			int k = 0;
			for (k = 0; k < mapRemove.size(); ++k) {
				if (mapping.m_position.get(j) == mapRemove.get(k)) {
					break;
				}
			}
			if (k == mapRemove.size()) {
				mapping.m_position.get(j).setIsMine(true);
				;
			}
		}
	}

	/**
	 * sets every position in mapping object to true for safe to open 
	 * except the position which can be found in mapRemove
	 * 
	 * @param mapping
	 *            it is a mapping object
	 * @param mapRemove
	 *            list of position
	 */
	public void setDefuse(Mapping mapping, LinkedList<Position> mapRemove) {
		for (int j = 0; j < mapping.m_position.size(); ++j) {
			int k = 0;
			for (k = 0; k < mapRemove.size(); ++k) {
				if (mapping.m_position.get(j) == mapRemove.get(k)) {
					break;
				}
			}
			if (k == mapRemove.size()) {
				mapping.m_position.get(j).setIsSafeToOpen(true);
			}
		}
	}

	/**
	 * tries to find the mine or a safe position by saying a mapping 
	 * is a subset of another mapping and then checking the mine counts 
	 * eg .. this object
	 * position {(0,1)(1,1) -> 1(number of mine)} mapping position
	 * {(0,1)(1,1)(2,1) -> X(number of mine)} 
	 * if X is 1 that means that position (2,1) is a safe location and 
	 * if X is 2 then it mean that position (2,1) is a mine
	 * 
	 * @param mapping
	 *            mapping class object
	 * @return true the method find a mine or a safe position to open 
	 * 				otherwise it return false
	 */
	public boolean computerAI(Mapping mapping) {
		LinkedList<Position> mapRemove = isSubset(mapping);
		if (mapRemove != null) {
			int bomb = 0;

			bomb = mapping.m_position.size() - mapRemove.size() + numberOfMine;
			if (bomb == mapping.numberOfMine) {
				setMines(mapping, mapRemove);
				return true;
			} else if (mapping.numberOfMine == numberOfMine) {
				setDefuse(mapping, mapRemove);
				return true;
			}
		}
		return false;
	}

}
