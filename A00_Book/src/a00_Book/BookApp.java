package a00_Book;

import java.util.List;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Test client to confirm Book class runs.  Takes a csv file and sends it to the
 * Book app to be divided into a list. 
 * 
 * @author Gearld Brady
 */
public class BookApp {

	public static void main(String[] args) {
		List<Book> books = new ArrayList<Book>(); 
		
		try {
			books = Book.getList("books.csv");
		} catch (FileNotFoundException e) {
			System.out.println("missing file");
			e.printStackTrace();
		}
		
		System.out.println("Number of books read in: " + books.size());
		System.out.println();
		
		printListOfBooks(books);
				
		Collections.reverse(books);		
		printListOfBooks(books);
		
	}

	/**
	 * Method to print out to the screen a list.
	 * @param books
	 */
	private static void printListOfBooks(List<Book> books) {
		System.out.println(books);
	}

}
