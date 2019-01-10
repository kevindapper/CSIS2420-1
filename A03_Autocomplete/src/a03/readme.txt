/******************************************************************************
 *  Name: Gerald Brady and Rachel Barton    
 *  
 *  Hours to complete assignment (optional): Combined (30 hours)
 *
 ******************************************************************************/

Programming Assignment 3: Autocomplete

/******************************************************************************
 *  Describe how your firstIndexOf() method in BinarySearchDeluxe.java
 *  finds the first index of a key that equals the search key.
 *	
 *  We started with the BinarySearch.java by Robert Sedgewick and Kevin Wayne. 
 *  Initially low represents start of array [0], and high the array.length-1. If
 *  low remained less than high we compared the key with a mid point in the array. 
 *  If the key was less we adjust the high to mid -1 (essentially splitting the 
 *  array in half to search). If key is greater then we set the low pointer to mid 
 *  + 1, splitting the array in half to search again. To ensure we have the first
 *  occurrence we then compare mid to mid-1 to check if it was equal stopping when 
 *  it is no longer equal.     
 *
 *****************************************************************************/

/******************************************************************************
 *  What is the order of growth of the number of compares (in the
 *  worst case) that each of the operations in the Autocomplete
 *  data type make, as a function of the number of terms n and the
 *  number of matching terms m?
 *
 *  Recall that with order-of-growth notation, you should discard
 *  leading coefficients and lower-order terms, e.g., m^2 + m log n.
 *****************************************************************************/

constructor: n log n  (when consulting Google on Arrays.sort())

allMatches(): log n + n + m log m

numberOfMatches(): log n

/******************************************************************************
 *  Known bugs / limitations. 
 *
 *  None that we are aware of.
 *
 *****************************************************************************/

/******************************************************************************
 *  Describe whatever help (if any) that you received.
 *  Don't include readings, lectures, and precepts, but do
 *  include any help from people (including course staff, lab TAs,
 *  classmates, and friends) and attribute them by name.
 *
 *  Also include any resources (including the web) that you may
 *  may have used in creating your design.
 * 
 *  We used Algs4 BinarySearch.java, we also consulted java 8 for information on
 *  Arrays.sort time complexity. 
 *****************************************************************************/

/******************************************************************************
 *  Describe any serious problems you encountered.                 
 *****************************************************************************/

/******************************************************************************
 *  If you worked with a partner, assert below that you followed
 *  the protocol as described on the assignment page. Give one
 *  sentence explaining what each of you contributed.
 * 
 *  We both worked on a variety of code. At times we met up and pair programmed,
 *  sent code with bugs and fixes back and forth, adopted and removed code
 *  here and there. All in all it was a 50/50 effort.
 *****************************************************************************/

/******************************************************************************
 *  List any other comments here. Feel free to provide any feedback   
 *  on how much you learned from doing the assignment, and whether    
 *  you enjoyed doing it.                                             
 *****************************************************************************/