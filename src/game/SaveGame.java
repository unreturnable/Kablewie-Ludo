/**
 * @file GameController.java
 * @author Isabel Jenkins
 * @date 13 March 2016
 *
 * Manages Saving and loading games.
 */

package game;

import java.awt.Dimension;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import main.MainMenu;

public class SaveGame {
	private final int TILE = 0;
	private final int DEFUSED_TILE = 1;
	private final int MINE = 2;
	private final int DEFUSED_MINE = 3;
	private final int REVEALED = 4;
	
	private final int MAX_BOARD_SIZE = 30;
	private final int X_BUFFER = 50;
	private final int Y_BUFFER = 105;
	private final int X_MIN_MULTIPLY = 5;
	private final int Y_MIN_MULTIPLY = 5;
	private final int X_MIN_BUFFER = 100;

	/**
	 * Saves a game to file
	 * 
	 * @param board the board to be saved
	 * @param player the player to be saved
	 */
	public void saveGame(Board board, Player player) {
		String toOutput = "";

		// Add player name
		toOutput += player.getUsername();

		// Add width and height of board.
		toOutput += "," + board.getm_Columns();
		toOutput += "," + board.getm_Rows();

		ArrayList<ArrayList<Tile>> tiles = board.getm_Board();

		for (int i = 0; i < tiles.size(); ++i) {
			for (int j = 0; j < tiles.get(i).size(); ++j) {
				Tile tile = tiles.get(i).get(j);

				if (tile.isHidden()) {

					if (tile.isDefused()) {
						if (tile.isMine()) {
							toOutput += "," + DEFUSED_MINE;
						} else {
							toOutput += "," + DEFUSED_TILE;
						}
					} else {
						if (tile.isMine()) {
							toOutput += "," + MINE;
						} else {
							toOutput += "," + TILE;
						}
					}

				} else {
					toOutput += "," + REVEALED;
				}
			}
		}

		writeFile(toOutput);
	}

	/**
	 * Writes the save data to a file
	 * 
	 * @param saveData a String containing the data to be saved.
	 */
	private void writeFile(String saveData) {
		String PATH = "./"; // Current Directory
		String directoryName = PATH.concat("saves"); // Add the directory name
		String filename = getTimeStamp() + ".kpp"; // Create the File name.

		// Check to see if the directory already exists. And if not create it.
		File directory = new File(String.valueOf(directoryName));
		if (!directory.exists()) {
			directory.mkdir();
		}

		// Write output to file.
		File file = new File(directoryName + "/" + filename);
		try {
			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(saveData);
			bw.close();

			JOptionPane.showMessageDialog(null, "Saved game.");
		} catch (IOException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Failed to save game.");
		}
	}

	/**
	 * Gets the current timeStamp
	 * 
	 * @return a String of the timestamp
	 */
	private String getTimeStamp() {
		return new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss").format(new Date());
	}
	
	/**
	 * Loads a game from file
	 * 
	 * @param frame the current games frame
	 * @param menu a copy of the mainmenu object
	 */
	public void loadGame(JFrame frame, MainMenu menu) {
		JFileChooser chooser;

		chooser = new JFileChooser();
		chooser.setCurrentDirectory(new java.io.File("./saves/"));
		chooser.setDialogTitle("Select a level file");
		chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		
		// disable the "All files" option.
		chooser.setAcceptAllFileFilterUsed(false);
		
		if (chooser.showOpenDialog(frame) == JFileChooser.APPROVE_OPTION) {
			
			try {
				Scanner scanner = new Scanner(chooser.getSelectedFile(), "UTF-8" );
				String fileContent = scanner.next();
				scanner.close();
				
				processGameData(fileContent, frame, menu);
				
			} catch (FileNotFoundException e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(null, "Failed to load game.");
			} catch (Exception e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(null, "Failed to load game.");
			}
			
		} else {
			System.out.println("No Selection");
		}
	}

	/**
	 * Processes the data read from file so a new game can be started
	 * 
	 * @param gameData a String containing the game data
	 * @param frame the current games frame
	 * @param menu a copy of the mainmenu object
	 */
	public void processGameData(String gameData, JFrame frame, MainMenu menu) throws Exception {
		String[] parts = gameData.split(",");
		
		String username = parts[0];
		int width = Integer.parseInt(parts[1]);
		int height = Integer.parseInt(parts[2]);
		
		int count = 3;
		Board board = new Board(width, height, 0);
		int mineCount=0;
		for (int i=0; i<width; i++) {
			for (int q=0; q<height; q++) {
				
				Tile newTile = processTile(Integer.parseInt(parts[count]));
				if(newTile.isMine()){
					++mineCount;
				}
				board.setTile(newTile, q, i);
				count++;
			}
		}
		for (int i=0; i<width; i++) {
			for (int q=0; q<height; q++) {
				Tile newTile = board.getm_Board().get(i).get(q);
				if(!(newTile.isHidden())){
					Revealed reveal=(Revealed) newTile;
					reveal.setNearByMines(board.getm_Board(), i, q);
				}
			}
		}
		board.setm_MineCount(mineCount);
		frame.getContentPane().removeAll();
		frame.setSize(width * MAX_BOARD_SIZE + X_BUFFER+X_MIN_BUFFER,
				width * MAX_BOARD_SIZE + Y_BUFFER);
		frame.setMinimumSize(new Dimension(
				X_MIN_MULTIPLY * MAX_BOARD_SIZE + X_BUFFER + X_MIN_BUFFER,
				Y_MIN_MULTIPLY * MAX_BOARD_SIZE + Y_BUFFER));
		
		Human player = new Human(username);
		new GameController(board, player, frame, menu);
	}
	
	/**
	 * Processes the id from a tile and creates a tile from it
	 * 
	 * @param type the type of the tile represented by an interger ID
	 */
	public Tile processTile(int type) {
		Tile newTile;
		
		if (type == TILE) {
			newTile = new Hidden(false, true, false);
		} else if (type == DEFUSED_TILE) {
			newTile = new Hidden(false, true, true);
		} else if (type == DEFUSED_MINE) {
			newTile = new Hidden(true, true, true);
		} else if (type == MINE) {
			newTile = new Hidden(true, true, false);
		} else if (type == REVEALED) {
			newTile = new Revealed(false, false, false);
			
		} else {
			// Something went wrong
			return null;
		}
		
		return newTile;
	}
	
}
