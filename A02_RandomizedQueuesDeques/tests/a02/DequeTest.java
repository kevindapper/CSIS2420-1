package a02;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class DequeTest {
	private Deque<String> deque;
	
	@Before
	public void setUp() throws Exception {
		deque = new Deque<String>();
	}

	@Test
	public void testIsEmpty() {
		assertTrue(deque.isEmpty());
	}

	@Test
	public void testIsEmpty_No() {
		deque.addFirst("Ironman");
		assertFalse(deque.isEmpty());
	}
	
	@Test
	public void testSize2() {
		deque.addFirst("Ironman");
		deque.addFirst("Supdman");
		assertEquals(deque.size(), 2);
	}

	@Test
	public void testSize4() {
		deque.addFirst("Ironman");
		deque.addFirst("Supdman");
		deque.addFirst("Runningman");
		deque.addFirst("I am Batman");
		assertEquals(deque.size(), 4);
	}
	
	@Test
	public void testAddFirst() {
		deque.addFirst("Ironman");
		deque.addFirst("Runningman");
		deque.addFirst("I am Batman");
		fail("Not yet implemented");
	}

	@Test
	public void testAddLast() {
		fail("Not yet implemented");
	}

	@Test
	public void testRemoveFirst() {
		fail("Not yet implemented");
	}

	@Test
	public void testRemoveLast() {
		fail("Not yet implemented");
	}

	@Test
	public void testIterator() {
		fail("Not yet implemented");
	}

}
