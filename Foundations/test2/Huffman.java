import java.util.*;
import java.io.*;

class Huffman {
	private Node root;
	private ArrayList<Character> symbols = new ArrayList<Character>();
	private ArrayList<Integer> frequencys = new ArrayList<Integer>();

	public Huffman(String f) throws IOException {
		processInput(f);
		System.out.println(symbols.toString() + '\n' + frequencys.toString());
		root = run(this.frequencys, this.symbols);
		String encoded = encodeString(f, root);
		System.out.println(encoded);
		System.out.println(decodeString("encoded2.txt", "counts2.txt"));
	}

	private void processInput(String f) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader(f));
		int x = in.read();
		while (x != -1) {
			char c = (char) x;
			if (symbols.contains(c))
				frequencys.set(symbols.indexOf(c), frequencys.get(symbols.indexOf(c)) + 1);
			else {
				symbols.add(c);
				frequencys.add(1);
			}
			x = in.read();
		}
		in.close();
		sort(symbols, frequencys);
	}

	public static Node run(ArrayList<Integer> frequencys, ArrayList<Character> symbols) {
		PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(l -> l.getFrequency()));
		Huffman.enqueue(pq, frequencys, symbols);
		while (pq.size() != 1) {
			Node first = pq.poll();
			Node second = pq.poll();
			Node sum = new Node(first.getFrequency() + second.getFrequency(), '@');
			sum.setLeft(first);
			sum.setRight(second);

			pq.add(sum);

		}
		return pq.poll();

	}

	public String encodeString(String f, Node root) throws IOException {
		// buildCodes for symbols
		String[] loc = new String[symbols.size()];
		buildCode(loc, root, "", this.symbols);

		// Encode input
		BufferedReader in = new BufferedReader(new FileReader(f));
		int x = in.read();
		String output = "";
		while (x != -1) {
			char c = (char) x;
			output += loc[symbols.indexOf(c)];
			x = in.read();
		}
		in.close();
		return output;
	}

	public static String decodeString(String encoded_file, String counts) throws IOException {
		// Read in counts.txt and construct symbol and frequency lists
		ArrayList<Character> symbols = new ArrayList<Character>();
		ArrayList<Integer> frequencys = new ArrayList<Integer>();
		BufferedReader in = new BufferedReader(new FileReader(counts));
		String characters = in.readLine();
		String[] char_arr = characters.split(",");
		for (String x : char_arr) {
			char c = x.charAt(0);
			symbols.add(c);
		}

		String freq = in.readLine();
		String[] freq_arr = freq.split(",");
		for (String x : freq_arr) {
			int n = Integer.parseInt(x);
			frequencys.add(n);
		}
		in.close();

		if (frequencys.size() != symbols.size()) {
			System.out.println("Bad input");
		}

		// Construct Tree
		sort(symbols, frequencys);
		System.out.println(symbols);
		System.out.println(frequencys);
		Node root = Huffman.run(frequencys, symbols);

		// Build codes based on tree
		String[] loc = new String[symbols.size()];
		buildCode(loc, root, "", symbols);
		System.out.println(Arrays.toString(loc));

		// Read in encoded.txt
		BufferedReader in2 = new BufferedReader(new FileReader(encoded_file));
		String encoded = in2.readLine();
		System.out.println("test");

		// Decode based on build codes
		int i = 0;
		String decoded = "";
		while (i < encoded.length()) {
			for (int k = 0; k < loc.length; k++) {
				String code = loc[k];
				if (i + code.length() <= encoded.length() && encoded.substring(i, i + code.length()).equals(code)) {
					decoded += symbols.get(k);
					i += code.length();
					// System.out.println(i + " " + encoded.length());
					break;
				}
			}
		}
		in2.close();

		return decoded;
	}

	private static void buildCode(String[] loc, Node cur, String s, ArrayList<Character> symbols) {
		if (!cur.isLeaf()) {
			buildCode(loc, cur.getLeft(), s + '0', symbols);
			buildCode(loc, cur.getRight(), s + '1', symbols);
		} else {
			loc[symbols.indexOf(cur.getCharacter())] = s;
		}
	}

	private static void enqueue(PriorityQueue<Node> pq, ArrayList<Integer> frequencys, ArrayList<Character> symbols) {
		for (int i = 0; i < frequencys.size(); i++) {
			pq.add(new Node(frequencys.get(i), symbols.get(i)));
		}
	}

	private static void sort(ArrayList<Character> symbols, ArrayList<Integer> frequencys) {
		for (int i = 0; i < symbols.size() - 1; i++) {
			int min = i;
			for (int j = min + 1; j < symbols.size(); j++) {
				if (frequencys.get(j) < frequencys.get(min))
					min = j;
			}
			int temp = frequencys.get(i);
			char temp2 = symbols.get(i);
			frequencys.set(i, frequencys.get(min));
			symbols.set(i, symbols.get(min));
			frequencys.set(min, temp);
			symbols.set(min, temp2);
		}
	}
}

class Node implements Comparable<Node> {
	private int frequency;
	private char character;
	private Node left, right;

	public Node(int f, char c) {
		frequency = f;
		character = c;
		left = right = null;
	}

	public Node(int f) {
		this(f, '@');
	}

	public char getCharacter() {
		return character;
	}

	public int getFrequency() {
		return frequency;
	}

	public Node getLeft() {
		return left;
	}

	public Node getRight() {
		return right;
	}

	public void setCharacter(char c) {
		character = c;
	}

	public void setFrequency(int f) {
		frequency = f;
	}

	public void setLeft(Node l) {
		left = l;
	}

	public void setRight(Node r) {
		right = r;
	}

	public String toString() {
		if (character == '@')
			return "f: " + frequency;
		return "[ f: " + frequency + ", c: " + character + " ]\n";
	}

	public int compareTo(Node e) {
		return this.getFrequency() - e.getFrequency();
	}

	public boolean isLeaf() {
		return character != '@';
	}
}

class Test {
	public static void main(String[] args) throws IOException {
		new Huffman("raw.txt");
		System.out.println(Huffman.decodeString("encoded.txt", "counts.txt"));
	}
}