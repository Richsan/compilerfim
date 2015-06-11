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

public class StringExpr extends Factor
{
	
      private String value;

      public StringExpr(String value)
      {
	 this.value = value;
      }

      public String getIOIdent()
      {
	 return "%s";
      }

      public String getValue()
      {
	 return value;
      }

      public void genC(PW pw)
      {
	 pw.out.print("\""+value+"\"");
      }
}
