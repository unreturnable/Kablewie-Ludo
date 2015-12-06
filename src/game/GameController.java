package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

/**
 * 
 * @author Peter Jenkins
 *
 */

public class GameController implements MouseListener{

	private Player player;
	private Board board;
	private long gameStartTime = 0;
	private boolean gamePlaying = true;
	private JFrame frame;
	Human humanPlayer;
	JPanel panel;

	public GameController(Board board, Player player, JFrame frame) {
		this.board = board;
		this.player = player;

		this.frame = frame;

		startGame();
	}

	private void startGame() {
		gameStartTime = System.currentTimeMillis();

		 panel = new JPanel(){
			@Override
			public void paintComponent(Graphics g){
				super.paintComponent(g);
				board.render(g);
			}
		};
		panel.addMouseListener(this);
		panel.setBounds(0, 0, 640, 480);
		frame.getContentPane().add(panel);
		frame.setJMenuBar(mymenu());
		try {
			 humanPlayer = (Human) player;
		} catch(ClassCastException e) {
			// Player was not human.
		}
		frame.validate();
		frame.repaint();
		
		// Loop until the game is over
	//	while (gamePlaying) {

			panel.repaint();
			player.takeTurn();
			gamePlaying = false;
			
	//	}
	}
	public void mouseClicked(MouseEvent e) {
		
			if (e.getButton() == MouseEvent.BUTTON1) {
				board.revealTile(e.getX(), e.getY());
				panel.repaint();
				System.out.println("Turn over");
			} else if (e.getButton() == MouseEvent.BUTTON3) {
				board.defusedTile(e.getX(), e.getY());
				panel.repaint();
				System.out.println("Turn over");
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
		JMenuBar menu=new JMenuBar();
		JMenu game=new JMenu("game");
		JMenuItem newGame=new JMenuItem("New Game");
		JMenuItem customatio=new JMenuItem("New Game");
		JMenuItem exit=new JMenuItem("Exit");
		game.add(newGame);
		game.add(customatio);
		game.add(exit);
		JMenu help=new JMenu("Help");
		JMenuItem about=new JMenuItem("About");
		JMenuItem ins=new JMenuItem("Instrution");
		help.add(about);
		help.add(ins);
		menu.add(game);
		menu.add(help);
		return menu;
		
	}

}
