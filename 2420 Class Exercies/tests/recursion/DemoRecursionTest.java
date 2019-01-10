package recursion;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class DemoRecursionTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testSum_4() {
		int aspected = 10;
		int actual = DemoRecursion.sum(4);
		assertEquals(aspected, actual);
	}
	
//	@Test
//	void testSum_negative() { // cant use - in the name
//		assertThrows(IllegalArgumentException.class, () -> {
//			DemoRecursion.sum(-4);
//			
//		});
//	}

	@Test
	public void testSum_0() {
		assertEquals(0, DemoRecursion.sum(0));
	}
	
	@Test
	public void collatzSequence_5() {
		String aspected = "5 16 8 4 2 1 ";
		String actual = DemoRecursion.collatzSequence(5);
		assertEquals(aspected, actual);
	}
	
	@Test
	public void collatzSequence_2() {
		String aspected = "2 1 ";
		String actual = DemoRecursion.collatzSequence(2);
		assertEquals(aspected, actual);
	}
	
	@Test
	public void collatzSequence_13() {
		String aspected = "13 40 20 10 5 16 8 4 2 1 ";
		String actual = DemoRecursion.collatzSequence(13);
		assertEquals(aspected, actual);
	}
	
	@Test
	public void collatzSequence_1() {
		String aspected = "1 ";// Boundary Value
		String actual = DemoRecursion.collatzSequence(1);
		assertEquals(aspected, actual);
	}
}
