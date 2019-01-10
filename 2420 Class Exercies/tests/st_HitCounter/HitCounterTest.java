package st_HitCounter;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class HitCounterTest {

	private HitCounter counter;
	
	@Before
	public void setUp() throws Exception {
		counter = new HitCounter();

	}

	@Test
	public void testHitRed() {
		counter.hit("Red");
		assertTrue(1 == counter.st.get("Red"));
		counter.hit("Red");
		assertTrue(2 == counter.st.get("Red"));
	}
	
	@Test
	public void testNotIncluded() {
		assertTrue(0 == counter.count("Blue"));
	}
	
	@Test
	public void testHitYellow() {
		counter.hit("Yellow");
		assertTrue(1 == counter.st.get("Yellow"));
	}

	@Test
	public void testCountRed() {
		counter.hit("Red");
		counter.hit("Red");
		counter.hit("Red");
		assertEquals(3, counter.count("Red"));
	}
	
	@Test
	public void testComboHitCount() {
		counter.hit("Red");
		counter.hit("Red");
		counter.hit("Yellow");
		assertEquals(2, counter.count("Red"));
		counter.hit("Red");
		counter.hit("Red");
		counter.hit("Red");
		counter.hit("Blue");
		counter.hit("Yellow");
		counter.hit("Red");
		assertEquals(6, counter.count("Red"));
		assertEquals(2, counter.count("Yellow"));
		assertEquals(1, counter.count("Blue"));
	}
	
	@Test
	public void testCount0() {
		assertEquals(0, counter.count("Blue"));
	}

}
