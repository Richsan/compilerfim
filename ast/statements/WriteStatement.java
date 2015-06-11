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
import java.util.ArrayList;

public class WriteStatement extends Statement
{
      private boolean hasNewLine;
      private ArrayList<Expression> exprList;

      public WriteStatement(boolean hasNewLine, ArrayList<Expression> exprList)
      {
	 this.hasNewLine = hasNewLine;
	 this.exprList = exprList;
      }

      public void genC(PW pw)
      {
	 String printStr = "\"";

	 if(hasNewLine)
	    printStr = "\"\\n";
		
	 for(Expression e : exprList)
	    printStr += e.getIOIdent() + " ";
	 printStr += "\"";

	 pw.out.print("printf(" + printStr + ",");

	 int size = exprList.size();
	 for(Expression e : exprList)
	 {
	    e.genC(pw);
	    if(--size != 0)
	       pw.out.print(",");
	 }

	 pw.out.println(");");


      }
}
