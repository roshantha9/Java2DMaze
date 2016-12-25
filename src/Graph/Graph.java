package Graph;

import java.util.*;

/**
* <h1>Graph</h1>
* Graph class implements undirected graphs using 
* adjacency lists
* <p>
*
* @author  Rosh Mendis
* @version 1.0
* @since   23/12/16
*/
public class Graph {
	
	private final int V; // number of vertices
	private int E;
	private ArrayList<ArrayList<Integer>> adj; // adjacency list to store connections
	
	
	/**
	 * Default constructor for Graph
	 * @param V : number of vertices
	 */
	public Graph(int V){
		this.V = V; 
		this.adj = new ArrayList<ArrayList<Integer>>(); // initialise
		this.E = 0;
		
		// add V number of vertices
		for(int i=0; i<V; i++){
            this.adj.add(new ArrayList<Integer>());
        }
	}
	
	
	
	//================================================
	// Getters/setters 
	//================================================
	public int getV(){
		return this.V;
	}
	public int getE(){
		return this.E;
	}
	
	
	
	
	/**
	 * Validate a given vertex
	 */
	public void validateVertex(int v){
		boolean valid = true;
		if(v < 0){
			valid=false;
		}else{
			// bounds
			if(v > this.V){
				valid=false;
			}else{
				valid=true;
			}
		}
		
		if (!valid){
			throw new IllegalArgumentException("Error: the value of vertex is invalid"+v);
		}		
	}
	
	
	/**
	 * add an edge to the graph
	 */
	public void addEdge(int v, int w) {
        validateVertex(v);
        validateVertex(w);
        
        if (this.hasEdge(v, w)){
        	throw new IllegalArgumentException("Error: Edge already exists ! - " + v + "," + w);
        }
        
        this.E++;        
        adj.get(v).add(w);
        adj.get(w).add(v);
    }
	
	
	/**
	 * remove an edge from the graph
	 */
	public void removeEdge(int v, int w) {
        validateVertex(v);
        validateVertex(w);
        
        if (!this.hasEdge(v, w)){
        	throw new IllegalArgumentException("Error: Edge does not exists ! - " + v + "," + w);
        }
        
        this.E--;                
        // remove v-w connection
        for (int i=0;i<this.adj.get(v).size();i++){
        	if (this.adj.get(v).get(i) == w){
        		this.adj.get(v).remove(i);
        		break;
        	}
        }        
        // remove w-v connection
        for (int i=0;i<this.adj.get(w).size();i++){
        	if (this.adj.get(w).get(i) == v){
        		this.adj.get(w).remove(i);
        		break;
        	}
        }
    }
	
	
	/**
	 * get adjacent vertices for a given vertex
	 */
	public ArrayList<Integer> getAdj(int v){
		validateVertex(v);
		return this.adj.get(v);
	}
	
	/**
	 * check if a there exists an edge between two vertices
	 */
	public boolean hasEdge(int v, int w){
		validateVertex(v);
        validateVertex(w);
		for (int i=0;i<this.adj.get(v).size();i++){
			if (this.adj.get(v).get(i) == w){
				return true;
			}
		}
		return false;
	}
	
	
	/**
	 * overridden toString 
	 */
	public String toString(){
		String result = "";
		for (int i=0;i<this.adj.size();i++){
			result += Integer.toString(i) + " = " + this.adj.get(i) + "\n";			
		}
		return result;
	}
	
	
	
}
