package a03;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

public class TermTest {
	private Term term;
	Term[] terms = {
    			new Term ("Jump", 2), 
    			new Term ("Jar", 2), 
    			new Term ("Jaxan", 4), 
    			new Term ("Jam",1),
    			new Term ("Jam",1)};    	

	
	@Before
	public void setUp() throws Exception {
		term = new Term("Apple",1);  
	}	
		
	@Test
	public final void testTerm() {
		Term term1 = new Term ("Apple",1);
		int expected = 0;
		int actual = term.compareTo(term1);
		assertTrue(expected == actual);
	}

	// Test byReverseWeightOrder 
	@Test
	public final void testByReverseWeightOrder() {
		Arrays.sort(terms,Term.byReverseWeightOrder());
		assertTrue(terms[1].compareTo(terms[2]) >= 1);		
	}

	// Test byPrefixOrder
	@Test
	public final void testByPrefixOrder() {
		Arrays.sort(terms, Term.byPrefixOrder(2));
		assertTrue(terms[0].compareTo(terms[1]) < 0);
	}

	// Test compareTo when equal
	@Test
	public final void testCompareToEqual() {
		assertTrue(terms[3].compareTo(terms[4])== 0);
	}
	
	// Test compareTo when less
	@Test
	public final void testCompareToLess() {
		Term term2 = new Term ("Banana",1);
		int expected = -1;
		int actual = term.compareTo(term2);
		assertEquals(expected, actual);
	}
	
	@Test
	public final void testCompareToGreater() {
		Term term2 = new Term ("Banana",1);
		int expected = 1;
		int actual = term2.compareTo(term);
		assertEquals(expected, actual);
	}

	// Test toString
	@Test
	public final void testToString() {
		String expected = 1.0 + "\t" + "Apple";
		String actual = term.toString();
		assertEquals(expected,actual);
	}

}