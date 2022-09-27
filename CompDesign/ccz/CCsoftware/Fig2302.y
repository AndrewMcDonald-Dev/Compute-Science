// Fig2302.y

// no part 1

%%
S : B C   {System.out.println("Prod 1");} 
  ;
B : 'b' B {System.out.println("Prod 2");}
  | 'b'   {System.out.println("Prod 3");}
  ;
C : 'c'   {System.out.println("Prod 4 " + yytext);}
  ;
%%
// parser expects 0 on end of file
private static final int EOF = 0;
private String input;
private int inputIndex = 0;
private char currentChar;
//-----------------------------------------
public static void main(String[] args)
{
  Parser parser = new Parser();
  parser.input = args[0];
  parser.yyparse();    // call yacc-generated parser
}
//-----------------------------------------
private int yylex()  // lexical analyzer
{
  if (inputIndex >= input.length())
    return EOF;
  else
  { 
    currentChar = input.charAt(inputIndex++);
    yytext = Character.toString(currentChar);
    return currentChar;
  }
}
//-----------------------------------------
private void yyerror(String s) // error handler
{
  System.err.println(s);
  System.exit(1);
}
