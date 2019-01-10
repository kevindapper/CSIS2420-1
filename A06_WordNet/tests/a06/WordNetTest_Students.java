/**	
 * Authors: Andrew and Spenser
 */

package a06;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class WordNetTest_Students {
	WordNet wordnet;
	String[] nouns;

	@Before
	public void setUp() throws Exception {
		wordnet = new WordNet("src/res/synsets11.txt", "src/res/hypernyms11ManyPathsOneAncestor.txt");
		nouns = new String[] { "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k" };
	}

	@Test(expected = NullPointerException.class)
	public void testWordNetp1() {
		new WordNet(null, "");
	}

	@Test(expected = NullPointerException.class)
	public void testWordNetp2() {
		new WordNet("", null);
	}

	@Test(expected = NullPointerException.class)
	public void testWordNetp1p2() {
		new WordNet(null, null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testWordNetNotRooted() {
		new WordNet("src/res/synsets8.txt", "src/res/hypernyms8TwoAncestorsModified.txt");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testWordNetNotDAG() {
		new WordNet("src/res/synsets8.txt", "src/res/hypernyms8Cycle.txt");
	}

	@Test
	public void testNouns() {
		for (String word : wordnet.nouns()) {
			boolean isContained = false;
			for (int i = 0; i < nouns.length; i++) {
				if (word.equals(nouns[i]))
					isContained = true;
			}
			if (!isContained)
				fail(word + " is not contained");
		}
	}

	@Test
	public void testIsNoun() {
		for (int i = 0; i < nouns.length; i++)
			if (!wordnet.isNoun(nouns[i]))
				fail(nouns[i] + " is not a noun");
		String[] badNouns = { "aa", "bb", "bob", "cat", "derp", "a ", "a b" };
		for (int i = 0; i < badNouns.length; i++)
			if (wordnet.isNoun(badNouns[i]))
				fail(badNouns[i] + " shouldn't be a noun");
	}

	@Test(expected = NullPointerException.class)
	public void testIsNounBadInput() {
		wordnet.isNoun(null);
	}

	@Test
	public void testDistance() {
		assertEquals(2, wordnet.distance("b", "c"));
		assertEquals(1, wordnet.distance("a", "d"));
		assertEquals(0, wordnet.distance("f", "f"));
	}

	@Test(expected = NullPointerException.class)
	public void testDistanceNullp1() {
		wordnet.distance(null, "");
	}

	@Test(expected = NullPointerException.class)
	public void testDistanceNullp2() {
		wordnet.distance("", null);
	}

	@Test(expected = NullPointerException.class)
	public void testDistanceNullp1p2() {
		wordnet.distance(null, null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testDistanceNounNotContained1() {
		wordnet.distance("derp", "herp");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testDistanceNounNotContained2() {
		wordnet.distance("a", "joe");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testDistanceNounNotContained3() {
		wordnet.distance("aaah", "e");
	}

	@Test
	public void testSap() {
		assertEquals("f", wordnet.sap("b", "c"));
		assertEquals("a", wordnet.sap("a", "a"));
		//assertEquals("f", wordnet.sap("a", "k"));
		//assertEquals("b", wordnet.sap("a", "b"));
	}

	@Test(expected = NullPointerException.class)
	public void testSapNullp1() {
		wordnet.sap(null, "");
	}

	@Test(expected = NullPointerException.class)
	public void testSapNullp2() {
		wordnet.sap("", null);
	}

	@Test(expected = NullPointerException.class)
	public void testSapNullp1p2() {
		wordnet.sap(null, null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testSapNounNotContained1() {
		wordnet.sap("bob", "a");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testSapNounNotContained2() {
		wordnet.sap("b", "aa");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testSapNounNotContained3() {
		wordnet.sap("cc", "b ");
	}
}