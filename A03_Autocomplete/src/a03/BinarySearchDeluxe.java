package a03;

/******************************************************************************
 * Author(s): Rachel Barton, Gerald Brady
 * Class: CSIS 2420
 * Professor: Margarethe Posch
 * Assignment: A03 Autocomplete
 * 
 * BinarySearchDeluxe represents a generic array search tool for sorted arrays
 * that can be searched for the firstIndex of a given key or by the last index 
 * of a given key. 
 * 
 * References: algs4.cs.princeton.edu/code/edu/princeton/cs/algs4/BinarySearch.
 * java.html
 * 
 ******************************************************************************/

import java.util.Comparator;

public class BinarySearchDeluxe {

    /**
     * Return the index of the first key in a[] that equals the search key, or -1 
     * if no such key exists.
     * 
     * @param a - The array to search through. Must be sorted.
	 * @param key - The key we are searching for the first occurrence of.
	 * @param comparator - The comparator the key is sorted with.
	 * @return The index of the first occurrence of an element matching key in 
	 * a[] or -1 if no such key.
     *
     */
    public static <Key> int firstIndexOf(Key[] a, Key key, Comparator<Key> comparator){
    	if (a == null || key == null || comparator == null) {
    		throw new java.lang.NullPointerException("Parameters cannot be null.");
    	}

    	if (comparator.compare(a[0], key) == 0)
    		return 0;
    	
    	int low = 0;
    	int high = a.length - 1;
    	int mid;
    	
    	while (low <= high){
    		mid = low + (high - low) / 2; 
    		
    		if (comparator.compare(key, a[mid]) < 0)
    			high = mid - 1;
    		else if (comparator.compare(key, a[mid]) > 0)
    			low = mid + 1;
    		else if (comparator.compare(a[mid - 1], a[mid]) == 0)
    			high = mid - 1;
    		else return mid;
    	}
		return -1;
    }

    /**
     * Return the index of the last key in a[] that equals the search key, or 
     * -1 if no such key.
     * 
     * @param a - The array to search through. Must be sorted.
     * @param key - The key we are searching for the last occurrence of.
     * @param comparator - The comparator the key is sorted with.
     * @return The index of the last occurrence of an element matching key in 
     * a[] or -1 if no such key.
     *
     */
    public static <Key> int lastIndexOf(Key[] a, Key key, Comparator<Key> comparator){
    	if (a == null || key == null || comparator == null) {
    		throw new java.lang.NullPointerException("parameters cannot be null");
    	}
    	
    	int low = 0;
    	int high = a.length - 1;
    	int mid;
    	
    	if (comparator.compare(a[high], key) == 0)
    		return high;
    	
    	while (low <= high){
    		mid = low + (high - low) / 2;

            if (comparator.compare(key, a[mid]) < 0)
            	high = mid -1;
            else if (comparator.compare(key, a[mid]) > 0)
            	low = mid +1;
            else if (comparator.compare(a[mid + 1], a[mid]) == 0)
            	low = mid + 1;
    		else return mid;
        }
        return -1;
    }
}