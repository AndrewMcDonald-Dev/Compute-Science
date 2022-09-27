/* Figure 9.1: Recursive-descent parser for

             Selection set
   1) S -> BD   {b, c}
   2) B -> bB   {b}
   3) B -> c    {c}
   4) D -> de   {d}
*/
import java.util.Scanner;
//======================================================
class Fig0901 
{
  public static void main(String[] args)
  {
    // Construct token manager (see Fig. 6.12)
    ArgsTokenMgr tm = new ArgsTokenMgr(args);
    // Construct parser
    Fig0901Parser parser = new Fig0901Parser(tm);

    try
    {
      parser.parse();                  // do parse
    }
    catch (RuntimeException e) 
    {
      System.err.println(e.getMessage());
      System.err.println("reject");
      System.exit(1);
    }
    // reach here then accept
    System.out.println("accept");
  }
}                                      // end of Fig0901
//======================================================
class ArgsTokenMgr
{
  private int index;
  String input;
  //-----------------------------------------
  public ArgsTokenMgr(String[] args) 
  {
    if (args.length > 0)
      input = args[0];
    else  // treat no command line arg as null string
      input = "";
    index = 0;
    System.out.println("input = " + input);
  }                            
  //-----------------------------------------
  public char getNextToken() 
  {
    if (index < input.length())
      return input.charAt(index++); // return next char
    else
      return '#';              // # signals end of input
  }
}                                 // end of ArgsTokenMgr
//======================================================
class Fig0901Parser
{
  private ArgsTokenMgr tm;
  private char currentToken;
  //-----------------------------------------
  public Fig0901Parser(ArgsTokenMgr tm)
  {
    this.tm = tm;
    advance();        // prime currentToken
  }
  //-----------------------------------------
  private void advance()
  {
    currentToken = tm.getNextToken();
  }
  //-----------------------------------------
  private void consume(char expected)
  {
    if (currentToken == expected)
      advance();
    else
      throw new RuntimeException(
                  "Expecting \""  +  expected  +  "\""); 
  }
  //-----------------------------------------
  public void parse()
  {
    S();
    if (currentToken != '#')    // trailing-garbage test
      throw new RuntimeException(
                              "Expecting end of input");
  }
  //-----------------------------------------
  private void S()
  {
    B();                           // apply S -> BD
    D();
  }
  //-----------------------------------------
  private void B()
  {
    switch(currentToken)
    {
      case 'b': 
        consume('b');              // apply B -> bB
        B();
        break;
      case 'c': 
        consume('c');             // apply B -> c
        break;
      default:  
        throw new RuntimeException(
                            "Expecting \"b\" or \"c\"");
    }
  }
  //-----------------------------------------
  private void D()
  {
    consume('d');              // apply D -> de
    consume('e');
  }
}                                // end of Fig0901Parser
