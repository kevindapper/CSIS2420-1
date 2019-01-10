package priorityQueue;

import edu.princeton.cs.algs4.StdRandom;

/**
 * Represents an article of mail that is defined by its type, priority, and the 
 * name of the recipient. //What is this class for
 * This class allows us to create mail based on Arguments provided by he user or
 * a random piece of mail. //What does it do
 * 
 * @author Gerald Brady
 *
 */
public class Mail implements Comparable<Mail>{
	private MailType type;
	private MailPriority priority;
	private String name;
	
	/**
	 * Initializes a new instance of Mail with he values provided by user.
	 * 
	 * @param type
	 * @param priority
	 * @param name
	 */
	public Mail(MailType type, MailPriority priority, String name) {
		super();
		this.type = type;
		this.priority = priority;
		this.name = name;
	}

	/**
	 * Initializes a new instance of Mail with random values.
	 * The random name should be of the form Mr. X or Ms. Y, where X, Y could be any uppercase letter.
	 */
	public Mail(){
		this.type = randomType();
		this.priority = randomPriority();
		this.name = randomName();
	}
	
	
	/**
	 * Generates a random name of the form {title}{initial}.
	 * {title} is either "Mr." or "Ms."
	 * {initial} is an uppercase letter.
	 * @return
	 */
	private String randomName() {
		String title = StdRandom.bernoulli() ? "Mr. " : "Ms. ";
		char inital = (char) (StdRandom.uniform(26) + 'A');
		return title + " " + inital;
		
		// use the A to start with and send 26 to get a random number A - Z, caste it to a Char
		//"Mr. " (char)((int) 'A' + Math.random()*((int)'Z' - (int) 'A' + 1));
		
	}

	/**
	 * Generates a random priority form the choices defined in the enum MailPriority.
	 * @return
	 */
	private MailPriority randomPriority() {
		MailPriority[] allPriority = MailPriority.values();
		int randomIndex = StdRandom.uniform(allPriority.length);
		return allPriority[randomIndex];
		
	}

	/**
	 * Generates a random package Type form the choices defined in the enum MailType.
	 * @return
	 */
	private MailType randomType() {
		MailType[] allType = MailType.values();
		int randomIndex = StdRandom.uniform(allType.length);
		return allType[randomIndex];
		
	}

	public MailType getType() {
		return type;
	}

	public void setType(MailType type) {
		this.type = type;
	}

	public MailPriority getPriority() {
		return priority;
	}

	public void setPriority(MailPriority priority) {
		this.priority = priority;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public int compareTo(Mail other) {
		return this.priority.compareTo(other.priority);

	}
	
	@Override
	public String toString() {
		return priority + " " + type + " for " + name;
		//return "Mail [type=" + type + ", priority=" + priority + ", name=" + name + "]";
	}

}
