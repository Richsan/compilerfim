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

import lexer.Symbol;

public class Expression
{
      private SimExp simple;
      private Expression expr;
      private Symbol operator;

      public Expression(SimExp opLeft, Symbol operator, Expression opRight)
      {
	 this.simple = opLeft;
	 this.operator = operator;
	 this.expr = opRight;
      }

      public String getIOIdent()
      {
	 String ret = "%d";
	 if(simple.getIOIdent() == "%c")
	    ret = "%c";
	 if(simple.getIOIdent() == "%s")
	    ret = "%s";

	 if(simple.getIOIdent() == "%lf")
	    ret = "%lf";

	 if(operator != null)
	 {
	    if(expr.getIOIdent() == "%c")
	       ret = "%c";
			
	    if(expr.getIOIdent() == "%lf")
	       ret = "%lf";
	 }

	 return ret;

      }

      public void genC(PW pw)
      {

	    simple.genC(pw);
	    if(operator != null)
	    {
	       pw.out.print(" "+operator.toString()+" ");
	       expr.genC(pw);
	    }
	 
      }

}
