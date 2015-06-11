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

package ast.types;

import ast.*;
import ast.expressions.*;
import ast.statements.*;

public class IntegerType extends StdType {
    
      public IntegerType()
      {
	 super("INTEGER");
      }

      public String getIOIdent()
      {
	 return "%d";
      } 
    
      public String getCName()
      {
	 return "int";
      }
   
}
