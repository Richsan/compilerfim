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
import java.util.ArrayList;

public class Term 
{
      private Factor factor;
      private ArrayList<MulFactor> mulFactorList;

      public Term(Factor factor, ArrayList<MulFactor> mulFactorList)
      {
	 this.factor = factor;
	 this.mulFactorList = mulFactorList;
      }

      public String getIOIdent()
      {
	 String ret = "%d";

	 if(factor.getIOIdent() == "%c")
	    ret = "%c";
	 if(factor.getIOIdent() == "%lf")
	    ret = "%lf";

	 if(factor.getIOIdent() == "%s")
	    ret = "%s";

	 for(MulFactor m : mulFactorList)
	 {

	    if(m.getIOIdent() == "%lf")
	       return "%lf";
	 }

	 return ret;
      }

      public void genC(PW pw)
      {
	 factor.genC(pw);

	 for(MulFactor m : mulFactorList)
	    m.genC(pw);
      }
}
