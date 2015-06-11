/* ==================================================================================
 * Universidade Federal de São Carlos - Campus Sorocaba
 * Disciplina: Compiladores
 * Prof. Tiemi Chistine Sakata
 *
 * Aluno: Guilherme José de Carvalho Gois    RA: 552240
 * Aluno: Henrique Manoel de Lima Sebastião  RA: 552259
 *
 *   -----Informacoes-----
 *Para executar o programa Java Main [arquivoPascal]
 *Saida: arquivo compilado em C com nome do pid do arquivo pascal
 *
 * ==================================================================================== */

package lexer;

public enum Symbol {

   EOF("eof"),
   IDENT("Ident"),
   NUMBER("Number"),
   REALNUMBER("double"),
   PLUS("+"),
   MINUS("-"),
   MULT("*"),
   DIV("/"),
   LT("<"),
   LE("<="),
   GT(">"),
   GE(">="),
   NEQ("!="),
   EQ("=="),
   ASSIGN("="),
   LEFTPAR("("),
   RIGHTPAR(")"),
   LEFTBRACKET("["),
   RIGHTBRACKET("]"),
   SEMICOLON(";"),
   DOT("."),
   DOUBLEDOT(".."),
   VAR("VAR"),
   BEGIN("BEGIN"),
   END("END"),
   IF("IF"),
   THEN("THEN"),
   ELSE("ELSE"),
   ENDIF("ENDIF"),
   WHILE("WHILE"),
   DO("DO"),
   ENDWHILE("ENDWHILE"),
   COMMA(","),
   READ("READ"),
   WRITE("WRITE"),
   WRITELN("WRITELN"),
   COLON(":"),
   INTEGER("INTEGER"),
   STRING("STRING"),
   CHAR("CHAR"),
   REAL("REAL"),
   CHARACTER("character"),
   ARRAY("ARRAY"),
   TRUE("TRUE"),
   FALSE("FALSE"),
   OR   ("||"),
   AND  ("&&"),
   MOD("%"),
   PROGRAM("PROGRAM"),
   NOT("!"),
   OF("OF");

   Symbol(String name) {
      this.name = name;
   }

   public String toString() {
      return name;
   }

   private String name;

}
