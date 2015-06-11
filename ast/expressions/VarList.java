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

import java.util.ArrayList;

public class VarList
{
      private TypeComp typeList;
      private ArrayList<Variable> varList;

      public VarList(ArrayList<Variable> varList, TypeComp typeList)
      {
	 this.varList = varList;
	 this.typeList = typeList;
      }

      public void genC(PW pw)
      {
	 pw.out.print(typeList.getCName() + " ");

	 int size = varList.size();
	 for(Variable v: varList)
	 {

	    v.genC(pw);
	    if(v.getType().getName() == "ARRAY")
	       pw.out.print("["+((ArrayType)(v.getType())).getMaximum()+"]");

	    if(--size != 0)
	       pw.out.print(",");
	 }
	 pw.out.println(";");
      }
}
