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

public class Variable extends Identifier
{
      private String name;
      private TypeComp varType;

      public Variable(String name, TypeComp varType)
      {
	 this.name = name;
	 this.varType = varType;
      }

      public String getIOIdent()
      {
	 return varType.getIOIdent();
      } 

      public TypeComp getType()
      {
	 return varType;
      }

      public String getName()
      {
	 return name;
      }

      public void genC(PW pw)
      {
	 pw.out.print(this.name);
      }
}
