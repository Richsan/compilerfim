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

public class ArrayType extends TypeComp {
    
      public ArrayType(StdType type, int num1, int num2)
      {
	 super("ARRAY");
	 this.num1 = num1;
	 this.num2 = num2;
	 this.type = type;
      }

      private int num1, num2;
      private StdType type;

      public StdType getType()
      {
	 return type;
      }

      public int getMinimum()
      {
	 return num1;
      }

      public int getMaximum()
      {
	 return num2;
      }
    
      public String getCName()
      {
	 return type.getCName();
      }

      public String getIOIdent()
      {
	 return type.getIOIdent();
      }
  
  
   
}
