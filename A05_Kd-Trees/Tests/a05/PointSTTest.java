package a05;

/*******************************************************************************
 * Author(s): Aaron Sadler, Gerald Brady 
 * Class: CSIS-2420
 * Professor: Margarethe Posch
 * Assignment: A05  KD-Trees
 ******************************************************************************/

import edu.princeton.cs.algs4.Point2D;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class PointSTTest {
	private Point2D p = new Point2D(1.1, 1.1);
	private PointST<Point2D> points = new PointST<>();
	
	@Before
	public void setUp() throws Exception {
		points.put(p, p);
	}

	@Test
	public void testPointST() {
		assertTrue(points.size() == 1);
	}

	@Test
	public void testIsEmpty() {
		PointST<Point2D> points2 = new PointST<>();		
		assertTrue(points2.isEmpty());
	}

	@Test
	public void testIsEmptyFalse() {
		assertFalse(points.isEmpty());
	}
	
	@Test
	public void testSize() {
		assertEquals(1, points.size());
	}

	@Test
	public void testPut() {
		Point2D p4 = new Point2D(2.2,2.2);
		points.put(p4, p4);
		assertTrue(points.contains(p4));
	}

	@Test
	public void testGet() {
		Point2D p2 = new Point2D(1.1, 1.1);
		assertTrue(p2.equals(points.get(p)));
	}

	@Test
	public void testContains() {
		assertTrue(points.contains(p));
	}

	@Test
	public void testPoints() {
		Point2D point2 = new Point2D(2.0, 3.0);
		points.put(point2, point2);
				
		String expected = "(1.1, 1.1) (2.0, 3.0) ";
		String actual = points.points().toString(); 
		
		assertEquals(expected, actual);
	}
	
//	@Test
//	public void testRange() {
//	RectHV rectangleRange = new RectHV(3.5, 6, 6, 8);
//	
//	Point2D point5 = new Point2D(5,6);
//	Point2D point6 = new Point2D(6,7);
//	
////	points.put(point1, point1);
////	points.put(point2, point2);
////	points.put(point3, point3);
////	points.put(point4, point4);
//	points.put(point5, point5);
//	points.put(point6, point6);
//	
//	String expected = "(5.0, 6.0) (6.0, 7.0) ";
//	String actual = points.range(rectangleRange).toString();
//	
//	assertEquals(expected,actual);
//}
	
	
	@Test
	public void testNearest() {
		Point2D[] p = {
				new Point2D(0.5,0.5),
				new Point2D(1.0,1.0),
				new Point2D(1.5,1.5),
				new Point2D(2.0,2.0),
				new Point2D(2.5,2.5),
				new Point2D(3.0,3.0),
				new Point2D(3.5,3.5),
				new Point2D(4.0,4.0)
				};
		
		Point2D p5 = new Point2D(1.1,1.1);
		PointST<Point2D> nearestTest = new PointST<>();		
		for(Point2D el: p) {
			nearestTest.put(el, el);
		}
		
		Point2D expected = new Point2D(1.0,1.0);
		Point2D actual = nearestTest.nearest(p5);
		assertEquals(expected, actual);
	}

}
