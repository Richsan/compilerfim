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

public class AssignStatement extends Statement
{
      private Expression expr;
      private Identifier ident;

      public AssignStatement(Identifier ident, Expression expr)
      {
	 this.ident = ident;
	 this.expr = expr;
      } 

      public void genC(PW pw)
      {
	 if(expr.getIOIdent() == ident.getIOIdent() && ident.getIOIdent() == "%s")
	 {
	 	ident.genC(pw);
	 	pw.out.print(" = (char *)realloc(");
	 	ident.genC(pw);
	 	pw.out.print(",sizeof(char) * strlen(");
	 	expr.genC(pw);
	 	pw.out.println("));");
	    pw.out.print("strcpy(");
	    ident.genC(pw);
	    pw.out.print(",");
	    expr.genC(pw);
	    pw.out.println(");");
	 }
	 else
	 {
	    ident.genC(pw);
	    pw.out.print(" = ");
	    expr.genC(pw);
	    pw.out.println(";");
	 }
      }
}
