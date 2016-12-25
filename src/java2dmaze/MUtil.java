package java2dmaze;

import java.awt.Point;

public class MUtil {		
		/**
		 * Convert a node ID to an x,y coord
		 */
		public static Point nid2xy(int nid, int width){
			int x = nid % width;
			int y = nid / width;	// integer division			
			Point p = new Point(x,y);
			return p;		
		}
		/**
		 * Convert an x,y coord to a node ID	 
		 */
		public static int xy2nid(int x, int y, int width){
			int nid = (y*width) + x;
			return nid;
		}
		
		
}
