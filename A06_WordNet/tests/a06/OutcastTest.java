package a06;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class OutcastTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test() {
		WordNet wn = new WordNet("src/res/videoSynsets.txt", "src/res/videoHypernyms.txt");
		Outcast oc = new Outcast(wn);
		String[] nouns1 = {"slip", "schoolchild", "school-age_child", "pupil", "puppy", "pup", "hobbledehoy", "Frankie_the_urchin"};
		assertEquals("Frankie_the_urchin", oc.outcast(nouns1));
		
		String[] nouns2 = {"young_man", "younker", "rocker", "stripling"};
		assertEquals("younker", oc.outcast(nouns2));
		
		String[] nouns3 = {"sprog", "punk_rock", "rock'n'roll", "popular_music_genre"};
		assertEquals("sprog", oc.outcast(nouns3));
	}
	
	@Test
	public void testOutcast() {
		fail("Not yet implemented");
	}

	@Test
	public void testOutcast1() {
		fail("Not yet implemented");
	}

	@Test
	public void testMain() {
		fail("Not yet implemented");
	}

}
