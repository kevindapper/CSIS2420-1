package comparingStable;

import java.util.Arrays;
import java.util.Collections;

import edu.princeton.cs.algs4.Insertion;
import edu.princeton.cs.algs4.Selection;

/**
 * @author Gerald Brady
 *
 */
public class TestClient {

	public static void main(String[] args) {
		Rectangle[] rectangles = {
				new Rectangle(4.4, 6), 
				new Rectangle(2.2, 4),
				new Rectangle(7.7, 6),
				new Rectangle(5.5, 2),
				new Rectangle(9.9, 4),
				new Rectangle(3.3, 2),
				new Rectangle(6.6, 4),
				new Rectangle(1.1, 6),
				new Rectangle(8.8, 6)};
		printRectangle(rectangles);
		
		// sorting based on the natural order
		// demoInterfaceComparable(rectangles);

		// sort using comparators
		Arrays.sort(rectangles, Rectangle.BY_LENGTH);
		printRectangle(rectangles);
		
		// not stable [4.4,6.0] [7.7,6.0] [8.8,6.0] [1.1,6.0] - 1.1 is at the end
//		Selection.sort(rectangles, Rectangle.BY_WIDTH);
//		printRectangle(rectangles);
		
		// stable [1.1,6.0] [4.4,6.0] [7.7,6.0] [8.8,6.0] - original order is preserved.
		Insertion.sort(rectangles, Rectangle.BY_WIDTH);
		printRectangle(rectangles);
		
	}

	@SuppressWarnings("unused")
	private static void demoInterfaceComparable(Rectangle[] rectangles) {
		Arrays.sort(rectangles);
		printRectangle(rectangles);
		
		// sorting based on reverse natural order
		Arrays.sort(rectangles, Collections.reverseOrder());
		printRectangle(rectangles);
	}

	private static void printRectangle(Rectangle[] rectangles) {
		for (Rectangle r : rectangles) {
		    System.out.print(r + " ");
		}
		System.out.println();
	}

}
