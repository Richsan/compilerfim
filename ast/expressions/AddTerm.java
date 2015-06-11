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

public class AddTerm 
{
      private Symbol addOp;
      private Term term;

      public AddTerm(Symbol addOp, Term term)
      {
	 this.addOp = addOp;
	 this.term = term;
      }

      public String getIOIdent()
      {
	 return term.getIOIdent();
      }

      public void genC(PW pw)
      {
	 pw.out.print(" "+addOp.toString()+" ");
	 term.genC(pw);
      }
}
