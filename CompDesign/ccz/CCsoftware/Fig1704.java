class Fig1704
{
  public static void main(String[] args)
  {
    // create token manager (see Fig. 6.12)
    ArgsTokenMgr tm = new ArgsTokenMgr(args);

    // create DFA, pass it the token manager
    Fig1704DFA m = new Fig1704DFA(tm); 

    m.runDFA();
  }
}                                      // end of Fig1704
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
class Fig1704DFA
{
  ArgsTokenMgr tm;
  private char currentToken;
  //-----------------------------------------
  public Fig1704DFA(ArgsTokenMgr tm)
  {
     this.tm = tm;
  }
  //-----------------------------------------
  public void advance()
  {
    // get next token and save in currentToken
    currentToken = tm.getNextToken();
  }
  //-----------------------------------------
  public void runDFA()
  {
    int currentState = 0;      // 0 is the start state
    advance();                 // get first char

    while (currentToken != '#') 
    {
      switch(currentState)
      {
        case 0:
          if (currentToken == 'b') currentState = 1;
          else
          if (currentToken == 'c') currentState = 1;
          break;
        case 1:
          if (currentToken == 'b') currentState = 2;
          else
          if (currentToken == 'c') currentState = 3;
          break;
        case 2:
          if (currentToken == 'b') currentState = 4;
          else
          if (currentToken == 'c') currentState = 4;
          break;
        case 3:
          if (currentToken == 'b') currentState = 4;
          else
          if (currentToken == 'c') currentState = 3;
          break;
        case 4:
          if (currentToken == 'b') currentState = 4;
          else
          if (currentToken == 'c') currentState = 4;
          break;
      }
      advance();
    }

    if (currentState == 2 || currentState == 3)
      System.out.println("accept");
    else
      System.out.println("reject");
 }
}                                          // end of DFA
