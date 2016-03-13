/**
 * @file MainMenu.java
 * @author Eromosele Gideon, Michael Jeffrey
 * @date 7 December 2015
 *
 * Handles creation of the main menu
 * and user input on the menu.
 */

package main;

import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.lang.Math;

import game.Board;
import game.Human;
import game.Player;
import game.SaveGame;

/** 
 * Suppress serial ID warning as ID would not
 * match coding conventions.
 */
@SuppressWarnings("serial")
public class MainMenu extends JPanel implements MouseListener, KeyListener {

	private JTextField m_userNameText;
	private JTextField m_boardSizeText;
	private JTextField m_totalMinesText;
	private JFrame m_frame;
	private Kablewie m_kablewie;
	private boolean test = false;
	private final int MAX_BOARD_SIZE = 30;
	private final int TXT_BOX_WIDTH = 110;
	private final int TXT_BOX_HEIGHT = 26;
	private final int LABEL_WIDTH = 82;
	private final int LABEL_HEIGHT = 26;
	private final int LABEL_X_COORD = 6;
	private final int TXT_X_COORD = 98;
	private final int USER_Y_COORD = 26;
	private final int BOARD_Y_COORD = 69;
	private final int MINE_Y_COORD = 116;
	private final int TXT_BOX_COLUMNS = 10;
	private final int MAX_MINES = 150;
	private final int X_BUFFER = 50;
	private final int Y_BUFFER = 105;
	private final int X_MIN_MULTIPLY = 10;
	private final int Y_MIN_MULTIPLY = 10;
	private final int X_MIN_BUFFER = 130;
	private final int COLOUR_R = 153;
	private final int COLOUR_G = 180;
	private final int COLOUR_B = 209;
	private final int MENU_WIDTH = 440;
	private final int MENU_HEIGHT = 320;
	private final int TXT_SIZE = 10;
	private final int BUTTON_X_COORD = 110;
	private final int BUTTON_Y_COORD = 160;
	private final int BUTTON_LOAD_Y_COORD = 200;
	private final int BUTTON_WIDTH = 100;
	private final int BUTTON_HEIGHT = 32;
	private final int JPANEL_X_COORD = 95;
	private final int JPANEL_Y_COORD = 27;
	private final int JPANEL_WIDTH = 274;
	private final int JPANEL_HEIGHT = 260;
	
	/**
	 * getTest initialises m_userNameText,
	 * m_boardSizeText and m_totalMinesText
	 * for testing in the main method
	 * 
	 * @return test
	 */
	public boolean getTest(){
		m_userNameText = new JTextField();
		m_boardSizeText = new JTextField();
		m_totalMinesText = new JTextField();
		return test;
	}
	
	/**
	 * setUserNameText sets the content of
	 * m_userNameText for use in testing
	 * 
	 * @param username
	 */
	public void setUserNameText(String username){
		m_userNameText.setText(username);
	}
	
	/**
	 * setBoardSizeText sets the content of
	 * m_boardSizeText for use in testing
	 * 
	 * @param boardSize
	 */
	public void setBoardSizeText(int boardSize){
		m_boardSizeText.setText(Integer.toString(boardSize));
	}
	
	/**
	 * setTotalMinesText sets the content of
	 * m_totalMinesText for use in testing
	 * 
	 * @param totalMines
	 */
	public void setTotalMinesText(int totalMines){
		m_totalMinesText.setText(Integer.toString(totalMines));
	}
	
	/**
	 * getUserNameText gets the content of
	 * m_userNameText for use in testing
	 * 
	 * @return m_userNameText.getText()
	 */
	public String getUserNameText(){
		if (m_userNameText.getText().length() > 0){
			return m_userNameText.getText();
		} else {
			return "m_userNameText not set";
		}
	}
	
	/**
	 * getBoardSizeText gets the content of
	 * m_boardSizeText for use in testing
	 * 
	 * @return m_boardSizeText.getText()
	 */
	public int getBoardSizeText(){
		if (m_boardSizeText.getText().length() > 0){
			return Integer.parseInt(m_boardSizeText.getText());
		} else {
			return 0;
		}
	}
	
	/**
	 * getTotalMinesText gets the content of
	 * m_totalMinesText for use in testing
	 * 
	 * @return m_totalMinesText.getText()
	 */
	public int getTotalMinesText(){
		if (m_totalMinesText.getText().length() > 0){
			return Integer.parseInt(m_totalMinesText.getText());
		} else {
			return 0;
		}
	}
	
	/**
	 * Constructor to be able to create instances of the methods in
	 * this class for use in the main method for testing
	 */
	public MainMenu(){
		
	}
	/**
	 * Constructor that sets variable values
	 * and starts display of the menu.
	 * 
	 * @param frame a JFrame that the menu can be attached to.
	 * @param kablewie the instance of Kablewie which started this Menu.
	 */
	public MainMenu(JFrame frame, Kablewie kablewie) {
		// Set Class variables
		this.m_frame = frame;
		this.m_kablewie = kablewie;
		
		// Display the menu
		display();
		
		// Repaint the frame so it displays the menu
		frame.validate();
		frame.repaint();
	}

	/**
	 * Create the username input.
	 * 
	 * @param gamePanel the panel to add elements to. 
	 */
	public void createUserName(JPanel gamePanel) {
		JLabel userName = new JLabel("User Name:");
		userName.setBounds(LABEL_X_COORD, USER_Y_COORD, LABEL_WIDTH,
				LABEL_HEIGHT);
		gamePanel.add(userName);

		m_userNameText = new JTextField();
		m_userNameText.addKeyListener(this);
		m_userNameText.setBorder(null);
		m_userNameText.setBounds(TXT_X_COORD, USER_Y_COORD,
				TXT_BOX_WIDTH, TXT_BOX_HEIGHT);
		m_userNameText.setForeground(Color.RED);
		gamePanel.add(m_userNameText);
		m_userNameText.setColumns(TXT_BOX_COLUMNS);
	}

	/**
	 * Create the board size input.
	 * 
	 * @param gamePanel the panel to add elements to. 
	 */
	public void createBoardSize(JPanel gamePanel) {
		JLabel boardSize = new JLabel("Board Size:");
		boardSize.setBounds(LABEL_X_COORD, BOARD_Y_COORD,
				LABEL_WIDTH, LABEL_HEIGHT);
		gamePanel.add(boardSize);

		m_boardSizeText = new JTextField();
		m_boardSizeText.addKeyListener(this);
		m_boardSizeText.setText("10");
		m_boardSizeText.setBorder(null);
		m_boardSizeText.setBounds(TXT_X_COORD, BOARD_Y_COORD,
				TXT_BOX_WIDTH, TXT_BOX_HEIGHT);
		m_boardSizeText.setForeground(Color.RED);
		gamePanel.add(m_boardSizeText);
		m_boardSizeText.setColumns(TXT_BOX_COLUMNS);
	}

	/**
	 * Create the mine input.
	 * 
	 * @param gamePanel the panel to add elements to. 
	 */
	public void createTotalMines(JPanel gamePanel) {
		JLabel totalMines = new JLabel("Total Mines:");
		totalMines.setBounds(LABEL_X_COORD, MINE_Y_COORD, LABEL_WIDTH,
				LABEL_HEIGHT);
		gamePanel.add(totalMines);

		m_totalMinesText = new JTextField();
		m_totalMinesText.addKeyListener(this);
		m_totalMinesText.setText("10");
		m_totalMinesText.setBorder(null);
		m_totalMinesText.setBounds(TXT_X_COORD, MINE_Y_COORD, TXT_BOX_WIDTH,
				TXT_BOX_HEIGHT);
		m_totalMinesText.setForeground(Color.RED);
		gamePanel.add(m_totalMinesText);
		m_totalMinesText.setColumns(TXT_BOX_COLUMNS);
	}

	/**
	 * Create the start game button.
	 * 
	 * @param gamePanel the panel to add elements to. 
	 */
	public void createStartGameBtn(JPanel gamePanel) {
		JButton startGame = new JButton("Start Game");
		startGame.setIcon(null);
		startGame.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN,
				TXT_SIZE));
		startGame.setForeground(Color.BLACK);
		startGame.setBackground(Color.DARK_GRAY);
		startGame.setBounds(BUTTON_X_COORD, BUTTON_Y_COORD, BUTTON_WIDTH,
				BUTTON_HEIGHT);
		gamePanel.add(startGame);

		startGame.addMouseListener(this);		
	}
	
	/**
	 * Create the load game button.
	 * 
	 * @param gamePanel the panel to add elements to. 
	 */
	public void createLoadGameBtn(final JPanel gamePanel) {
		JButton loadGame = new JButton("Load Game");
		loadGame.setIcon(null);
		loadGame.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN,
				TXT_SIZE));
		loadGame.setForeground(Color.BLACK);
		loadGame.setBackground(Color.DARK_GRAY);
		loadGame.setBounds(BUTTON_X_COORD, BUTTON_LOAD_Y_COORD, BUTTON_WIDTH,
				BUTTON_HEIGHT);
		gamePanel.add(loadGame);
		
		final MainMenu menu = this;
		loadGame.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
	        	SaveGame saveGame = new SaveGame();
	        	saveGame.loadGame(m_frame, menu);
			}
		});
	}

	/**
	 * Add everything that needs to be asked
	 *  before starting the game in gamePanel
	 * 
	 * @param gamePanel
	 */
	public void createPanel(JPanel gamePanel) {

		gamePanel.setBackground(Color.GRAY);
		gamePanel.setBorder(new TitledBorder(null, "Kablewie Status", 
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		gamePanel.setBounds(JPANEL_X_COORD, JPANEL_Y_COORD, JPANEL_WIDTH,
				JPANEL_HEIGHT);
		m_frame.getContentPane().add(gamePanel);
		gamePanel.setLayout(null);
		createUserName(gamePanel);
		createBoardSize(gamePanel);
		createTotalMines(gamePanel);
		createStartGameBtn(gamePanel);
		createLoadGameBtn(gamePanel);
	}

	/**
	 * Create a panel and add this to the main frame.
	 */
	public void display() {
		// Frame options for menu.
		m_frame.setResizable(false);
		m_frame.setSize(MENU_WIDTH, MENU_HEIGHT);
		m_frame.setTitle("Kablewie");
		m_frame.setForeground(Color.RED);
		m_frame.setBackground(Color.RED);
		m_frame.getContentPane().setBackground(new Color(COLOUR_R, COLOUR_G,
				COLOUR_B));
		m_frame.getContentPane().setLayout(null);
		
		// Create and add the panel.
		JPanel gamePanel = new JPanel();
		m_frame.addKeyListener(this);
		createPanel(gamePanel);
	}

	/**
	 * Checks that the text boxes contains valid data and starts the game 
	 */
	public void startGame() {
		String username = m_userNameText.getText();
		int boardSize = 0;
		int numMines = 0;
		if (username.equalsIgnoreCase("")) {
			if(!test){
				JOptionPane.showMessageDialog(null,"Please enter player name",
					"No player name",JOptionPane.ERROR_MESSAGE);
				return;
			} else {
				System.out.println("Please enter player name");
			}
		} else if (username.length() >= 20){
			if(!test){
				JOptionPane.showMessageDialog(null,
					"Please enter a player name less than 20 characters long",
                    "Player name too long",JOptionPane.ERROR_MESSAGE);
				return;
			} else {
				System.out.println("Please enter player name < 20 chars long");
			}
		}
		try {
			boardSize = Integer.parseInt(m_boardSizeText.getText());
			numMines = Integer.parseInt(m_totalMinesText.getText());
		} catch (Exception e) {
			if(!test){
				JOptionPane.showMessageDialog(null,
					"Please enter integers only in Board Size and Total Mines",
					"Not Integer",JOptionPane.ERROR_MESSAGE);
				return;
			} else {
				System.out.println("Please enter integers only");
			}
		}
		if (!(boardSize > 0 && boardSize <= MAX_BOARD_SIZE)) {
			if(!test){
				JOptionPane.showMessageDialog(null,
					"Please enter a board size between 0 and 30",
					"Value Out of Bounds",JOptionPane.ERROR_MESSAGE);
				return;
			} else {
				System.out.println("Please enter board size between 0 and 30");
			}
		}
		if (!(numMines < boardSize * boardSize && 
				numMines <= MAX_MINES && numMines > 0)){
			m_totalMinesText.setText(m_boardSizeText.getText());
			int boardSquared = boardSize * boardSize;
			int maxMines = Math.min(boardSquared, MAX_MINES);
			if(!test) {
				JOptionPane.showMessageDialog(null,
					"Please enter a total number of mines between 0 and "
					+maxMines,"Value Out of Bounds",JOptionPane.ERROR_MESSAGE);
				return;
			} else {
				System.out.println("Please enter total mines between 0 and "
						+ maxMines);
			}
		}
		Board board = new Board(boardSize, boardSize, numMines);
		Player player = new Human(username);

		m_frame.setMinimumSize(new Dimension(
				X_MIN_MULTIPLY * MAX_BOARD_SIZE + X_BUFFER + X_MIN_BUFFER,
				boardSize * MAX_BOARD_SIZE + Y_BUFFER));
		// to x adding X_MIN_BUFFER cause needed space for computer button
		m_frame.setSize(boardSize * MAX_BOARD_SIZE + X_BUFFER,
				boardSize * MAX_BOARD_SIZE + Y_BUFFER);
		m_kablewie.startGame(board, player, this);
	}

	/**
	 * Called if the user clicks on the start game button.
	 */
	public void mouseClicked(MouseEvent arg0) {
		startGame();
	}
	
	/**
	 * Called on KeyRelease, start the game if
	 * its the enter key.
	 */
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			startGame();
		}
	}

	/*
	 * Methods below not used but included due to implements.
	 */
	public void mouseEntered(MouseEvent arg0) {

	}

	public void mouseExited(MouseEvent arg0) {

	}

	public void mousePressed(MouseEvent arg0) {

	}

	public void mouseReleased(MouseEvent arg0) {

	}

	public void keyPressed(KeyEvent e) {

	}

	public void keyTyped(KeyEvent e) {

	}
	
	public static void main(String[] args){
		MainMenu test = new MainMenu();
		if (test.getTest()){
			test.getTest();
			test.setUserNameText("abcdefghijklmnopqrs");
			test.setBoardSizeText(30);
			test.setTotalMinesText(-1);
			test.startGame();
		}
		
		
	}
}