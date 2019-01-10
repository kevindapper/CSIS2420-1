package a03;

/*****************************************************************************
 * Author(s): Rachel Barton, Gerald Brady
 * Class: CSIS 2420
 * Professor: Margarethe Posch
 * Assignment: A03 Autocomplete
 * 
 * Class Term represents Terms with a query and weight which can be 
 * compared and sorted by reverse weight order, lexicographic order, and by a 
 * specified length lexicographic prefix order. 
 * 
 ******************************************************************************/

import java.util.Comparator;

public class Term implements Comparable<Term> {

	private String query;
	private double weight;

	/**
	 * Initializes a term with the given query (string) and weight (double).
	 * 
	 * @param query of type String.
	 * @param weight of type double.
	 * 
	 */
	public Term(String query, double weight) {
		if (query == null)
			throw new java.lang.NullPointerException("Cannot be null. ");

		if (weight < 0)
			throw new IllegalArgumentException("Weight cannot be a negative. ");

		this.query = query;
		this.weight = weight;
	}

	/**
	 * Compares the terms in descending order by weight.
	 * 
	 * @return a Comparator of terms in descending order by weight.
	 *
	 */
	public static Comparator<Term> byReverseWeightOrder() {
		Comparator<Term> reverseWieghtOrderComparator = new Comparator<Term>() {
			public int compare(Term term1, Term term2) {
				
				if (term1.weight == term2.weight) 
					return 0;
				if (term1.weight > term2.weight)
					return -1;
				return 1;
			}
		};
		return reverseWieghtOrderComparator;
	}
	
	/**
	 * Compares the terms in lexicographic order but using only the first r
	 * characters of each query.
	 * 
	 * @param r number of characters to compare from each query.
	 * @return a Term comparator of the terms in lexicographic order using only
	 * the first r characters of each query.
	 *         
	 */
	public static Comparator<Term> byPrefixOrder(int r) {
		if (r < 0)
			throw new java.lang.IllegalArgumentException("r cannot be a negative. ");

		return new Comparator<Term>() {
     		public int compare(Term term1, Term term2) {
     			String term1Query = term1.query.toLowerCase();
     			String term2Query = term2.query.toLowerCase();
     			int minLength = less(term1Query.length(), term2Query.length(), r);
     			     			
     			return term1Query.substring(0, minLength).
     					compareTo(term2Query.substring(0, minLength));
    		}

			private int less(int i, int j, int z) {
				int min = z;
				if (i < z) min = i;
				if (j < i) min = j;

				return min;
			}
    	};
	}

	/**
	 * Compares terms in lexicographic order by query.
	 * 
	 * @return int a negative integer if this query is less then that, 0 if 
	 * both are equal, and positive integer if this query is greater.
	 * 
	 */
	public int compareTo(Term that) {
		return this.query.compareToIgnoreCase(that.query); // ignore cases 
	}

	/**
	 * Returns a string representation of the term in the following format: 
	 * The weight, followed by a tab, followed by the query. 
	 * 
	 * @return a string representation of the term.
	 * 
	 */
	public String toString() {
		return Double.toString(this.weight) + "\t" + this.query; 
	}
}