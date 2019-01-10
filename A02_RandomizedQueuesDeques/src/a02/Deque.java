/************************************************
 * Author(s): Gerald Brady, Spencer Rosenvall
 * Class: CSIS 2420
 * Professor: Margarethe Posch
 * Assignment: A02_RadomizedQueuesAndDeques
 ************************************************/

package a02;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Class Deque implements iterable and can add, remove, and insert generic Items
 * to/from Deque.
 * 
 * @authors Gerald Brady, Spencer Rosenvall
 *
 * @param <Item>
 */
public class Deque<Item> implements Iterable<Item> {

	private Node<Item> head; // beginning of queue
	private Node<Item> tail; // end of queue
	private int size;

	/**
	 * Class Node creates a node of type item to be added to the Deque. Class Node
	 * provides us with information about neighboring nodes, which in turn provides
	 * self-position.
	 * 
	 * @authors Gerald Brady, Spencer Rosenvall
	 *
	 * @param <Item>
	 */
	private static class Node<Item> {
		private Item item;
		private Node<Item> next;
		@SuppressWarnings("unused")
		private Node<Item> previous;
	}

	/**
	 * Create an empty Deque.
	 * 
	 */
	public Deque() {
		size = 0;
	}

	/**
	 * Checks to see if the Deque is empty?
	 * 
	 * @return
	 * 
	 */
	public boolean isEmpty() {
		if (size() <= 0) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Return the number of Items on the Deque.
	 * 
	 * @return
	 * 
	 */
	public int size() {
		return size;
	}

	/**
	 * Inserts Item at the front of the Deque.
	 * 
	 * @param item
	 * 
	 */
	public void addFirst(Item item) {
		if (item != null) {
			if (size() == 0) {
				head = new Node<Item>();
				head.item = item;
				tail = head;
			} else {
				Node<Item> predecessor = head;
				head.item = item;
				head.next = predecessor;
			}
			size++;
		} else {
			throw new NullPointerException("Cannot insert null item.");
		}
	}

	/**
	 * Inserts the Item at the end of the Deque.
	 * 
	 * @param item
	 */
	public void addLast(Item item) {
		if (item != null) {
			if (size() == 0) {
				head = new Node<Item>();
				head.item = item;
				tail = head;
			} else {
				Node<Item> successor = tail;
				tail.item = item;
				tail.previous = successor;
				successor.next = tail;
			}
			size++;
		} else {
			throw new NullPointerException("Cannot insert null item.");
		}
	}

	/**
	 * Delete and return the item at the front of the Deque.
	 * 
	 * @return
	 * 
	 */
	public Item removeFirst() {
		if (!isEmpty()) {
			Item i = head.item;
			head = head.next;
			size--;
			if (size <= 0) {
				tail = null;
			}
			return i;
		} else {
			throw new NoSuchElementException("No item to remove from deque");
		}
	}

	/**
	 * Delete and return the item at the end of the Deque.
	 * 
	 * @return
	 * 
	 */
	public Item removeLast() {
		if (!isEmpty()) {
			Item i = tail.item;
			tail = tail.next;
			size--;
			if (size <= 0) {
				tail = null;
			}
			return i;
		} else {
			throw new NoSuchElementException("No item to remove from deque");
		}
	}

	/**
	 * Creates a DequeIterator object.
	 */
	@Override
	public Iterator<Item> iterator() {
		return new DequeIterator();
	}

	/**
	 * Class DequeIterator iterates through from front to end.
	 * 
	 * @authors Gerald Brady, Spencer Rosenvall
	 *
	 */
	private class DequeIterator implements Iterator<Item> {

		private Node<Item> current = head;

		/**
		 * Checks is the deque has a next item.
		 */
		@Override
		public boolean hasNext() {
			return current == null ? false : true;
		}

		/**
		 * Returns the next item in the deque.
		 */
		@Override
		public Item next() {
			if (!hasNext()) {
				throw new NoSuchElementException("No next item, end of deque reached.");
			}
			Item next = current.item;
			current = current.next;
			return next;
		}

		/**
		 * Throws an UnsopportedOperationException if remove() is attempted.
		 */
		@Override
		public void remove() {
			throw new UnsupportedOperationException("Cannot remove");
		}
	}

	/**
	 * Test client for class Deque.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		Deque<Object> d = new Deque<>();

		System.out.println("isEmpty: " + d.isEmpty());
		System.out.println("Size: " + d.size());
		System.out.println();

		d.addFirst("ninja");
		d.addFirst("toaster");
		d.addLast("I am last");

		System.out.println("isEmpty: " + d.isEmpty());
		System.out.println("Size of items inserted to beginning and end of deque: " + d.size());
		System.out.println();

		d.removeLast();
		System.out.println("Size after last item removed: " + d.size());
		System.out.println();

		d.removeFirst();
		System.out.println("Size after first item removed: " + d.size());
	}

}