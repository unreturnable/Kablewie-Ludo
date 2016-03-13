/**
 * @file Computer.java
 * @author Zongbo Xu , Anshul Kumar
 * @date 5 December 2015
 * 
 * A class for computer players.
 */

package game;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;
import java.util.Scanner;

public class Computer extends Player {

	private boolean m_isComputerAI;
	private LinkedList<Position> m_copyBoard;
	private LinkedList<Position> m_cloneBoard;
	private LinkedList<Mapping> m_mapping;
	private boolean firstComputerAIMove;
	private static boolean m_test;

	/**
	 * set the is it computer AI going to play or a random computer will play
	 * 
	 * @param computerTurn
	 *            boolean
	 */
	public void setIsComputerAI(boolean computerTurn) {
		m_isComputerAI = computerTurn;
	}

	/**
	 * 
	 * @return true is return if computer AI is playing otherwise false
	 */
	public boolean isComputerAI() {
		return m_isComputerAI;
	}// not sure if we needd it

	/**
	 * it is set to true whenever it is going to be the first turn from 
	 * computer AI
	 * 
	 * @param computerAIFirst
	 *            boolean
	 */
	public void setComputerAIFirst(boolean computerAIFirst) {
		this.firstComputerAIMove = computerAIFirst;
	}

	/**
	 * 
	 * @return if it is first move from computer then true otherwise false
	 */
	public boolean getComputerAIFirst() {
		return firstComputerAIMove;
	}

	/**
	 * it initialising all the class variable and set the firstComputerAIMove 
	 * to true because whenever the ai is called it will be the first time 
	 * for now
	 * 
	 * @param name
	 *            it the name of the player
	 */
	public Computer(String name) {
		super(name);
		m_copyBoard = new LinkedList<Position>();
		m_cloneBoard = new LinkedList<Position>();
		m_mapping = new LinkedList<Mapping>();
		firstComputerAIMove = true;
	}

	/**
	 * it just gets x and y (which is the position on board) and it makes sure
	 * that it is a hidden tile and if it is defused then it undefused and
	 * reveal it
	 * 
	 * @param board
	 *            the board where it has all the tiles stores in it
	 */
	public void computerPlaysRandom(Board board) {
		int openR = 0;
		int openC = 0;
		do {
			Random rnd = new Random();
			openR = rnd.nextInt(board.getm_Rows());
			openC = rnd.nextInt(board.getm_Columns());
		} while (!(board.getm_Board().get(openC).get(openR).isHidden()));
		if (board.getm_Board().get(openC).get(openR).isDefused()) {
			board.defusedTile(openR * Tile.WIDTH, openC * Tile.HEIGHT);
		}
		board.revealTile(openR * Tile.WIDTH, openC * Tile.HEIGHT);
		if (m_test) {
			System.out.print("\t");
			for (int i = 0; i < board.getm_Board().size(); ++i) {
				System.out.print(i + "\t");
			}
			System.out.println();

			for (int i = 0; i < board.getm_Board().size(); ++i) {
				System.out.print(i + "\t");
				for (int j = 0; j < board.getm_Board().get(0).size(); ++j) {
					if (board.getm_Board().get(i).get(j).m_isHidden) {
						System.out.print("\t");
					} else if (j == openR && i == openC) {
						if (board.getm_Board().get(i).get(j).isMine()) {
							System.out.print("CM*\t");
							return;
						}
						Revealed r = (Revealed) board.getm_Board()
								.get(i).get(j);
						System.out.print(r.getm_NearByMines() + "C\t");
					} else {
						if (board.getm_Board().get(i).get(j).isMine())
							return;
						Revealed r = (Revealed) board.getm_Board()
								.get(i).get(j);
						System.out.print(r.getm_NearByMines() + "\t");
					}
				}

				System.out.println();
			}
			System.out.println("\n\n\n\n\n");
		}
	}

	/*
	 * AI for the computer class which thinks itself and tries to find a good
	 * tile to open and if it can't find then it goes random
	 */

	/**
	 * ComputerAI is call the other AI method and tries to open a safe tile
	 * otherwise it opens a random tile
	 * 
	 * @param board
	 *            Board object
	 */
	public void computerAI(Board board) {
		initialisingComputerAI(board);
		while (!(mapToNumberOfMine(board.getm_Board())))
			;
		while (!(isSubsetOfAnyMap())) {
			while (!(mapToNumberOfMine(board.getm_Board()))) {

			}
		}
		defuseTile(board);
		m_cloneBoard = m_copyBoard;
	}

	/**
	 * it makes a copy of the board which has all the tile stored in it
	 * 
	 * @param board
	 *            Board object
	 */
	public void initialisingComputerAI(Board board) {
		m_copyBoard = new LinkedList<Position>();
		ArrayList<ArrayList<Tile>> tiles = board.getm_Board();
		int k = 0;
		for (int i = 0; i < tiles.size(); ++i) {
			for (int j = 0; j < tiles.get(0).size(); ++j) {
				Position position = new Position(i, j);
				/*
				 * if the tile is opened then get the number of mine around it
				 * and set the mine count to the number got
				 */
				if (!(tiles.get(i).get(j).m_isHidden)) {
					Revealed r = (Revealed) tiles.get(i).get(j);
					position.setMineCount(r.getm_NearByMines());
				}
				/*
				 * if the tile is defused and it is the first move of 
				 * computerAI then set is flag there to false
				 */
				else if (tiles.get(i).get(j).m_isDefused 
						&& firstComputerAIMove) {
					position.setFlagIsThere(false);
				}
				/*
				 * if the tile is defused and it is not ComputerAI first move
				 * then set the is flag to true but before that we make sure
				 * that there was a flag before in my cloned board
				 */
				else if (tiles.get(i).get(j).m_isDefused 
						&& !firstComputerAIMove) {
					if (m_cloneBoard.get(k).isFlagIsThere()) {
						position.setFlagIsThere(true);
					}
				}
				++k;
				m_copyBoard.add(position);
			}
		}
		/*
		 * firstComputerMove is set to false if it was true
		 */
		if (firstComputerAIMove) {
			firstComputerAIMove = false;
		}
	}
	/**
	 * add a position to the mapping object before adding it make sure that it
	 * is not a discovered mine when this method was called previously
	 * 
	 * @param tileArround
	 *            list of Position
	 * @param mapping
	 *            an object of Mapping
	 * @param numberOfMine
	 *            integer which has the number of mine can be there
	 *            in the list
	 * @return mine information is returned like bomb around which where found
	 *         to what it should map to (number of mine)
	 */
	public int[] createMapping(LinkedList<Position> tileArround
			, Mapping mapping, int numberOfMine) {
		int bombArround = 0;
		int notMapPosition = 0;
		for (Position position : tileArround) {
			if (position.isMine()) {
				++bombArround;
				--numberOfMine;
			} else if (position.getMineCount() == -1 
					&& !(position.isSafeToOpen())) {
				mapping.add(position);
				++notMapPosition;
			}
		}
		int[] mineInfo = new int[3];
		mineInfo[0] = bombArround;
		mineInfo[1] = notMapPosition;
		mineInfo[2] = numberOfMine;
		return mineInfo;
	}

	/**
	 * set that there is a mine at position
	 * 
	 * @param mapping
	 * @return
	 */
	public boolean setMine(Mapping mapping) {
		for (Position position : mapping.getPosition()) {
			if (!(position.isMine())) {
				position.setIsMine(true);
				return false;
			}
		}
		return true;
	}

	/**
	 * it has a list of Mapping which when added can have N number of mine and
	 * that N number is stored in Mapping class
	 * 
	 * @param tiles
	 *            it stores the original tile which is just use for getting 
	 *            the size
	 * @return 
	 * 			true is return if method does not find any mine or any safe 
	 * 			tile to open if it could find it then it returns false
	 */
	public boolean mapToNumberOfMine(ArrayList<ArrayList<Tile>> tiles) {
		boolean runCompleted = true;
		m_mapping = new LinkedList<Mapping>();
		for (int i = 0; i < m_copyBoard.size(); ++i) {
			Position positionCheck = m_copyBoard.get(i);
			LinkedList<Position> tileArround;
			if (!(positionCheck.getMineCount() == -1) 
					&& !(positionCheck.getMineCount() == 0)) {
				tileArround = getTileArround(positionCheck
						, tiles.size(), tiles.get(0).size());

				Mapping mapping = new Mapping();
				int mapTarget = positionCheck.getMineCount();
				int[] t = createMapping(tileArround, mapping, mapTarget);
				int bombArround = t[0];
				int notMapPosition = t[1];
				mapTarget = t[2];
				mapping.setNumberOfMine(mapTarget);
				if ((mapping.getPostionSize() == positionCheck.getMineCount()
						&& bombArround == 0)) {
					/*
					 * for(Position k : mapping.getPosition())
					 * k.setIsMine(true);
					 */
					runCompleted = setMine(mapping);
				} else if ((notMapPosition + bombArround)
						== positionCheck.getMineCount()) {
					runCompleted = setMine(mapping);
				} else if (bombArround == positionCheck.getMineCount()) {
					for (Position position : tileArround) {
						if (position.getMineCount() == -1 
								&& !(position.isMine()) 
								&& !(position.isSafeToOpen())) {
							position.setIsSafeToOpen(true);
							;
							runCompleted = false;
						}
					}
				} else {
					m_mapping.add(mapping);
				}
			}

		}
		return runCompleted;
	}

	/**
	 * 
	 * @param position
	 *            has the position of a tile which around tile i need to return
	 * @param row
	 *            Size has the size of row on the board
	 * @param column
	 *            Size has the size of column on the board
	 * @return the linked list of position which has the tile position around
	 *         the position
	 */
	public LinkedList<Position> getTileArround(Position position
			, int rowSize, int columnSize) {
		int i = position.getXPos();
		int j = position.getYPos();
		int prevrow = i - 1;
		int prevrcol = j - 1;
		int nextrow = i + 1;
		int nextcol = j + 1;
		LinkedList<Position> t2 = new LinkedList<Position>();
		for (int k = prevrow; k <= nextrow; ++k) {
			for (int m = prevrcol; m <= nextcol; ++m) {
				if (!(k < 0 || m < 0 || k >= rowSize || m >= columnSize) 
						&& (!(k == i && m == j))) {
					/*
					 * before adding it the condition makes sure that it is 
					 * not out of bound of the board and it is not equals 
					 * to the position given
					 */
					t2.add(m_copyBoard.get(k * rowSize + m));
				}
			}
		}
		return t2;
	}

	/**
	 * It tries to find two mapping where first mapping size is less then 
	 * other mapping and then we call computerAI() method in mapping class
	 * 
	 * @return true is return if a mapping is not in other object of mapping
	 *         other wise it returns false
	 * @see Mapping Class computerAI() method
	 * 
	 */
	public boolean isSubsetOfAnyMap() {
		boolean methodComplete = true;
		for (int i = 0; i < m_mapping.size(); ++i) {
			Mapping mapSubset = m_mapping.get(i);
			for (int j = 0; j < m_mapping.size(); ++j) {
				if (i != j) {
					Mapping map = m_mapping.get(j);
					if (mapSubset.getPostionSize() < map.getPostionSize()) {
						if (mapSubset.computerAI(map)) {
							methodComplete = false;
						}
						;
					}
				}
			}
		}
		return methodComplete;
	}

	/**
	 * it diffuse the tile if the copy board know that some of the mine are at
	 * x,y position then it diffuse that tile
	 * 
	 * @param board
	 *            it has all the tile stored in it
	 */
	public void defuseTile(Board board) {
		for (int i = 0; i < m_copyBoard.size(); ++i) {
			Position pos = m_copyBoard.get(i);
			int x = pos.getXPos();
			int y = pos.getYPos();
			if (pos.isMine() && pos.isFlagIsThere() == false) {
				if (board.getm_Board().get(x).get(y).isDefused()) {
					pos.setFlagIsThere(true);
					continue;
				}
				board.defusedTile(y * Tile.WIDTH, x * Tile.HEIGHT);
				pos.setFlagIsThere(true);
				return;
			}
		}
		revealTile(board);
	}

	/**
	 * it reveal a tile if copy board say that it is save to open the 
	 * tile then it reveal it
	 * 
	 * @param board
	 *            it has all the tile stored in it
	 */
	public void revealTile(Board board) {
		for (int i = 0; i < m_copyBoard.size(); ++i) {
			Position position = m_copyBoard.get(i);
			int x = position.getXPos();
			int y = position.getYPos();
			/*
			 * if the tile is diffused and it is safe to open then undiffuse 
			 * it
			 */
			if (board.getm_Board().get(x).get(y).isDefused() 
					&& position.isSafeToOpen()) {
				board.defusedTile(position.getYPos() * Tile.WIDTH
						, position.getXPos() * Tile.HEIGHT);
				return;
			}
			/*
			 * if the tile is not hidden which mean that it is revealed 
			 * then set the tile is open to true
			 */
			else if (!(board.getm_Board().get(x).get(y).isHidden())) {
				position.setTileIsOpen(true);
				continue;
			}
			/*
			 * if it is safe to open and the tile is not open then open it
			 */
			else if (position.isSafeToOpen() && !(position.isTileIsOpen())) {
				board.revealTile(y * Tile.WIDTH, x * Tile.HEIGHT);
				position.setTileIsOpen(true);
				return;
			}
		}
		computeRandomAI(board);
	}

	/**
	 * picks a random tile on the board and check some of the important things
	 * and if the tile is defused then undefused it and reveal it
	 * 
	 * @param board
	 *            the board where it has all the tiles stores in it
	 */
	public void computeRandomAI(Board board) {
		int openTile = 0;
		Position check = null;
		int x = 0;
		int y = 0;
		do {
			// getting the random position till the copyBoard size
			Random rnd = new Random();
			openTile = rnd.nextInt(m_copyBoard.size());
			check = m_copyBoard.get(openTile);
			x = check.getXPos();
			y = check.getYPos();
		} while (check.isMine() || check.isFlagIsThere() 
				|| !(board.getm_Board().get(x).get(y).isHidden()));
		if (board.getm_Board().get(x).get(y).m_isDefused) {
			board.defusedTile(y * Tile.WIDTH, x * Tile.HEIGHT);
		}
		board.revealTile(y * Tile.WIDTH, x * Tile.HEIGHT);
		if (board.getm_GameLost()) {
			m_isComputerAI = false;
		}

	}

	public void resetAI() {
		m_copyBoard.clear();
		m_mapping.clear();
		m_cloneBoard.clear();
		firstComputerAIMove = true;
	}

	public void testComputerAI() {
		Board board = new Board(10, 10, 10);
		ArrayList<ArrayList<Tile>> tiles = board.getm_Board();
		board.revealTile(2 * Tile.WIDTH, 0 * Tile.HEIGHT);
		board.revealTile(3 * Tile.WIDTH, 0 * Tile.HEIGHT);
		board.revealTile(4 * Tile.WIDTH, 0 * Tile.HEIGHT);
		board.revealTile(4 * Tile.WIDTH, 1 * Tile.HEIGHT);
		board.revealTile(3 * Tile.WIDTH, 2 * Tile.HEIGHT);
		board.revealTile(4 * Tile.WIDTH, 2 * Tile.HEIGHT);
		board.revealTile(3 * Tile.WIDTH, 3 * Tile.HEIGHT);
		board.revealTile(4 * Tile.WIDTH, 3 * Tile.HEIGHT);
		board.revealTile(3 * Tile.WIDTH, 4 * Tile.HEIGHT);
		board.revealTile(4 * Tile.WIDTH, 4 * Tile.HEIGHT);
		board.revealTile(1 * Tile.WIDTH, 3 * Tile.HEIGHT);
		board.revealTile(1 * Tile.WIDTH, 4 * Tile.HEIGHT);
		initialisingComputerAI(board);
		displayAI(board);
		setComputerAIFirst(true);
		computerAI(board);
		displayAI(board);
	}

	private void displayAI(Board board) {
		int k = 0;
		for (int i = 0; i < board.getm_Rows(); ++i) {
			for (int j = 0; j < board.getm_Columns(); ++j) {
				if (m_copyBoard.get(k).isMine()) {
					System.out.print("M\t");
				} else if (m_copyBoard.get(k).isSafeToOpen()) {
					System.out.print("S\t");
				} else if (m_copyBoard.get(k).getMineCount() != -1) {
					System.out.print(m_copyBoard.get(k).getMineCount() + "\t");
				} else {
					System.out.print("*\t");
				}
				++k;
			}
			System.out.println();
		}
		System.out.println("\n\n");
	}

	public static void main(String args[]) {
		m_test = true;
		if(m_test) {
		System.out.println("TESTING THE RANDOM COMPUTER IT "
				+ "SHOULD OPEN A NEW SUQUARE EEACH TIME");
		new Computer("testing").computerRandomTest();
		System.out.println("PRESS ENTER TO GO TO COMPUTER AI TESTIN");
		Scanner in = new Scanner(System.in);
		in.nextLine();
		new Computer("testing").testComputerAI();
		System.out.println("M mean mine is there (maybe)");
		System.out.println("S mean safe to open  (maybe)");
		System.out.println("* mean hidden");
		}
	}

	public void computerRandomTest() {
		Board b = new Board(10, 10, 10);
		System.out.print("\t");
		for (int i = 0; i < b.getm_Board().size(); ++i) {
			System.out.print(i + "\t");
		}
		System.out.println();

		for (int i = 0; i < b.getm_Board().size(); ++i) {
			System.out.print(i + "\t");
			for (int j = 0; j < b.getm_Board().get(0).size(); ++j) {

				System.out.print("*\t");

			}
			System.out.println();
		}

		m_isComputerAI = true;
		while (m_isComputerAI) {
			computerPlaysRandom(b);
			if (b.getm_GameLost()) {
				return;
			}
			try {
				Thread.sleep(1000 * 3);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
