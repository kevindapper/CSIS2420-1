package symbolTableElement;

public class Pet {
	private String name;
	private int age;
	private String species;
	
	/**
	 * Initializes the fields with the values provied by the user.
	 * @param name
	 * @param age
	 * @param species
	 */
	public Pet(String name, int age, String species) {
		this.name = name;
		this.age = age;
		this.species = species;
	}
	
	/**
	 * Returns the name of the Object
	 * @return
	 */
	public String getName() {
		return name;
	}

	/**
	 * Returns the age of the Object
	 * @return
	 */
	public int getAge() {
		return age;
	}

	/**
	 * Returns the Species of the object
	 * @return
	 */
	public String getSpecies() {
		return species;
	}

	@Override
	public String toString() {
		return name + ": a " + species + " whom is " + age + " years old";
	}
	
	
}
