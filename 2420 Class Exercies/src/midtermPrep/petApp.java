package midtermPrep;

import java.util.Arrays;
import java.util.Collections;

import edu.princeton.cs.algs4.Insertion;

public class petApp {

	public static void main(String[] args) {
//		Pet cat = new Pet("Luna", 2);
//		Pet dog = new Pet("Max", 5);
		Pet[] pets = {new Pet("Luna", 2), new Pet("Max", 2), new Pet("Paul", 7), new Pet("Martin", 2), new Pet("Luna", 3)};
		printPets(pets);
		
		Arrays.sort(pets);
		printPets(pets);
		
		// sort the array in reverse order
		Arrays.sort(pets, Collections.reverseOrder());
		printPets(pets);
		
		// sore the first 3 elements of the array pets
		Insertion.sort(pets, 0, 2);
		printPets(pets);
		
		// use class Insertion to sort
		Insertion.sort(pets);
		printPets(pets);

	}

	private static void printPets(Pet[] pets) {
		for (Pet p : pets){
			System.out.print(p + " ");
		}
		System.out.println();
	}
}
