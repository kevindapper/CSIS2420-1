package m1;

import java.util.Arrays;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

/**
 * Midterm1 includes the code for CSIS-2420 Midterm1 Fall 2018
 * @author Gerald Brady
 *
 */
public class Midterm1 {

	public static void main(String[] args) {
		Brand[] brands = {
				new Brand("Nike", 1441334),	
				new Brand("adidas", 3686084),	
				new Brand("Microsoft", 1200236),	
				new Brand("lenovo", 3226026),	
				new Brand("SAMSUNG", 3503660),	
				new Brand("nook", 4286950),
				new Brand("IBM", 2183815)
		};
		
		StdOut.println("Brands:");
		StdOut.println("=======");
		printBrands(brands);
		
		
		// = = = =  Part 1	= = = = 		
		Arrays.sort(brands); 
		
		StdOut.println("Brands by name:");
		StdOut.println("===============");
		printBrands(brands);
		

		// = = = =  Part 2a  = = = = 	
		Stack<Brand> s = new Stack<Brand>();
		for (int i = 0; i < brands.length; i++)
			s.push(brands[i]);				
		
		// = = = =  Part 2b  = = = = 
		Queue<String> s2 = new Queue<String>();
		
		while(!s.isEmpty()){
			if(s.peek().getName().length() <= 6)
			{
				if (Character.isUpperCase(s.peek().getName().charAt(0)))
				{
					s2.enqueue(s.pop().getName().toUpperCase());
				}else s2.enqueue(s.pop().getName());
			}else s.pop();
		}
		
		StdOut.println("Names in reverse order:");
		StdOut.println("=======================");
		
		while(!s2.isEmpty()){
			StdOut.println(s2.dequeue());
		}

	}

	/** 
	 * Prints all the elements of array brands side by side, separated by a single blank.
	 * @param brands
	 */
	private static void printBrands(Brand[] brands) {
		for(Brand c : brands) {
			StdOut.println(c);
		}	
		System.out.println();
	}

}
