
package main;

public class Kablewie {
	
	private String username;
	private int boardHeight;
	private int boardWidth;
	private int totalMines;
	

	public static void main(String[] args) {
		//I think this is where we want the main class?
		Kablewie kablewie = new Kablewie();
		

	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public int getBoardHeight() {
		return boardHeight;
	}


	public void setBoardHeight(int boardHeight) {
		this.boardHeight = boardHeight;
	}


	public int getBoardWidth() {
		return boardWidth;
	}


	public void setBoardWidth(int boardWidth) {
		this.boardWidth = boardWidth;
	}


	public int getTotalMines() {
		return totalMines;
	}


	public void setTotalMines(int totalMines) {
		this.totalMines = totalMines;
	}

}
