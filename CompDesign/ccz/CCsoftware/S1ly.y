// S1ly.y yacc input file
// To use, enter
//   yacc -J S1ly.y
//   jflex S1l.l
//   javac Parser.java
//   javac Yylex.java
//   java Parser S1
//   a S1.a
//   e S1 /c

%{
import java.io.*;
import java.util.Scanner;
%}

%token PRINTLN
%token UNSIGNED
%token ID
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
   error ';'
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
%%
//======================================================
FileReader inFile;
PrintWriter outFile;
S1SymTab st;
S1CodeGen cg;
private Yylex lexer;

public static void main(String[] args) throws IOException
{
  System.out.println("S1ly compiler written by ..."); 

  if (args.length != 1) 
  {
    System.err.println("Wrong number cmd line args");
    System.exit(1);
  }

  // build input and output file names
  String inFileName  = args[0] + ".s";
  String outFileName = args[0] + ".a";

  // construct file objects
  FileReader inFile = new FileReader(inFileName);
  PrintWriter outFile = new PrintWriter(outFileName);

  outFile.println("; from S1ly compiler written by ...");
 
  // construct objects that make up the compiler
  S1SymTab st = new S1SymTab();
  S1CodeGen cg = new S1CodeGen(outFile, st);
  Parser parser = new Parser();

  // initialize instance variables in parser
  parser.lexer = new Yylex(inFile, parser);
  parser.inFile = inFile;
  parser.outFile = outFile;
  parser.st = st;
  parser.cg = cg;

  // parse and translate
  parser.yyparse();                                    
   
  outFile.close();
}
//------------------------------------
private int yylex()
{
  int yyl_return = -1;
  try
  {
    yyl_return = lexer.yylex();
  }
  catch (IOException e)
  {
    System.err.println(e);
  }
  return yyl_return;
}
//------------------------------------
void yyerror(String s)
{
  System.err.println(s);
  outFile.println(s);
}
