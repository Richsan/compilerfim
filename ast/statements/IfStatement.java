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

package ast.statements;

import ast.types.*;
import ast.expressions.*;
import ast.*;

public class IfStatement extends Statement
{
      private StmtList thenStmts, elseStmts;
      private Expression expr;

      public IfStatement(Expression expr, StmtList thenStmts, StmtList elseStmts)
      {
	 this.expr = expr;
	 this.thenStmts = thenStmts;
	 this.elseStmts = elseStmts;
      }

      public void genC(PW pw)
      {
	 pw.out.print("if( ");
	 expr.genC(pw);
	 pw.out.println("){");
	 thenStmts.genC(pw);
	 pw.out.println("\n}");
	 if(elseStmts != null)
	 {
	    pw.out.println("else{");
	    elseStmts.genC(pw);
	    pw.out.println("\n}");

	 }


      }
}
