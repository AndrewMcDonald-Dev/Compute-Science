import java.util.Stack;

public class BinaryTree<E> {
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

	public static void printTree(String s, Node<String> n, boolean isLeft) {
		if (n != null) {
			System.out.println(s + (isLeft ? " |---- " : " \\--- ") + n.data);
			printTree(s + (isLeft ? " |     " : "     "), n.left, true);
			printTree(s + (isLeft ? " |     " : "     "), n.right, false);
			System.out.println(s + (isLeft ? " |     " : ""));
		}
	}

	public static void printTree(Node<String> root) {
		printTree("", root, false);
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
