/**
 * @file GameController.java
 * @author Isabel Jenkins
 * @date 13 March 2016
 *
 * Controls the flow of the game takes the
 * click of the user and passes the position
 * of the clicked tile to the Board
 */

package game;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

import main.MainMenu;

/* 
 * Suppress serial ID warning as ID would not
 * match coding conventions.
 */
@SuppressWarnings("serial")
public class GameController implements MouseListener, ActionListener {

	private static boolean m_test = false;
	
	private MainMenu m_menu;
	private Player m_player;
	private Board m_board;
	private Human m_humanPlayer;

	private JFrame m_frame;
	private JPanel m_panelGame;
	private JPanel m_panelInfo;
	private JButton m_GameFinshed;
	private JButton m_Computer;
	private Computer m_computer;

	private Timer m_time;
	private long m_hoursPlayed;
	private long m_minuntesPlayed;
	private long m_secoundPlayed;
	private String m_timePassed;

	private JMenuItem m_newGame;
	private JMenuItem m_settings;
	private JMenuItem m_exit;
	private JMenuItem m_about;
	private JMenuItem m_instructions;
	private JMenuItem m_computerAI;
	private JMenuItem m_computerRandom;
	private JMenuItem m_showHideMines;
	private JMenuItem m_saveGame;

	private Clip m_tick;
	private Clip m_bomb;
	private Clip m_won;
	
	private Boolean m_gameFinshed;
	private Boolean m_isComputerAI;
	private Boolean m_computerIsPressed;
	
	private boolean m_minesRevealed; //toggle mines being shown

	public static void main(String[] args) {
		boolean test = true;
		m_test = true;
		
	    if (test) {
	    	System.out.println("GameController::main() BEGIN");
	    }
	    
	    JFrame testFrame = new JFrame("Kablewie");
	    
	    System.out.println("Test Constructor");
	    GameController gc = new GameController(null, new Human("TEST"), testFrame, null);
	    
	    // Test methods that return values.
	    System.out.println("Test getting JMenuBar");
		JMenuBar menu = gc.myMenu();
		if (menu != null) {
			System.out.println("Success in creating menu bar");
		} else  {
			System.out.println("Failed to create menu bar");
		}
		
		System.out.println("Test getting Instructions");
		String instructions = gc.getInstructions();
		if (instructions != null && instructions.length() > 0) {
			System.out.println("Success in getting instructions");
		} else {
			System.out.println("Failed to get instructions");
		}
		
		System.out.println("Test getting JMenuBar");
		
	    if (test) {
	       System.out.println("GameController::main() END");
	    }
	}
	
	/**
	 * Constructor
	 * 
	 * @param board a Board object for containing the tiles.
	 * @param player a Player object
	 * @param frame a JFrame to add the JPanel to
	 * @param menu the mainmenu so it
	 */
	public GameController(Board board, Player player, 
							JFrame frame, MainMenu menu) {
		// Set Class variables
		this.m_board = board;
		this.m_player = player;
		this.m_frame = frame;
		this.m_menu = menu;
		m_gameFinshed=false;
		setInfo();
		startGame();
		setSound();
		m_time = new Timer(1000, this);
		m_computer = new Computer(player.getUsername());
		m_time.start();
		m_tick.loop(Clip.LOOP_CONTINUOUSLY);
		m_isComputerAI=true;
		m_computerIsPressed=false;
		
		m_minesRevealed = false; //hidden set to false initially
	}

	/**
	 * Display game UI info
	 */
	private void setInfo() {
		m_panelInfo = new JPanel() {
			@Override
			public void paintComponent(Graphics g) {
				super.paintComponent(g);
				m_board.renderInfo(g, m_player, m_timePassed);
			}
		};

		m_panelInfo.setBounds(0, 0, m_frame.getWidth(), 50);
		m_frame.getContentPane().add(m_panelInfo);
		m_panelInfo.setLayout(null);

		m_GameFinshed = new JButton();
		m_GameFinshed.setVisible(false);
		m_GameFinshed.setBounds(130, 12, 38, 38);

		m_panelInfo.add(m_GameFinshed);
		
		m_Computer= new JButton("Computer");
		m_Computer.setBounds(325, 12, 100, 30);
		m_Computer.addMouseListener(this);
		m_panelInfo.add(m_Computer);
		m_GameFinshed.addActionListener(this);

		m_panelInfo.repaint();
		m_frame.validate();
		m_frame.repaint();
	}

	/**
	 * Set the sounds up
	 */
	public void setSound() {
		try {
			AudioInputStream audioInputStream = 
					AudioSystem.getAudioInputStream(
							new File("sound/tick.wav").getAbsoluteFile()
					);
			m_tick = AudioSystem.getClip();
			m_tick.open(audioInputStream);
			
			audioInputStream =
					AudioSystem.getAudioInputStream(
							new File("sound/bomb.wav").getAbsoluteFile()
					);
			m_bomb = AudioSystem.getClip();
			m_bomb.open(audioInputStream);
			
			audioInputStream =
					AudioSystem.getAudioInputStream(
							new File("sound/won.wav").getAbsoluteFile()
					);
			m_won = AudioSystem.getClip();
			m_won.open(audioInputStream);
			
		} catch (Exception x) {
		}
	}

	/**
	 * Show the animation when game is lost
	 */
	public void setGameLost() {
		m_gameFinshed=true;
		m_bomb.loop(1);
		m_time.stop();
		m_tick.stop();
		m_GameFinshed.setVisible(true);
		m_GameFinshed.setIcon(new ImageIcon("images/gameLost.jpg"));
	}

	/**
	 * Show the animation when game is won
	 */
	public void setGameWin() {
		m_gameFinshed=true;
		m_won.loop(1);
		m_time.stop();
		m_tick.stop();
		m_GameFinshed.setVisible(true);
		m_GameFinshed.setIcon(new ImageIcon("images/GameWon.jpg"));
		
		String v = "You Have won\n time taken- " + m_timePassed;
		JOptionPane.showMessageDialog(m_frame, v, "Congratulation", JOptionPane.YES_NO_CANCEL_OPTION);
	}

	/**
	 * Called when the game starts. Loads the JPanel
	 * and starts the players turn.
	 */

	private void startGame() {
		
		m_panelGame = new JPanel() {
			@Override
			public void paintComponent(Graphics g) {
				super.paintComponent(g);
				m_board.render(g);
			}
		};

		m_panelGame.addMouseListener(this);
		m_panelGame.setBounds(0, 50, m_frame.getWidth(), m_frame.getHeight());
		m_frame.getContentPane().add(m_panelGame);
		m_frame.setJMenuBar(myMenu());
		
		try {
			m_humanPlayer = (Human) m_player;
		} catch (ClassCastException e) {
			// Player was not human.
		}
		
		m_frame.validate();
		m_frame.repaint();

		m_panelGame.repaint();
		
		if (!m_test) {
			m_humanPlayer.takeTurn();
		}
	}

	/**
	 * Called on mouse event
	 */
	public void mouseClicked(MouseEvent e) {
		if(m_gameFinshed) {
			return;
		}
		if(e.getSource() == m_panelGame) {
			if (!(m_board.getm_GameLost())) {
				
				if (e.getButton() == MouseEvent.BUTTON1) {
					
					m_board.revealTile(e.getX(), e.getY());
					m_panelGame.repaint();
					m_panelInfo.repaint();
				} else if (e.getButton() == MouseEvent.BUTTON3) {
					
					m_board.defusedTile(e.getX(), e.getY());
					m_panelGame.repaint();
					m_panelInfo.repaint();
				}
			}
		} else if(e.getSource() == m_Computer){
			m_computerIsPressed = !m_computerIsPressed;
			m_computer.setComputerTurn(m_computerIsPressed);
			if(m_computerIsPressed) {
				m_computer.start(m_board);
			}
		}
		checkWonOrLoss();
	}

	public void mouseEntered(MouseEvent e) {
	}

	public void mouseExited(MouseEvent e) {
	}

	public void mousePressed(MouseEvent e) {
	}

	public void mouseReleased(MouseEvent arg0) {
	}
	
	public void checkWonOrLoss(){
		if (m_board.getm_GameLost()) {
			setGameLost();
		}
		if (m_board.getm_GameWon()) {
			setGameWin();
		}
	}
	
	/**
	 * Builds a JMenuBar with options
	 * 
	 * @return - a JMenuBar Object
	 */
	private JMenuBar myMenu() {
		JMenuBar menu = new JMenuBar();
		JMenu game = new JMenu("Game");
		
		m_newGame = new JMenuItem("New Game");
		m_newGame.addActionListener(this);
		m_settings = new JMenuItem("Settings");
		m_settings.addActionListener(this);
		m_exit = new JMenuItem("Exit");
		m_exit.addActionListener(this);

		JMenu computer=new JMenu("Computer");
		m_computerAI=new JMenuItem("Computer AI");
		m_computerAI.addActionListener(this);
		m_computerRandom=new JMenuItem("Computer Random");
		m_computerRandom.addActionListener(this);
		computer.add(m_computerAI);
		computer.add(m_computerRandom);
		m_showHideMines = new JMenuItem("Show/Hide Mines");
		m_showHideMines.addActionListener(this);
		m_saveGame = new JMenuItem("Save Game");
		m_saveGame.addActionListener(this);
		
		game.add(m_newGame);
		game.add(m_settings);
		game.add(computer);
		game.add(m_showHideMines);
		game.add(m_saveGame);
		game.add(m_exit);
		
		JMenu help = new JMenu("Help");
		
		m_about = new JMenuItem("About");
		m_about.addActionListener(this);
		m_instructions = new JMenuItem("Instructions");
		m_instructions.addActionListener(this);
		
		help.add(m_about);
		help.add(m_instructions);
		menu.add(game);
		menu.add(help);
		
		return menu;
	}

	/**
	 * Called by Time or JMenuBar
	 * 
	 * @param event an ActionEvent describing what happened
	 */
	public void actionPerformed(ActionEvent event) {
		if (event.getSource() == m_time) {
			m_panelInfo.repaint();
			m_secoundPlayed += m_time.getDelay() / 1000;
			
			if (m_secoundPlayed >= 60) {
				
				m_minuntesPlayed = m_minuntesPlayed + 1;
				m_secoundPlayed = 0;
				
				if (m_minuntesPlayed >= 60) {
					m_hoursPlayed = m_hoursPlayed + 1;
					m_minuntesPlayed = 0;
				}
			}
			
			m_timePassed = m_hoursPlayed 
							+ " : " 
							+ m_minuntesPlayed 
							+ " : " 
							+ m_secoundPlayed;
			
			if(m_computerIsPressed){
				if(m_isComputerAI){
				m_computer.computerAI(m_board);
				m_panelGame.repaint();
					m_panelInfo.repaint();
				}
				else{
					m_computer.computerPlaysRandom(m_board);
					m_panelGame.repaint();
					m_panelInfo.repaint();
				}
				checkWonOrLoss();
			}
			
		} else if (event.getSource() == m_newGame) {
			reset();
		} else if (event.getSource() == m_settings) {
			m_frame.getContentPane().removeAll();
			m_frame.getJMenuBar().setVisible(false);
			m_tick.close();
			m_won.close();
			m_bomb.close();
			m_menu.display();
		}else if (event.getSource() == m_computerAI) { 
			m_isComputerAI=true;
		}else if (event.getSource() == m_computerRandom) {
			m_isComputerAI=false;
		}else if (event.getSource() == m_exit) {
			System.exit(0);
		} else if(event.getSource() == m_showHideMines){
			
			m_board.toggleMines(m_minesRevealed);
			m_panelGame.repaint();
			setMinesRevealed(!m_minesRevealed);
		
		} else if(event.getSource() == m_saveGame){
			
			SaveGame saveGame = new SaveGame();
			saveGame.saveGame(m_board, m_player);
			
		} else if (event.getSource() == m_about) {
			
			String author = "Author: Software Engineering Group 14\n";
			author += "Date created : 06/12/2015 \n";
			author += "Version : 1.0\n";
			author += "The game was part of an assignment and ";
			author += "is based on the famous game Minesweeper";
			JOptionPane.showMessageDialog(m_about,
											author,
											"About",
											JOptionPane.PLAIN_MESSAGE);
			
		} else if (event.getSource() == m_instructions) {
			
			JOptionPane.showMessageDialog(m_instructions,
											getInstructions(),
											"About",
											JOptionPane.PLAIN_MESSAGE);

		} else if (event.getSource() == m_GameFinshed) {
			reset();
		} 
	}

	/**
	 * Builds a String of the instructions
	 * 
	 * @return a String of the instructions
	 */
	private String getInstructions() {
		return "Information:\n" 
				+ "The goal of the game is to defuse all the mines on the "
				+ "board without revealing\n " 
				+ "a mine, if a mine is revealed by the player then the game "
				+ "will be over and the player will deemed to " 
				+ "have lost the game.\n" 
				+ "How to play:\n"
				+ "The user can left click to reveal a tile on the board.\n"
				+ "If the user wishes to defuse a tile then the user would " 
				+ "right click in\n"
				+ "order to place a flag on the board and defuse a possible "
				+ "mine. If the flag is placed on a tile\n" 
				+ "that is deemed to be a mine then the number of mines "
				+ "defused is increased.\n";
	}

	/**
	 * resets the game so it can be replayed
	 */
	private void reset() {
		m_board.reset();
		m_gameFinshed=false;
		m_GameFinshed.setVisible(false);
		m_panelGame.repaint();
		m_panelInfo.repaint();
		m_frame.repaint();
		m_secoundPlayed = 0;
		m_minuntesPlayed = 0;
		m_hoursPlayed = 0;
		m_timePassed = null;
		m_computer.resetAI();
		m_isComputerAI=true;
		m_computerIsPressed=false;
		m_won.stop();
		m_won.flush();
		m_won.setFramePosition(0);
		m_bomb.stop();
		m_bomb.flush();
		m_bomb.setFramePosition(0);
		m_time.start();
		m_tick.loop(Clip.LOOP_CONTINUOUSLY);
		setMinesRevealed(false);
	}
	
	public void setMinesRevealed(boolean tf){
		m_minesRevealed = tf;
	}

}
