package a02;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class RandomizedQueueTest {
	private RandomizedQueue<String> randomQueue;
	
	@Before
	public void setUp() throws Exception {
		randomQueue = new RandomizedQueue<String>();
	}

	@Test
	public void testIsEmpty() {
		assertTrue(randomQueue.isEmpty());
	}

	@Test
	public void testIsEmpty_No() {
		randomQueue.enqueue("Ironman");
		assertFalse(randomQueue.isEmpty());
	}

	@Test
	public void testSize2() {
		randomQueue.enqueue("Ironman");
		randomQueue.enqueue("Supdman");
		assertEquals(randomQueue.size(), 2);
	}
	
	@Test
	public void testSize4() {
		randomQueue.enqueue("Ironman");
		randomQueue.enqueue("Supdman");
		randomQueue.enqueue("Runningman");
		randomQueue.enqueue("I am Batman");
		assertEquals(randomQueue.size(), 4);
	}

	@Test
	public void testEnqueue() {
		randomQueue.enqueue("Ironman");
		assertEquals(randomQueue.sample(), "Ironman");
	}

	@Test
	public void testEnqueue2() {
		//TODO not sure how to check a random item name
		randomQueue.enqueue("Ironman");
		randomQueue.enqueue("Supdman");
		randomQueue.enqueue("Runningman");
		randomQueue.enqueue("I am Batman");
		assertEquals(randomQueue.sample(), "Ironman");//This should return a non failure about 1/4 the time
	}
	
	@Test
	public void testDequeue3() {
		randomQueue.enqueue("Ironman");
		randomQueue.enqueue("Supdman");
		randomQueue.enqueue("Runningman");
		randomQueue.enqueue("I am Batman");
		randomQueue.dequeue();
		assertEquals(randomQueue.size(), 3);
	}

	@Test
	public void testDequeue1() {
		randomQueue.enqueue("Ironman");
		randomQueue.enqueue("Supdman");
		randomQueue.dequeue();
		randomQueue.enqueue("Runningman");
		randomQueue.enqueue("I am Batman");
		randomQueue.dequeue();
		randomQueue.dequeue();
		assertEquals(randomQueue.size(), 1);
	}
	
	@Test
	public void testSample() {
		randomQueue.enqueue("I am Batman");
		assertEquals(randomQueue.sample(), "I am Batman");
	}
	
	 @Test
	 public void testIterator() {
	 fail("Not yet implemented");
	 }
	
}
