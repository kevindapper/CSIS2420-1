package a05;

import edu.princeton.cs.algs4.MaxPQ;
import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.RectHV;

public class EnhancedKdTreeST<Value> {
	private static final boolean VERTICAL = true;		//vertical asymptote in KdTree
	private static final boolean HORIZONTAL = false;	//horizontal asymptote in KdTree
	private Node root;									//root node of the symbol table
	private int size;	 								//size of the symbol table

	private class Node {
		private Point2D p;  			//the point 
		private Value val;  			//the symbol table maps the point to this value
		private RectHV rect;  			//the axis-aligned rectangle corresponding to this node
		private Node left;  			//the left/bottom subtree
		private Node right;			 	//the right/top  subtree
		private boolean orientation;	//Orientation of the node
		
		//constructs a node to for Kd-Tree symbol tree
		public Node(Point2D p, Value val, RectHV rect, boolean orientation) {
			this.p = p;
			this.val = val;
			this.rect = rect;
			this.orientation = orientation;
		}
	}
	
	/**
	 * Construct an empty symbol table of points.
	 */
	public EnhancedKdTreeST() {
		size = 0;
	}


	/**
	 * Returns true if this symbol table is empty
	 * @return true if the symbol table is empty otherwise false.
	 */
	public boolean isEmpty() {
		return size == 0;
	}

	/**
	 * Returns the number of points in the symbol table.
	 * @return the number of points in the symbol table
	 */
	public int size() {
		return size;
	}
	
	/**
	 * Inserts the specified point into the symbol table, overwrites the old
	 * value with the new value if the symbol table already contains the specified
	 * point. Orders the table based on the value of x when the orientation in a node
	 * is vertical and y when the orientation in a node is horizontal. This method also
	 * constructs an axis-aligned rectangle for each point inserted in the table. 
	 * 
	 * @param p the point
	 * @param val the value
	 */
	public void put(Point2D p, Value val) {
		if(p == null || val == null) throw new NullPointerException();
		Double lo = Double.NEGATIVE_INFINITY;		//minimum parameter of rectangle
		Double hi = Double.POSITIVE_INFINITY;		//maximum parameter of rectangle
		RectHV rect = new RectHV(lo,lo,hi,hi);		//construct a rectangle with infinite dimensions
		root = put(p, val, root, VERTICAL, rect);
		
	}
	
	private Node put(Point2D p, Value val, Node node, boolean orientation, RectHV rect) {		
		//put a new node in the symbol table 
		//and increment the size of the table.
		if (node == null) {
			size++;
			return new Node(p, val, rect, orientation);
		}
		
		int cmp = pointCompare(p, node, orientation);
		
		//update the rect containing the next node from the current node based
		//on the x-coordinate.
		if(orientation == VERTICAL) {
			if(cmp < 0) rect = new RectHV(rect.xmin(), rect.ymin(), node.p.x(), rect.ymax());
			if(cmp > 0) rect = new RectHV(node.p.x(), rect.ymin(), rect.xmax(), rect.ymax());
		}
		
		//update the rect containing the next node from the current node based
		//on the y-coordinate.		
		if(orientation == HORIZONTAL) {
			if(cmp < 0) rect = new RectHV(rect.xmin(), rect.ymin(), rect.xmax(), node.p.y());
			if(cmp > 0) rect = new RectHV(rect.xmin(), node.p.y(), rect.xmax(), rect.ymax());
		}
		
		//put left and switch the orientation
		if (cmp < 0) node.left = put(p, val, node.left, !orientation, rect);
		//put right and switch the orientation
		else if(cmp > 0) node.right = put(p, val, node.right, !orientation, rect);
		//Query point and point in node are equal. Update the value in the node.
		else node.val = val;
		
		return node;
	}
	

	/**
	 * Returns value associated with given point p or null if no such point exists.
	 * 
	 * @param p the point
	 * @return the value associated with the point if the point is in the table and
	 * 		   null if the key is not in the table.
	 */
	public Value get(Point2D p) {
		if(p == null) throw new NullPointerException();
		return get(p, root);
		
	}

	private Value get(Point2D p, Node x) {
		//Check if the value is null
		if(x == null) return null;  
		//Compare point p to the point in the node and store the result.
		int cmp = pointCompare(p, x, x.orientation);  
		
		//Return the value in the node when it equals point p.
		if (cmp == 0) return x.val;  
		//Recursively look left for the value of point p. 
		else if (cmp < 0) return get(p, x.left);
		//Recursively look right for the value of point p.
		else return get(p, x.right); //(cmp > 0)
	}
	
	/**
	 * Does the symbol table contain the given point?
	 * 
	 * @param p the point
	 * @return true if this symbol table contains the point and
	 * 		   false otherwise.
	 */
	public boolean contains(Point2D p) {
		if(p == null) throw new NullPointerException();
		return get(p) != null;	
	}
	
	/**
	 * Returns all of the points in the symbol table in level order.
	 *
	 * @return a level order itertable of points.
	 */
	public Iterable<Point2D> points() {
		Queue<Node> temp = new Queue<>();	//stores nodes temporarily
		Queue<Point2D> levelOrder = new Queue<>(); //level-order traversal
		
		//Return an Iterable of points with zero points.
		if(root == null) return levelOrder; 
		
		//Enqueue the root node and point in root node.
		temp.enqueue(root);
		
		while(!temp.isEmpty()) {
			//Enqueue the points of the children of the dequeued node
			Node level = temp.dequeue();
			levelOrder.enqueue(level.p);
			
			if(level.left != null) { 
				temp.enqueue(level.left);
			}
			
			if(level.right != null) {
				temp.enqueue(level.right);
			}
		}		
		return levelOrder;
	}

	/**
	 * Takes a 2-D rectangle and returns an iterable of all the
	 * points in the symbol table that are inside the rectangle.
	 * 
	 * @param rect A 2-dimensional rectangle
	 * @return Iterable of all of the points in the given rectangle 
	 * 		   from the symbol table.
	 */
	public Iterable<Point2D> range(RectHV rect) {
		if (rect == null) throw new NullPointerException();
		Queue<Point2D> pointsInRect = new Queue<>();
		return range(rect, pointsInRect, root);
	}	
	
	private Iterable<Point2D> range(RectHV rect, Queue<Point2D> pointsInRect, Node x) {
		//return an Iterable of points with zero points
		if(x == null) return pointsInRect;
		
		//If the query rectangle intersects the nodes axis-aligned rectangle.
		if(x.rect.intersects(rect)) {

			//If the query rectangle contains the point of the node. Enqueue the point.
			if(rect.contains(x.p)) pointsInRect.enqueue(x.p);
			
			//recursively check all the nodes to the left.
			range(rect, pointsInRect, x.left);
			//recursively check all the nodes to the right.
			range(rect, pointsInRect, x.right);			
		}
		return pointsInRect;
	}

	/**
	 * A nearest neighbor to point p; null if the symbol table is empty.
	 * 
	 * @param p
	 * @return
	 */
	public Point2D nearest(Point2D p) {
		if (p == null) throw new NullPointerException();
		MaxPQ<Point2D> kPoints = new MaxPQ<>();
		nearest(root, p, kPoints, 1);
		return kPoints.max();
	}

	public Iterable<Point2D> nearest(Point2D p, int k) {
		if(p == null) throw new NullPointerException();
		if(k < 0) throw new IllegalArgumentException();
		
		MaxPQ<Point2D> kPoints = new MaxPQ<>();
		nearest(root, p, kPoints, k);
		return kPoints;
	}

	private MaxPQ<Point2D> nearest( Node node, Point2D p, MaxPQ<Point2D> kPoints, int k) {
		//Base cases for nearest.
		if(node == null) return kPoints;
		
		if(kPoints.size() < k) { 
			kPoints.insert(node.p);
		}
		else {
			if(Double.compare(p.distanceSquaredTo(node.p), p.distanceSquaredTo(kPoints.max())) < 0) {
				kPoints.insert(node.p);
				if(kPoints.size() > k) { 
					kPoints.delMax();
				}	
			}
		}
//		if(node.rect.distanceSquaredTo(p) > root.p.distanceSquaredTo(p)) kPoints.insert(node.p);
		
		//determine which direction to go in the symbol table.

		int cmp = pointCompare(p, node, node.orientation);
		
		//recursively find the nearest point to the query point.
		if(cmp < 0) {			
			kPoints = nearest(node.right, p, kPoints, k);
			if(kPoints.max().distanceSquaredTo(p) > node.rect.distanceSquaredTo(p)) {
				kPoints = nearest(node.left, p, kPoints, k);				
			}
		}	
		else {
			kPoints = nearest(node.left, p, kPoints, k);
			if(kPoints.max().distanceSquaredTo(p) > node.rect.distanceSquaredTo(p)) {
				kPoints = nearest(node.right, p, kPoints, k);				
			}
		}
			
		return kPoints;
	}
	

// **************************************** Helper Methods *********************************************** //

	/**
	 * Helper method to compare two points based on their level in the symbol table.
	 * If the orientation is vertical the comparison is performed on the x-coordinate.
	 * If the orientation is horizontal the comparison is performed on the y-coordinate.
	 * The method returns a 1, -1 in most cases. The method returns 0 only if the query point
	 * and the point in the node are equal.
	 * @param p the query point
	 * @param node the node 
	 * @param orientation the vertical or horizontal orientation of the node
	 * @return returns -1, 0, 1 based on the results of the comparison.
	 */
	private int pointCompare(Point2D p, Node node, boolean orientation) {
		int cmp = 0;
				
		if(orientation == VERTICAL) {
			if(p.x() < node.p.x()) cmp = -1;
			else if(p.x() >= node.p.x()) cmp = 1;
		}
		
		if(orientation == HORIZONTAL) {
			if(p.y() < node.p.y()) cmp = -1;
			else if(p.y() >= node.p.y()) cmp = 1;
		}
		
		if(p.equals(node.p)) {
			cmp = 0;
		}

		return cmp;
	}
}
