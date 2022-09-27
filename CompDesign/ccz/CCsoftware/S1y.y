// S1y.y yacc input file
// To use, enter
//   yacc -J S1y.y     (outputs compiler to Parser.java)
//   javac Parser.java (compiles Parser.java)
//   java Parser S1    (compiles S1.s source program)
//   a S1.a            (assembles S1.a)
//   e S1 /c           (executes S1.e with checking)

%{
import java.io.*;
import java.util.Scanner;
%}

%token ID
%token UNSIGNED
%token PRINTLN
//======================================================
%%
program: 
   statementList  
   {cg.endCode();}
;
//------------------------------
statementList: 
   statementList statement
 |
   /* empty */
;
//------------------------------
statement: 
   assignmentStatement
 |
   printlnStatement
 |
   error ';' // matches from error to the next semicolon
;
//------------------------------
assignmentStatement: 
   ID
   {
     st.enter($1.sval); 
     cg.emitInstruction("pc", $1.sval);
   }
   '=' 
   expr 
   ';' 
   {cg.emitInstruction("stav", "");}
;
//------------------------------
printlnStatement: 
   PRINTLN
   '('
   expr
   {
     cg.emitInstruction("dout", "");
     cg.emitInstruction("pc","'\\n'");
     cg.emitInstruction("aout", "");
   }
  ')'
  ';'
;
//------------------------------
expr: 
   expr
   '+' 
   term    
   {cg.emitInstruction("add", "");} 
 | 
   term
;
//------------------------------
term: 
   term 
   '*' 
   factor    
   {cg.emitInstruction("mult", "");} 
 | 
   factor
;
//------------------------------
factor: 
   UNSIGNED  
   {cg.emitInstruction("pwc", $1.sval); }    
 | 
   '+'
   UNSIGNED  
   {cg.emitInstruction("pwc", $2.sval); }    
 | 
   '-'
   UNSIGNED  
   {cg.emitInstruction("pwc", "-" + $2.sval); }    
 | 
   ID
   {
     st.enter($1.sval);
     cg.emitInstruction("p", $1.sval);
   }
 | 
   '(' 
   expr 
   ')'
;
//======================================================
%%
Scanner inFile;
PrintWriter outFile;
static final int EOF = 0;
String inputLine;
char currentChar = '\n';
int currentLine = 0, currentColumn = 0;
int yybeginLine, yyendLine, yybeginColumn, yyendColumn;
S1SymTab st;
S1CodeGen cg;
StringBuffer buffer;
//------------------------------------
public static void main(String[] args) throws IOException
{
  System.out.println("S1y compiler written by ..."); 

  if (args.length != 1) 
  {
    System.err.println("Wrong number cmd line args");
    System.exit(1);
  }

  // build input and output file names
  String inFileName  = args[0] + ".s";
  String outFileName = args[0] + ".a";

  // construct file objects
  Scanner inFile = new Scanner(new File(inFileName));
  PrintWriter outFile = new PrintWriter(outFileName);

  outFile.println("; from S1y compiler written by ...");

  // construct objects that make up the compiler
  S1SymTab st = new S1SymTab();
  S1CodeGen cg = new S1CodeGen(outFile, st);
  Parser parser = new Parser();

  // initialize instance variables in parser
  parser.inFile = inFile;
  parser.outFile = outFile;
  parser.st = st;
  parser.cg = cg;
  parser.buffer = new StringBuffer();

  // parse and translate
  parser.yyparse();                                    
  
  outFile.close();
}
//------------------------------------
private int yylex() 
{
  int kind;

  // skip whitespace
  while (Character.isWhitespace(currentChar))
    getNextChar();

  yybeginLine = currentLine;
  yybeginColumn = currentColumn;

  // check for EOF
  if (currentChar == EOF) 
  {
    yytext = "<EOF>";
    yyendLine = currentLine;
    yyendColumn = currentColumn;
    kind = currentChar;
  }

  else  // check for unsigned int 
  if (Character.isDigit(currentChar)) 
  {
    buffer.setLength(0);  // clear buffer
    do 
    {
      buffer.append(currentChar);
      yyendLine = currentLine;
      yyendColumn = currentColumn;
      getNextChar();
    } while (Character.isDigit(currentChar));
    yytext = buffer.toString();
    kind = UNSIGNED;
  }
 
  else  // check for identifier
  if (Character.isLetter(currentChar)) 
  { 
    buffer.setLength(0);  // clear buffer
    do 
    {
      buffer.append(currentChar);
      yyendLine = currentLine;
      yyendColumn = currentColumn;
      getNextChar();
    } while (Character.isLetterOrDigit(currentChar));
    yytext = buffer.toString();

    // check if keyword
    if (yytext.equals("println"))
      kind = PRINTLN;
    else  // not a keyword so kind is ID
      kind = ID;
  }

  else  // do this if preceding cases do not apply
  {                                 
    // use character itself as its kind value
    kind = currentChar;
    yyendLine = currentLine;
    yyendColumn = currentColumn;
    yytext = Character.toString(currentChar);
    getNextChar();  // read one char beyond end of token
  }

  // return ParserVal obj containing yytext via yylval
  yylval = new ParserVal(yytext);
  // use return statement to return token kind
  return kind;
}
//------------------------------------
private void getNextChar()
{
  if (currentChar == EOF)
     return;
  if (currentChar == '\n')        // need line?
  {
    if (inFile.hasNextLine())     // any lines left?
    {
      inputLine = inFile.nextLine();  // get next line

      // output source line as comment
      outFile.println("; " + inputLine);

      inputLine = inputLine + "\n";  // mark line end
      currentColumn = 0;
      currentLine++;

    }
    else  // at EOF
    {
      currentChar = EOF;
      return;
    }
  }
  currentChar = inputLine.charAt(currentColumn++);
}
//------------------------------------
private void yyerror(String s)
{
  String message = 
    s + " while scanning \"" + yytext + "\" on line " +  
    yybeginLine + " column " + yybeginColumn;
  System.err.println(message);
  outFile.println(message);
}
