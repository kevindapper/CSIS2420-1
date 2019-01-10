package recursion;

public class DemoRecursion {

	/**
	 * Calculates the sum of 1 + 2 + ... + n.
	 * @param n
	 * @return
	 */
	public static int sum(int n){
		if (n < 0)
			throw new IllegalArgumentException("the parameter n can't be negative.");
		
		if (n == 0)
			return 0;
		return n + sum(n-1);
		
	}
	
	/**
	 * Returns a string that Collatz sequence for a given number n.
	 * @param n
	 */
	public static String collatzSequence(int n){		
		if (n == 1)
			return "1 ";
		
		int next;		
		if ((n % 2) == 0)
			next = n / 2;
		else 
			next = n * 3 + 1;
		
		return n + " " + collatzSequence(next);//TODO
	}
}
