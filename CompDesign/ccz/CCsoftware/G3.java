import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

class G3 {
	/**
	 * @param args
	 */
	public static void main(String[] args) throws IOException {
		// Check here if number of args is correct
		if (args.length != 2) {
			System.out.println("Invalid Number of Arguments");
			System.exit(1);
		}

		G3TokenMgr tm = new G3TokenMgr(args[0]);
		G3CodeGen cg = new G3CodeGen();
		G3Parser parser = new G3Parser(tm, cg);

		try {
			// parse regular expression
			NFAState startState = parser.parse();
			// NFAState.displayNFA(startState);

			G3Matcher m = new G3Matcher(startState, args[1]);
			m.match();
		} catch (RuntimeException e) {
			System.err.println(e.getMessage());
			System.exit(1);
		} catch (IOException e) {
			System.err.println(e.getMessage());
			System.exit(1);
		}
	}
}

interface G3Constants {
	int EORE = 0;
	int CHAR = 1;
	int PERIOD = 2;
	int LEFTPAREN = 3;
	int RIGHTPAREN = 4;
	int OR = 5;
	int STAR = 6;
	int ERROR = 7;

	int CONCAT = 8; // no corresponding token

	String[] tokenImage = {
			"<EORE>",
			"<CHAR>",
			"\".\"",
			"\"(\"",
			"\")\"",
			"\"|\"",
			"\"*\"",
			"<ERROR>"
	};
}

class G3TokenMgr implements G3Constants {
	private char currentChar;
	private int currentColumnNumber;
	private String inputLine; // holds 1 line of input
	private Token token; // holds 1 token
	// -----------------------------------------

	public G3TokenMgr(String regEx) {

		inputLine = regEx;
		currentChar = inputLine.charAt(0); // '\n' triggers read
		currentColumnNumber = 1;
		// getNextChar();
	}

	// -----------------------------------------
	public Token getNextToken() {
		// skip whitespace
		while (Character.isWhitespace(currentChar)) {
			getNextChar();
		}

		// b*\*
		// construct token to be returned to parser
		token = new Token();
		token.next = null;

		// save start-of-token position
		token.beginColumn = currentColumnNumber;

		boolean specialCharacter = currentChar == '.' ||
				currentChar == '(' ||
				currentChar == ')' ||
				currentChar == '|' ||
				currentChar == '*';

		if (currentChar == EORE) {
			token.image = "<EORE>";
			token.kind = EORE;
			return token;
		} else if (specialCharacter) {
			token.kind = mapToken();
		} else if (currentChar == '\\') {
			getNextChar();
			token.beginColumn = currentColumnNumber;
			token.kind = CHAR;
		} else {
			// currentChar can be two things a char of some sort of one of the tokens
			token.kind = CHAR;
		}
		token.image = Character.toString(currentChar);

		getNextChar(); // read beyond end of token

		return token; // return token to parser
	}

	private void getNextChar() {
		if (currentChar == EORE)
			return;

		if (currentColumnNumber >= inputLine.length()) {
			// if at end return EORE
			currentChar = EORE;
		} else {
			// get next char from inputLine
			currentChar = inputLine.charAt(currentColumnNumber++);
		}
	}

	private int mapToken() {
		switch (currentChar) {
			case EORE:
				return EORE;
			case '.':
				return PERIOD;
			case '(':
				return LEFTPAREN;
			case ')':
				return RIGHTPAREN;
			case '|':
				return OR;
			case '*':
				return STAR;
			default:
				return CHAR;
		}
	}
}

class G3Parser implements G3Constants {
	private G3TokenMgr tm;
	private G3CodeGen cg;
	private Token currentToken;
	private Token previousToken;

	// -----------------------------------------
	public G3Parser(G3TokenMgr tm, G3CodeGen cg) {
		this.tm = tm;
		this.cg = cg;
		// prime currentToken with first token
		currentToken = tm.getNextToken();
		previousToken = null;
	}

	// -----------------------------------------
	// Construct and return an exception that contains
	// a message consisting of the image of the current
	// token, its location, and the expected tokens.
	//
	private RuntimeException genEx(String errorMessage) {
		return new RuntimeException("Encountered \"" +
				currentToken.image + "\" on column " +
				currentToken.beginColumn + "." +
				System.getProperty("line.separator") +
				errorMessage);
	}

	// -----------------------------------------
	// Advance currentToken to next token.
	//
	private void advance() {
		previousToken = currentToken;

		// If next token is on token list, advance to it.
		if (currentToken.next != null)
			currentToken = currentToken.next;

		// Otherwise, get next token from token mgr and
		// put it on the list.
		else
			currentToken = currentToken.next = tm.getNextToken();
	}

	// -----------------------------------------
	// getToken(i) returns ith token without advancing
	// in token stream. getToken(0) returns
	// previousToken. getToken(1) returns currentToken.
	// getToken(2) returns next token, and so on.
	//
	private Token getToken(int i) {
		if (i <= 0)
			return previousToken;

		Token t = currentToken;
		for (int j = 1; j < i; j++) // loop to ith token
		{
			// if next token is on token list, move t to it
			if (t.next != null)
				t = t.next;

			// Otherwise, get next token from token mgr and
			// put it on the list.
			else
				t = t.next = tm.getNextToken();
		}
		return t;
	}

	// -----------------------------------------
	// If the kind of the current token matches the
	// expected kind, then consume advances to the next
	// token. Otherwise, it throws an exception.
	//
	private void consume(int expected) {
		if (currentToken.kind == expected)
			advance();
		else
			throw genEx("Expecting " + tokenImage[expected]);
	}

	// -----------------------------------------
	public NFAState parse() {
		return program(); // program is start symbol for grammar
	}

	// -----------------------------------------
	private NFAState program() {
		// while (currentToken.kind != EORE) {
		// System.out.println("HERE: " + currentToken);
		// advance();
		// }

		NFAState p;
		p = expr();
		if (currentToken.kind != EORE) // garbage at end?
			throw genEx("Expecting <EORE>");
		return p;
	}

	// -----------------------------------------
	private NFAState expr() {
		NFAState p;

		p = term();
		p = termList(p);
		return p;
	}

	// -----------------------------------------
	private NFAState termList(NFAState p) {
		NFAState q;

		switch (currentToken.kind) {
			case OR:
				consume(OR);
				q = term();
				p = cg.make(OR, p, q);
				p = termList(p);
				break;
			case RIGHTPAREN:
			case EORE:
				break;
			default:
				throw genEx("Expecting \"|\", \")\", or \"<EORE>\"");
		}
		return p;
	}

	// -----------------------------------------
	private NFAState term() {
		NFAState p;

		p = factor();
		p = factorList(p);
		return p;
	}

	// -----------------------------------------
	private NFAState factorList(NFAState p) {
		NFAState q;

		switch (currentToken.kind) {
			case CHAR:
			case PERIOD:
			case LEFTPAREN:
				q = factor();
				p = cg.make(CONCAT, p, q);
				p = factorList(p);

				break;
			case OR:
			case RIGHTPAREN:
			case EORE:
				break;
			default:
				throw genEx("Expecting <CHAR>, \".\", \"(\", \"|\", \")\", or <EORE>");
		}

		return p;
	}

	// -----------------------------------------
	private NFAState factor() {
		NFAState p;

		switch (currentToken.kind) {
			case CHAR:
				p = cg.make(CHAR, currentToken);
				consume(CHAR);
				p = factorTail(p);
				break;
			case PERIOD:
				p = cg.make(PERIOD, currentToken);
				consume(PERIOD);
				p = factorTail(p);
				break;
			case LEFTPAREN:
				consume(LEFTPAREN);
				p = expr();
				consume(RIGHTPAREN);
				p = factorTail(p);
				break;
			default:
				throw genEx("Expecting <CHAR>, \".\", or \"(\"");
		}

		return p;
	}

	private NFAState factorTail(NFAState p) {
		switch (currentToken.kind) {
			case STAR:
				p = cg.make(STAR, p);
				consume(STAR);
				p = factorTail(p);
			case CHAR:
			case PERIOD:
			case LEFTPAREN:
			case OR:
			case RIGHTPAREN:
			case EORE:
				break;
			default:
				throw genEx("Expecting \"*\", <CHAR>, \".\", \"(\", \"|\", \")\", or <EORE>");
		}

		return p;
	}
}

class G3CodeGen implements G3Constants {

	public G3CodeGen() {

	}

	public NFAState make(int op, NFAState p, NFAState q) {
		// s is new start state; a is new accept state
		NFAState s, a;

		switch (op) {
			case OR:
				s = new NFAState();
				a = new NFAState();
				s.arrow1 = p; // make s point to p and q
				s.arrow2 = q;
				// make accept states of p and q NFAs point to a
				p.acceptState.arrow1 = a;
				q.acceptState.arrow1 = a;
				s.acceptState = a; // make a the accept state
				return s;
			case CONCAT:
				s = new NFAState();
				a = new NFAState();
				s.arrow1 = p;

				p.acceptState.arrow1 = q;
				q.acceptState.arrow1 = a;
				s.acceptState = a;
				return s;
			default:
				throw new RuntimeException("Bad call of make");
		}
	}

	// ----------------------------------------
	public NFAState make(int op, Token t) {
		// s is new start state; a is new acccept state
		NFAState s, a;

		switch (op) {
			case CHAR:
				s = new NFAState();
				a = new NFAState();
				s.arrow1 = a; // make s point to a
				s.label1 = t.image.charAt(0);
				s.acceptState = a; // make a the accept state
				return s;
			case PERIOD:
				s = new NFAState();
				a = new NFAState();
				s.arrow1 = a; // make s point to a
				s.label1 = PERIOD;
				s.acceptState = a; // make a the accept state
				return s;
			default:
				throw new RuntimeException("Bad call of maker");
		}
	}

	// ----------------------------------------
	public NFAState make(int op, NFAState p) {
		// s is new start state; a is new accept state
		NFAState s, a;

		switch (op) {
			case STAR:
				s = new NFAState();
				a = new NFAState();
				s.arrow1 = p;
				s.arrow2 = a;

				p.acceptState.arrow1 = s;
				s.acceptState = a;

				return s;
			default:
				throw new RuntimeException("Bad call of make");
		}
	}

}

class G3Matcher implements G3Constants {
	public ArrayList<NFAState> currentStates;
	public ArrayList<NFAState> nextStates;
	public NFAState startState;
	public Scanner inFile;

	public G3Matcher(NFAState startState, String inputFile) throws IOException {
		this.startState = startState;
		this.inFile = new Scanner(new File(inputFile));
		currentStates = new ArrayList<NFAState>();
		nextStates = new ArrayList<NFAState>();
	}

	private boolean lambdaClosure() {
		boolean gotAccept = false;
		for (int i = 0; i < currentStates.size(); i++) {
			NFAState s = currentStates.get(i);
			if (startState.acceptState == s)
				gotAccept = true;

			if (s.arrow1 != null && s.label1 == 0 && !isInArrayList(s.arrow1, currentStates))
				currentStates.add(s.arrow1);

			if (s.arrow2 != null && !isInArrayList(s.arrow2, currentStates))
				currentStates.add(s.arrow2);
		}
		return gotAccept;
	}

	//
	private void applyChar(char c) {
		nextStates.clear();

		for (NFAState s : currentStates)
			if (s.arrow1 != null && !isInArrayList(s, nextStates) && (s.label1 == PERIOD || s.label1 == c))
				nextStates.add(s.arrow1);

		ArrayList<NFAState> temp = currentStates;
		currentStates = nextStates;
		nextStates = temp;
	}

	public void match() {
		// process input line in buf
		while (inFile.hasNextLine()) {
			String buf = inFile.nextLine();

			boolean gotAccept;

			for (int startIndex = 0; startIndex < buf.length(); startIndex++) {
				currentStates.clear();
				currentStates.add(startState);
				int bufIndex = startIndex;

				// apply substring starting at bufIndex to
				// NFA. Exit on an accept, end of substring,
				// or trap state
				while (true) {
					gotAccept = lambdaClosure();
					if (gotAccept // accept state entered
							|| bufIndex >= buf.length() // end substring
							|| currentStates.size() == 0) // trap state
						break;
					applyChar(buf.charAt(bufIndex++));
				}

				// display line if match occurred somewhere
				if (gotAccept) {
					System.out.println(buf);
					startIndex = buf.length();
					break; // go to next line
				}
			} // end of for loop
		}
	}

	// return true if NFAState is in currentStates
	public static boolean isInArrayList(NFAState n, ArrayList<NFAState> states) {
		for (NFAState state : states)
			if (state == n)
				return true;
		return false;
	}
}