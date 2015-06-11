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

import lexer.Lexer;
import java.io.PrintWriter;
import java.lang.RuntimeException;

public class CompilerError {
    
      public CompilerError() {
	 // output of an error is done in out
	 //this.out = out;
      }
    
      public void setLexer( Lexer lexer ) {
	 this.lexer = lexer;
      }

      public void signal( String strMessage ) {
	 System.out.println("Error at line " + lexer.getLineNumber() + ": ");
	 System.out.println(lexer.getCurrentLine());
	 System.out.println( "Message: "+strMessage );
	 /*if ( out.checkError() )
	   System.out.println("Error in signaling an error");*/
	 throw new RuntimeException(strMessage);
      }
    
      private Lexer lexer;
      PrintWriter out;
}
