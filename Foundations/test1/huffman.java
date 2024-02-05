import java.util.Comparator;
import java.util.PriorityQueue;

class Node {
	char ch;
	int freq;
	private static int next_id = 1;
	public final int id;
	Node left = null;
	Node right = null;

	public Node(char ch, int freq, Node left, Node right) {
		id = next_id++;
		this.ch = ch;
		this.freq = freq;
		this.left = left;
		this.right = right;
	}

	// Used for drawing tree nicely
	static int depth(Node root, int id) {
		if (root.id == id) {
			return 0;
		}

		int left = -1;
		int right = -1;

		if (root.left != null) {
			left = depth(root.left, id);
		}
		if (root.right != null) {
			right = depth(root.right, id);
		}

		if (left != -1) {
			return left + 1;
		}
		if (right != -1) {
			return right + 1;
		}
		return -1;
	}

	public static void print_tree(Node root, Node first_root) {
		if (root.right != null) {
			print_tree(root.right, first_root);
		}

		for (int i = 0; i < depth(first_root, root.id) * 4; i++) {
			System.err.print(" ");
		}

		System.err.printf("%c:%d\n", root.ch, root.freq);

		if (root.left != null) {
			print_tree(root.left, first_root);
		}

	}
}

class Huffman {
	public static void main(String args[]) {
		String text = "https://andrewmcd.dev/";
		createHuffmanTree(text.toLowerCase());
		// You can remove the .toLowerCase() if you want uppercase characters as well.
		// I think it make a nicer output.
	}

	public static void createHuffmanTree(String text) {
		// Count chars and save that in array
		int[] char_freq = new int[95];
		for (char c : text.toCharArray()) {
			int i = c - ' ';
			char_freq[i] += 1;
		}

		// Create PriorityQueue that sorts based on letter frequency
		PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(l -> l.freq));
		for (int i = 0; i < 95; i++) {
			if (char_freq[i] != 0) {
				pq.add(new Node((char) (i + ' '), char_freq[i], null, null));
			}
		}

		// Grabs the two lowest freqs and then combines their frequencies into new node
		while (pq.size() != 1) {
			Node left = pq.poll();
			Node right = pq.poll();

			int sum = left.freq + right.freq;
			// I couldn't use null cause it caused errors
			// So these _ still print well but are supposed to be ignored
			pq.add(new Node(' ', sum, left, right));
		}

		// Pretty printing
		Node root = pq.peek();
		Node.print_tree(root, root);
	}

	public static boolean isLeaf(Node root) {
		return root.left == null && root.right == null;
	}

}