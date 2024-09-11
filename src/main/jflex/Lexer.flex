package srbn.Lexer;

import srbn.Parser.ParserSym;
import java_cup.runtime.*;
import java.util.ArrayList;
import srbn.Backend.Domain.ErrorP;

%%

//JFlex Config
%class Lexer
%unicode
%line
%column
%caseless
%cup
%public

//REGEX
WITHESPACE = [ \t\n\r\f]
NUMBER = "-"?[0-9]+
LETTER = [a-zA-Z]
CHAR_CONT = "'"([^'\n])"'"
STRING_CONT = "\""([^\"\n])([^\"\n])+"\"" | "'"([^'\n])([^'\n])+"'"
COMMENTS = "(*" [^*)]* "*)" | "{" [^}]* "}"
DECIMAL = "-"?{NUMBER}+"."{NUMBER}+
ID = {LETTER}({LETTER}|{NUMBER}|-|_)*
//AGRUPATION SYMBS
OPENBRAKET = "{"
CLOSEBRAKET = "}"
OPENBSQUARE = "["
CLOSEBSQUARE = "]"
OPENPAREN = "("
CLOSEPAREN = ")"
//TYPES
INTEGER = "integer"
REAL = "real"
CHAR = "char"
BOOLEAN = "boolean"
STRING = "string"
//PUNTATION N MATH SYMBS
COMMA = ","
SEMICOLON = ";"
DOT = "."
EQUAL = "="
PLUS = "+"
MINUS = "-"
MULT = "*"
SLASH = "/"
//SPECIAL SYMBS

ASSIGN = ":="
COLON = ":"
SUSPEND = ".." | "..."
//LOGICAL SYMBS
DIFF = "<>"
LESS = "<"
GREATER = ">"
LESSEQUAL = "<="
GREATEREQUAL = ">="
//KEYWORDS
AND = "and"
ARRAY = "array"
BEGIN = "begin"
BREAK = "break"
CASE = "case"
CONST = "const"
CONTINUE = "continue"
DIV = "div"
DO = "do"
DOWNTO = "downto"
ELSE = "else"
END = "end"
EXIT = "exit"
FILE = "file"
FOR = "for"
FUNCTION = "function"
GOTO = "goto"
IF = "if"
IN = "in"
LABEL = "label"
MOD = "mod"
NIL = "nil"
NOT = "not"
OF = "of"
OR = "or"
PACKED = "packed"
PROCEDURE = "procedure"
PROGRAM = "program"
RECORD = "record"
REPEAT = "repeat"
SET = "set"
THEN = "then"
TO = "to"
TYPE = "type"
UNTIL = "until"
VAR = "var"
WHILE = "while"
WITH = "with"
//COMS
WRITELN = "writeln"
READLN = "readln"

%{
    StringBuffer sb = new StringBuffer();
    ArrayList<ErrorP> errors = new ArrayList<>();
    private Symbol symbT(int type) {
        return new Symbol(type, yyline+1, yycolumn+1);
    }

    private Symbol symbT(int type, Object value) {
        return new Symbol(type, yyline+1, yycolumn+1, value);
    }

    private void sintaxError() {
        System.out.println("Row: "+yyline+1 +"Col: "+ yycolumn+1+ "No se reconoce el simbolo: " + yytext());
        this.errors.add(new ErrorP(yyline+1, yycolumn+1, yytext(), 0, "No se reconoce el simbolo" + yytext()));
    }

    public void setErrorList(ArrayList<ErrorP> errors) {
        if(this.errors == null){
        this.errors = errors;
        } else {
            this.errors.addAll(errors);
        }
    }

    public ArrayList<ErrorP> getErrors() {
        return errors;
    }

%}

%eofval{
	return new Symbol(ParserSym.EOF);
%eofval}

%%

{NUMBER}              {return symbT(ParserSym.NUMBER, Integer.parseInt(yytext()));}
{DECIMAL}             {return symbT(ParserSym.DECIMAL, Double.parseDouble(yytext()));}
{SUSPEND}             {return symbT(ParserSym.SUSPEND, yytext());}
{OPENBRAKET}          {return symbT(ParserSym.OPENBRAKET, yytext());}
{CLOSEBRAKET}         {return symbT(ParserSym.CLOSEBRAKET, yytext());}
{OPENBSQUARE}         {return symbT(ParserSym.OPENBSQUARE, yytext());}
{CLOSEBSQUARE}        {return symbT(ParserSym.CLOSEBSQUARE, yytext());}
{OPENPAREN}           {return symbT(ParserSym.OPENPAREN, yytext());}
{CLOSEPAREN}          {return symbT(ParserSym.CLOSEPAREN, yytext());}
{ASSIGN}              {return symbT(ParserSym.ASSIGN, yytext());}
{COMMA}               {return symbT(ParserSym.COMMA, yytext());}
{SEMICOLON}           {return symbT(ParserSym.SEMICOLON, yytext());}
{DOT}                 {return symbT(ParserSym.DOT, yytext());}
{EQUAL}               {return symbT(ParserSym.EQUAL, yytext());}
{PLUS}                {return symbT(ParserSym.PLUS, yytext());}
{MINUS}               {return symbT(ParserSym.MINUS, yytext());}
{MULT}                {return symbT(ParserSym.MULT, yytext());}
{SLASH}               {return symbT(ParserSym.SLASH, yytext());}
{COLON}               {return symbT(ParserSym.COLON, yytext());}
{DIFF}                {return symbT(ParserSym.DIFF, yytext());}
{LESSEQUAL}           {return symbT(ParserSym.LESSEQUAL, yytext());}
{GREATEREQUAL}        {return symbT(ParserSym.GREATEREQUAL, yytext());}
{LESS}                {return symbT(ParserSym.LESS, yytext());}
{GREATER}             {return symbT(ParserSym.GREATER, yytext());}
{INTEGER}             {return symbT(ParserSym.INTEGER, yytext());}
{REAL}                {return symbT(ParserSym.REAL, yytext());}
{CHAR}                {return symbT(ParserSym.CHAR, yytext());}
{BOOLEAN}             {return symbT(ParserSym.BOOLEAN, yytext());}
{STRING}              {return symbT(ParserSym.STRING, yytext());}
{AND}                 {return symbT(ParserSym.AND, yytext());}
{ARRAY}               {return symbT(ParserSym.ARRAY, yytext());}
{BEGIN}               {return symbT(ParserSym.BEGIN, yytext());}
{BREAK}               {return symbT(ParserSym.BREAK, yytext());}
{CASE}                {return symbT(ParserSym.CASE, yytext());}
{CONST}               {return symbT(ParserSym.CONST, yytext());}
{CONTINUE}            {return symbT(ParserSym.CONTINUE, yytext());}
{DIV}                 {return symbT(ParserSym.DIV, yytext());}
{DO}                  {return symbT(ParserSym.DO, yytext());}
{DOWNTO}              {return symbT(ParserSym.DOWNTO, yytext());}
{ELSE}                {return symbT(ParserSym.ELSE, yytext());}
{END}                 {return symbT(ParserSym.END, yytext());}
{EXIT}                {return symbT(ParserSym.EXIT, yytext());}
{FILE}                {return symbT(ParserSym.FILE, yytext());}
{FOR}                 {return symbT(ParserSym.FOR, yytext());}
{FUNCTION}            {return symbT(ParserSym.FUNCTION, yytext());}
{GOTO}                {return symbT(ParserSym.GOTO, yytext());}
{IF}                  {return symbT(ParserSym.IF, yytext());}
{IN}                  {return symbT(ParserSym.IN, yytext());}
{LABEL}               {return symbT(ParserSym.LABEL, yytext());}
{MOD}                 {return symbT(ParserSym.MOD, yytext());}
{NIL}                 {return symbT(ParserSym.NIL, yytext());}
{NOT}                 {return symbT(ParserSym.NOT, yytext());}
{OF}                  {return symbT(ParserSym.OF, yytext());}
{OR}                  {return symbT(ParserSym.OR, yytext());}
{PACKED}              {return symbT(ParserSym.PACKED, yytext());}
{PROCEDURE}           {return symbT(ParserSym.PROCEDURE, yytext());}
{PROGRAM}             {return symbT(ParserSym.PROGRAM, yytext());}
{RECORD}              {return symbT(ParserSym.RECORD, yytext());}
{REPEAT}              {return symbT(ParserSym.REPEAT, yytext());}
{SET}                 {return symbT(ParserSym.SET, yytext());}
{THEN}                {return symbT(ParserSym.THEN, yytext());}
{TO}                  {return symbT(ParserSym.TO, yytext());}
{TYPE}                {return symbT(ParserSym.TYPE, yytext());}
{UNTIL}               {return symbT(ParserSym.UNTIL, yytext());}
{VAR}                 {return symbT(ParserSym.VAR, yytext());}
{WHILE}               {return symbT(ParserSym.WHILE, yytext());}
{WITH}                {return symbT(ParserSym.WITH, yytext());}
{WRITELN}             {return symbT(ParserSym.WRITELN, yytext());}
{READLN}              {return symbT(ParserSym.READLN, yytext());}
{ID}                  {return symbT(ParserSym.ID, yytext());}
{CHAR_CONT}           {return symbT(ParserSym.CHAR_CONT, yytext().substring(1, yytext().length()-1));}
{STRING_CONT}         {return symbT(ParserSym.STRING_CONT, yytext().substring(1, yytext().length()-1));}
{COMMENTS}            {/* ignore */}
{WITHESPACE}+         { /* ignore */ }

.                     { sintaxError(); }