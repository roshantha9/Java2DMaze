package java2dmaze;

public class Cell {
	public int north;
	public int west;
	public int east;
	public int south;
	
	public Cell(int n, int w, int e, int s){
		this.north = n;
		this.west = w;
		this.east = e;
		this.south = s;
	}
	
}
