import compilerTools.Token;

%%
%class Lexer
%type Token
%line
%column
%{
    private Token token(String lexeme, String lexicalComp, int line, int column){
        return new Token(lexeme, lexicalComp, line+1, column+1);
    }
%}
/* Variables básicas de comentarios y espacios */
TerminadorDeLinea = \r|\n|\r\n
EntradaDeCaracter = [^\r\n]
EspacioEnBlanco = {TerminadorDeLinea} | [ \t\f]
ComentarioTradicional = "/*" [^*] ~"*/" | "/*" "*"+ "/"
FinDeLineaComentario = "//" {EntradaDeCaracter}* {TerminadorDeLinea}?
ContenidoComentario = ( [^*] | \*+ [^/*] )*
ComentarioDeDocumentacion = "/**" {ContenidoComentario} "*"+ "/"

/* Comentario */
Comentario = {ComentarioTradicional} | {FinDeLineaComentario} | {ComentarioDeDocumentacion}

/* Identificador */
Letra = [A-Za-zÑñ_ÁÉÍÓÚáéíóúÜü]
Digito = [0-9]
Identificador = {Letra}({Letra}|{Digito})*

/* Número */
Numero = 0 | [1-9][0-9]*
%%

/* Comentarios o espacios en blanco */
{Comentario}|{EspacioEnBlanco} { /*Ignorar*/ }

/*TIPOS DE DATOS*/
int     | float   | double  | char  | boolean | String | long | short | byte 
        { return token(yytext(), "TIPO_DATO", yyline, yycolumn); }

/*PALABRAS CLAVE*/
class | public | private | protected | static | final | void | return 
      | new | if | else | while | do | for | switch | case | break | continue |System |new
      { return token(yytext(), "PALABRA_CLAVE", yyline, yycolumn); }

/*OPERADORES ARITMÉTICOS*/
"+" | "-" | "*" | "/" | "%" 
    { return token(yytext(), "OPERADOR_ARITMETICO", yyline, yycolumn); }

/*OPERADORES RELACIONALES*/
"==" | "!=" | ">" | "<" | ">=" | "<=" 
    { return token(yytext(), "OPERADOR_RELACIONAL", yyline, yycolumn); }

/*OPERADORES LÓGICOS*/
"&&" | "||" | "!" 
    { return token(yytext(), "OPERADOR_LOGICO", yyline, yycolumn); }

/*IDENTIFICADORES*/
[a-zA-Z_][a-zA-Z0-9_]* { return token(yytext(), "IDENTIFICADOR", yyline, yycolumn); }

/*CADENAS DE TEXTO*/
\"([^\"\\]|\\.)*\" { return token(yytext(), "CADENA_TEXTO", yyline, yycolumn); }

/*DELIMITADORES*/
"{" | "}" | "(" | ")" | ";" | "," 
    { return token(yytext(), "DELIMITADOR", yyline, yycolumn); }

(int|float|double|char|boolean|byte|short|String|long)\[\]   {return token(yytext(),  "ARRAY_TYPE",  yyline, yycolumn); }

/*NÚMEROS ENTEROS*/
[0-9]+ { return token(yytext(), "NUMERO_ENTERO", yyline, yycolumn); }

/*NÚMEROS FLOTANTES*/
[0-9]+\.[0-9]+ { return token(yytext(), "NUMERO_FLOTANTE", yyline, yycolumn); }

/*OPERADORES DE ASIGNACIÓN*/
"=" | "+=" | "-=" | "*=" | "/=" | "%=" | "&=" | "|=" | "^=" | "<<=" | ">>=" 
    { return token(yytext(), "OPERADOR_ASIGNACION", yyline, yycolumn); }

/*OPERADOR DE ACCESO*/
"." { return token(yytext(), "OPERADOR_ACCESO", yyline, yycolumn); }

. { return token(yytext(), "ERROR", yyline, yycolumn); }