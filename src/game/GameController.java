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

	private MainMenu m_menu;
	private Player m_player;
	private Board m_board;
	private boolean m_gamePlaying = true;
	private JFrame m_frame;
	private Human m_humanPlayer;
	private JPanel m_panelGame;
	private JPanel m_panelInfo;
	private JButton m_GameFinsh;
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

	public GameController(Board board, Player player, JFrame frame, MainMenu menu) {
		this.m_board = board;
		this.m_player = player;
		m_time = new Timer(1000, this);
		this.m_frame = frame;
		this.m_menu = menu;
		setInfo();
		startGame();
		m_time.start();
	}

	private void setInfo() {
		m_panelInfo = new JPanel() {
			@Override
			public void paintComponent(Graphics g) {
				super.paintComponent(g);
				m_board.renderInfo(g, m_player, m_timePassed);
			}
		};
		m_panelInfo.setBounds(0, 0, 640, 50);
		m_frame.getContentPane().add(m_panelInfo);
		m_panelInfo.setLayout(null);
		m_GameFinsh = new JButton();
		m_GameFinsh.setVisible(false);
		m_GameFinsh.setBounds(130, 12, 38, 38);
		m_panelInfo.add(m_GameFinsh);
		m_GameFinsh.addMouseListener(this);
		m_frame.validate();
		m_frame.repaint();
		m_panelInfo.repaint();

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
		m_panelGame = new JPanel() {
			@Override
			public void paintComponent(Graphics g) {
				super.paintComponent(g);
				m_board.render(g);
			}
		};
		m_panelGame.setBounds(0, 50, m_frame.getWidth(), m_frame.getHeight());
		m_panelGame.addMouseListener(this);

		m_frame.getContentPane().add(m_panelGame);
		m_frame.setJMenuBar(mymenu());
		try {
			m_humanPlayer = (Human) m_player;
		} catch (ClassCastException e) {
			// Player was not human.
		}
		m_frame.validate();
		m_frame.repaint();

		m_panelGame.repaint();
		m_player.takeTurn();
		m_gamePlaying = false;
	}

	public void mouseClicked(MouseEvent e) {
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
		if (m_board.getm_GameLost()) {
			m_time.stop();
			setm_GameLost();
			if (e.getSource() == m_GameFinsh) {
				reset();
			}
		}
		if(m_board.getm_GameWon()) {
			m_time.stop();
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
		JMenu game = new JMenu("Game");
		m_newGame = new JMenuItem("New Game");
		m_newGame.addActionListener(this);
		m_settings = new JMenuItem("Settings");
		m_settings.addActionListener(this);
		m_exit = new JMenuItem("Exit");
		m_exit.addActionListener(this);
		game.add(m_newGame);
		game.add(m_settings);
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
			m_timePassed = m_hoursPlayed + " : " + m_minuntesPlayed + " : " + m_secoundPlayed;
		} else if (event.getSource() == m_newGame) {
			reset();
		} else if (event.getSource() == m_settings) {
			m_frame.getContentPane().removeAll();
			m_menu.display();
		} else if (event.getSource() == m_exit) {
			System.exit(0);
		} else if (event.getSource() == m_about) {
			String author="Author: Software Engineering Group 14\n";
			author+="Date created : 06/12/2015 \n";
			author+="Version : 1.0\n";
			author+="The game was part of an assignment and is based on the famous game Minesweeper";
			JOptionPane.showMessageDialog(m_about, author, "About", JOptionPane.PLAIN_MESSAGE);
		} else if (event.getSource() == m_instructions) {
			JOptionPane.showMessageDialog(m_instructions, getInformation(), "About", JOptionPane.PLAIN_MESSAGE);

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
		m_board.reset();
		m_GameFinsh.setVisible(false);
		m_panelGame.repaint();
		m_panelInfo.repaint();
		m_frame.repaint();
		m_secoundPlayed = 0;
		m_minuntesPlayed = 0;
		m_hoursPlayed = 0;
		m_timePassed = null;
		m_time.start();
	}

}
