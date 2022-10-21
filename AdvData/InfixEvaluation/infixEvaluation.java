import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

class infixEvaluation {
	public static void main(String[] args) throws FileNotFoundException {
		// 1 read in from in.dat
		File file = new File("./in.dat");
		Scanner sys = new Scanner(System.in);
		try (Scanner sc = new Scanner(file)) {
			while (sc.hasNextLine()) {
				// grab next line and trim down all white space
				String exp = sc.nextLine().trim().replaceAll(" +", " ");

				// gets rid of everything after the $
				exp = exp.substring(0, exp.lastIndexOf('$') + 1);

				// 2 print the current infix expression
				System.out.println("The expression to be evaluated is " + exp);

				// 3 convert the expression to postfix and print
				exp = infixToPostfix(exp);
				System.out.println("The Postfix is " + exp);
				System.out.println();

				// 4 print the expression tree

				Node<String> root = BinaryTree.construct(exp.substring(0, exp.length() - 2));
				BinaryTree.printTree(root);

				// 5 convert to fully parenthesized version and print
				BinaryTree.inorder(root);
				System.out.println();

				// 6 evaluate and get the result
				System.out.println("The answer is: " + BinaryTree.solve(root));

				// pause between each example
				if (sc.hasNextLine()) {
					System.out.println("Press <Enter> to continue...");
					sys.nextLine();
				}
			}
		}
	}

	public static int precedence(String c) {
		switch (c) {
			case "||":
				return 1;
			case "&&":
				return 2;
			case "==":
			case "!=":
				return 3;
			case "<":
			case "<=":
			case ">":
			case ">=":
				return 4;
			case "+":
			case "-":
				return 5;
			case "*":
			case "/":
			case "%":
				return 6;
			case "^":
				return 7;
			case "!":
				return 8;
		}
		return -1;
	}

	public static String infixToPostfix(String exp) {
		// split the expression into tokens
		String[] tokens = exp.split(" ");
		ArrayList<String> result = new ArrayList<String>();

		// setup stack
		Stack<String> stack = new Stack<>();

		for (int i = 0; i < tokens.length; ++i) {
			// grab current char
			String c = tokens[i];

			// If operand add to output
			if (c.matches("-?[0-9]+"))
				result.add(c);
			else if (c.equals("("))
				stack.push(c);
			else if (c.equals(")")) {
				while (!stack.peek().equals("("))
					result.add(stack.pop());
				stack.pop();
			} else { // an operator is encounter
				while (!stack.isEmpty() && precedence(c) <= precedence(stack.peek()) && precedence(c) < 7)
					result.add(stack.pop());
				stack.push(c);
			}
		}

		while (!stack.isEmpty())
			if (stack.peek() == "(")
				return "Invalid Expression";
			else
				result.add(stack.pop());

		return String.join(" ", result);
	}
}