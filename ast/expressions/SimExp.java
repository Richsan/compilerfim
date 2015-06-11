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

public class SimExp 
{
      private Symbol unary;
      private Term term;
      private  ArrayList<AddTerm> addTermList;

      public SimExp(Symbol unary,Term term,  ArrayList<AddTerm> addTermList)
      {
	 this.unary = unary;
	 this.term = term;
	 this.addTermList = addTermList;
      }

      public String getIOIdent()
      {
	 String ret = "%d";

	 if(term.getIOIdent() == "%c")
	    ret = "%c";
	 if(term.getIOIdent() == "%lf")
	    ret = "%lf";
	 if(term.getIOIdent() == "%s")
	    ret = "%s";
		
	 for(AddTerm t : addTermList)
	 {
	    if(t.getIOIdent() == "%lf")
	       return "%lf";
	 }

	 return ret;
      }

      public void genC(PW pw)
      {
	 if(unary == Symbol.MINUS)
	    pw.out.print("-");

	 if(unary == Symbol.NOT)	
	    pw.out.print("!");

	 term.genC(pw);

	 for(AddTerm a : addTermList)
	    a.genC(pw);
		
      }

}
