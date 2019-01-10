package a01;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class PercolationTest {
	private Percolation p4;

	@Before
	public void setUp() throws Exception {
		p4 = new Percolation(4); // put this in the before so it is created each
									// time
	}

	@Test
	public void testPercolation() {
		Percolation p3 = new Percolation(3);
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				assertFalse(p3.isOpen(i, j));
			}
		}
	}

	// @Test
	// //For Jtest 5
	// public void testPercolationGridSize() {
	// Percolation p3 = new Percolation(3);
	// assertThrows(IndexOutOfBoundsException.class,() -> {
	// assertFalse(p3.isOpen(3, 3)); //(3,3) should not exist in a 3x3 grid.
	// });
	// }

	@Test
	public void testOpen() {
		assertFalse(p4.isOpen(0, 0));
		p4.open(0, 0);
		assertTrue(p4.isOpen(0, 0));
	}

	@Test
	public void testIsOpen() {
		assertFalse(p4.isOpen(3, 3));
		p4.open(3, 3);
		assertTrue(p4.isOpen(3, 3));
	}

	@Test
	public void testIsFull_closedSite() {
		assertFalse(p4.isFull(0, 1));
	}

	@Test
	public void testIsFull_01() {
		p4.open(0, 1);
		assertTrue(p4.isFull(0, 1));
	}

	@Test
	public void testIsFull_Diagonal() {
		p4.open(0, 1);
		p4.open(1, 2);
		assertFalse(p4.isFull(1, 2));
	}

	@Test
	public void testIsFull_flowAroundCorners() {
		p4.open(0, 1);
		p4.open(1, 1);
		p4.open(1, 2);
		p4.open(2, 2);
		assertTrue(p4.isFull(2, 2));
	}

	@Test
	public void testPercolates_allClosed() {
		assertFalse(p4.percolates());
	}

	@Test
	public void testIsFull_touchBottomWithoutPercolation() {
		p4.open(0, 1);
		p4.open(1, 1);
		p4.open(1, 2);
		p4.open(2, 2);
		p4.open(3, 1);
		assertFalse(p4.percolates());
	}
	
	//important: test for the expected Exceptions.
	//Junit 5 test
//	@Test
//	public void testPercolation_IllegalArgumentException_negArgument() {	
//		assertThrows(IllegalArgumentException.class,() -> {
//			new Percolation(-1);
//		});		
//	}

	@Test
	public void testNumberOfOpenSites() {
		p4.open(0, 1);
		p4.open(1, 1);
		p4.open(1, 2);
		String expected = "3 open sites";
		String actual = p4.numberOfOpenSites();
		assertEquals(expected, actual);
	}

	

}
