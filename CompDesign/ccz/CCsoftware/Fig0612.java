/* Figure 6.12: Top-down stack parser for

   1) S -> bScA
   2) S -> cbd
   3) A -> bcA
   4) A -> d
*/
import java.util.*;  // import Stack and Scanner classes
//======================================================
class Fig0612 
{
  public static void main(String[] args)
  {
    // construct token manager
    ArgsTokenMgr tm = new ArgsTokenMgr(args);

    // construct parser, pass it the token manager
    Fig0612Parser parser = new Fig0612Parser(tm); 

    parser.parse();                    // do parse
  }
}                                      // end of Fig0612
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
class Fig0612Parser
{
  private ArgsTokenMgr tm;         // token manager
  private Stack<Character> stk;    // stack for parser
  private char currentToken;       // current token
  //-----------------------------------------
  public Fig0612Parser(ArgsTokenMgr tm)
  {
    this.tm = tm;                  // save tm  
    advance();                     // prime currentToken
    stk = new Stack<Character>();  // create stack
    stk.push('$');                 // mark stack bottom
    stk.push('S');                 // push start symbol
  }
  //-----------------------------------------
  private void advance()
  {
    // get next token and save in currentToken
    currentToken = tm.getNextToken();
  }
  //-----------------------------------------
  public void parse()
  {
    boolean done = false;      // controls loop exit

    while (!done) 
    { 
      switch(stk.peek()) 
      {
        case 'S': 
          if (currentToken == 'b') 
          { 
            stk.pop();         // apply production 1
            stk.push('A');
            stk.push('c'); 
            stk.push('S'); 
            advance();
          }  
          else 
          if (currentToken == 'c') 
          {
            stk.pop();         // apply production 2
            stk.push('d');
            stk.push('b');
            advance();
          }  
          else
            done = true;       // exit on reject config
          break;

        case 'A':
          if (currentToken == 'b') 
          {
            stk.push('c');     // apply production 3
            advance();
          }
          else 
          if (currentToken == 'd') 
          {
            stk.pop();         // apply production 4
            advance();
          }
          else
            done = true;       // exit on reject config
          break;

        case 'b':
        case 'c':
        case 'd':
          if (stk.peek().charValue() == currentToken) 
          {
            stk.pop();    // discard terminal on stack 
            advance();    // discard matching input
          }
          else
            done = true;  // exit on reject config
          break;

        case '$':         // exit on empty stack
          done = true;   
          break;
      }                   // end of switch
    }                     // end of while

    // test if in accept configuration
    if (currentToken =='#' && stk.peek() == '$')
      System.out.println("accept");
    else
      System.out.println("reject");
  }
}
