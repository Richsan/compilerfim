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

public class VarDecList
{
      private ArrayList<VarList> varList;
	
      public VarDecList(ArrayList<VarList> varList)
      {
	 this.varList = varList;
      }

      public void genC(PW pw)
      {
	 for(VarList v : varList)
	    v.genC(pw);
      }
}
