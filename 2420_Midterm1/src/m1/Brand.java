package m1;

/**
 * @author Midterm1 CSIS-2420 provided code.
 * Class object for a Brand.  Providing Brand name as a String and a
 * trademark registration number as an in.
 */
public class Brand implements Comparable<Brand>  {
	private String name;
	private int registrationNumber;
	
	/**
	 * Creates a Brand object with a Name and a trademark registration number.
	 * @param name Name of the Brand as a String.
	 * @param registrationNumber the Trademark number as an int.
	 */
	public Brand(String name, int registrationNumber) {
		this.name = name;
		this.registrationNumber = registrationNumber; // trade mark registration number
	}
	
	/**
	 * Returns the Brand name of the object. 
	 * @return String name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Returns the Brand trademark number of the object.
	 * @return int registrationNumber
	 */
	public int getRegistrationNumber() {
		return registrationNumber;
	}

	/**
	 * Compares this Brand name with another Brand name.
	 * This Brand is smaller than the other Brand if the name is smaller (alphabetically)
	 */
	@Override
	public int compareTo(Brand other) {
		return this.name.compareToIgnoreCase(other.name);
	}
	
	@Override
	public String toString() {
		return String.format("%-10s#%d", name, registrationNumber);
	}
	
}
