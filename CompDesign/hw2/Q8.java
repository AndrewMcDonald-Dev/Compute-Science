
/* Figure 8.


               Selection set
   0) S -> fBC     {f}
   1) B -> bb      {b}
   2) B -> CD      {c, d, e}
   3) C -> cC      {c}
   4) C -> lambda  {d, e, #}
   5) D -> dD      {d}
   6) D -> e       {e}
*/
import java.util.*; // import Stack and Scanner classes
//======================================================

class Q8 {
	public static void main(String[] args) {
		// construct token manager (see Fig. 6.12)
		ArgsTokenMgr tm = new ArgsTokenMgr(args);

		// construct parser, pass it the token manager
		Q8Parser parser = new Q8Parser(tm);

		parser.parse(); // do parse
	}
} // end of Fig0804
	// ======================================================

interface DataPart {
	// These constants are available to any class that
	// implements this interface.

	String tokens = "bcde"; // list of tokens
	String nonTerms = "SBC"; // list of non-terminals

	// right-hand sides of productions in reverse order
	String[] pTab = { "","CB", "BC", "Bb", "d", "Cc", "e" };

	int[][] parseTable = {
			{ 1, 2, 1, 2 }, // -1 means reject
			{ 3, -1, 4, -1 }, // Non-neg numbers are
			{ -1, 5, -1, 6 }, // production numbers
	};
} // end of DataPart
	// ======================================================

class ArgsTokenMgr {
	private int index;
	String input;

	// -----------------------------------------
	public ArgsTokenMgr(String[] args) {
		if (args.length > 0)
			input = args[0];
		else // treat no command line arg as null string
			input = "";
		index = 0;
		System.out.println("input = " + input);
	}

	// -----------------------------------------
	public char getNextToken() {
		if (index < input.length())
			return input.charAt(index++); // return next char
		else
			return '#'; // # signals end of input
	}
} // end of ArgsTokenMgr
	// ======================================================

class Q8Parser implements DataPart {
	private ArgsTokenMgr tm;
	private Stack<Character> stk;
	private char currentToken;

	// -----------------------------------------
	public Q8Parser(ArgsTokenMgr tm) {
		this.tm = tm; // save tm
		advance(); // prime currentToken
		stk = new Stack<Character>(); // create stack
		stk.push('S'); // init stack with start symbol
	}

	// -----------------------------------------
	private void advance() {
		currentToken = tm.getNextToken();
	}

	// -----------------------------------------
	public void parse() {
		int nonTermIndex, tokenIndex, pNumber;

		while (true) {

			// convert current token into index
			tokenIndex = tokens.indexOf(currentToken);

			// check if bad token or stk empty
			if (tokenIndex == -1 || stk.empty())
				break;

			// convert top-of-stack symbol to index
			// get -1 if top of stack is terminal
			nonTermIndex = nonTerms.indexOf(stk.peek());

			if (nonTermIndex >= 0) // nonterm on top of stk?
			{
				// get production number
				pNumber = parseTable[nonTermIndex][tokenIndex];

				if (pNumber < 0) // -1 means reject
					break;

				// apply production whose number is // pNumber
				stk.pop();
				for (int i = 0; i < pTab[pNumber].length(); i++)
					stk.push(pTab[pNumber].charAt(i));
			}

			else
			// does term on top of stack match current token?
			if (currentToken == stk.peek()) {
				stk.pop(); // discard term on top of stack
				advance(); // discard matching current token
			} else
				break;
		}

		// test if in accept configuration
		if (currentToken == '#' && stk.empty())
			System.out.println("accept");
		else
			System.out.println("reject");
	}
} // end of Fig0804Parser