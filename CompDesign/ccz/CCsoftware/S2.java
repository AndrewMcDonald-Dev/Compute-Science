
// Hand-written S1 compiler
import java.io.*;
import java.util.*;

//======================================================
class S2 {
	public static void main(String[] args) throws IOException {
		System.out.println("S2 compiler written by Andrew McDonald");

		if (args.length != 1) {
			System.err.println("Wrong number cmd line args");
			System.exit(1);
		}

		// set to true to debug token manager
		boolean debug = false;

		// build the input and output file names
		String inFileName = args[0] + ".s";
		String outFileName = args[0] + ".a";

		// construct file objects
		Scanner inFile = new Scanner(new File(inFileName));
		PrintWriter outFile = new PrintWriter(outFileName);

		// identify compiler/author in the output file
		outFile.println("; from S2 compiler written by Andrew McDonald");

		// construct objects that make up compiler
		S2SymTab st = new S2SymTab();
		S2TokenMgr tm = new S2TokenMgr(inFile, outFile, debug);
		S2CodeGen cg = new S2CodeGen(outFile, st);
		S2Parser parser = new S2Parser(st, tm, cg);

		// parse and translate
		try {
			parser.parse();
		} catch (RuntimeException e) {
			System.err.println(e.getMessage());
			outFile.println(e.getMessage());
			outFile.close();
			System.exit(1);
		}

		outFile.close();
	}
} // end of S2
	// ======================================================

interface S2Constants {
	// integers that identify token kinds
	int EOF = 0;
	int PRINTLN = 1;
	int UNSIGNED = 2;
	int ID = 3;
	int ASSIGN = 4;
	int SEMICOLON = 5;
	int LEFTPAREN = 6;
	int RIGHTPAREN = 7;
	int PLUS = 8;
	int MINUS = 9;
	int TIMES = 10;
	int ERROR = 11;
	int PRINT = 12;
	int LEFTCURLY = 13;
	int RIGHTCURLY = 14;
	int DIV = 15;
	int COMMENT = 16;

	// tokenImage provides string for each token kind
	String[] tokenImage = {
			"<EOF>",
			"\"println\"",
			"<UNSIGNED>",
			"<ID>",
			"\"=\"",
			"\";\"",
			"\"(\"",
			"\")\"",
			"\"+\"",
			"\"-\"",
			"\"*\"",
			"<ERROR>",
			"\"print\"",
			"\"{\"",
			"\"}\"",
			"\"/\"",
			"\"//\"",

	};
} // end of S2Constants
	// ======================================================

class S2SymTab {
	private ArrayList<String> symbol;

	// -----------------------------------------
	public S2SymTab() {
		symbol = new ArrayList<String>();
	}

	// -----------------------------------------
	public void enter(String s) {
		int index = symbol.indexOf(s);

		// if s is not in symbol, then add it
		if (index < 0)
			symbol.add(s);
	}

	// -----------------------------------------
	public String getSymbol(int index) {
		return symbol.get(index);
	}

	// -----------------------------------------
	public int getSize() {
		return symbol.size();
	}
} // end of S1SymTab
	// ======================================================

class S2TokenMgr implements S2Constants {
	private Scanner inFile;
	private PrintWriter outFile;
	private boolean debug;
	private char currentChar;
	private int currentColumnNumber;
	private int currentLineNumber;
	private String inputLine; // holds 1 line of input
	private Token token; // holds 1 token
	private StringBuffer buffer; // token image built here
	// -----------------------------------------

	public S2TokenMgr(Scanner inFile,
			PrintWriter outFile, boolean debug) {
		this.inFile = inFile;
		this.outFile = outFile;
		this.debug = debug;
		currentChar = '\n'; // '\n' triggers read
		currentLineNumber = 0;
		buffer = new StringBuffer();
	}

	// -----------------------------------------
	public Token getNextToken() {
		// skip whitespace
		while (Character.isWhitespace(currentChar))
			getNextChar();

		// construct token to be returned to parser
		token = new Token();
		token.next = null;

		boolean isSingle = true;

		// save start-of-token position
		token.beginLine = currentLineNumber;
		token.beginColumn = currentColumnNumber;

		// check for EOF
		if (currentChar == EOF) {
			token.image = "<EOF>";
			token.endLine = currentLineNumber;
			token.endColumn = currentColumnNumber;
			token.kind = EOF;
			isSingle = false;
		}

		else // check for unsigned int
		if (Character.isDigit(currentChar)) {
			buffer.setLength(0); // clear buffer
			do // build token image in buffer
			{
				buffer.append(currentChar);
				token.endLine = currentLineNumber;
				token.endColumn = currentColumnNumber;
				getNextChar();
			} while (Character.isDigit(currentChar));
			// save buffer as String in token.image
			token.image = buffer.toString();
			token.kind = UNSIGNED;
			isSingle = false;
		}

		else // check for identifier
		if (Character.isLetter(currentChar)) {
			buffer.setLength(0); // clear buffer
			do // build token image in buffer
			{
				buffer.append(currentChar);
				token.endLine = currentLineNumber;
				token.endColumn = currentColumnNumber;
				getNextChar();
			} while (Character.isLetterOrDigit(currentChar));
			// save buffer as String in token.image
			token.image = buffer.toString();

			// check if keyword
			if (token.image.equals("println"))
				token.kind = PRINTLN;
			else if (token.image.equals("print"))
				token.kind = PRINT;
			else // not a keyword so kind is ID
				token.kind = ID;
			isSingle = false;
		}

		else if (currentChar == '/') {
			buffer.setLength(0);
			buffer.append(currentChar);
			getNextChar();
			if (currentChar == '/') {
				buffer.append(currentChar);
				token.image = buffer.toString();
				token.kind = COMMENT;
				token.endColumn = currentColumnNumber;
				token.endLine = currentLineNumber;
				getNextChar();
				isSingle = false;

				do {
					getNextChar();
				} while (currentChar != '\n');

			} else { // handles division
				currentColumnNumber = token.beginColumn;
				currentLineNumber = token.beginLine;
				token.kind = DIV;
				token.image = "/";
				isSingle = false;
			}
		}

		if (isSingle) // process single-character token
		{
			switch (currentChar) {
				case '=':
					token.kind = ASSIGN;
					break;
				case ';':
					token.kind = SEMICOLON;
					break;
				case '(':
					token.kind = LEFTPAREN;
					break;
				case ')':
					token.kind = RIGHTPAREN;
					break;
				case '+':
					token.kind = PLUS;
					break;
				case '-':
					token.kind = MINUS;
					break;
				case '*':
					token.kind = TIMES;
					break;
				case '{':
					token.kind = LEFTCURLY;
					break;
				case '}':
					token.kind = RIGHTCURLY;
					break;
				default:
					token.kind = ERROR;
					break;
			}

			// save currentChar as String in token.image
			token.image = Character.toString(currentChar);

			// save end-of-token position
			token.endLine = currentLineNumber;
			token.endColumn = currentColumnNumber;

			getNextChar(); // read beyond end of token
		}

		// token trace appears as comments in output file
		if (debug)
			outFile.printf(
					"; kd=%3d bL=%3d bC=%3d eL=%3d eC=%3d im=%s%n",
					token.kind, token.beginLine, token.beginColumn,
					token.endLine, token.endColumn, token.image);

		return token; // return token to parser
	}

	// -----------------------------------------
	private void getNextChar() {
		if (currentChar == EOF)
			return;

		if (currentChar == '\n') // need next line?
		{
			if (inFile.hasNextLine()) // any lines left?
			{
				inputLine = inFile.nextLine(); // get next line
				// output source line as comment
				outFile.println("; " + inputLine);
				inputLine = inputLine + "\n"; // mark line end
				currentColumnNumber = 0;
				currentLineNumber++;
			} else // at end of file
			{
				currentChar = EOF;
				return;
			}
		}

		// get next char from inputLine
		currentChar = inputLine.charAt(currentColumnNumber++);

		// in S2, test for single-line comment goes here
	}
} // end of S1TokenMgr
	// ======================================================

class S2Parser implements S2Constants {
	private S2SymTab st;
	private S2TokenMgr tm;
	private S2CodeGen cg;
	private Token currentToken;
	private Token previousToken;

	// -----------------------------------------
	public S2Parser(S2SymTab st, S2TokenMgr tm,
			S2CodeGen cg) {
		this.st = st;
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
				currentToken.image + "\" on line " +
				currentToken.beginLine + ", column " +
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
	public void parse() {
		program(); // program is start symbol for grammar
	}

	// -----------------------------------------
	private void program() {
		statementList();
		cg.endCode();
		if (currentToken.kind != EOF) // garbage at end?
			throw genEx("Expecting <EOF>");
	}

	// -----------------------------------------
	private void statementList() {
		switch (currentToken.kind) {
			case ID:
			case PRINTLN:
			case PRINT:
			case SEMICOLON:
			case LEFTCURLY:
			case COMMENT:
				statement();
				statementList();
				break;
			case EOF:
			case RIGHTCURLY:
				;
				break;
			default:
				throw genEx("Expecting statement or <EOF>");
		}
	}

	// -----------------------------------------
	private void statement() {
		switch (currentToken.kind) {
			case ID:
				assignmentStatement();
				break;
			case PRINTLN:
				printlnStatement();
				break;
			case PRINT:
				printStatement();
				break;
			case SEMICOLON:
				nullStatement();
				break;
			case LEFTCURLY:
				compoundStatement();
				break;
			case COMMENT:
				commentStatement();
				break;
			default:
				throw genEx("Expecting statement");
		}
	}

	// -----------------------------------------
	private void commentStatement() {
		consume(COMMENT);
	}

	// -----------------------------------------
	private void assignmentStatement() {
		Token t;

		t = currentToken;
		consume(ID);
		st.enter(t.image);
		cg.emitInstruction("pc", t.image);
		consume(ASSIGN);
		expr();
		cg.emitInstruction("stav");
		consume(SEMICOLON);
	}

	// -----------------------------------------
	private void printlnStatement() {
		consume(PRINTLN);
		consume(LEFTPAREN);
		expr();
		cg.emitInstruction("dout");
		cg.emitInstruction("pc", "'\\n'");
		cg.emitInstruction("aout");
		consume(RIGHTPAREN);
		consume(SEMICOLON);
	}

	// -----------------------------------------
	private void printStatement() {
		consume(PRINT);
		consume(LEFTPAREN);
		expr();
		cg.emitInstruction("dout");
		consume(RIGHTPAREN);
		consume(SEMICOLON);
	}

	// -----------------------------------------
	private void nullStatement() {
		consume(SEMICOLON);
	}

	// -----------------------------------------
	private void compoundStatement() {
		consume(LEFTCURLY);
		statementList();
		consume(RIGHTCURLY);
	}

	// -----------------------------------------
	private void expr() {
		term();
		termList();
	}

	// -----------------------------------------
	private void termList() {
		switch (currentToken.kind) {
			case PLUS:
				consume(PLUS);
				term();
				cg.emitInstruction("add");
				termList();
				break;
			case MINUS:
				consume(MINUS);
				term();
				cg.emitInstruction("sub");
				termList();
				break;
			case RIGHTPAREN:
			case SEMICOLON:
				;
				break;
			default:
				throw genEx("Expecting \"+\", \")\", or \";\"");
		}
	}

	// -----------------------------------------
	private void term() {
		factor();
		factorList();
	}

	// -----------------------------------------
	private void factorList() {
		switch (currentToken.kind) {
			case TIMES:
				consume(TIMES);
				factor();
				cg.emitInstruction("mult");
				factorList();
				break;

			case DIV:
				consume(DIV);
				factor();
				cg.emitInstruction("div");
				factorList();
				break;
			case PLUS:
			case MINUS:
			case RIGHTPAREN:
			case SEMICOLON:
				;
				break;
			case COMMENT:
				commentStatement();
				break;
			default:
				throw genEx("Expecting op, \")\", or \";\"");
		}
	}

	// -----------------------------------------
	private void factor() {
		Token t;

		switch (currentToken.kind) {
			case UNSIGNED:
				t = currentToken;
				consume(UNSIGNED);
				cg.emitInstruction("pwc", t.image);
				break;
			case PLUS:
				consume(PLUS);
				t = currentToken;
				consume(UNSIGNED);
				cg.emitInstruction("pwc", t.image);
				break;
			case MINUS:
				consume(MINUS);
				t = currentToken;
				consume(UNSIGNED);
				cg.emitInstruction("pwc", "-" + t.image);
				break;
			case ID:
				t = currentToken;
				consume(ID);
				st.enter(t.image);
				cg.emitInstruction("p", t.image);
				break;
			case LEFTPAREN:
				consume(LEFTPAREN);
				expr();
				consume(RIGHTPAREN);
				break;
			default:
				throw genEx("Expecting factor");
		}
	}
} // end of S1Parser
	// ======================================================

class S2CodeGen {
	private PrintWriter outFile;
	private S2SymTab st;

	// -----------------------------------------
	public S2CodeGen(PrintWriter outFile, S2SymTab st) {
		this.outFile = outFile;
		this.st = st;
	}

	// -----------------------------------------
	public void emitInstruction(String op) {
		outFile.printf("          %-4s%n", op);
	}

	// -----------------------------------------
	public void emitInstruction(String op, String opnd) {
		outFile.printf("          %-4s      %s%n", op, opnd);
	}

	// -----------------------------------------
	private void emitdw(String label, String value) {
		outFile.printf(
				"%-9s dw        %s%n", label + ":", value);
	}

	// -----------------------------------------
	public void endCode() {
		outFile.println();
		emitInstruction("halt");

		int size = st.getSize();
		// emit dw for each symbol in the symbol table
		for (int i = 0; i < size; i++)
			emitdw(st.getSymbol(i), "0");
	}
} // end of S1CodeGen
