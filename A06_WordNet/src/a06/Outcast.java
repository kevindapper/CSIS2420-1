package a06;

import edu.princeton.cs.algs4.In;

/*******************************************************************************
 * Author(s): Caleb Stam, Gerald Brady 
 * Class: CSIS-2420
 * Professor: Margarethe Posch
 * Assignment: A06  Word Net
 ******************************************************************************/

import edu.princeton.cs.algs4.MaxPQ;
import edu.princeton.cs.algs4.ST;
import edu.princeton.cs.algs4.StdOut;

public class Outcast
{

	private WordNet wn;

	/**
	 * Constructor takes a WordNet object to create an Outcast object.
	 * 
	 * @param wordnet
	 */
	public Outcast(WordNet wordnet)
	{
		this.wn = wordnet;
	}

	/**
	 * Given an array of WordNet nouns, it returns an outcast.
	 * 
	 * @param nouns
	 * @return
	 */
	public String outcast(String[] nouns)
	{
		ST<Integer, String> st = new ST<>();
		MaxPQ<Integer> maxPQ = new MaxPQ<>();
		
		for (int i = 0; i < nouns.length; i++)
		{
			int distance = 0;
			for (int j = 0; j < nouns.length; j++)
			{
				distance += wn.distance(nouns[i], nouns[j]);
			}
			
			maxPQ.insert(distance);
			st.put(distance, nouns[i]);
		}
		
		return st.get(maxPQ.max());
	}

	// ==================== test client below ====================

	public static void main(String[] args)
	{
		WordNet wordnet = new WordNet("src/res/synsets.txt", "src/res/hypernyms.txt");
	    Outcast outcast = new Outcast(wordnet);
	    In in = new In("src/res/outcast5.txt");
	    String[] nouns = in.readAllStrings();
	    for (int t = 2; t < nouns.length; t++) {
	        StdOut.println(1 + ": " + outcast.outcast(nouns));
	    }
		
		
		//test1();
	}

	private static void test1() {
		String[] words = {"dog", "cat", "tiger", "elephant", "penguin", "bunny" };
		WordNet wordnet = new WordNet("src/res/synsets.txt", "src/res/hypernyms.txt");
		Outcast outcast = new Outcast(wordnet);
		
		for (int t = 2; t < words.length; t++)
		{
			StdOut.println(t + ": " + outcast.outcast(words));
		}
	}
}
