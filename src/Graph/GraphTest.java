package Graph;

public class GraphTest {

	public static void main(String[] args) {
		// create a graph for 16 nodes
		Graph G = new Graph(10);
		
		// add edges
		G.addEdge(1, 2);
		G.addEdge(1, 3);
		G.addEdge(5, 8);
		
		System.out.println(G.hasEdge(1, 0));
		
		G.removeEdge(1, 3);
		
		// print graph
		System.out.println(G);
		
	}

}
