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
	JFrame frame;
	JMenuBar topMenuBar;
	JMenu file;
	JMenu about;
	JMenuItem aboutUs;
	JMenuItem gameMainMenu;
	JMenuItem newGame;
	JMenuItem exitGame;
	Kablewie kablewie;
	private static JTextField userNameText;
	private static JTextField boardSizeText;
	private static JTextField totalMinesText;
	/**
	 * the constructor of the MainMenu create the Main Menu on which
	 * the board size can be give and the username is asked 
	 * @param frame - the main frame which is build in Kablewie
	 * @param kablewie - the Kablewie object which starts 
	 * 						the game after the inputs have been entered
	 */
	public MainMenu(JFrame frame, Kablewie kablewie) {

		this.frame = frame;
		this.kablewie = kablewie;
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

		userNameText = new JTextField();
		userNameText.addKeyListener(this);
		userNameText.setBorder(null);
		userNameText.setBounds(110, 25, 120, 25);
		gamePanel.add(userNameText);
		userNameText.setColumns(10);
	}
	/**
	 * create the Board Size on the gamePanel
	 * @param gamePanel - the Panel is which is added on the frame
	 */
	public void createBoardSize(JPanel gamePanel) {
		JLabel boardSize = new JLabel("Board Size:");
		boardSize.setBounds(10, 75, 100, 25);
		gamePanel.add(boardSize);

		boardSizeText = new JTextField();
		boardSizeText.addKeyListener(this);
		boardSizeText.setText("10");
		boardSizeText.setBorder(null);
		boardSizeText.setBounds(110, 75, 120, 25);
		gamePanel.add(boardSizeText);
		boardSizeText.setColumns(10);
	}
	/**
	 * create the Total Mine on the gamePanel
	 * @param gamePanel - the Panel is which is added on the frame
	 */
	public void createTotalMines(JPanel gamePanel) {
		JLabel totalMines = new JLabel("Total Mines:");
		totalMines.setBounds(10, 120, 100, 25);
		gamePanel.add(totalMines);
		totalMinesText = new JTextField();
		totalMinesText.addKeyListener(this);
		totalMinesText.setText("10");
		totalMinesText.setBorder(null);
		totalMinesText.setBounds(110, 120, 120, 25);
		gamePanel.add(totalMinesText);
		totalMinesText.setColumns(10);
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
		gamePanel.setBorder(
				new TitledBorder(null, "Kablewie Status"
						, TitledBorder.LEADING, TitledBorder.TOP, null, null));
		gamePanel.setBounds(100, 25, 440, 400);
		frame.getContentPane().add(gamePanel);
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

		frame.setResizable(false);
		frame.setSize(640, 480);
		frame.setTitle("Kablewie");
		frame.setForeground(Color.RED);
		frame.setBackground(Color.RED);
		frame.getContentPane().setBackground(new Color(153, 180, 209));
		frame.getContentPane().setLayout(null);
		JPanel gamePanel = new JPanel();
		frame.addKeyListener(this);
		createPanel(gamePanel);
	}
	/**
	 * it starts the game if you press enter 
	 * or pressed the start button
	 */
	public void startGame() {
		String username = userNameText.getText();
		int boardSize;
		int numMines;
		if (username.equalsIgnoreCase("")) {
			return;
		}
		try {
			boardSize = Integer.parseInt(boardSizeText.getText());
			numMines = Integer.parseInt(totalMinesText.getText());
		} catch (Exception e) {
			return;
		}
		if (!(boardSize > 0 && boardSize <= 30) 
				|| !(numMines < boardSize * boardSize && numMines <= 150)) {
			totalMinesText.setText(boardSizeText.getText());
			return;
		}
		Board board = new Board(boardSize, boardSize, numMines);
		Player player = new Human(username);
		frame.setSize(boardSize * 30 + 50, boardSize * 30 + 105);
		kablewie.startGame(board, player, this);
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
		System.out.println(e.getKeyCode());
		if (e.getKeyCode() == 10) {
			System.out.println("camd");
			startGame();
		}
	}

	public void keyTyped(KeyEvent e) {

	}
}