package a06;

import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.SeparateChainingHashST;

/*******************************************************************************
 * Author(s): Caleb Stam, Gerald Brady
 * Class: CSIS-2420 
 * Professor: Margarethe
 * Posch Assignment: A06 Word Net
 ******************************************************************************/

public class WordNet {

	private static SeparateChainingHashST<String, Queue<Integer>> wordToInt;
	private static SeparateChainingHashST<Integer, String> intToWord;
	private static Digraph graph;
	private static SAP sap;

	/**
	 * The constructor takes the name of the two input files.
	 * 
	 * @param synsets
	 * @param hypernyms
	 */
	public WordNet(String synsets, String hypernyms){
		checkNull(synsets, hypernyms, "File names cannot be null in the constructor");
		wordToInt = new SeparateChainingHashST<>();
		intToWord = new SeparateChainingHashST<>();
		int vertices = 0;		

		vertices = readInSynsets(synsets, vertices);
		graph = new Digraph(vertices);
		readInHypernyms(hypernyms);
		sap = new SAP(graph);

	}

	/**
	 * Returns all WordNet nouns.
	 * 
	 * @return
	 */
	public Iterable<String> nouns()
	{
		return wordToInt.keys();
	}

	/**
	 * Determines whether the word is a WordNet noun.
	 * 
	 * @param word
	 * @return
	 */
	public boolean isNoun(String word) {
		if (word == null)
			throw new NullPointerException("The word cannot be null in isNoun()");
		
		return wordToInt.contains(word);
	}

	/**
	 * Returns the distance between nounA and nounB.
	 * 
	 * @param nounA
	 * @param nounB
	 * @return
	 */
	public int distance(String nounA, String nounB) {
		checkNull(nounA, nounB, "Cannot have null values in distance()");
		checkContains(wordToInt.get(nounA), wordToInt.get(nounB), "The nouns must be in WordNet");

		Iterable<Integer> integerA = wordToInt.get(nounA);
		Iterable<Integer> integerB = wordToInt.get(nounB);

		return sap.length(integerA, integerB);
	}

	/**
	 * Returns a synset (second field of synsets.txt) that is the common
	 * ancestor of nounA and nounB in a shortest ancestral path.
	 * 
	 * @param nounA
	 * @param nounB
	 * @return
	 */
	public String sap(String nounA, String nounB) {
		checkNull(nounA, nounB, "Cannot have null values in sap()");
		checkContains(wordToInt.get(nounA), wordToInt.get(nounB), "The nouns must be in WordNet");

		Iterable<Integer> integerA = wordToInt.get(nounA);
		Iterable<Integer> integerB = wordToInt.get(nounB);

		return intToWord.get(sap.ancestor(integerA, integerB));
	}

	// ******************** Helper Methods ********************

	private void checkNull(String word1, String word2, String message) {
		if (word1 == null || word2 == null)
			throw new NullPointerException(message);
	}

	private void checkContains(Queue<Integer> queue, Queue<Integer> queue2, String message) {
		if (queue == null || queue2 == null)
			throw new IllegalArgumentException(message);
	}

	private int readInSynsets(String synsets, int vertices) {
		In in = new In(synsets);
		
		while(in.hasNextLine())
		{
			vertices++;
			String[] line = in.readLine().split(",");
			String[] words = line[1].split(" ");
			Integer number = Integer.valueOf(line[0]);
			intToWord.put(number, line[1]);
			
			for (int i = 0; i < words.length; i++) {
				Queue<Integer> wordToIntQueue = wordToInt.get(words[i]);
				if (wordToIntQueue == null) {
					wordToIntQueue = new Queue<>();
					wordToIntQueue.enqueue(number);
					wordToInt.put(words[i], wordToIntQueue);
				}
				else {
					wordToIntQueue.enqueue(number);
				}
			}
		}
		return vertices;
	}
	
	private void readInHypernyms(String hypernyms) {
		In in;
		in=new In(hypernyms);
		
		while(in.hasNextLine())
		{
			String[] line = in.readLine().split(",");
			for (int i = 1; i < line.length; i++)
				graph.addEdge(Integer.parseInt(line[0]), Integer.parseInt(line[i]));
		}
	}
	
	// ==================== test client below ====================
	public static void main(String[] args) {
		// see Junit Tests

	}
}