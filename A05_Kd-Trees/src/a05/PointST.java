package a05;

/*******************************************************************************
 * Author(s): Aaron Sadler, Gerald Brady 
 * Class: CSIS-2420
 * Professor: Margarethe Posch
 * Assignment: A05  KD-Trees
 ******************************************************************************/

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.RedBlackBST;


public class PointST<Value> {
	private RedBlackBST<Point2D, Value> points;
	
	/**
	 * Construct an empty symbol table of points.
	 */
	public PointST() {
		points = new RedBlackBST<>();
	   }
	
	/**
	 * Is the symbol table empty? 
	 * @return
	 */
   public boolean isEmpty() {
	   return points.isEmpty();
   }
   
   /**
    * Number of points. 
    * @return
    */
   public int size() {
	   return points.size();
   }
   
   /**
    * Associate the value val with point p.
    * @param p
    * @param val
    */
   public void put(Point2D p, Value val) {
	   points.put(p,  val);
   }
   
   /**
    * Value associated with point p.
    * @param p
    * @return
    */
   public Value get(Point2D p) {
	   return points.get(p);
   }
   
   /**
    * Does the symbol table contain point p?
    * @param p
    * @return
    */
   public boolean contains(Point2D p) {
	   return points.contains(p);
   }
   
   /**
    * All points in the symbol table
    * @return
    */
   public Iterable<Point2D> points() {
	   return points.keys();
   }
   
   /**
    * All points that are inside the rectangle. 
    * @param rect
    * @return
    */
   public Iterable<Point2D> range(RectHV rect) {
	   Point2D lo = new Point2D(rect.xmin(), rect.ymin());
	   Point2D hi = new Point2D(rect.xmax(), rect.ymax());
	   return points.keys(lo, hi);
   }
   
   /**
    * A nearest neighbor to point p; null if the symbol table is empty.
    * @param p
    * @return
    */
   public Point2D nearest(Point2D p) {
	   Point2D nearest = points.max(); 
	   for(Point2D point: points.keys()) {
		   if(point.distanceSquaredTo(p)< nearest.distanceSquaredTo(p)) {
			   nearest = point;
		   }
	   }
	   return nearest;
   }
	    
}
