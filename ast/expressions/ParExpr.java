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
package ast.expressions;

import ast.*;
import ast.statements.*;
import ast.types.*;

public class ParExpr extends Factor
{
      private Expression expr;

      public ParExpr(Expression expr)
      {
	 this.expr = expr;
      }

      public String getIOIdent()
      {
	 return expr.getIOIdent();
      }

      public void genC(PW pw)
      {
	 pw.out.print("( ");
	 expr.genC(pw);
	 pw.out.print(")");
      }
}
