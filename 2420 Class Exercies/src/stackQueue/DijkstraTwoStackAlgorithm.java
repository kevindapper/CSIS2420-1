package stackQueue;

import java.util.Scanner;

import edu.princeton.cs.algs4.Stack;

public class DijkstraTwoStackAlgorithm {

	public static void main(String[] args) {
		String expression = "( ( ( 4 - 1 ) * ( 12 - ( 7 / 2 ) ) ) * 2 )";
		System.out.println(evaluate(expression));
	}

	/**
	 * Evaluates the expression using the two stack algorithm from Dijkstra and
	 * returns the result.
	 * 
	 * @param expression
	 * @return
	 */
	public static int evaluate(String expression) {
		Stack<Integer> valueStack = new Stack<Integer>();
		Stack<Character> operatorStack = new Stack<Character>();

		@SuppressWarnings("resource")		
		Scanner input = new Scanner(expression);

		while (input.hasNext()) {
			if (input.hasNextInt()) {
				valueStack.push(input.nextInt());
			} else {
				char c = input.next().charAt(0);
				if (c == '-' || c == '+' || c == '/' || c == '*') {
					operatorStack.push(c);
				} else if (c == ')') {
					int result = evaluate(valueStack.pop(), valueStack.pop(), operatorStack.pop());
					valueStack.push(result);
				}
			}
		}
		return valueStack.peek();
	}

	private static int evaluate(Integer n1, Integer n2, Character operator) {

		int result = 0;
		switch (operator) {
			case '*':
				result = n1 * n2;
				break;
			case '+':
				result = n1 + n2;
				break;
			case '-':
				result = n2 - n1;
				break;
			case '/':
				result = n2 / n1;
				break;
		}
		return result;
	}

}