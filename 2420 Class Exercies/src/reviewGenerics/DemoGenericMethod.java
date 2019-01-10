package reviewGenerics;

public class DemoGenericMethod
{

	public static void main(String[] args)
	{
		//Character[] array = {'a', 'r', 'd', 'a', 'r', 'd', 'a', 'r', 'd', 'R', 'Y', 'Y', 'h', 'D', 't'};
		String[] array = {"Rock", "rr", "dd", "rr", "rr", "Rock", "dd"};
		System.out.println("number of ocurrences of \"Rock\" in array: " 
				+ countOccurrences(array, "Rock"));

	}

	private static <T> int countOccurrences (T[] array, T key)
	{
		int count = 0;
		for (T el : array)
			{
				if (el.equals(key))
					count++;
			}
		
		return count;
	}	
	
}