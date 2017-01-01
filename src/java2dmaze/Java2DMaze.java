package java2dmaze;

// java imports
import java.awt.*;
import java.util.Formatter;
import javax.swing.*;

// local imports
import MazeGen.MazeGenType;


public class Java2DMaze extends JPanel{
	
	private Maze mazeObj; 
	private int mWidth, mHeight, wallSize;
	
	
	// constructor
	public Java2DMaze(){
		
		this.mWidth = Params.MAZE_CELL_COLS;
		this.mHeight = Params.MAZE_CELL_ROWS;
		this.wallSize = Params.MAZE_WALL_SIZE;
		
		this.setPreferredSize(new Dimension((this.mWidth*this.wallSize) + Params.MAZE_FRAME_LEFT_OFFSET + this.wallSize,
											(this.mHeight*this.wallSize) + Params.MAZE_FRAME_TOP_OFFSET + this.wallSize));
		
		
		// maze generation
		
		this.mazeObj = new Maze(Params.MAZE_CELL_ROWS,
		   						Params.MAZE_CELL_COLS,
		   						Params.MAZE_WALL_SIZE,
		   						MazeGenType.MGENTYPE_RANDOM
						);
		this.mazeObj.generateMaze();
		
	}
	
	
	@Override
	public void paintComponent(Graphics g) {
      super.paintComponent(g);    // Paint background
      
      g.setColor(Color.BLACK); // draw the box
      
      g.drawRect(Params.MAZE_FRAME_LEFT_OFFSET,
			   Params.MAZE_FRAME_TOP_OFFSET, 
			   this.mWidth * this.wallSize,
			   this.mHeight * this.wallSize
			   );		      
      
      this.mazeObj.drawMaze(g);
  
    }
	
	
	
	
	public static void main(String[] args) {
		
		System.out.println("Starting 2D Maze");
		
		// Run GUI in the Event Dispatcher Thread (EDT) instead of main thread.
	      javax.swing.SwingUtilities.invokeLater(new Runnable() {
	         public void run() {
	        	 System.out.println("javax.swing.SwingUtilities.invokeLater: run():Enter");
	        	 
	            // Set up main window (using Swing's Jframe)
	            JFrame frame = new JFrame("Java Maze Demo");
	            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	            frame.setContentPane(new Java2DMaze());
	            		
	            		
	            frame.pack();
	            frame.setVisible(true);
	         }
	      });
		
		

	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
