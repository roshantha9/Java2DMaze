package MazeGen;

import java.util.*;
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
	private int genType;
	
	private ArrayList<Integer> visited;
	
	
	public MazeGen(Graph MG, Map<Integer, Cell> cells, int h, int w, int gt){
		this.MG = MG;
		this.height = h;
		this.width = w;
		this.cells = cells;
		this.genType = gt;
		
		this.visited = new ArrayList<Integer>(); // visited cells by the maze generator (default: all false)
	}
	
	
	
	//=========================================
	// Maze generator - Handler
	//=========================================
	/*
	 * returns the generated walls as graph object
	 */
	public Graph getGeneratedWalls() {		
		
		switch (this.genType) {
		
			case MazeGenType.MGENTYPE_ALLWALLS:
				this.genAllWalls(); // generate walls
				break;
			
			case MazeGenType.MGENTYPE_RANDOM:
				this.genRandomWalls();
				break;
				
			default: 
				throw new IllegalArgumentException("Error - invalid wall generation type - " + this.genType);

		}
			
		
		return this.MG;
	}
	
	
	//=========================================
	// Maze generator - all walls
	//=========================================
	public void genAllWalls(){		
		
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
		
		//System.out.println("Num Edges =" + this.MG.getE());
		
	}
	
	
	
	//=========================================
	// Maze generator - Random
	//=========================================
	public void genRandomWalls(){
		// make all walls first
		this.genAllWalls();
		boolean[] vis = new boolean[this.MG.getV()];
		
		int ci = 0;
		genRandomWallsRecur(vis, ci);
		
		/*
		for (int i=0;i<this.visited.size()-1;i++){
			this.MG.removeEdge(this.visited.get(i), this.visited.get(i+1));
		}
		*/
		
		System.out.println("done ---");
				
	}
	
	public void genRandomWallsRecur(boolean[] visited, int ci){
		System.out.println(ci);

		// add current cell		
		visited[ci]=true; 
		this.visited.add(ci);
		
		// get valid neighbours
		ArrayList<Integer> neighbours = this.MG.getAdj().get(ci);
		
		Random randomGenerator = new Random();
		
		
		// while there are neighbours not visited
		while(!this.visited.containsAll(neighbours)){ 			
			
			while(true){
				
				// get random neighbour
				int nix = randomGenerator.nextInt(this.MG.getAdj().get(ci).size());
				int randNeighbour = this.MG.getAdj().get(ci).get(nix);

				if(!visited[randNeighbour]){
					this.MG.removeEdge(ci, randNeighbour);
					genRandomWallsRecur(visited, randNeighbour);
					break;
				}
			}
		}
			
		
		
		
	}
	
	
	
	
	
	//=========================================
	// utils
	//=========================================
	
	
}
