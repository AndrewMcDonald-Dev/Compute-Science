// S1l.l
%%

%byaccj

%{
private Parser parser;
public Yylex(java.io.Reader inFile, Parser parser)
{
  this(inFile);
  this.parser = parser;
}
%}

IDENT = [A-Za-z][A-Za-z0-9]* 

%%

[ \t\n\r] { /* do nothing */ }  // discard whitespace
println   {
            parser.yylval = new ParserVal(yytext());
            return parser.PRINTLN;
          }
[0-9]+    {
            parser.yylval = new ParserVal(yytext());
            return parser.UNSIGNED;
          }
{IDENT}      {
            parser.yylval = new ParserVal(yytext());
            return parser.ID;
          }
.         { // <-- period at the start of this line
            parser.yylval = new ParserVal(yytext());
            return yytext().charAt(0);
          }
