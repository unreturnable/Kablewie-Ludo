package game;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

import main.MainMenu;

/**
 * 
 * @author Peter Jenkins
 *
 */

public class GameController implements MouseListener, ActionListener {

	private MainMenu menu;
	private Player player;
	private Board board;
	private boolean gamePlaying = true;
	private JFrame frame;
	private Human humanPlayer;
	private JPanel panelGame;
	private JPanel panelInfo;
	private JButton m_GameFinsh;
	private Timer time;
	private long hoursPlayed;
	private long minuntesPlayed;
	private long secoundPlayed;
	private String m_timePassed;

	JMenuItem newGame;
	JMenuItem settings;
	JMenuItem exit;
	JMenuItem about;
	JMenuItem instructions;

	public GameController(Board board, Player player, JFrame frame, MainMenu menu) {
		this.board = board;
		this.player = player;
		time = new Timer(1000, this);
		this.frame = frame;
		this.menu = menu;
		setInfo();
		startGame();
		time.start();
	}

	private void setInfo() {
		panelInfo = new JPanel() {
			@Override
			public void paintComponent(Graphics g) {
				super.paintComponent(g);
				board.renderInfo(g, player, m_timePassed);
			}
		};
		panelInfo.setBounds(0, 0, 640, 50);
		frame.getContentPane().add(panelInfo);
		panelInfo.setLayout(null);
		m_GameFinsh = new JButton();
		m_GameFinsh.setVisible(false);
		m_GameFinsh.setBounds(130, 12, 38, 38);
		panelInfo.add(m_GameFinsh);
		m_GameFinsh.addMouseListener(this);
		frame.validate();
		frame.repaint();
		panelInfo.repaint();

	}

	public void setm_GameLost() {
		m_GameFinsh.setVisible(true);
		m_GameFinsh.setIcon(new ImageIcon("gameLost.jpg"));
	}

	public void sesetm_GameWin() {
		m_GameFinsh.setVisible(true);
		m_GameFinsh.setIcon(new ImageIcon("GameWon.jpg"));
	}

	private void startGame() {
		panelGame = new JPanel() {
			@Override
			public void paintComponent(Graphics g) {
				super.paintComponent(g);
				board.render(g);
			}
		};
		panelGame.setBounds(0, 50, frame.getWidth(), frame.getHeight());
		panelGame.addMouseListener(this);

		frame.getContentPane().add(panelGame);
		frame.setJMenuBar(mymenu());
		try {
			humanPlayer = (Human) player;
		} catch (ClassCastException e) {
			// Player was not human.
		}
		frame.validate();
		frame.repaint();

		// Loop until the game is over
		// while (gamePlaying) {

		panelGame.repaint();
		player.takeTurn();
		gamePlaying = false;

		// }
	}

	public void mouseClicked(MouseEvent e) {
		if (!(board.getGameLost())) {
			if (e.getButton() == MouseEvent.BUTTON1) {
				board.revealTile(e.getX(), e.getY());
				panelGame.repaint();
				panelInfo.repaint();
			} else if (e.getButton() == MouseEvent.BUTTON3) {
				board.defusedTile(e.getX(), e.getY());
				panelGame.repaint();
				panelInfo.repaint();
			}
		}
		if (board.getGameLost()) {
			time.stop();
			setm_GameLost();
			if (e.getSource() == m_GameFinsh) {
				reset();
			}
		}
		if(board.getGameWon()) {
			time.stop();
			sesetm_GameWin();
			if (e.getSource() == m_GameFinsh) {
				reset();
			}
		}
	}

	public void mouseEntered(MouseEvent e) {
	}

	public void mouseExited(MouseEvent e) {
	}

	public void mousePressed(MouseEvent e) {
	}

	public void mouseReleased(MouseEvent arg0) {
	}

	private JMenuBar mymenu() {
		JMenuBar menu = new JMenuBar();
		JMenu game = new JMenu("game");
		newGame = new JMenuItem("New Game");
		newGame.addActionListener(this);
		settings = new JMenuItem("Settings");
		settings.addActionListener(this);
		exit = new JMenuItem("Exit");
		exit.addActionListener(this);
		game.add(newGame);
		game.add(settings);
		game.add(exit);
		JMenu help = new JMenu("Help");
		about = new JMenuItem("About");
		about.addActionListener(this);
		instructions = new JMenuItem("Instructions");
		instructions.addActionListener(this);
		help.add(about);
		help.add(instructions);
		menu.add(game);
		menu.add(help);
		return menu;

	}

	public void actionPerformed(ActionEvent event) {
		if (event.getSource() == time) {
			panelInfo.repaint();
			secoundPlayed += time.getDelay() / 1000;
			if (secoundPlayed >= 60) {
				minuntesPlayed = minuntesPlayed + 1;
				secoundPlayed = 0;
				if (minuntesPlayed >= 60) {
					hoursPlayed = hoursPlayed + 1;
					minuntesPlayed = 0;
				}
			}
			m_timePassed = hoursPlayed + " : " + minuntesPlayed + " : " + secoundPlayed;
		} else if (event.getSource() == newGame) {
			reset();
		} else if (event.getSource() == settings) {
			frame.getContentPane().removeAll();
			menu.display();
		} else if (event.getSource() == exit) {
			System.exit(0);
		} else if (event.getSource() == about) {
			String author="Author: Software Engineering Group 14\n";
			author+="Date created : 06/12/2015 \n";
			author+="Version : 1.0\n";
			author+="The game was part of an assignment and is based on the famous game Minesweeper";
			JOptionPane.showMessageDialog(about, author, "About", JOptionPane.PLAIN_MESSAGE);
		} else if (event.getSource() == instructions) {
			JOptionPane.showMessageDialog(about, getInformation(), "About", JOptionPane.PLAIN_MESSAGE);

		}
	}
	public String getInformation() {
		return "Information:\n"+
				"The goal of the game is to defuse all the mines on the board without revealing\n "+
				"a mine, if a mine is revealed by the player then the game will be over and the player will deemed to have lost the game.\n"+
				"How to play:\n"+
				"The user can left click to reveal a tile on the board.\n"
				+ "If the user wishes to defuse a tile then the user would right click in\n"
				+ "order to place a flag on the board and defuse a possible mine. If the flag is placed on a tile\n"
				+ "that is deemed to be a mine then the number of mines defused is increased.\n";
	}
	public void reset() {
		board.reset();
		m_GameFinsh.setVisible(false);
		panelGame.repaint();
		panelInfo.repaint();
		frame.repaint();
		secoundPlayed = 0;
		minuntesPlayed = 0;
		hoursPlayed = 0;
		m_timePassed = null;
		time.start();
	}

}
