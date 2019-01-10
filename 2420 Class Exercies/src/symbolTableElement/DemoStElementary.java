package symbolTableElement;

import edu.princeton.cs.algs4.BinarySearchST;
import edu.princeton.cs.algs4.SET;

public class DemoStElementary {

	public static void main(String[] args) {
		BinarySearchST<Integer, Pet> st = new BinarySearchST<>();
		
		st.put(1, new Pet("Aba", 13, "German Shepherd"));
		st.put(2, new Pet("Ben", 6,  "Bird"));
		st.put(3, new Pet("Cem", 2,  "Cat"));
		st.put(4, new Pet("Dan", 20, "Parrot"));
		st.put(5, new Pet("Eva", 99, "Turtle"));
		st.put(6, new Pet("Fin", 7,  "Penguin"));
		st.put(7, new Pet("Gal", 33, "Squirrel"));
		st.put(11, new Pet("Ada", 13, "Cat"));
		st.put(12, new Pet("Bob", 6,  "Bird"));
		st.put(23, new Pet("Cat", 2,  "Bat"));
		st.put(24, new Pet("Don", 20, "Ferret"));
		st.put(55, new Pet("Eli", 99, "Turtle"));
		st.put(56, new Pet("Fin", 7,  "Fish"));
		st.put(77, new Pet("Grr", 33, "Dog"));

		//contains key
		demoContains(st, 10);
		demoContains(st, 7);
		
		System.out.println("The smallest pet Key ID: " + st.min() + " - entry is: " + st.get(st.min()));
		System.out.println("The smallest pet Key ID > 15: " + st.ceiling(15)  + " - entry is: " + st.get(st.ceiling(15)));
		printSTSize(st);
		
		st.put(100, new Pet("Sylvester", 10,  "Cat"));
		printSTSize(st);
		
		System.out.println("Number of pets IDs smaller then 25: " + st.rank(25));
		demoContains(st, 100);// use get method
		System.out.println("The 3rd smallest pet ID in the st is: " + st.select(2) + " - " 
				+ st.get(st.select(2)));// select uses the index
		st.put(100, new Pet("Garfield", 10, "Cat"));
		demoContains(st, 100);
		System.out.println();
		
		System.out.println("PetID  Pet Info");
		System.out.println("--------------------");
		for(Integer key : st.keys())
			System.out.printf("%-5d  %s%n", key, st.get(key));
		System.out.println();
		
		System.out.println("all pet names:");
		for(Integer key : st.keys())
			System.out.print(st.get(key).getName() + " ");
		System.out.println("\n");
		
		System.out.println("Species in the table are:");
		SET<String> set = new SET<>();
		for(Integer key : st.keys()){
			set.add(st.get(key).getSpecies());
		}
		for(String species : set){
			System.out.print(species + " ");
		}
		System.out.println("\n");
	}

	private static void printSTSize(BinarySearchST<Integer, Pet> st) {
		System.out.println("Number of pets in the Symbol Table: " + st.size());
	}

	private static void demoContains(BinarySearchST<Integer, Pet> st, int key) {
		if (st.contains(key))
			System.out.println("st contains key " + key + ": " + st.get(key));
		else
			System.out.println("key " + key + " not found");
	}

}
