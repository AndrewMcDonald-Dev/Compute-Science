/* Figure 10.4: Recursive-descent parser/translator for

   void S():  {}                           Selection Set
   {
      expr() {System.out.println();} {"+", "-", "*", 
                                     "/", "b", "c", "d"}
   }

   void expr()  : {}
   {
      "+" expr() expr() {System.out.print('+');}   {"+"}
    |
      "-" expr() expr() {System.out.print('-');}   {"-"}
    |
      "*" expr() expr() {System.out.print('*');}   {"*"}
    |
      "/" expr() expr() {System.out.print('/');}   {"/"}
    |
      "b" {System.out.print('b');}                 {"b"}
    |                               
      "c" {System.out.print('c');}                 {"c"}
    |
      "d" {System.out.print('d');}                 {"d"} 
   }
*/
import java.util.Scanner; 
//======================================================
class Fig1004 
{
  public static void main(String[] args)
  {
    // construct token manager (see Fig. 6.12)
    ArgsTokenMgr tm = new ArgsTokenMgr(args);
    // construct parser
    Fig1004Parser parser = new Fig1004Parser(tm);

    try
    {
      parser.parse();           // parse and translate
    }
    catch (RuntimeException e)
    {
      System.err.println();
      System.err.println(e.getMessage());
      System.exit(1);
    }
  }
}                                      // end of Fig1004
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
class Fig1004Parser
{
  private ArgsTokenMgr tm;
  private char currentToken;
  //-----------------------------
  public Fig1004Parser(ArgsTokenMgr tm)
  {
    this.tm = tm;
    advance();             // prime currentToken
  }
  //-----------------------------
  private void advance()
  {
    currentToken = tm.getNextToken();
  }
  //-----------------------------
  private void consume(char expected)
  {
    if (currentToken == expected)
      advance();
    else
      throw new RuntimeException(
                      "Expecting \"" + expected + "\"");
  }
  //-----------------------------
  public void parse()
  { 
    S();
    if (currentToken != '#')
      throw new RuntimeException(
                              "Expecting end of input");
  }
  //-----------------------------
  private void S()
  {
    expr();
    System.out.println();
  }
  //-----------------------------
  private void expr()
  {
    switch(currentToken)
    {
      case '+':
        consume('+');
        expr();
        expr();
        System.out.print('+');
        break;
      case '-':
        consume('-');
        expr();
        expr();
        System.out.print('-');
        break;
      case '*':
        consume('*');
        expr();
        expr();
        System.out.print('*');
        break;
      case '/':
        consume('/');
        expr();
        expr();
        System.out.print('/');
        break;
      case 'b':
        consume('b');
        System.out.print('b');
        break;
      case 'c':
        consume('c');
        System.out.print('c');
        break;
      case 'd':
        consume('d');
        System.out.print('d');
        break;
      default:
        throw new RuntimeException(
                         "Expecting prefix expression");
    }
  }
}                                // end of Fig1004Parser
