package reviewGenerics;

public class DemoGenericsChar
{
	private static int countOccurrences (char[] characters, char key)
	{
		int count = 0;
		for (char el : characters)
			{
				if (el == key)
					count++;
			}
		
		return count;
	}
	
	public static void main(String[] args)
	{
		char[] array = {'a', 'r', 'd', 'a', 'r', 'd', 'a', 'r', 'd', 'R', 'Y', 'Y', 'h', 'D', 't'};
		System.out.println("number of ocurrences of a in array: " 
				+ countOccurrences(array, 'a'));

	}

}
