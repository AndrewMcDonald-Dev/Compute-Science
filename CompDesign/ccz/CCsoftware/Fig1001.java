/* Figure 10.1: Recursive-descent parser/translator for

   void S() : {}                        Selection Set
   {
      "b" C() "d"                           {"b"}
   }

   void C(): {}
   {      
      "c" {System.out.println('c');} C()    {"c"}
    |
      {}                                    {"d"}
   }
*/
import java.util.Scanner; 
//======================================================
class Fig1001 
{
  public static void main(String[] args)
  {
    // construct token manager (see Fig. 6.12)
    ArgsTokenMgr tm = new ArgsTokenMgr(args);
    // Construct parser
    Fig1001Parser parser = new Fig1001Parser(tm);

    try
    {
      parser.parse();            // parse and translate
    }
    catch (RuntimeException e)
    {
      System.err.println(); 
      System.err.println(e.getMessage());
      System.exit(1);
    }
 }
}                                      // end of Fig1001
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
class Fig1001Parser
{
  private ArgsTokenMgr tm;
  private char currentToken;
  //-----------------------------------------
  public Fig1001Parser(ArgsTokenMgr tm)
  {
    this.tm = tm;
    advance();              // prime currentToken
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
                      "Expecting \"" + expected + "\"");
  }
  //-----------------------------------------
  public void parse()
  { 
    S();
    if (currentToken != '#')
      throw new RuntimeException(
                              "Expecting end of input");
  }
  //-----------------------------------------
  private void S()
  {
    consume('b');           // apply production S -> bCd
    C();
    consume('d');
  }
  //-----------------------------------------
  private void C()
  {
    switch(currentToken)
    {
      case 'c':             // apply production C -> cC
        consume('c');
        System.out.print('c');      // this is an action
        C();
        break;
      case 'd':
        ;                   // apply lambda production
        break;
      default:
        throw new RuntimeException(
                            "Expecting \"c\" or \"d\"");
    }
  }
}                                // end of Fig1001Parser

