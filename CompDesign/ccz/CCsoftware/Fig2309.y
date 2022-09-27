// Fig2309.y

%token  UNSIGNED


%%
S    : expr {System.out.println($1.ival);}
     ;
expr : expr '+' expr  {$$.ival = $1.ival + $3.ival;}
     | expr '-' expr  {$$.ival = $1.ival - $3.ival;}
     | expr '*' expr  {$$.ival = $1.ival * $3.ival;}
     | expr '/' expr  {$$.ival = $1.ival / $3.ival;}
     | UNSIGNED
     ;
%%
private static final int EOF = 0;
private char currentChar= '\n';
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

  if (Character.isDigit(currentChar))
  {
    buffer.setLength(0);  // clear buffer
    do
    {
      buffer.append(currentChar);
      getNextChar();
    } while (Character.isDigit(currentChar));
    kind = UNSIGNED;
    yytext = buffer.toString();
    yylval = new ParserVal(Integer.parseInt(yytext));
  }
  else 
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
}
