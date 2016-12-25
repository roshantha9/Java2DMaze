package java2dmaze;

//import java.awt.Point;

// local imports
import Graph.*;


// java imports
import java.awt.*;
import java.awt.event.*;
import java.util.*;




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

public class Maze extends Frame{
	
	private Graph MG;
	private int height;
	private int width;
	private int wallSize;
	private int numWalls;
	private Map<Integer, Cell> cells;
	
	
	public Maze(int h, int w, int wallSize){
		
		super("Java 2D Maze");
		
		this.height = h; // number of cell rows 
		this.width = w; // number of cell columns		
		this.wallSize = wallSize; // dimention of a cell (assume a square cell)
		this.MG = new Graph(h*w); // graph representation of the maze		
		
		this.cells = new HashMap<Integer, Cell>();
		
		// initialise maze drawing canvas
		this.initMazeDraw();
		
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
		
	}
	
	/*	 
	 * gets invoked automatically
	 */
	public void paint (Graphics g) {
		System.out.println("paint: Enter");
		g.setColor(Color.black);
		//g.drawRect(0,0,this.height,this.width);
		g.drawRect(Params.MAZE_FRAME_LEFT_OFFSET,
				   Params.MAZE_FRAME_TOP_OFFSET, 
				   this.width * this.wallSize,
				   this.height * this.wallSize
				   );
		
	}
	
	
	//================================================
	// Functions related to Cells
	//================================================
	private void initCells(){
		
		for (int i=0;i<this.MG.getV();i++){
			Point c = MUtil.nid2xy(i, this.width); // x,y coordinates of the cell
			
			// top row
			if (c.getX() == 0){				
				// first column
				if (c.getY() == 0){
					this.cells.put(i, new Cell(-1, -1, i+1, i+this.width)); // (n,w,e,s)					
				}else if (c.getY() == this.width-1){ // last column
					this.cells.put(i, new Cell(-1, i-1, -1, i+this.width)); // (n,w,e,s)					
				}else{ // others
					this.cells.put(i, new Cell(-1, i-1, i+1, i+this.width)); // (n,w,e,s)
				}
			}
			
			// bottom row
			if (c.getX() == this.height-1){
				// first column
				if (c.getY() == 0){
					this.cells.put(i, new Cell(i-this.width, -1, i+1, -1)); // (n,w,e,s)					
				}else if (c.getY() == this.width-1){ // last column
					this.cells.put(i, new Cell(i-this.width, i-1, -1, -1)); // (n,w,e,s)					
				}else{ // others
					this.cells.put(i, new Cell(i-this.width, i-1, i+1, -1)); // (n,w,e,s)
				}
			}
		}
		
	}
	
	
	
	
}
