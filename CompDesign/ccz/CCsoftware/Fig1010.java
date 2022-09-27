// Figure 10.10: Compiles a prefix expr to its value.
import java.io.*;
import java.util.Scanner;
//======================================================
class Fig1010
{
  public static void main(String[] args) throws 
                                             IOException
  {
    if (args.length != 1)
    {
      System.err.println("Wrong number cmd line args");  
      System.exit(1);
    }

    // create the objects that make up the compiler
    Scanner inFile = new Scanner(new File(args[0]));
    Fig1010TokenMgr tm = new Fig1010TokenMgr(inFile);
    Fig1010Parser parser = new Fig1010Parser(tm);

    // parse and translate
    try
    {
      parser.parse();
    }
    catch (RuntimeException e)
    {
      System.err.println(e.getMessage());
      System.exit(1);
    }
  }
}                                      // end of Fig1010
//======================================================
interface Fig1010Constants
{
  // integers that identify token kinds
  public int EOF = 0;
  public int UNSIGNED = 1;
  public int PLUS = 2;
  public int MINUS = 3;
  public int TIMES = 4;
  public int DIVIDE = 5;
  public int ERROR = 6;

  // tokenImage provides string for each kind
  String[] tokenImage = 
  {
    "<EOF>",
    "<UNSIGNED>",
    "\"+\"",
    "\"-\"",
    "\"*\"",
    "\"/\"",
    "<ERROR>"
  };
}                             // end of Fig1010Constants
//======================================================
class Fig1010TokenMgr implements Fig1010Constants
{
  private Scanner inFile;
  private char currentChar;
  private int currentColumnNumber;
  private int currentLineNumber;
  private int lineLength;
  private String inputLine;     // holds 1 line of input
  private Token token;          // holds 1 token
  private StringBuffer buffer;  // token image built here
  //-----------------------------------------
  public Fig1010TokenMgr(Scanner inFile)
  {
    this.inFile = inFile;
    currentChar = '\n';         //  \n will trigger read
    currentColumnNumber = 0;
    currentLineNumber = 0; 
    buffer = new StringBuffer();
  }
  //-----------------------------------------
  public Token getNextToken()
  {
    // skip whitespace
    while (Character.isWhitespace(currentChar))
      getNextChar();

    // create token to be returned to the parser
    token = new Token();
    token.next = null; 

    // save start-of-token position
    token.beginLine = currentLineNumber;
    token.beginColumn = currentColumnNumber;

    // check for EOF
    if (currentChar == EOF)
    {
      token.image = "<EOF>";
      token.endLine = currentLineNumber;
      token.endColumn = currentColumnNumber;
      token.kind = EOF;
    }

    else  // check for unsigned int
    if (Character.isDigit(currentChar)) 
    {
      buffer.setLength(0);   // set length to 0
      do                     // process unsigned int
      {
        // append currentChar to buffer
        buffer.append(currentChar);

        // save token end location
        // must do this before calling getNextChar()
        token.endLine = currentLineNumber;
        token.endColumn = currentColumnNumber;

        getNextChar();
      } while (Character.isDigit(currentChar));
      token.image = buffer.toString();  
      token.kind = UNSIGNED;
    }

    else  // process one-character token
    {
      switch(currentChar)
      {
        case '+':
          token.kind = PLUS;
          break;
        case '-':
          token.kind = MINUS;
          break;
        case '*':
          token.kind = TIMES;
          break;
        case '/':
          token.kind = DIVIDE;
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

      getNextChar();  // read 1 char beyond end of token
    }    

    // token trace appears as comments in output file
    System.out.printf(
      "kd=%3d bL=%3d bC=%3d eL=%3d eC=%3d im= %s%n",
      token.kind, token.beginLine, token.beginColumn, 
      token.endLine, token.endColumn, token.image);

    return token;
 }
 //------------------------------------------
  private void getNextChar()
  {
    if (currentChar == EOF)
      return;

    if (currentChar == '\n')        // need line?
    {
      if (inFile.hasNextLine())     // any lines left?
      {
        inputLine = inFile.nextLine();  // get next line
        inputLine = inputLine + "\n";   // mark line end
        currentColumnNumber = 0;      
        currentLineNumber++;   
      }                                
      else  // at end of file
      {
         currentChar = EOF;
         return;
      }
    }

    // get next character from inputLine
    currentChar = 
                inputLine.charAt(currentColumnNumber++);
  }
}                              // end of Fig1010TokenMgr
//======================================================
class Fig1010Parser implements Fig1010Constants
{
  private Fig1010TokenMgr tm;
  private Token currentToken;
  private Token previousToken;
  //-----------------------------------------
  public Fig1010Parser(Fig1010TokenMgr tm)
  {
    this.tm = tm;
    currentToken = tm.getNextToken();  // prime
    previousToken = null;
  }
  //-----------------------------------------
  public void parse()
  {
    S();                            // do parse
    if (currentToken.kind != EOF)   // check for garbage
      throw genEx("Expecting <EOF>");
  }
  //-----------------------------------------
  // Construct and return an exception that contains
  // a message consisting of the image of the current
  // token, its location, and the expected tokens.
  //
  private RuntimeException genEx(String errorMessage)
  {
    return new RuntimeException("Encountered \"" + 
      currentToken.image + "\" on line " + 
      currentToken.beginLine + " column " + 
      currentToken.beginColumn +
      System.getProperty("line.separator") + 
      errorMessage);
  }
  //-----------------------------------------
  // Advance currentToken to next token.
  //
  private void advance()
  {
    previousToken = currentToken; 

    // If next token is on token list, advance to it.
    if (currentToken.next != null)
      currentToken = currentToken.next;

    // Otherwise, get next token from token mgr and 
    // put it on the list.
    else
      currentToken = 
                  currentToken.next = tm.getNextToken();
  }
  //-----------------------------------------
  // getToken(i) returns ith token without advancing
  // in token stream.  getToken(0) returns 
  // previousToken.  getToken(1) returns currentToken.
  // getToken(2) returns next token, and so on.
  //
  private Token getToken(int i)
  {
    if (i <= 0)
      return previousToken;

    Token t = currentToken;
    for (int j = 1; j < i; j++)  // loop to ith token
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
  //-----------------------------------------
  // If the kind of the current token matches the
  // expected kind, then consume advances to the next
  // token. Otherwise, it throws an exception.
  //
  private void consume(int expected)
  {
    if (currentToken.kind == expected)
      advance();
    else
      throw genEx("Expecting " + tokenImage[expected]);
  }
  //-----------------------------------------
  private void S()
  {
    int p;

    p = expr();
    System.out.println(p);  // display value of expr
  }
  //-----------------------------------------
  private int expr()
  {
    int p, q;
    Token t;

    switch(currentToken.kind)
    {
      case PLUS:
        consume(PLUS);
        p = expr();       // get value of first operand
        q = expr();       // get value of second operand
        return p + q;     // compute/return expr value
      case MINUS:
        consume(MINUS);
        p = expr();       // get value of first operand     
        q = expr();       // get value of second operand    
        return p - q;     // compute/return expr value
      case TIMES:
        consume(TIMES);
        p = expr();       // get value of first operand     
        q = expr();       // get value of second operand    
        return p * q;     // compute/return expr value
      case DIVIDE:
        consume(DIVIDE);
        p = expr();       // get value of first operand     
        q = expr();       // get value of second operand    
        return p / q;     // compute/return expr value
      case UNSIGNED:
        t = currentToken;              // save in t
        consume(UNSIGNED);             // consume token
        p = Integer.parseInt(t.image); // now use t
        return p;                      // return int val
      default:
        throw genEx("Expecting operator or " + 
                                  tokenImage[UNSIGNED]);
    }
  }
}                               // end of Fig1010 parser
