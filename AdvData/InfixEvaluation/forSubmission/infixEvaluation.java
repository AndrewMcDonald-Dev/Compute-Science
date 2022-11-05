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
				System.out.println();

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

class BinaryTree<E> {
	private Node<E> root;

	public BinaryTree(E val, Node<E> left, Node<E> right) {
		this.root = new Node<E>(val, left, right);
	}

	public static void postorder(Node<String> root) {
		if (root == null)
			return;
		postorder(root.left);
		postorder(root.right);
		System.out.print(root.data + " ");
	}

	public static void inorder(Node<String> root) {
		if (root == null)
			return;

		if (infixEvaluation.precedence(root.data) != -1)
			System.out.print("( ");

		inorder(root.left);
		System.out.print(root.data + " ");
		inorder(root.right);

		if (infixEvaluation.precedence(root.data) != -1)
			System.out.print(") ");
	}

	public static Node<String> construct(String postfix) {
		if (postfix == null || postfix.length() == 0)
			return null;

		Stack<Node<String>> stack = new Stack<>();

		for (String c : postfix.split(" ")) {
			// if current token is an operator
			if (infixEvaluation.precedence(c) != -1) {
				Node<String> node;
				if (c.equals("!")) {
					Node<String> operand = stack.pop();

					node = new Node<String>(c, null, operand);
				} else {
					Node<String> right = stack.pop();
					Node<String> left = stack.pop();

					node = new Node<String>(c, left, right);
				}
				stack.push(node);
			} else
				stack.add(new Node<String>(c, null, null));

		}
		return stack.pop();
	}

	public static void printTree(Node<String> root, Node<String> firstRoot) {
		if (root.right != null)
			printTree(root.right, firstRoot);
		for(int i = 0; i < (depth(firstRoot, root)) * 4; i++){
			System.out.print(" ");
		}
		System.out.print(root.data);
		System.out.println();
        if (root.left != null)
            printTree(root.left, firstRoot);
	}

	public static void printTree(Node<String> root) {
		printTree(root, root);
	}

	public static int height(Node<String> root) {
		int countLeft = -1;
		int countRight = -1;
		if(root.left  != null)
			countLeft = height(root.left);
		if(root.right  != null)
			countRight = height(root.right);
		return Math.max(countLeft, countRight) + 1;
	}



	public static int depth(Node<String> root, Node<String> key){
		if(key == root)
			return 0;


		int left = -1;
		int right = -1;	
		if(root.left != null)
			left = depth(root.left, key);
		if(root.right != null)
			right = depth(root.right, key);
		
		if (left != -1)
			return left + 1;
		if (right != -1)
			return right +1;
		return -1; //bad result
	}

	public static int solve(Node<String> root) {
		if (root == null)
			return 0;

		if (isLeaf(root))
			return Integer.parseInt(root.data);

		if (root.data.equals("!")) {
			int right = solve(root.right);
			return right == 0 ? 1 : 0;
		} else {
			int left = solve(root.left);
			int right = solve(root.right);
			return process(root.data, left, right);
		}

	}

	public static int process(String op, int left, int right) {
		boolean error = false;
		switch (op) {
			case "^":
				return (int) (Math.pow(left, right));
			case "*":
				return left * right;
			case "/":
				if (right == 0) {
					System.out.println("Cannot divide by zero.");
					error = true;
				} else
					return left / right;
			case "%":
				return left % right;
			case "+":
				return left + right;
			case "-":
				return left - right;
			case "<":
				return left < right ? 1 : 0;
			case "<=":
				return left <= right ? 1 : 0;
			case ">":
				return left > right ? 1 : 0;
			case ">=":
				return left >= right ? 1 : 0;
			case "==":
				return right == left ? 1 : 0;
			case "!=":
				return right != left ? 1 : 0;
			case "&&":
				return (left != 0) && (right != 0) ? 1 : 0;
			case "||":
				return (left != 0) || (right != 0) ? 1 : 0;
		}
		return 0;
	}

	public static boolean isLeaf(Node<String> node) {
		return node.left == null && node.right == null;
	}
}

class Node<E> {
	public E data;
	public Node<E> left;
	public Node<E> right;

	public Node(E val, Node<E> left, Node<E> right) {
		this.data = val;
		this.left = left;
		this.right = right;
	}
}
