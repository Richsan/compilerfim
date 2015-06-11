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

public class ReadStatement extends Statement
{
      private ArrayList<Identifier> variables;

      public ReadStatement(ArrayList<Identifier> variables)
      {
	 this.variables = variables;
      }

      public void genC(PW pw)
      {	
	 String readStr = "\"";
	 for(Identifier i : variables)
	    readStr += i.getIOIdent()+"";
	 readStr += "\"";


	 pw.out.print("scanf("+readStr+",");

	 int size = variables.size();
	 for(Identifier i : variables)
	 {
	    pw.out.print("&");
	    i.genC(pw);
			
	    if(--size != 0)
	       pw.out.print(",");
	 }

	 pw.out.println(");");
      }
}
