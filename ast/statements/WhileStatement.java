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
public class WhileStatement extends Statement
{
      private StmtList stmts;
      private Expression expr;

      public WhileStatement(Expression expr, StmtList stmts)
      {
	 this.expr = expr;
	 this.stmts = stmts;
      }

      public void genC(PW pw)
      {
	 pw.out.print("while( ");
	 expr.genC(pw);
	 pw.out.println("){");
	 stmts.genC(pw);
	 pw.out.println("\n}");


      }
}
