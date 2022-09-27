// Fig2315.l
import java.io.*;

%%

%byaccj     // byacc/j compatibility mode

%{
private int lineno = 1;
PrintWriter w;
public static void main(String[] args) throws IOException
{
  FileReader r = new FileReader(args[0]);
  PrintWriter w = new PrintWriter(args[1]);
  Yylex numberFile = new Yylex(r);

  // initialize instance variable in numberFile
  numberFile.w = w;

  // call lexical analyzer
  numberFile.yylex();

  w.close();
}
%}

%%

[^\n\r]+    {w.printf("%4d %s%n", lineno++, yytext());}
.|\n        {/* do nothing */}
