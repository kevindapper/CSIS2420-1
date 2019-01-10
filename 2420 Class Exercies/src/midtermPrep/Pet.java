package midtermPrep;

/**
 * Pet that has a name and an age
 * @author spikegif
 *
 */
public class Pet implements Comparable<Pet>{
	private String name;
	private int age;
	
	/**
	 * Initializes the fields name and age
	 * @param name
	 * @param age
	 */
	public Pet(String name, int age) {
		this.name = name;
		this.age = age;
	}

	/**
	 * Compares this pet with the other pet, first by name then by age.
	 * That means,
	 * This pet is smaller than the other pet in the name is smaller (alphabetically)
	 * If the names are the same, the pet is smaller if the age is smaller.
	 */
	@Override
	public int compareTo(Pet other) {
		if (this.name.compareTo(other.name) != 0)
			return this.name.compareTo(other.name);
		return Integer.compare(this.age, other.age);
	}
	
	@Override
	public String toString() {
		return name + "[" + age + "]";
	}

}
