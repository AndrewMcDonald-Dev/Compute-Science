// Fig2314.l
import java.io.*;

%%

%byaccj   // byacc/j compatibility mode

%{
private int wordCnt = 0;
public static void main(String[] args) throws IOException
{
  FileReader r = new FileReader(args[0]);

  // create lexical analyzer
  Yylex counter = new Yylex(r);

  // call lexical analyzer
  counter.yylex();

  System.out.println("Word count = " + counter.wordCnt);
}
%}

%%

[^ \n\r\t]+  {wordCnt++;}       // match entire line
.|\n         {/* do nothing */} // match any single char
