package searchingApp;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.ST;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/******************************************************************************
 *  Compilation:  javac LookupCSV.java
 *  Execution:    java LookupCSV file.csv keyField valField
 *  Dependencies: ST.java In.java StdIn.java StdOut.java
 *  Data files:   https://algs4.cs.princeton.edu/35applications/DJIA.csv
 *                https://algs4.cs.princeton.edu/35applications/UPC.csv
 *                https://algs4.cs.princeton.edu/35applications/amino.csv
 *                https://algs4.cs.princeton.edu/35applications/elements.csv
 *                https://algs4.cs.princeton.edu/35applications/ip.csv
 *                https://algs4.cs.princeton.edu/35applications/morse.csv
 *  
 *  Reads in a set of key-value pairs from a two-column CSV file
 *  specified on the command line; then, reads in keys from standard
 *  input and prints out corresponding values.
 * 
 *  % java LookupCSV amino.csv 0 3     % java LookupCSV ip.csv 0 1 
 *  TTA                                www.google.com 
 *  Leucine                            216.239.41.99 
 *  ABC                               
 *  Not found                          % java LookupCSV ip.csv 1 0 
 *  TCT                                216.239.41.99 
 *  Serine                             www.google.com 
 *                                 
 *  % java LookupCSV amino.csv 3 0     % java LookupCSV DJIA.csv 0 1 
 *  Glycine                            29-Oct-29 
 *  GGG                                252.38 
 *                                     20-Oct-87 
 *                                     1738.74
 *
 *
 ******************************************************************************/

/**
 *  The {@code LookupCSV} class provides a data-driven client for reading in a
 *  key-value pairs from a file; then, printing the values corresponding to the
 *  keys found on standard input. Both keys and values are strings.
 *  The fields to serve as the key and value are taken as command-line arguments.
 *  <p>
 *  For additional documentation, see <a href="https://algs4.cs.princeton.edu/35applications">Section 3.5</a> of
 *  <i>Algorithms, 4th Edition</i> by Robert Sedgewick and Kevin Wayne.
 *  
 *  @author Robert Sedgewick
 *  @author Kevin Wayne
 */
public class StocksApp {

	private static SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");  
	
    // Do not instantiate.
    private StocksApp() { }

    public static void main(String[] args) throws ParseException {
        String file = "src/searchingApp/Toyota";
        int closeValue = 4; // close column
        int openValue = 1; // close column
        ST<Date, Double> toyotaST = readInDataFromCsv(closeValue, file);
        ST<Date, Double> toyotaOpenST = readInDataFromCsv(openValue, file);
        
        printPart1(toyotaST);
        printStep4(toyotaST);
        printStep5(toyotaST);
        printPart6(toyotaST);
        printPart7(toyotaOpenST);
        printPart8(toyotaOpenST);
        printPart9(toyotaOpenST);
        
        //Step 10
        System.out.println("File only includes 1 date.  Opening and Closing date is undefined in the csv.");
        System.out.println("max() and min() provide the first and last dates in the symbol table.");
        System.out.println("If we were looking for a opening and closing value for each date the symbol"
        		+ " table would need to store additional values for each key.");
        System.out.println("I created a 2nd symbol table for the opening values.");
        
        // Original full list printout
//        for (Date key : toyotaST.keys()){
//        	System.out.println(key + " . . " + toyotaST.get(key));
//        }
    }

	private static void printPart9(ST<Date, Double> toyotaOpenST) {
		System.out.println("Most recent opening value: ");
        System.out.println(toyotaOpenST.max() + " " + toyotaOpenST.get(toyotaOpenST.floor(toyotaOpenST.max())));
        System.out.println("");
	}

	private static void printPart8(ST<Date, Double> toyotaOpenST) throws ParseException {
		Date end = formatter.parse("2009-12-31");
        System.out.println("Opening value at the end of 2009: ");
        System.out.println(toyotaOpenST.floor(end) + " " + toyotaOpenST.get(toyotaOpenST.floor(end)));
        System.out.println("");
	}

	private static void printPart7(ST<Date, Double> toyotaOpenST) throws ParseException {
		Date startKey = formatter.parse("2012-12-01");
        Date endKey = formatter.parse("2012-12-31");
        System.out.println("All opening values in December 2012: ");
        for (Date key : toyotaOpenST.keys()){
        	if (key.compareTo(startKey) >= 0 && key.compareTo(endKey) <= 0){
        		System.out.println(key + " Opens: " + toyotaOpenST.get(key));
        		
        	}
        }
        System.out.println("");
	}

	private static void printPart6(ST<Date, Double> toyotaST) throws ParseException {
		Date startKey = formatter.parse("2012-12-01");
        Date endKey = formatter.parse("2012-12-31");
        System.out.println("All closing values in December 2012: ");
        for (Date key : toyotaST.keys()){
        	if (key.compareTo(startKey) >= 0 && key.compareTo(endKey) <= 0){
        		System.out.println(key + " closes: " + toyotaST.get(key));
        		
        	}
        }
        System.out.println("");
	}

	private static void printStep5(ST<Date, Double> toyotaST) throws ParseException {
		Date close2005key = formatter.parse("2005-01-01");
        System.out.println("First closing value in 2005: ");
        System.out.println(toyotaST.ceiling(close2005key) + " " + toyotaST.get(toyotaST.ceiling(close2005key)));
        System.out.println("");
	}

	private static void printStep4(ST<Date, Double> toyotaST) {
		System.out.println("Most Recent closing Value: " + toyotaST.get(toyotaST.max()));
        System.out.println("Oldest closing Value: " + toyotaST.get(toyotaST.min()));
        System.out.println("");
	}

	private static void printPart1(ST<Date, Double> toyotaST) throws ParseException {
		System.out.println("Records read: " + toyotaST.size());
        Date startKey = formatter.parse("2012-03-12");
        Date endKey = formatter.parse("2015-03-12");
        System.out.println("Closing price on 2012-03-12: " + toyotaST.get(startKey));
        System.out.println("Closing price on 2015-03-12: " + toyotaST.get(endKey));
        System.out.println("");
	}
    
    // reads in stock data from a csv file. The key is always the data in column 0.
    private static ST<Date, Double> readInDataFromCsv(int valField, String stockFile)
    		throws ParseException {
    	
    	int keyField = 0; // date column
        //SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");        
        
        // symbol table
        ST<Date, Double> st = new ST<>();

        // read in the data from csv file
        In in = new In(stockFile);
        if (in.hasNextLine()) in.readLine();//remove header
        
        while (in.hasNextLine()) {
            String line = in.readLine();
            String[] tokens = line.split(",");
            Date key = formatter.parse(tokens[keyField]);
            Double val = Double.parseDouble(tokens[valField]);
            st.put(key, val);
        }
    	return st;
    }
}