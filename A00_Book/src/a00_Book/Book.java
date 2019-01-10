package a00_Book;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/**
 * Comparable assignment
 * Book object,  Parses out the title author and year from a provided file.
 * 
 * @author Gerald Brady
 */
public class Book implements Comparable<Book> {

	private String title;
	private String author;
	private int year;
	
	public Book(String title, String author, int year)
	{
		this.title = title;
		this.author = author;
		this.year = year;
	}

	public String getTitle() {
		return title;
	}

	public String getAuthor() {
		return author;
	}

	public int getYear() {
		return year;
	}

	/**
	 * A file sent is parsed out into 3 sections including the author, title, and the year. 
	 * If additional info is provided a message indicating data does not match required formate is
	 *    sent. 
	 * 
	 * @param file
	 * @return
	 * @throws FileNotFoundException
	 */
	public static List<Book> getList (String file) throws FileNotFoundException{
		List<Book> bookList = new LinkedList<Book>();
		Scanner in = new Scanner(new File (file));
		String entryLine = "";
		
		while (in.hasNextLine())
		{
			try
			{
				entryLine = in.nextLine();
				String[] entries = entryLine.split(",");
				if (entries.length <= 3)
				{
					Book b = new Book(entries[0], entries[1], Integer.parseInt(entries[2]));
					bookList.add(b);
				}
				else
				{
					System.out.println("Problem reading in " + entryLine);
				}
			}
			catch(NumberFormatException | ArrayIndexOutOfBoundsException e)
			{
				System.out.println("Problem reading in " + entryLine);
			}
		}
		return bookList;
	}
	
	@Override
	public String toString() {
		return title + " by " + author + " (" + year + ")\n";
	}

	@Override
	public int compareTo(Book other) {

		int result;
		result = this.title.compareTo(other.title);
		
		if (result == 0)
		{
			result = this.author.compareTo(other.author);
			if (result == 0)
			{
				if ((this.year - other.year) == 0)
				{
					return result;
				}
			}
		}	
		return result;
	}
}