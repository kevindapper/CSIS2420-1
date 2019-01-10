package reviewGenerics;

public class DemoGenericNumbers
{

	private static int countOccurrences (int[] numbers, int key)
	{
		int count = 0;
		for (int el : numbers)
			{
				if (el == key)
					count++;
			}
		
		return count;
	}
	
	public static void main(String[] args)
	{
		int[] array = {1, 3, 2, 4, 3, 2, 3, 6, 7, 1};
		System.out.println(countOccurrences(array, 2));

	}

}
