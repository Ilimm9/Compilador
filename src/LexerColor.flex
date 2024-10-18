import compilerTools.TextColor;
import java.awt.Color;

%%
%class LexerColor
%type TextColor
%char
%{
    private TextColor textColor(long start, int size, Color color){
        return new TextColor((int) start, size, color);
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
{Comentario} { return textColor(yychar, yylength(), new Color(146, 146, 146)); }
{EspacioEnBlanco} { /*Ignorar*/ }

/*TIPOS DE DATOS*/
int | float | double | char | boolean | String | long | short | byte 
    { return textColor(yychar, yylength(), new Color(0, 0, 255));} /* Azul*/

true
    { /*Ignorar*/ } 
false
    { /*Ignorar*/ } 

/*PALABRAS CLAVE*/
class | public | private | protected | static | final | void | return 
      | new | if | else | while | do | for | switch | case | break | continue | System |new
      { return textColor(yychar, yylength(), new Color(255, 102, 0)); } /* Naranja*/

/*OPERADORES ARITMÉTICOS*/
"+" | "-" | "*" | "/" | "%"  { /*Ignorar*/}

/*OPERADORES RELACIONALES*/
"==" | "!=" | ">" | "<" | ">=" | "<="  { /*Ignorar*/ } 

/*OPERADORES LÓGICOS*/
"&&" | "||" | "!"     { /*Ignorar*/}

/*IDENTIFICADORES*/
[a-zA-Z_][a-zA-Z0-9_]* { return textColor(yychar, yylength(), new Color(92, 17, 102)); } /* Morado*/

/*CADENAS DE TEXTO*/
\"([^\"\\]|\\.)*\" { return textColor(yychar, yylength(), new Color(0, 143, 57));} /*Verde*/

/*DELIMITADORES*/
";"  { /*Ignorar*/ }
"("  {/*Ignorar*/}
 ")"  {/*Ignorar*/}
"{"   {/*Ignorar*/}
 "}"  {/*Ignorar*/}
"["   {/*Ignorar*/}
 "]"  {/*Ignorar*/}
","  {/*Ignorar*/}

(int|float|double|char|boolean|byte|short|String|long)\[\]   {/*Ignorar*/}

/*NÚMEROS ENTEROS*/
[0-9]+ { return textColor(yychar, yylength(), new Color(108, 59, 42)); } 

/*NÚMEROS FLOTANTES*/
[0-9]+\.[0-9]+ { return textColor(yychar, yylength(), new Color(108, 59, 42)); } 

/*OPERADORES DE ASIGNACIÓN*/
"=" | "+=" | "-=" | "*=" | "/=" | "%=" | "&=" | "|=" | "^=" | "<<=" | ">>=" { /*Ignorar*/ } 

/*OPERADOR DE ACCESO*/
"." { return textColor(yychar, yylength(), new Color(252, 15, 92));} 

/*ERROR*/
. { return textColor(yychar, yylength(), new Color(255, 0, 0)); } 
