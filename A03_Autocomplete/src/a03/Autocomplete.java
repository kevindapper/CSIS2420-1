package a03;

/******************************************************************************
 * Author(s): Rachel Barton, Gerald Brady
 * Class: CSIS 2420
 * Professor: Margarethe Posch
 * Assignment: A03 Autocomplete
 * 
 * Autocomplete represents an array of sorted Terms that can be searched for 
 * all matches of a given Terms query and return an integer total of all matches 
 * of a given Terms query.
 * 
 ******************************************************************************/

import java.util.Arrays;

public class Autocomplete {
	private Term[] terms;
	
    /**
     * Initializes a sorted array data structure from the given array of terms.
     * 
     * @param terms
     * 
     */
    public Autocomplete(Term[] terms){
    	if (terms == null) {
    		throw new java.lang.NullPointerException("Terms can't be null");
    	}
    	
    	this.terms = Arrays.copyOf(terms, terms.length);
    	Arrays.sort(this.terms);
    }

    /**
     * Return all terms that start with the given prefix, in descending order of 
     * weight.
     * 
     * @param prefix - String to search given array with. 
     * @return Term[] containing all matches for given prefix.
     *
     */
    public Term[] allMatches(String prefix){
    	if (prefix == null) {
    		throw new java.lang.NullPointerException("Prefix can't be null");
    	}
    	
    	Term key = new Term (prefix,0); 
    	
    	int firstOccurence = BinarySearchDeluxe.firstIndexOf(terms, key, 
    			Term.byPrefixOrder(prefix.length()));
    	if (firstOccurence == -1)
    		return new Term[0];
    	
    	int lastOccurrence = BinarySearchDeluxe.lastIndexOf(terms, key, 
    			Term.byPrefixOrder(prefix.length()));
    	Term[] autoIndex = new Term[1 + lastOccurrence - firstOccurence];
    	
    	for (int i = 0; i < autoIndex.length; i++){
    		autoIndex[i] = terms[firstOccurence + i];
    	}

    	Arrays.sort(autoIndex, Term.byReverseWeightOrder());    	
    	return autoIndex;
    }

    /**
     * Return the number of terms that start with the given prefix.
     * 
     * @param prefix String to search given array with. 
     * @return int - Total number of matches in the Term array.
     * 
     */
    public int numberOfMatches(String prefix){
    	if (prefix == null) {
    		throw new java.lang.NullPointerException("Prefix can't be null");
    	}
    	
    	Term key = new Term (prefix,0);
    	int firstIndex = BinarySearchDeluxe.firstIndexOf(terms, key, 
    			Term.byPrefixOrder(prefix.length()));
    	int lastIndex = BinarySearchDeluxe.lastIndexOf(terms, key, 
    			Term.byPrefixOrder(prefix.length()));
    	
    	return 1 + lastIndex - firstIndex;
    }
}