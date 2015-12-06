package main;

import java.awt.event.*;
import java.awt.*;

import javax.swing.*;
import javax.swing.border.TitledBorder;

import game.Board;
import game.Human;
import game.Player;

/**
 * 
 * @author Eromosele Gideon
 *
 */
public class MainMenu extends JPanel 
implements MouseListener, KeyListener {
	JFrame m_frame;
	JMenuBar m_topMenuBar;
	JMenu m_file;
	JMenu m_about;
	JMenuItem m_aboutUs;
	JMenuItem m_gameMainMenu;
	JMenuItem m_newGame;
	JMenuItem m_exitGame;
	Kablewie m_kablewie;
	private static JTextField m_userNameText;
	private static JTextField m_boardSizeText;
	private static JTextField m_totalMinesText;
	/**
	 * the constructor of the MainMenu create the Main Menu on which
	 * the board size can be give and the username is asked 
	 * @param frame - the main frame which is build in Kablewie
	 * @param kablewie - the Kablewie object which starts 
	 * 						the game after the inputs have been entered
	 */
	public MainMenu(JFrame frame, Kablewie kablewie) {

		this.m_frame = frame;
		this.m_kablewie = kablewie;
		display();

		frame.validate();
		frame.repaint();
	}
	/**
	 * create the user name on the gamePanel
	 * @param gamePanel - the Panel is which is added on the frame
	 */
	public void createUserName(JPanel gamePanel) {
		JLabel userName = new JLabel("User Name:");
		userName.setBounds(10, 25, 100, 25);
		gamePanel.add(userName);

		m_userNameText = new JTextField();
		m_userNameText.addKeyListener(this);
		m_userNameText.setBorder(null);
		m_userNameText.setBounds(110, 25, 120, 25);
		gamePanel.add(m_userNameText);
		m_userNameText.setColumns(10);
	}
	/**
	 * create the Board Size on the gamePanel
	 * @param gamePanel - the Panel is which is added on the frame
	 */
	public void createBoardSize(JPanel gamePanel) {
		JLabel boardSize = new JLabel("Board Size:");
		boardSize.setBounds(10, 75, 100, 25);
		gamePanel.add(boardSize);

		m_boardSizeText = new JTextField();
		m_boardSizeText.addKeyListener(this);
		m_boardSizeText.setText("10");
		m_boardSizeText.setBorder(null);
		m_boardSizeText.setBounds(110, 75, 120, 25);
		gamePanel.add(m_boardSizeText);
		m_boardSizeText.setColumns(10);
	}
	/**
	 * create the Total Mine on the gamePanel
	 * @param gamePanel - the Panel is which is added on the frame
	 */
	public void createTotalMines(JPanel gamePanel) {
		JLabel totalMines = new JLabel("Total Mines:");
		totalMines.setBounds(10, 120, 100, 25);
		gamePanel.add(totalMines);
		m_totalMinesText = new JTextField();
		m_totalMinesText.addKeyListener(this);
		m_totalMinesText.setText("10");
		m_totalMinesText.setBorder(null);
		m_totalMinesText.setBounds(110, 120, 120, 25);
		gamePanel.add(m_totalMinesText);
		m_totalMinesText.setColumns(10);
	}
	/**
	 * create the Start Button on the gamePanel
	 * @param gamePanel - the Panel is which is added on the frame
	 */
	public void createStartGameBtn(JPanel gamePanel) {
		JButton startGame = new JButton("Start Game");
		startGame.setIcon(null);
		startGame.setFont(new Font("Copperplate Gothic Bold"
				, Font.PLAIN, 11));
		startGame.setForeground(Color.BLUE);
		startGame.setBounds(25, 250, 200, 25);
		gamePanel.add(startGame);

		startGame.addMouseListener(this);
	}
	/**
	 * add every thing which needs to be asked before starting the game
	 * in gamePanel
	 * @param gamePanel
	 */
	public void createPanel(JPanel gamePanel) {

		gamePanel.setBackground(SystemColor.inactiveCaption);
		gamePanel.setBorder(new TitledBorder(null, "Kablewie Status"
				, TitledBorder.LEADING, TitledBorder.TOP, null, null));
		gamePanel.setBounds(100, 25, 440, 400);
		m_frame.getContentPane().add(gamePanel);
		gamePanel.setLayout(null);
		createUserName(gamePanel);
		createBoardSize(gamePanel);
		createTotalMines(gamePanel);
		createStartGameBtn(gamePanel);
	}
	/**
	 *  display the main menu using the above method
	 */
	public void display() {

		m_frame.setResizable(false);
		m_frame.setSize(640, 480);
		m_frame.setTitle("Kablewie");
		m_frame.setForeground(Color.RED);
		m_frame.setBackground(Color.RED);
		m_frame.getContentPane().setBackground(new Color(153, 180, 209));
		m_frame.getContentPane().setLayout(null);
		JPanel gamePanel = new JPanel();
		m_frame.addKeyListener(this);
		createPanel(gamePanel);
	}
	/**
	 * it starts the game if you press enter 
	 * or pressed the start button
	 */
	public void startGame() {
		String username = m_userNameText.getText();
		int boardSize;
		int numMines;
		if (username.equalsIgnoreCase("")) {
			return;
		}
		try {
			boardSize = Integer.parseInt(m_boardSizeText.getText());
			numMines = Integer.parseInt(m_totalMinesText.getText());
		} catch (Exception e) {
			return;
		}
		if (!(boardSize > 0 && boardSize <= 30) 
				|| !(numMines < boardSize * boardSize
						&& numMines <= 150 && numMines>0)) {
			m_totalMinesText.setText(m_boardSizeText.getText());
			return;
		}
		Board board = new Board(boardSize, boardSize, numMines);
		Player player = new Human(username);
		m_frame.setSize(boardSize * 30 + 50, boardSize * 30 + 105);
		m_frame.setMinimumSize(new Dimension(5 * 30 + 50+130, 5 * 30 + 105));
		m_kablewie.startGame(board, player, this);
	}
	
	/**
	 * whenever the start button is pressed the mouseclicked
	 *  is called which calls startGame()
	 */
	public void mouseClicked(MouseEvent arg0) {

		startGame();

	}
	/**
	 * we do not use these method
	 */
	public void mouseEntered(MouseEvent arg0) {

	}

	public void mouseExited(MouseEvent arg0) {

	}

	public void mousePressed(MouseEvent arg0) {

	}

	public void mouseReleased(MouseEvent arg0) {

	}
	/**
	 * whenever enter is pressed then you can start game if the inputs are valid
	 */
	public void keyPressed(KeyEvent e) {
		
	}

	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == 10) {
			startGame();
		}
	}

	public void keyTyped(KeyEvent e) {

	}
}