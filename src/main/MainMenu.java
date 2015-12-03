package main;

/**
 * 
 * @author Eromosele Gideon
 *
 */
import java.awt.event.*;
import java.awt.*;

import javax.swing.*;
import javax.swing.border.TitledBorder;

public class MainMenu extends JFrame {
	JFrame Kablewie;
	JMenuBar topMenuBar;
	JMenu file;
	JMenu about;
	JMenuItem aboutUs;
	JMenuItem gameMainMenu;
	JMenuItem newGame;
	JMenuItem exitGame;
	
	private static JTextField userNameText;
	private static JTextField boardHeightText;
	private static JTextField boardWidthText;
	private static JTextField totalMinesText;
	
	public MainMenu(){
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
		
		display();
	}
	
	public class event implements ActionListener{
		public void actionPerformed(ActionEvent e){
			System.exit(0);
		}
	}
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainMenu window = new MainMenu();
					window.Kablewie.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	
	public void display(){
		final JFrame main = this;
		Kablewie = new JFrame();
		//Kablewie.setTitle("Game Board Setting");
		Kablewie.setResizable(false);
		Kablewie.setSize(540, 380);
		Kablewie.setTitle("Kablewie");
		Kablewie.setForeground(Color.RED);
		Kablewie.setBackground(Color.RED);
		Kablewie.getContentPane().setBackground(new Color(153, 180, 209));
		Kablewie.getContentPane().setLayout(null);
		
		JPanel gamePanel = new JPanel();
		gamePanel.setBackground(SystemColor.inactiveCaption);
		gamePanel.setBorder(new TitledBorder(null, "Kablewie Status", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		gamePanel.setBounds(95, 27, 274, 240);
		Kablewie.getContentPane().add(gamePanel);
		gamePanel.setLayout(null);
		
		JLabel userName = new JLabel("User Name:");
		userName.setBounds(6, 16, 82, 26);
		gamePanel.add(userName);
		
		userNameText = new JTextField();
		userNameText.setBounds(98, 16, 110, 26);
		gamePanel.add(userNameText);
		userNameText.setColumns(10);
		
		JLabel boardHeight = new JLabel("Board Height:");
		boardHeight.setBounds(6, 59, 82, 26);
		gamePanel.add(boardHeight);
		
		boardHeightText = new JTextField();
		boardHeightText.setBounds(98, 59, 110, 26);
		gamePanel.add(boardHeightText);
		boardHeightText.setColumns(10);
		
		JLabel boardWidth = new JLabel("Board Width:");
		boardWidth.setBounds(6, 106, 82, 26);
		gamePanel.add(boardWidth);
		
		boardWidthText = new JTextField();
		boardWidthText.setBounds(98, 106, 110, 26);
		gamePanel.add(boardWidthText);
		boardWidthText.setColumns(10);
		
		JLabel totalMines = new JLabel("Total Mines:");
		totalMines.setBounds(6, 153, 82, 26);
		gamePanel.add(totalMines);
		
		totalMinesText = new JTextField();
		totalMinesText.setBounds(98, 153, 110, 26);
		gamePanel.add(totalMinesText);
		totalMinesText.setColumns(10);
		
		JButton startGame = new JButton(" Start Game");
		startGame.setIcon(null);
		startGame.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 11));
		startGame.setForeground(Color.BLUE);
		startGame.setBounds(259, 278, 110, 32);
		Kablewie.getContentPane().add(startGame);
		
		JCheckBox tickBox = new JCheckBox("Tick box to Agree");
		tickBox.setBounds(76, 200, 192, 23);
		gamePanel.add(tickBox);
	
	}


}