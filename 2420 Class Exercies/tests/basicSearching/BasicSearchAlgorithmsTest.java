package basicSearching;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class BasicSearchAlgorithmsTest {

	private char[] array = {'a', 'b', 'c', 'd', 'c', 'e', 'c', 'f', 'o'};
	
	@Before
	public void setUp() throws Exception {
	}

	//test for duplicates and boundaries (first and last)
	// example with expected and value stored separately
	@Test
	public void testLinearSearchNonExistingKey() {
		char key = 'w';
		int expected = -1;
		int actual = BasicSearchAlgorithms.linearSearch(array, key);
		assertEquals(expected, actual);
		
	}
	
	@Test
	public void testLinearSearchTypicalKey() {
		char key = 'b';
		assertEquals(1, BasicSearchAlgorithms.linearSearch(array, key));
		
	}
	
	@Test
	public void testLinearSearchLastKey() {
		char key = 'o';
		assertEquals(8, BasicSearchAlgorithms.linearSearch(array, key));
		
	}
	
	@Test
	public void testLinearSearchDuplicateKey() {
		char key = 'c';
		assertEquals(2, BasicSearchAlgorithms.linearSearch(array, key));
		//why is it a 2 and not a 3??
		
	}

}
