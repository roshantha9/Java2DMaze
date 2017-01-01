package java2dmaze;

// java imports
import java.awt.Point;
import java.util.*;

// local imports



public class Cell {
	
	public int cid;
	public Point cords;
	
	// neighbours
	public int north;
	public int west;
	public int east;
	public int south;
	
	public Cell(int cid, Point c, int n, int w, int e, int s){
		
		this.cid = cid;
		this.cords = c;
		
		// validate neighbours are unique
		validateNeighbours(n, w, e, s);
		
		// neighbours
		this.north = n;
		this.west = w;
		this.east = e;
		this.south = s;
	}
	
	
	public int neighbourDirection(int ncid){
		
		if (ncid == this.north){
			return Direction.NORTH;
		}else if (ncid == this.west){
			return Direction.WEST;
		}else if (ncid == this.east){
			return Direction.EAST;
		}else if (ncid == this.south){
			return Direction.SOUTH;
		}else{
			return Direction.UNKNOWN;
		}
	}
	
	public ArrayList<Integer> getValidNeighbours(){
		ArrayList<Integer> vn = new ArrayList<Integer>();
		if (this.north != -1){ vn.add(this.north); }
		if (this.east != -1){ vn.add(this.east); }
		if (this.west != -1){ vn.add(this.west); }
		if (this.south != -1){ vn.add(this.south); }
		return vn;
	}
	
	private boolean validateNeighbours(int n, int w, int e, int s){
		Set<Integer> tmpSet = new HashSet<Integer>();
		int count=0;
		if (n!=-1){tmpSet.add(n);count++;}
		if (e!=-1){tmpSet.add(e);count++;}
		if (w!=-1){tmpSet.add(w);count++;}
		if (s!=-1){tmpSet.add(s);count++;}
		
		if (tmpSet.size() != count){
			throw new IllegalArgumentException("Error: there are duplicates in the neighbour ids - <" + n +
					"," + w +
					"," + e +
					"," + s + ">");
		}else{
			return true;
		}		
	}
	
	
	public String toString(){
		String s = "<Cell " +
				"cid="+this.cid + ", " +
				"xy=("+this.cords.getX()+","+this.cords.getY()+")" + ", " +
				"n="+this.north + ", " +
				"e="+this.east + ", " +
				"w="+this.west + ", " +
				"s="+this.south + 
				" >";
		return s;				
	}
	
	
}
