// Fig2304.y

// no part 1

%%
S : B C   {System.out.println("b count = " + bCount);}
  ;
B : 'b' B
  | 'b'
  ;
C : 'c'
  ;
%%
private static final int EOF = 0;
private char currentChar;
private int bCount = 0;
private String input;
private int inputIndex = 0;
//-----------------------------------------
public static void main(String[] args)
{
  Parser parser = new Parser();
  parser.input = args[0];
  parser.yyparse();
}
//-----------------------------------------
private int yylex()
{
  if (inputIndex >= input.length()) // at end-of-input?
    return EOF;
  else
  {
    currentChar = input.charAt(inputIndex++);
    if (currentChar == 'b')
      bCount++;
    return currentChar;
  }
}
//-----------------------------------------
private void yyerror(String s)
{
  System.err.println(s);
  System.exit(1);
}
