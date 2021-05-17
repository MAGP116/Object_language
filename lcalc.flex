
   
import java_cup.runtime.*;
      
%%
%class Lexer

%line
%column
%cup
   

%{   
    
    private Symbol symbol(int type) {
        return new Symbol(type, yyline, yycolumn);
    }
    
    private Symbol symbol(int type, Object value) {	  
    	return new Symbol(type, yyline, yycolumn, value);
    }
    
%}
   
LineTerminator = \r|\n|\r\n
WhiteSpace     = {LineTerminator} | [ \t\f]
ID             = ([a-z]|[A-Z])+([a-z]|[A-Z]|[0-9])*
TIPO           = "int"|"float"|"bool"
TIPOOBJETO     = "fuego"|"muro"
ENTERO         = [0-9]+[0-9]*
FLOTANTE       = [0-9]+"."[0-9]+
BOOL           = "true"|"false"
TIPOTRIGGER    = "creacion"|"destruccion"|"colision"|"iteracion"
 
%%  
   /* YYINITIAL is the state */
   
<YYINITIAL> {

       "funcion"                   {return symbol(sym.FUNCION); }
       "return"                    {return symbol(sym.RETURN);}
       "endArgs"                   {return symbol(sym.ENDARGS); }
       "endFuncion"                {return symbol(sym.ENDFUNCION); }
       {TIPO}                      {return symbol(sym.TIPO, new String(yytext()));}
       "crear"                     {return symbol(sym.CREAR); }
       "endCrear"                  {return symbol(sym.ENDCREAR); }
       "borrar"                    {return symbol(sym.BORRAR); }
       "realizar"                  {return symbol(sym.REALIZAR); }
       "endRealizar"               {return symbol(sym.ENDREALIZAR);}
       ">="                        {return symbol(sym.MAYORIGUAL); }
       "=="                        {return symbol(sym.IGUALDAD); }
       "="                         {return symbol(sym.ASIGNACION); }
       "if"                        {return symbol(sym.IF); }
       "then"                      {return symbol(sym.THEN); }
       "endIf"                     {return symbol(sym.ENDIF); }
       "else"                      {return symbol(sym.ELSE); }
       "buscar"                    {return symbol(sym.BUSCAR); }
       "mi"                        {return symbol(sym.MI); }
       {TIPOOBJETO}                {return symbol(sym.TIPOOBJETO, new String(yytext())); }
       "posicion"                  {return symbol(sym.POSICION); }
       "direccion"                 {return symbol(sym.DIRECCION); }
       "x"                         {return symbol(sym.X); }
       "y"                         {return symbol(sym.Y); }
       {FLOTANTE}                  {return symbol(sym.FLOTANTE, new String(yytext())); }
       {ENTERO}                    {return symbol(sym.ENTERO, new String(yytext())); }
       {BOOL}                      {return symbol(sym.BOOL, new String(yytext())); }
       "velocidad"                 {return symbol(sym.VELOCIDAD); }
       "aceleracion"               {return symbol(sym.ACELERACION); }
       "endTrigger"                {return symbol(sym.ENDTRIGGER); }
       {TIPOTRIGGER}               {return symbol(sym.TIPOTRIGGER, new String(yytext())); }
       "||"                        {return symbol(sym.OR); }
       "&&"                        {return symbol(sym.AND); }
       "!"                         {return symbol(sym.NOT); }
       "+"                         {return symbol(sym.SUMA); }
       "-"                         {return symbol(sym.RESTA); }
       "*"                         {return symbol(sym.MULTIPLICACION); }
       "/"                         {return symbol(sym.DIVISION); }
       "("                         {return symbol(sym.PARAP); }
       ")"                         {return symbol(sym.PARCIE); }
       ","                         {return symbol(sym.COMMA);}
       {ID}                        {return symbol(sym.ID, new String(yytext())); }

       {WhiteSpace}       { /* do nothing */ }
}

[^]                    { throw new Error("Illegal character <"+yytext()+">"); }
