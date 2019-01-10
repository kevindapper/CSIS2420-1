/************************************************
 * Author(s): Gerald Brady, Spencer Rosenvall
 * Class: CSIS 2420
 * Professor: Margarethe Posch
 * Assignment: A02_RadomizedQueuesAndDeques
 ************************************************/

package a02;

import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.StdRandom;

public class RandomizedQueue<Item> implements Iterable<Item> {
	private Item[] items;
	private int size;

	/**
	 * Constructs an empty randomized queue.
	 **/
	@SuppressWarnings("unchecked")
	public RandomizedQueue() {
		items = (Item[]) new Object[1];
		size = 0;
	}

	/**
	 * Checks if the queue is empty.
	 *
	 * @return boolean
	 */
	public boolean isEmpty() {
		return size == 0;
	}

	/**
	 * Returns the number of items on the queue.
	 * 
	 * @return int
	 */
	public int size() {
		return size;
	}

	/**
	 * Adds an item to the queue.
	 * 
	 * @param item
	 */
	public void enqueue(Item item) {
		if (item == null) {
			throw new NullPointerException();
		}
		determineResize();
		items[size] = item;
		size++;
	}

	/**
	 * Deletes and returns a random item.
	 *
	 * @return Item
	 */
	public Item dequeue() {
		if (isEmpty()) {
			throw new java.util.NoSuchElementException();
		}

		int random = StdRandom.uniform(size);
		Item item = items[random];
		size--;
		items[random] = items[size];
		items[size] = null;
		determineResize();
		return item;
	}

	/**
	 * Determines if items array should resize and resizes conditionally.
	 * 
	 */
	private void determineResize() {
		if (size > 2 && size == (items.length / 4)) {
			resize(items.length / 2);
		}
		if (size == items.length) {
			resize(items.length * 2);
		}
	}

	/**
	 * Returns (but does not delete) a random item.
	 * 
	 * @return Item
	 */
	public Item sample() {
		if (isEmpty()) {
			throw new java.util.NoSuchElementException();
		}
		int random = StdRandom.uniform(size);
		return items[random];
	}

	/**
	 * Helper method to resize the array
	 * @param newSize
	 */
	private void resize(int newSize) {
		@SuppressWarnings("unchecked")
		Item tempItems[] = (Item[]) new Object[newSize];

		for (int i = 0; i < size; i++) {
			tempItems[i] = items[i];
		}
		items = tempItems;
	}

	@Override
	public Iterator<Item> iterator() {
		return new IndependentIterator();
	}

	/**
	 * Class IndependentIterator iterates through a list of items.
	 */
	private class IndependentIterator implements Iterator<Item> {

		private Item[] independentIteratorItems;
		private int index;

		/**
		 * Constructs an independent, random iterator.
		 */
		@SuppressWarnings("unchecked")
		public IndependentIterator() {
			independentIteratorItems = (Item[]) new Object[size];
			for (int i = 0; i < size; i++) {
				independentIteratorItems[i] = items[i];
			}
			StdRandom.shuffle(independentIteratorItems);
		}

		/**
		 * Returns a boolean if the iterator has a next item.
		 */
		@Override
		public boolean hasNext() {
			return index < independentIteratorItems.length;
		}

		/**
		 * Returns the next item.
		 */
		@Override
		public Item next() {
			if (hasNext()) {
				return independentIteratorItems[index++];
			} else {
				throw new NoSuchElementException();
			}
		}

		/**
		 * Throws a UnsupportedOperationException because the user shouldn't remove with
		 * this iterator.
		 */
		public void remove() {
			throw new UnsupportedOperationException("Cannot remove during iteration");
		}
	}

	/**
	 * Test client for class RandomizedQueue.java
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		RandomizedQueue<Object> rq = new RandomizedQueue<>();

		System.out.println("isEmpty: " + rq.isEmpty());
		System.out.println();

		rq.enqueue("ninja");
		rq.enqueue("pirate");
		rq.enqueue("cowboy");
		rq.enqueue("warrior");
		System.out.println("Items: ");
		for (Object o : rq.items) {
			System.out.println(o.toString());
		}

		System.out.println("size: " + rq.size);
		System.out.println();

		System.out.println("Random sample: " + rq.sample().toString());
		System.out.println();

		rq.dequeue();
		rq.dequeue();
		System.out.println("Items: ");
		for (Object o : rq.items) {
			if (o != null) {
				System.out.println(o.toString());
			}
		}
		System.out.println("size: " + rq.size);
		System.out.println("isEmpty: " + rq.isEmpty());
	}
}