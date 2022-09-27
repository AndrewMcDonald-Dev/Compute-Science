// Fig2305.y

%token  UNSIGNED

%%
S    : expr {System.out.println($1.ival);}
     ;
expr :  expr '-' UNSIGNED  {$$.ival = $1.ival-$3.ival;}
     |  UNSIGNED
     ;
%%
private static final int EOF = 0;
private char currentChar = '\n';
private String input;
private int inputIndex = 0;
private StringBuffer buffer;
//-----------------------------------------
public static void main(String[] args)
{
  Parser parser = new Parser();
  parser.input = args[0];
  parser.buffer = new StringBuffer();
  parser.yyparse();
}
//-----------------------------------------
private int yylex()
{
  int kind;

  while (Character.isWhitespace(currentChar))
    getNextChar();

  // check if unsigned int
  if (Character.isDigit(currentChar))
  {
    buffer.setLength(0);
    do
    {
      buffer.append(currentChar);
      getNextChar();
    } while (Character.isDigit(currentChar));
    yytext = buffer.toString();
    kind = UNSIGNED;
    // assign yylval object that contains value
    yylval = new ParserVal(Integer.parseInt(yytext));
  }

  else  // return currentChar as next token
  {
    kind = currentChar;
    yytext = Character.toString(currentChar); 
    yylval = null;
    getNextChar();
  }
  return kind;
} 
//-----------------------------------------
private void getNextChar()
{
  if (inputIndex >= input.length())
    currentChar = EOF;
  else
    currentChar = input.charAt(inputIndex++);
}
//-----------------------------------------
private void yyerror(String s)
{
  System.err.println(s);
  System.exit(1);
}

