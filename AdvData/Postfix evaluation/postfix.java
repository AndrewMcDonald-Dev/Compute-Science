import java.io.*;
import java.util.Scanner;
import java.util.Stack;

class PostFix {

	public static void main(String[] args) throws Exception {
		// Main method
		// helper function
		System.out.println("Hello! This is a postfix form expression calculator.");

		File file = new File("./in.dat");
		Scanner sc = new Scanner(file);

		while (sc.hasNextLine()) {
			String exp = sc.nextLine();

			// trim all whitespace
			exp = exp.trim().replaceAll(" +", " ");

			// get rids of everything after the $
			exp = exp.substring(0, exp.lastIndexOf('$') + 1);
			System.out.println("The expression to be evaluated is " + exp);
			System.out.println();

			// assignment
			exp = assignVariables(exp);

			// algorithm
			String result = evaluateExp(exp);
			if (result != "Error has occured.")
				System.out.println("The value of this expression is " + result + ".");

			System.out.println();

		}

		sc.close();

	}

	public static String assignVariables(String input) {

		// split up tokens
		String[] tokens = input.split(" ");

		Scanner scan = new Scanner(System.in);

		// iterate over tokens looking for unassigned variables
		for (int i = 0; i < tokens.length; i++) {
			if (tokens[i].matches("^[a-z]+$")) {
				String var = tokens[i];
				System.out.print("Enter the value of " + var + " > ");
				int n = scan.nextInt();
				scan.nextLine();
				for (int j = i; j < tokens.length; j++)
					if (tokens[j].equals(var))
						tokens[j] = String.valueOf(n);
			}
		}
		System.out.println();
		return String.join(" ", tokens);
	}

	public static String evaluateExp(String exp) {
		String[] tokens = exp.split(" ");
		boolean error = false;
		Stack<Integer> stack = new Stack<Integer>();
		for (int i = 0; i < tokens.length; i++) {
			if (!error) {

				// if(!stack.isEmpty()){
				// System.out.print(stack.peek() + " ");
				// }
				// System.out.println(tokens[i]);
				if (tokens[i].matches("-?[0-9]+")) {
					stack.push(Integer.parseInt(tokens[i]));
				} else {
					switch (tokens[i]) {
						case "+":
							stack.push(stack.pop() + stack.pop());
							break;

						case "-":
							stack.push(-stack.pop() + stack.pop());
							break;

						case "/":
							int right = stack.pop();
							int left = stack.pop();
							if (right == 0) {
								System.out.println("Cannot divide by zero.");
								error = true;
							} else
								stack.push((left / right));
							break;

						case "*":
							stack.push(stack.pop() * stack.pop());
							break;

						case "_":
							stack.push(-stack.pop());
							break;

						case "!":
							int factorial = 1;
							int num = stack.pop();
							if (num < 0) {
								System.out.println("Cannot take the factorial of a negative number.");
								error = true;
							} else {
								for (int j = 1; j <= num; j++)
									factorial *= j;
								stack.push(factorial);
							}
							break;

						case "#":
							int sqrtNum = stack.pop();
							if (sqrtNum < 0) {
								System.out.println("Cannot take the sqrt of a negative number.");
								error = true;
							} else
								stack.push((int) (Math.sqrt(sqrtNum)));
							break;

						case "^":
							int rightOp = stack.pop();
							int leftOp = stack.pop();
							stack.push((int) (Math.pow(leftOp, rightOp)));
							break;

						case "<":
							stack.push((stack.pop() > stack.pop()) ? 1 : 0);
							break;

						case ">":
							stack.push((stack.pop() < stack.pop()) ? 1 : 0);
							break;

						case "<=":
							stack.push((stack.pop() >= stack.pop()) ? 1 : 0);
							break;

						case ">=":
							stack.push((stack.pop() <= stack.pop()) ? 1 : 0);
							break;

						case "==":
							stack.push((stack.pop() == stack.pop()) ? 1 : 0);
							break;

						case "!=":
							stack.push((stack.pop() != stack.pop()) ? 1 : 0);
							break;

						case "&&":
							stack.push(((stack.pop() != 0) && (stack.pop() != 0)) ? 1 : 0);
							break;

						case "||":
							stack.push(((stack.pop() != 0) || (stack.pop() != 0)) ? 1 : 0);
							break;

						case "$":
							return stack.pop().toString();
					}
				}
			} else {
				return "Error has occured.";
			}
		}
		throw new Error("error");
	}
}