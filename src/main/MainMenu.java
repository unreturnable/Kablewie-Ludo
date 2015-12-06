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
public class MainMenu extends JPanel implements MouseListener{
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
/*
 * 
		getContentPane().setLayout(new FlowLayout());
		
		topMenuBar = new JMenuBar();
		setJMenuBar(topMenuBar);

		file = new JMenu("File");
		topMenuBar.add(file);

		about = new JMenu("About Game");
		topMenuBar.add(about);

		aboutUs = new JMenuItem("About Us");
		about.add(aboutUs);

		event au = new event();
		aboutUs.addActionListener(au);

		gameMainMenu = new JMenuItem("Game MainMenu");
		file.add(gameMainMenu);

		event gmm = new event();
		gameMainMenu.addActionListener(gmm);

		newGame = new JMenuItem("New Game");
		file.add(newGame);

		event ng = new event();
		newGame.addActionListener(ng);

		exitGame = new JMenuItem("Exit Game");
		file.add(exitGame);

		event eg = new event();
		exitGame.addActionListener(eg);
		
		public class event implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			System.exit(0);
		}
	}

 */
	public MainMenu(JFrame frame, Kablewie kablewie) {

		this.frame = frame;
		this.kablewie=kablewie;
		display();

		frame.validate();
		frame.repaint();
	}

	

	public void display() {

		frame.setResizable(false);
		frame.setSize(640, 480);
		frame.setTitle("Kablewie");
		frame.setForeground(Color.RED);
		frame.setBackground(Color.RED);
		frame.getContentPane().setBackground(new Color(153, 180, 209));
		frame.getContentPane().setLayout(null);

		JPanel gamePanel = new JPanel();
		gamePanel.setBackground(SystemColor.inactiveCaption);
		gamePanel.setBorder(
				new TitledBorder(null, "Kablewie Status", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		gamePanel.setBounds(100, 25, 440, 400);
		frame.getContentPane().add(gamePanel);
		gamePanel.setLayout(null);

		JLabel userName = new JLabel("User Name:");
		userName.setBounds(10, 25, 100, 25);
		gamePanel.add(userName);

		userNameText = new JTextField();
		userNameText.setBounds(110, 25, 120, 25);
		gamePanel.add(userNameText);
		userNameText.setColumns(10);

		JLabel boardSize = new JLabel("Board Size:");
		boardSize.setBounds(10, 75, 100, 25);
		gamePanel.add(boardSize);

		boardSizeText = new JTextField();
		boardSizeText.setBounds(110, 75, 120, 25);
		gamePanel.add(boardSizeText);
		boardSizeText.setColumns(10);

		JLabel totalMines = new JLabel("Total Mines:");
		totalMines.setBounds(10, 175, 100, 25);
		gamePanel.add(totalMines);

		totalMinesText = new JTextField();
		totalMinesText.setBounds(110, 175, 120, 25);
		gamePanel.add(totalMinesText);
		totalMinesText.setColumns(10);

		JButton startGame = new JButton("Start Game");
		startGame.setIcon(null);
		startGame.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 11));
		startGame.setForeground(Color.BLUE);
		startGame.setBounds(25, 250, 200, 25);
		gamePanel.add(startGame);

		startGame.addMouseListener(this);

		// JCheckBox tickBox = new JCheckBox("Tick box to Agree");
		// tickBox.setBounds(25, 250, 200, 25);
		// gamePanel.add(tickBox);
	}

	public void mouseClicked(MouseEvent arg0) {

		String username = userNameText.getText();
		int boardSize = Integer.parseInt(boardSizeText.getText());
		int numMines = Integer.parseInt(totalMinesText.getText());

		Board board = new Board(boardSize, boardSize, numMines);
		Player player = new Human(username);

		kablewie.startGame(board, player);
	
		
	}

	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}