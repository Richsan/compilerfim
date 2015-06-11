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

package ast;

import ast.types.*;
import ast.expressions.*;
import ast.statements.*;

public class Program
{
      private String id;
      private VarDecList decList;
      private StmtList stmtList;

      public Program(String id, VarDecList decList, StmtList stmtList)
      {
	 this.id = id;
	 this.decList = decList;
	 this.stmtList = stmtList;
      }

      public String getPid()
      {
	 return id;
      }

      public void genC(PW pw)
      {
	 pw.out.println("#include<stdio.h>\n#incude<string.h>\n#include<stdlib.h>\n");
	 pw.out.println("int main(){\n");
	 this.decList.genC(pw);
	 this.stmtList.genC(pw);
	 pw.out.println("return 0;\n}");

      }
}
