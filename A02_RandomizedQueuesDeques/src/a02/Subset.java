package a02;

/************************************************
 * Author(s): Gerald Brady, Spencer Rosenvall
 * Class: CSIS 2420
 * Professor: Margarethe Posch
 * Assignment: A02_RadomizedQueuesAndDeques
 ************************************************/

import edu.princeton.cs.algs4.StdRandom;

/**
 * Class Subset is a test client for classes Deque.java and
 * RandomizedQueue.java. Class demonstrates operation of RandomizedQueue by
 * adding and removing Strings from the queue.
 * 
 * @author Gerald Brady, Spencer Rosenvall
 *
 */
public class Subset {
	public static void main(String[] args) {
		//String[] stringArray = {"A", "B", "C", "D", "E", "F", "G", "H", "I"};
		String[] stringArray = {"AA", "BB", "BB", "BB", "BB", "BB", "CC", "CC"};
		int k = StdRandom.uniform(1, stringArray.length);
		RandomizedQueue<String> rq = new RandomizedQueue<>();
		System.out.print(" % echo ");
		
		// Add String to queue.
		for (String s : stringArray) {
			rq.enqueue(s);
			System.out.print(rq.sample() + " ");
		}
		System.out.println("| java Subset " + k);

		// Remove String from deque.
		for (int i = 0; i < k; i++) {
			System.out.println(rq.dequeue());
		}
	}
}