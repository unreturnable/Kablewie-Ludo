/**
 * @file Computer.java
 * @author Zongbo Xu
 * @date 5 December 2015
 *
 * A class for computer players.
 */

package game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import javax.swing.Timer;

public class Computer extends Player {

	private boolean m_computerTurn;

	public void setComputerTurn(boolean computerTurn) {
		m_computerTurn = computerTurn;
	}

	public boolean getComputerTurn() {
		return m_computerTurn;
	}

	public Computer(String name) {
		super(name);
	}

	public void computerPlaysRandom(Board board) {
		if (m_computerTurn) {
			int openR = 0;
			int openC = 0;
			do {
				Random rnd = new Random();
				openR = rnd.nextInt(board.getm_Rows());
				openC = rnd.nextInt(board.getm_Columns());
			} while (!(board.getm_Board().get(openC).get(openR).isHidden()));
			board.revealTile(openR * Tile.WIDTH, openC * Tile.HEIGHT);
			if (board.getm_GameLost()) {
				m_computerTurn = false;
			}

		}

	}

	/*
	 * AI for the computer class
	 * 
	 * 
	 */

	/*
	 * method 1 make a copy of the length of the board and then fill it the
	 * vaule in it can only be M for mine or S for Safe
	 */
	private LinkedList<Position> copy;
	public void start(Board board) {
		copy = new LinkedList<Position>();
		 ArrayList<ArrayList<Tile>> tiles = board.getm_Board();
		for (int i = 0; i < tiles.size(); ++i) {
			for (int j = 0; j < tiles.get(0).size(); ++j) {
				Position pos = new Position(i,j);
				if(!(tiles.get(i).get(j).m_isHidden)) {
					Revealed r = (Revealed) tiles.get(i).get(j);
					pos.mineCount=r.getm_NearByMines();
				}
				if(tiles.get(i).get(j).m_isDefused){
					pos.doefusedComputing="D";
				}
				copy.add(pos);
			}
		}
	}
	public void method1(Board board) {
		 ArrayList<ArrayList<Tile>> tiles = board.getm_Board();
		 int k=0;
		for (int i = 0; i < tiles.size(); ++i) {
			for (int j = 0; j < tiles.get(0).size(); ++j) {
				Position pos = copy.get(k);
				++k;
				if(!(tiles.get(i).get(j).m_isHidden)) {
					Revealed r = (Revealed) tiles.get(i).get(j);
					pos.mineCount=r.getm_NearByMines();
				}
				copy.add(pos);
			}
		}
	}
	/*
	 * method 2
	 */
	LinkedList<Maping> map;
	public boolean method2(ArrayList<ArrayList<Tile>> tiles) {
		boolean runCompleted=true;
		map=new LinkedList<Maping>();
		
		for(int i=0;i<copy.size();++i) {
			//getting a posiiton
			Position sPos = copy.get(i);
			LinkedList<Position> tileArround;
			//if mine count is not 0 then it mean that has been clicked
			if(!(sPos.mineCount==-1) && !(sPos.mineCount==0)) {
				//getting the tile arround it
				tileArround=getTileArround(sPos,tiles.size(),tiles.get(0).size());
				int bombArround=0;
				int properArround=0;
				Maping m = new Maping();
				int mapTarget=sPos.mineCount;
				
				for(Position po : tileArround){
					if(po.comp.equals("M")) 
					{
						++bombArround;
						--mapTarget;
					}
					else if(po.comp.equals("S")){
						//--mapTarget;
					}
					else if( po.mineCount == -1){
						m.add(po);
						++properArround;
					}
				}
				m.target=mapTarget;
				//here !(sPos.mineCount==0)
				if((m.pos.size() == sPos.mineCount && bombArround==0)){
					for(Position k : m.pos)
					k.comp="M";
					runCompleted=false;
				}
				//and here !(sPos.mineCount==0)
				else if((properArround+bombArround)==sPos.mineCount){
					
					for(Position k : m.pos)
					{
						if(!(k.comp.equals("M"))){
							k.comp="M";
							runCompleted=false;
						}
					}
				}
				else if(bombArround==sPos.mineCount){
					for(Position po : tileArround){
						if(po.comp.equals("M")) 
						{
						}
						else if(po.comp.equals("S")) 
						{
						}
						else if(po.mineCount==-1){
							po.comp="S";
							runCompleted=false;
						}
					}
				}
				else{
					map.add(m);
				}
			}
			
		}
		
		return runCompleted;
	}
	public LinkedList<Position> getTileArround(Position sPos,int mR,int mC) {
		int i=sPos.xPos;
		int j=sPos.yPos;
		
		int prevrow = i - 1;
		int prevrcol = j - 1;
		int nextrow = i + 1;
		int nextcol = j + 1;
		
		// it run a for loop all around the tile i,j
		LinkedList<Position> t2 = new LinkedList<Position>();
		
		for (int k = prevrow; k <= nextrow; ++k) {
			for (int m = prevrcol; m <= nextcol; ++m) {
				
				if (!(	k < 0 ||
						m < 0 || 
						k >= mR 
						|| m >= mC  ) && (!(k==i && m==j)))  {
					/*
					 * before adding it the condition makes sure that it is not
					 * out of bound of the board
					 */
					t2.add(copy.get(k*mR+m));
				}
			}
		}
		
		return t2;
	
	    
	    
	}
	/*
	 * method 3 mapping thing in the right order
	 */
	public boolean method3(){
		boolean methodComplete=true;
		for ( int i=0;i<map.size();++i){
			Maping m = map.get(i);
			for(int j =0;j < map.size();++j){
				if(i!=j){
					Maping m2 = map.get(j);
					if(m.pos.size()<m2.pos.size()){
						if(m.contain(m2)){
							methodComplete=false;
							
						};
					}
				}
			}
		}
		return methodComplete;
	}
	
	/*
	 * method 4
	 */
	public void method4(Board b) {
		for(int i=0;i<copy.size();++i){
			Position pos=copy.get(i);
			int x=pos.xPos;
			int y=pos.yPos;
			if(b.getm_Board().get(x).get(y).isDefused() && pos.comp.equals("S")){
				b.defusedTile(pos.yPos*Tile.WIDTH, pos.xPos*Tile.HEIGHT);
				b.revealTile(y*Tile.WIDTH, x*Tile.HEIGHT);
				pos.doneComputing="D";
				return ;
			}
			else if(!(b.getm_Board().get(x).get(y).isHidden())){
				pos.doneComputing="D";
				continue;
			}
			else if(pos.comp.equals("S") && pos.doneComputing.equals("null")){
				b.revealTile(y*Tile.WIDTH, x*Tile.HEIGHT);
				pos.doneComputing="D";
				return ;
			}
		}
		computerPlaysRandom(b);
	}
	public void method5(Board b) {
		for(int i=0;i<copy.size();++i){
			Position pos=copy.get(i);
			if(pos.comp.equals("M") && pos.doefusedComputing.equals("null")){
				b.defusedTile(pos.yPos*Tile.WIDTH, pos.xPos*Tile.HEIGHT);
				pos.doefusedComputing="D";
				return ;
			}
		}
		method4(b);
	}
	public void startComputerAI(Board b){
		start(b);
	}
	public void computerAI(Board b){

		while(!(method2(b.getm_Board())));
		while(!(method3())){
			while(!(method2(b.getm_Board()))){
				
			}
		}
		method5(b);
		method1(b);
	}
	public void resetAI(){
		copy.clear();
		map.clear();
	}
	public void testComputerAI(){
		/*
		Board b = new Board(5, 5, 6);
		ArrayList<ArrayList<Tile>> tiles = b.getm_Board();
		tiles.clear();
		for(int i=0;i<5;++i){
			tiles.add(new ArrayList<Tile>());
			for( int j=0;j<5;++j)
			{
				tiles.get(i).add(new Hidden(false, true, false));
			}
		}

		tiles.get(0).get(0).setTileType(true, true);
		tiles.get(1).get(3).setTileType(true, true);
		tiles.get(2).get(0).setTileType(true, true);
		tiles.get(2).get(1).setTileType(true, true);
		tiles.get(2).get(2).setTileType(true, true);
		tiles.get(4).get(2).setTileType(true, true);
		
		b.setBoard(tiles);
		b.revealTile(2* Tile.WIDTH, 0* Tile.HEIGHT);
		b.revealTile(3* Tile.WIDTH, 0* Tile.HEIGHT);
		b.revealTile(4* Tile.WIDTH, 0* Tile.HEIGHT);
		b.revealTile(4* Tile.WIDTH, 1* Tile.HEIGHT);
		b.revealTile(3* Tile.WIDTH, 2* Tile.HEIGHT);
		b.revealTile(4* Tile.WIDTH, 2* Tile.HEIGHT);
		b.revealTile(3* Tile.WIDTH, 3* Tile.HEIGHT);
		b.revealTile(4* Tile.WIDTH, 3* Tile.HEIGHT);
		b.revealTile(3* Tile.WIDTH, 4* Tile.HEIGHT);
		b.revealTile(4* Tile.WIDTH, 4* Tile.HEIGHT);
		b.revealTile(1* Tile.WIDTH, 3* Tile.HEIGHT);
		b.revealTile(1* Tile.WIDTH, 4* Tile.HEIGHT);
		
		method1(b);
		int j=0;
		for(int i=0;i<copy.size();++i)
		{
			Position test =copy.get(i);
			if( test.mineCount!=-1){
				System.out.print(test.mineCount+"\t");
			}
			else
			{
				System.out.print(test.comp+"\t");
			}
			if(i==4*j+4+j && i!=0){
				System.out.println();
				++j;
			}
		}
		while(!(method2(b.getm_Board())));
		while(!(method3())){
			while(!(method2(b.getm_Board())));
		}
		for(int i=0;i<map.size();++i){
			for(Position  op : map.get(i).pos ){
				System.out.print(op.xPos+" :--: "+op.yPos+"  ;  ");
			}
			System.out.println("   :---===:   " +map.get(i).target);
		}
		System.out.println("\n\n");
		j=0;
		for(int i=0;i<copy.size();++i)
		{
			Position test =copy.get(i);
			if( test.mineCount!=-1){
				System.out.print(test.mineCount+"\t");
			}
			else
			{
				System.out.print(test.comp+"\t");
			}
			if(i==4*j+4+j && i!=0){
				System.out.println();
				++j;
			}
		}
		*/
		int r=10;
		int c=10;
		Board b = new Board(r, c, 10);
		/*ArrayList<ArrayList<Tile>> tiles = b.getm_Board();
		tiles.clear();
		for(int i=0;i<6;++i){
			tiles.add(new ArrayList<Tile>());
			for( int j=0;j<6;++j)
			{
				tiles.get(i).add(new Hidden(false, true, false));
			}
		}

		tiles.get(3).get(2).setTileType(true, true);
		tiles.get(5).get(0).setTileType(true, true);
		tiles.get(3).get(3).setTileType(true, true);
		tiles.get(1).get(3).setTileType(true, true);
		
		b.setBoard(tiles);*/
		b.revealTile(1* Tile.WIDTH, 0* Tile.HEIGHT);
		b.revealTile(2* Tile.WIDTH, 0* Tile.HEIGHT);
		b.revealTile(1* Tile.WIDTH, 1* Tile.HEIGHT);
		b.revealTile(2* Tile.WIDTH, 1* Tile.HEIGHT);
		b.revealTile(1* Tile.WIDTH, 2* Tile.HEIGHT);
		b.revealTile(2* Tile.WIDTH, 2* Tile.HEIGHT);
		
		start(b);
		int j=0;
		j=0;
		for(int i=0;i<r;++i)
		{
			for(int k=0;k<c;++k)
		
			
		{
			Position test =copy.get(j);
			++j;
			if( test.mineCount!=-1){
				System.out.print(test.mineCount+"\t");
			}
			else
			{
				System.out.print(test.comp+"\t");
			}
		}System.out.println();}
		//-------------------------------------
		while(!(method2(b.getm_Board())));
		
		//method2(b.getm_Board());
		/*for(int i=0;i<map.size();++i){
			for(Position  op : map.get(i).pos ){
				System.out.print(op.xPos+" :--: "+op.yPos+"  ;  ");
			}
			System.out.println("   :---===:   " +map.get(i).target);
		}
		j=0;
		for(int i=0;i<3;++i)
		{
			for(int k=0;k<3;++k)
		
			
		{
			Position test =copy.get(j);
			++j;
			if( test.mineCount!=-1){
				System.out.print(test.mineCount+"\t");
			}
			else
			{
				System.out.print(test.comp+"\t");
			}
		}System.out.println();}*/
		
		
		//lkihi-------------------------------------
		while(!(method3())){
			while(!(method2(b.getm_Board()))){
				
			}
		}
		for(int i=0;i<map.size();++i){
			for(Position  op : map.get(i).pos ){
				System.out.print(op.xPos+" :--: "+op.yPos+"  ;  ");
			}
			System.out.println("   :---===:   " +map.get(i).target);
		}
		System.out.println("\n\n");
		
		j=0;
		for(int i=0;i<r;++i)
		{
			for(int k=0;k<c;++k)
		
			
		{
			Position test =copy.get(j);
			++j;
			if( test.mineCount!=-1){
				System.out.print(test.mineCount+"\t");
			}
			else
			{
				System.out.print(test.comp+"\t");
			}
		}System.out.println();}
		method4(b);
		method1(b);
		j=0;
		System.out.println();
		System.out.println();
		System.out.println();
		for(int i=0;i<r;++i)
		{
			for(int k=0;k<c;++k)
		
			
		{
			Position test =copy.get(j);
			++j;
			if( test.mineCount!=-1){
				System.out.print(test.mineCount+"\t");
			}
			else
			{
				System.out.print(test.comp+"\t");
			}
		}System.out.println();}

		while(!(method2(b.getm_Board())));
		while(!(method3())){
			while(!(method2(b.getm_Board()))){
				
			}
		}
		method4(b);
		method1(b);
		System.out.println();
		System.out.println();
		System.out.println();
		for(int i=0;i<r;++i)
		{
			for(int k=0;k<c;++k)
		
			
		{
			Position test =copy.get(j);
			++j;
			if( test.mineCount!=-1){
				System.out.print(test.mineCount+"\t");
			}
			else
			{
				System.out.print(test.comp+"\t");
			}
		}System.out.println();}

		
	}
	public static void main(String args[]) {
		//new Computer("testing").unitTest();
		new Computer("testing").testComputerAI();
	}

	public void unitTest() {
		Board b = new Board(10, 10, 10);
		System.out.print("\t");
		for (int i = 0; i < b.getm_Board().size(); ++i) {
			System.out.print(i + "\t");
		}
		System.out.println();

		for (int i = 0; i < b.getm_Board().size(); ++i) {
			System.out.print(i + "\t");
			for (int j = 0; j < b.getm_Board().get(0).size(); ++j) {

				System.out.print("*\t");

			}
			System.out.println();
		}

		m_computerTurn = true;
		while (m_computerTurn) {
			computerPlaysRandom(b);

			System.out.print("\t");
			for (int i = 0; i < b.getm_Board().size(); ++i) {
				System.out.print(i + "\t");
			}
			System.out.println();

			for (int i = 0; i < b.getm_Board().size(); ++i) {
				System.out.print(i + "\t");

				for (int j = 0; j < b.getm_Board().get(0).size(); ++j) {
					if (b.getm_Board().get(i).get(j).m_isHidden) {
						System.out.print("\t");
					} else {
						if (b.getm_Board().get(i).get(j).isMine())
							return;

						Revealed r = (Revealed) b.getm_Board().get(i).get(j);
						System.out.print(r.getm_NearByMines() + "\t");
					}
				}

				System.out.println();
			}
			try {
				Thread.sleep(1000 * 3);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}

class Position {
	public int xPos;
	public int yPos;
	public int mineCount;
	public String comp;
	public String doneComputing;
	public String doefusedComputing;

	public Position(int x, int y) {
		xPos = x;
		yPos = y;
		mineCount=-1;
		comp="null";
		doneComputing="null";
		doefusedComputing="null";
	}
	public boolean equal(Position p){
		return (xPos==p.xPos && yPos==p.yPos);
	}
}
class Maping {
	LinkedList<Position> pos;
	int target;
	public Maping(){
		pos=new LinkedList<Position>();
	}
	public void add(Position p){
		pos.add(p);
	}
	public boolean equal(Maping map){
		for( int i=0;i<pos.size();++i){
			int j=0;
			for( j=0;j<map.pos.size();++j){
				if(pos.get(i).equal(map.pos.get(j))){
					break;
				}
			}
			if(j==map.pos.size()){
				return false;
			}
		}
		return true;
	}
	public boolean contain(Maping map){
		LinkedList<Position> mapRemove=new LinkedList<Position>();
		int i=0;
		for(i=0;i<pos.size();++i){
			Position p = pos.get(i);
			boolean pointFound=false;
			for( int j=0;j<map.pos.size();++j) {
				Position p2 = map.pos.get(j);
				if(p.equal(p2)){
					pointFound=true;
					mapRemove.add(p2);
					break;
				}
			}
			if(!pointFound){
				return false;
			}
		}
		if(i==pos.size()){
			int bomb=0;

			bomb=map.pos.size()-mapRemove.size()+target;
			if(bomb == map.target){
				System.out.println("me");
				for( int j=0;j<map.pos.size();++j){
					int k=0;
					for( k=0;k<mapRemove.size();++k)
					{	
						if(map.pos.get(j) == mapRemove.get(k)){
							break;
						}
					}
					if(k==mapRemove.size()){
						map.pos.get(j).comp="M";
					}
				}
				return true;
			}
			else if(map.target==target){
				for( int j=0;j<map.pos.size();++j){
					
					int k=0;
					for( k=0;k<mapRemove.size();++k)
					{	
						if(map.pos.get(j) == mapRemove.get(k)){
							break;
						}
					}
					if(k==mapRemove.size()){
						map.pos.get(j).comp="S";
					}
					
				}
				return true;
			}
		}
		return false;
	}
}