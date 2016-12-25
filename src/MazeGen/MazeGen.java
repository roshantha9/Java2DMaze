package MazeGen;

import java.util.HashMap;
import java.util.Map;
import java.awt.Point;

import Graph.*;
import java2dmaze.Cell;
import java2dmaze.MUtil;
import java2dmaze.MUtil.*;




public class MazeGen {
	
	private Graph MG;
	private int height;
	private int width;
	private Map<Integer, Cell> cells;
	
	public MazeGen(Graph MG, Map<Integer, Cell> cells, int h, int w){
		this.MG = MG;
		this.height = h;
		this.width = w;
		this.cells = cells;
	}
	
	
	
	//=========================================
	// Maze generator - all walls
	//=========================================
	public Graph genAllWalls(){		
		
		for (int i=0;i<this.MG.getV();i++){ // all nodes
			if (this.cells.get(i).north != -1){
				this.MG.addEdge(i, this.cells.get(i).north);
			}
			if (this.cells.get(i).west != -1){
				this.MG.addEdge(i, this.cells.get(i).west);
			}
			if (this.cells.get(i).south != -1){
				this.MG.addEdge(i, this.cells.get(i).south);
			}
			if (this.cells.get(i).east != -1){
				this.MG.addEdge(i, this.cells.get(i).east);
			}		
		}
		
		return this.MG;
	}
	
	
	
	
	//=========================================
	// utils
	//=========================================
	
	
}
