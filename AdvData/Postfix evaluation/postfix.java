import java.io.*;
import java.util.Scanner;
import java.util.Stack;

class PostFix{

	
	public static void main(String[] args) throws Exception {
		// Main method	
		// helper function
		System.out.println("Hello! This is a postfix form expression calculator.");
		
		File file = new File("./in.dat");
		Scanner sc = new Scanner(file);
		
		while (sc.hasNextLine()){
			String exp = sc.nextLine();
			System.out.println("The expression to be evaluated is " + exp);
			System.out.println();
			
			// assignment
			exp = assignVariables(exp);

			// algorithm
			int result = evaluateExp(exp);
			System.out.println("The value of this expression is " + result + ".");
			System.out.println();



		};
		sc.close();

	}

	public static String assignVariables(String input) {
		//trim all whitespace
		String temp = input.trim().replaceAll(" +", " ");

		//split up tokens
		String[] tokens = temp.split(" ");

		Scanner scan = new Scanner(System.in);

		//iterate over tokens looking for unassigned variables
		for(int i = 0; i < tokens.length; i++){
			if (tokens[i].matches("^[a-z]+$")){
				String var = tokens[i];
				System.out.print("Enter the value of " + var + " > ");
				int n = scan.nextInt();
				scan.nextLine();
				for(int j = 0; j < tokens.length; j++)
					if(tokens[j].equals(var))
						tokens[j] = String.valueOf(n);				
			}
		}
		System.out.println();
		return String.join(" ", tokens);
	}


	public static int evaluateExp(String exp) {
		String[] tokens = exp.split(" ");

		Stack<Integer> stack = new Stack<Integer>();
		for(int i = 0; i < tokens.length; i++){
			if(!stack.isEmpty()){
				System.out.print(stack.peek() + " ");
			}
			System.out.println(tokens[i]);
			if(tokens[i].matches("-?[0-9]+")){
				stack.push(Integer.parseInt(tokens[i]));
			}else{
				switch(tokens[i]){
					case "+":
					stack.push(stack.pop() + stack.pop());
					break;

					case "-":
					stack.push(-stack.pop()+ stack.pop());
					break;

					case "/":
					stack.push((int)(1.0/stack.pop() * stack.pop()));
					break;

					case "*":
					stack.push(stack.pop()*stack.pop());
					break;

					case "_":
					stack.push(-stack.pop());
					break;

					case "!":
					int factorial = 1;
					for(int j = 1; j <= stack.pop(); j++)
						factorial *= i;
					stack.push(factorial);
					break;

					case "#":
					stack.push((int)(Math.sqrt(stack.pop())));
					break;

					case "^":
					int right = stack.pop();
					int left = stack.pop();
					stack.push((int)(Math.pow(left, right)));
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
					stack.push(((stack.pop() != 0) && ( stack.pop() != 0)) ? 1 : 0);
					break;

					case "||":
					stack.push(((stack.pop() != 0) || ( stack.pop() != 0)) ? 1 : 0);
					break;

					case "$":
					return stack.pop();
				}
			}
		}
		throw new Error("error");
	}
}