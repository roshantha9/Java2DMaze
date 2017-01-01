package java2dmaze;

//import java.awt.Point;

// local imports
import Graph.*;
import MazeGen.*;

// java imports
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Line2D;
import java.util.*;

import javax.swing.*;    // Using Swing's components and containers


/**
* <h1>Maze</h1>
* Maze class implements a rectangular maze using undirected graph 
* as representation* 
* <p>
*
* @author  Rosh Mendis
* @version 1.0
* @since   24/12/16
*/

public class Maze{
	
	private Graph MG;
	private int height;
	private int width;
	private int wallSize;
	private int numWalls;
	private Map<Integer, Cell> cells;
	private MazeGen mazeGenEngine;
	private Graphics2D mzGraphics;
	
	
	
	public Maze(int h, int w, int wallSize, int mgenType){
		
		
		this.height = h; // number of cell rows 
		this.width = w; // number of cell columns		
		this.wallSize = wallSize; // dimention of a cell (assume a square cell)
		this.MG = new Graph(h*w); // graph representation of the maze		
		
		this.cells = new HashMap<Integer, Cell>();
		this.initCells();
		this.printCells();
		
		this.mazeGenEngine = new MazeGen(this.MG, this.cells, h, w, mgenType);
		
		// initialise maze drawing canvas
		//this.initMazeDraw();
		
	}
	
	
	
	//================================================
	// Getters/setters 
	//================================================
	public int getHeight(){
		return this.height;
	}
	public int getWidth(){
		return this.width;
	}
	public int getNumWalls(){
		return this.numWalls;
	}
	public Graph getMG(){
		return this.MG;		
	}
	
	
	//================================================
	// Maze drawing functions
	//================================================
	/*
	 * initialise the maze drawing canvas/frame
	 */
	private void initMazeDraw(){
		/*
		// size of the java frame
		setSize((this.width*this.wallSize) + Params.MAZE_FRAME_LEFT_OFFSET + this.wallSize,
				(this.height*this.wallSize) + Params.MAZE_FRAME_TOP_OFFSET + this.wallSize
				); 
		setVisible(true); 
		
		//Now, we want to be sure we properly dispose of resources
		//this frame is using when the window is closed.  We use
		//an anonymous inner class adapter for this.
		addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e){
				dispose(); System.exit(0);
			}
		});
		*/
	}
	
	/*	 
	 * gets invoked automatically
	 */
	/*
	public void paint (Graphics g) {
		System.out.println("paint: Enter");
		
		this.mzGraphics = (Graphics2D) g;
		
		this.mzGraphics.setColor(Color.black);
		//g.drawRect(0,0,this.height,this.width);
		this.mzGraphics.drawRect(Params.MAZE_FRAME_LEFT_OFFSET,
								   Params.MAZE_FRAME_TOP_OFFSET, 
								   this.width * this.wallSize,
								   this.height * this.wallSize
								   );		
	}
	*/
	
	/*
	 * Draw the walls, t
	 */
	private void drawWalls(Graphics g){
		Graphics2D g2d = (Graphics2D) g;
		
		g2d.setColor(Color.BLUE); // draw the box
		
		ArrayList<ArrayList<Integer>> edges = this.MG.getAdj();
		
		int alpha = Params.MAZE_FRAME_LEFT_OFFSET;
		int beta = Params.MAZE_FRAME_TOP_OFFSET;
		int w = Params.MAZE_WALL_SIZE;
		
		for (int i=0;i<edges.size();i++){
			int ci = i; // id of target cell
			for (int j=0;j<edges.get(i).size();j++){
				int cj = edges.get(i).get(j); // id of neighbour

				// draw wall based on direction
				int dir = this.cells.get(ci).neighbourDirection(cj);
				
				// get coords of target cell
				Point ciCords = MUtil.nid2xy(ci, this.width);
				
				switch(dir){
								
					case Direction.NORTH:
						//System.out.println("drawWalls - North -"+ci);						
						g2d.draw(new Line2D.Double(alpha+(ciCords.getX()*w), 
																beta+(ciCords.getY()*w), 
																(alpha+(ciCords.getX()*w))+w, 
																beta+(ciCords.getY()*w)
																));
						
					break;
				
					case Direction.EAST:
						//System.out.println("drawWalls - East -"+ci);
						g2d.draw(new Line2D.Double((alpha+(ciCords.getX()*w))+w,
																beta+(ciCords.getY()*w),
																(alpha+(ciCords.getX()*w))+w,
																(beta+(ciCords.getY()*w))+w
																));
					break;
					
					case Direction.WEST:
						//System.out.println("drawWalls - West -"+ci);
						g2d.draw(new Line2D.Double(alpha+(ciCords.getX()*w),
																beta+(ciCords.getY()*w),
																alpha+(ciCords.getX()*w),
																(beta+(ciCords.getY()*w))+w
																));
					break;
					
					case Direction.SOUTH:
						//System.out.println("drawWalls - South -"+ci);
						//System.out.println("here --1");
						g2d.draw(new Line2D.Double(alpha+(ciCords.getX()*w),
																(beta+(ciCords.getY()*w))+w,
																(alpha+(ciCords.getX()*w))+w,
																(beta+(ciCords.getY()*w))+w
																));
						//System.out.println("here --2");
					break;
					
					case Direction.UNKNOWN:
						new IllegalArgumentException("Error: invalid neighbour direction - "+ci+","+cj);
					break;
					
					default:
						break;
				
				}
				
				
				
				
			}
		}
		
	}
	
	
	
	
	
	//================================================
	// Functions related to Cells
	//================================================
	/*
	 * Initialise cell neighbours
	 */
	private void initCells(){
		
		for (int i=0;i<this.MG.getV();i++){
			Point c = MUtil.nid2xy(i, this.width); // x,y coordinates of the cell
			
			// left column
			if (c.getX() == 0){				
				
				if (c.getY() == 0){ // top row
					this.cells.put(i, new Cell(i, c, -1, -1, i+1, i+this.width)); // (n,w,e,s)					
				}else if (c.getY() == this.width-1){ // last row
					this.cells.put(i, new Cell(i, c, i-this.width, -1, i+1, -1)); // (n,w,e,s)					
				}else{ // others rows
					//Cell cc = 					
					this.cells.put(i, new Cell(i, c, i-this.width, -1, i+1, i+this.width)); // (n,w,e,s)
					//System.out.println(cc);
				}
			}
			
			// right column
			else if (c.getX() == this.width-1){

				if (c.getY() == 0){	// top row
					this.cells.put(i, new Cell(i, c, -1, i-1, -1, i+this.width)); // (n,w,e,s)					
				}else if (c.getY() == this.width-1){ // last row
					this.cells.put(i, new Cell(i, c, i-this.width, i-1, -1, -1)); // (n,w,e,s)					
				}else{ // others
					this.cells.put(i, new Cell(i, c, i-this.width, i-1, -1, i+this.width)); // (n,w,e,s)
				}
			}
			
			
			// other columns
			else{				
				
				if (c.getY() == 0){	// top row
					this.cells.put(i, new Cell(i, c, -1, i-1, i+1, i+this.width)); // (n,w,e,s)					
				}else if (c.getY() == this.width-1){ // last row
					this.cells.put(i, new Cell(i, c, i-this.width, i-1, i+1, -1)); // (n,w,e,s)					
				}else{ // others
					this.cells.put(i, new Cell(i, c, i-this.width, i-1, i+1, i+this.width)); // (n,w,e,s)
				}				
			}			
		}		
	}
	
	/*
	 * print cells
	 */
	private void printCells(){
		System.out.println("----");
		for (int i=0;i<this.MG.getV();i++){
			System.out.println(this.cells.get(i));
		}
		System.out.println("----");
	}
	
	
	//================================================
	// Functions related to Maze generation
	//================================================
	public void generateMaze(){
		this.MG = this.mazeGenEngine.getGeneratedWalls(); // get walls		
	}
	public void drawMaze(Graphics g){
		this.drawWalls(g); // draw the obtained walls
	}
	
	
	
}
