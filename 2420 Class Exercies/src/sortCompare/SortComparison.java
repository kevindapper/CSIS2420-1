package sortCompare;

import java.util.Arrays;

import edu.princeton.cs.algs4.Quick;
import edu.princeton.cs.algs4.Selection;
import edu.princeton.cs.algs4.StdRandom;

/**
 * SprtComparison compares the performance of sorting algorithms by
 * measuring the time it takes to sort arrays of doubling sizes.
 * 
 * @author Gerald Brady
 *
 */
public class SortComparison {

	public static void main(String[] args) {
		int size = 5_000;
		Integer[] numbersSelection;
		Integer[] numbersQuick;
		int numberOfDigits = 8;
		long startTime;
		long endTime;
		double elapsedTime;
		String duration = "";
		
//		numbers = getRandomNumbers(15, 3);
//		System.out.println(Arrays.toString(numbers));
//		System.out.println();
		
		System.out.printf("%-25s %s%n", "Selection Sort", "Quick Sort");
		System.out.printf("%-25s %s%n", "------------------------", "------------------");
		
		for (int i = 0; i < 7; i++){
			numbersSelection = getRandomNumbers(size, numberOfDigits);
			numbersQuick = Arrays.copyOf(numbersSelection, numbersSelection.length);
			
			//Selection Sort
			startTime = System.nanoTime();
			Selection.sort(numbersSelection);
			endTime = System.nanoTime();
			elapsedTime = (endTime - startTime) / 1000_000d;//the "d" at the end will remove the truncation on the divide
			
			duration = String.format("%.3f ms", elapsedTime);
			System.out.printf("%-7d %-17s ", size, duration);
			
			//Quick Sort
			startTime = System.nanoTime();
			Quick.sort(numbersQuick);
			endTime = System.nanoTime();
			elapsedTime = (endTime - startTime) / 1000_000d;//the "d" at the end will remove the truncation on the divide
			
			duration = String.format("%.3f ms", elapsedTime);
			System.out.printf("%-7d %-17s %n", size, duration);
			
			size *=2;
		}

	}
	
	/**
	 * creates an array of the specified size and fills it with random
	 * numbers who have the specified number of digits.
	 * @param size
	 * @return the array of random numbers
	 */
	private static Integer[] getRandomNumbers(int size, int numberOfDigits){
		if (size < 0) 
			throw new IllegalArgumentException("size must be a non negiative number");
		if (numberOfDigits < 1 || numberOfDigits > 9) 
			throw new IllegalArgumentException("The number of digits needs to be between 1 and 8");

		Integer[] array = new Integer[size];
		int lowerBound = (numberOfDigits == 1) ? 0 :(int) Math.pow(10, numberOfDigits - 1);
		int upperBound = (int) Math.pow(10, numberOfDigits);
		
		for (int i = 0; i < size; i++){
			array[i] = StdRandom.uniform(lowerBound, upperBound);// you may use the _ in place of a comma to spit out a number
		}
		
		return array; //TODO
	}

}
