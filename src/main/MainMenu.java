/**
 * @file MainMenu.java
 * @author Eromosele Gideon
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

import game.Board;
import game.Human;
import game.Player;

/* 
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
	private final int X_MIN_MULTIPLY = 5;
	private final int Y_MIN_MULTIPLY = 5;
	private final int X_MIN_BUFFER = 130;
	private final int COLOUR_R = 153;
	private final int COLOUR_G = 180;
	private final int COLOUR_B = 209;
	private final int MENU_WIDTH = 440;
	private final int MENU_HEIGHT = 320;
	private final int TXT_SIZE = 10;
	private final int BUTTON_X_COORD = 110;
	private final int BUTTON_Y_COORD = 160;
	private final int BUTTON_WIDTH = 100;
	private final int BUTTON_HEIGHT = 32;
	private final int JPANEL_X_COORD = 95;
	private final int JPANEL_Y_COORD = 27;
	private final int JPANEL_WIDTH = 274;
	private final int JPANEL_HEIGHT = 210;
	
	
	
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


	public void startGame() {
		String username = m_userNameText.getText();
		int boardSize;
		int numMines;
		if (username.equalsIgnoreCase("")) {
			JOptionPane.showMessageDialog(null,"Please enter a player name",
                    "No player name",JOptionPane.ERROR_MESSAGE);
			return;
		}
		try {
			boardSize = Integer.parseInt(m_boardSizeText.getText());
			numMines = Integer.parseInt(m_totalMinesText.getText());
		} catch (Exception e) {
			return;
		}
		if (!(boardSize > 0 && boardSize <= MAX_BOARD_SIZE)) {
			JOptionPane.showMessageDialog(null,
					"Please enter a value between 0 and 30",
					"Value Out of Bounds",JOptionPane.ERROR_MESSAGE);
			return;
		}
		if (!(numMines < boardSize * boardSize && 
				numMines <= MAX_MINES && numMines > 0)){
			m_totalMinesText.setText(m_boardSizeText.getText());
			JOptionPane.showMessageDialog(null,
					"Too many mines",
					"Value Out of Bounds",JOptionPane.ERROR_MESSAGE);
			return;
		}
		Board board = new Board(boardSize, boardSize, numMines);
		Player player = new Human(username);
		m_frame.setSize(boardSize * MAX_BOARD_SIZE + X_BUFFER,
				boardSize * MAX_BOARD_SIZE + Y_BUFFER);
		m_frame.setMinimumSize(new Dimension(
				X_MIN_MULTIPLY * MAX_BOARD_SIZE + X_BUFFER + X_MIN_BUFFER,
				Y_MIN_MULTIPLY * MAX_BOARD_SIZE + Y_BUFFER));
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
}