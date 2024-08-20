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
NUMBER = [0-9]+
LETTER = [a-zA-Z]
STRING_CONT = "\""([^\"\n])*"\""
COMMENTS = "(*" [^*)]* "*)" | "{" [^}]* "}"
DECIMAL = {NUMBER}+.{NUMBER}+
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
    ArrayList<ErrorP> errors = null;
    private Symbol symbol(int type) {
        return new Symbol(type, yyline+1, yycolumn+1);
    }

    private Symbol symbol(int type, Object value) {
        return new Symbol(type, yyline+1, yycolumn+1, value);
    }

    private void sintaxError() {
        System.out.println("Row: "+yyline+1 +"Col: "+ yycolumn+1+ "No se reconoce el simbolo: " + yytext());
        this.errors.add(new ErrorP(yyline+1, yycolumn+1, yytext()), 0, "No se reconoce el simbolo" + yytext());
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

{NUMBER}              {return symbol(ParserSym.NUMBER, Integer.parseInt(yytext()));}
{DECIMAL}             {return symbol(ParserSym.REAL, Double.parseDouble(yytext()));}

{SUSPEND}              {return symbol(ParserSym.SUSPEND, yytext());}
{OPENBRAKET}          {return symbol(ParserSym.OPENBRAKET, yytext());}
{CLOSEBRAKET}         {return symbol(ParserSym.CLOSEBRAKET, yytext());}
{OPENBSQUARE}         {return symbol(ParserSym.OPENBSQUARE, yytext());}
{CLOSEBSQUARE}        {return symbol(ParserSym.CLOSEBSQUARE, yytext());}
{OPENPAREN}           {return symbol(ParserSym.OPENPAREN, yytext());}
{CLOSEPAREN}          {return symbol(ParserSym.CLOSEPAREN, yytext());}
{ASSIGN}              {return symbol(ParserSym.ASSIGN, yytext());}
{COMMA}               {return symbol(ParserSym.COMMA, yytext());}
{SEMICOLON}           {return symbol(ParserSym.SEMICOLON, yytext());}
{DOT}                 {return symbol(ParserSym.DOT, yytext());}
{EQUAL}               {return symbol(ParserSym.EQUAL, yytext());}
{PLUS}                {return symbol(ParserSym.PLUS, yytext());}
{MINUS}               {return symbol(ParserSym.MINUS, yytext());}
{MULT}                {return symbol(ParserSym.MULT, yytext());}
{SLASH}               {return symbol(ParserSym.SLASH, yytext());}
{COLON}               {return symbol(ParserSym.COLON, yytext());}
{DIFF}                {return symbol(ParserSym.DIFF, yytext());}
{LESSEQUAL}           {return symbol(ParserSym.LESSEQUAL, yytext());}
{GREATEREQUAL}        {return symbol(ParserSym.GREATEREQUAL, yytext());}
{LESS}                {return symbol(ParserSym.LESS, yytext());}
{GREATER}             {return symbol(ParserSym.GREATER, yytext());}
{INTEGER}             {return symbol(ParserSym.INTEGER, yytext());}
{REAL}                {return symbol(ParserSym.REAL, yytext());}
{CHAR}                {return symbol(ParserSym.CHAR, yytext());}
{BOOLEAN}             {return symbol(ParserSym.BOOLEAN, yytext());}
{STRING}              {return symbol(ParserSym.STRING, yytext());}
{AND}                 {return symbol(ParserSym.AND, yytext());}
{ARRAY}               {return symbol(ParserSym.ARRAY, yytext());}
{BEGIN}               {return symbol(ParserSym.BEGIN, yytext());}
{BREAK}               {return symbol(ParserSym.BREAK, yytext());}
{CASE}                {return symbol(ParserSym.CASE, yytext());}
{CONST}               {return symbol(ParserSym.CONST, yytext());}
{CONTINUE}            {return symbol(ParserSym.CONTINUE, yytext());}
{DIV}                 {return symbol(ParserSym.DIV, yytext());}
{DO}                  {return symbol(ParserSym.DO, yytext());}
{DOWNTO}              {return symbol(ParserSym.DOWNTO, yytext());}
{ELSE}                {return symbol(ParserSym.ELSE, yytext());}
{END}                 {return symbol(ParserSym.END, yytext());}
{EXIT}                {return symbol(ParserSym.EXIT, yytext());}
{FILE}                {return symbol(ParserSym.FILE, yytext());}
{FOR}                 {return symbol(ParserSym.FOR, yytext());}
{FUNCTION}            {return symbol(ParserSym.FUNCTION, yytext());}
{GOTO}                {return symbol(ParserSym.GOTO, yytext());}
{IF}                  {return symbol(ParserSym.IF, yytext());}
{IN}                  {return symbol(ParserSym.IN, yytext());}
{LABEL}               {return symbol(ParserSym.LABEL, yytext());}
{MOD}                 {return symbol(ParserSym.MOD, yytext());}
{NIL}                 {return symbol(ParserSym.NIL, yytext());}
{NOT}                 {return symbol(ParserSym.NOT, yytext());}
{OF}                  {return symbol(ParserSym.OF, yytext());}
{OR}                  {return symbol(ParserSym.OR, yytext());}
{PACKED}              {return symbol(ParserSym.PACKED, yytext());}
{PROCEDURE}           {return symbol(ParserSym.PROCEDURE, yytext());}
{PROGRAM}             {return symbol(ParserSym.PROGRAM, yytext());}
{RECORD}              {return symbol(ParserSym.RECORD, yytext());}
{REPEAT}              {return symbol(ParserSym.REPEAT, yytext());}
{SET}                 {return symbol(ParserSym.SET, yytext());}
{THEN}                {return symbol(ParserSym.THEN, yytext());}
{TO}                  {return symbol(ParserSym.TO, yytext());}
{TYPE}                {return symbol(ParserSym.TYPE, yytext());}
{UNTIL}               {return symbol(ParserSym.UNTIL, yytext());}
{VAR}                 {return symbol(ParserSym.VAR, yytext());}
{WHILE}               {return symbol(ParserSym.WHILE, yytext());}
{WITH}                {return symbol(ParserSym.WITH, yytext());}
{WRITELN}             {return symbol(ParserSym.WRITELN, yytext());}
{READLN}              {return symbol(ParserSym.READLN, yytext());}
{ID}                  {return symbol(ParserSym.ID, yytext());}
{STRING_CONT}         {return symbol(ParserSym.STRING, yytext().substring(1, yytext().length()-1));}
{COMMENTS}            {/* ignore */}
{WITHESPACE}+         { /* ignore */ }
