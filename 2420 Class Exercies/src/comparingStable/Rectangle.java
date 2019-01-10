package comparingStable;

import java.util.Comparator;

/**
 * Represents a rectangle that is defined by its length and width.
 * 
 * @author Gerald Brady
 */
public class Rectangle implements Comparable<Rectangle>{
	private double length;
	private double width;
	public static final Comparator<Rectangle> BY_LENGTH = new ComparatorByLength();
	public static final Comparator<Rectangle> BY_WIDTH = new ComparatorByWIDTH();
	
	/**
	 * initializes the new rectangle with the given length and width.
	 * @param length
	 * @param width
	 */
	public Rectangle(double length, double width) {
		this.length = length;
		this.width = width;
	}

	public double getLength() {
		return length;
	}

	public double getWidth() {
		return width;
	}

	@Override
	public String toString() {
		return "[" + length + "," + width + "]";
	}

	@Override
	// default arg0 changed to other
	/**
	 *  Compares this instance with the other based on the area of the rectangle.
	 */
	public int compareTo(Rectangle other) {
		return Double.compare(this.length * this.width, other.length * other.width);
	}
	
	// old version
//	public int compareTo(Rectangle other) {
//		if(this.length * this.width > other.length + other.width)
//			return 1;// any positive number
//		if(this.length * this.width < other.length + other.width)
//			return -1;// any negative number
//		return 0; // this is equal in size
//	}
	
	/**
	 * Comparator that allows rectangles to be sorted by length.
	 * @author Gerald Brady
	 *
	 */
	private static class ComparatorByLength implements Comparator<Rectangle> {

		@Override
		public int compare(Rectangle r1, Rectangle r2) {
			
			return Double.compare(r1.length, r2.length);
		}
		
	}
	
	/**
	 * Comparator that allows rectangles to be sorted by width.
	 * @author Gerald Brady
	 *
	 */
	private static class ComparatorByWIDTH implements Comparator<Rectangle> {

		@Override
		public int compare(Rectangle r1, Rectangle r2) {
			
			return Double.compare(r1.width, r2.width);
		}
		
	}
}
