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

public class ArrayIndex extends Identifier
{
      private Variable var;
      private Expression index;

      public ArrayIndex(Variable var, Expression index)
      {
	 this.var = var;
	 this.index = index;
      }

      public String getIOIdent()
      {
	 return var.getIOIdent();
      } 

      public String getName()
      {
	 return var.getName();
      }

      public TypeComp getType()
      {
	 return var.getType();
      }


      public void genC(PW pw)
      {
	 var.genC(pw);
	 pw.out.print("[ ");
	 index.genC(pw);
	 pw.out.print(" ]");
      }
}
