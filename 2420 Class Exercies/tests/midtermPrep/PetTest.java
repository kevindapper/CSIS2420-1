package midtermPrep;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class PetTest {
	Pet pet = new Pet("Kevin", 13);
	
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testCompareTo_Greater() {// checking for a boolean, 
		int result = pet.compareTo(new Pet("Adam", 15));
		assertTrue(result > 0);// Adam is smaller than Kevin

	}

	@Test
	public void testCompareTo_Less() {// checking for a boolean, 
		int result = pet.compareTo(new Pet("Milo", 11));
		assertTrue(result < 0);// Milo is greater than Kevin

	}
	
	@Test
	public void testCompareTo_age() {// checking for a boolean, 
		int result = pet.compareTo(new Pet("Kevin", 11));
		assertTrue(result > 0);// checking for age, this Kevin is younger then Kevin 13

	}
	
	@Test
	public void testCompareTo_name() {// checking for a boolean, 
		int result = pet.compareTo(new Pet("Kevin", 13));
		assertTrue(result == 0);// checking for same name and age

	}
	
	@Test
	public void testToString() {
		fail("Not yet implemented");
	}

}
