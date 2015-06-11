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

abstract public class StdType extends TypeComp
{
      public StdType( String name )
      {
	 super(name);
      }

      abstract public String getIOIdent();
      abstract public String getCName();
      public static StdType stringType = new StringType();
      public static StdType integerType = new IntegerType();
      public static StdType realType = new RealType();
      public static StdType charType    = new CharType();
}
