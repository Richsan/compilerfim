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

package lexer;
import java.util.Hashtable;
public final class KeywordTable
{
      private KeywordTable(){}

      public static Hashtable<String, Symbol> createTable()
      {
	 Hashtable<String , Symbol >keywordsTable;
	 keywordsTable = new Hashtable<String, Symbol>();
	 keywordsTable.put( "VAR", Symbol.VAR );
	 keywordsTable.put( "PROGRAM", Symbol.PROGRAM );
	 keywordsTable.put( "BEGIN", Symbol.BEGIN );
	 keywordsTable.put( "END", Symbol.END );
	 keywordsTable.put( "IF", Symbol.IF );
	 keywordsTable.put( "THEN", Symbol.THEN );
	 keywordsTable.put( "ELSE", Symbol.ELSE );
	 keywordsTable.put( "ENDIF", Symbol.ENDIF );
	 keywordsTable.put( "WHILE", Symbol.WHILE );
	 keywordsTable.put( "DO", Symbol.DO );
	 keywordsTable.put( "ENDWHILE", Symbol.ENDWHILE );
	 keywordsTable.put( "READ", Symbol.READ );
	 keywordsTable.put( "WRITE", Symbol.WRITE );
	 keywordsTable.put( "WRITELN", Symbol.WRITELN );
	 keywordsTable.put( "INTEGER", Symbol.INTEGER );
	 keywordsTable.put( "REAL", Symbol.REAL );
	 keywordsTable.put( "CHAR", Symbol.CHAR );
	 keywordsTable.put( "STRING", Symbol.STRING );
	 keywordsTable.put( "ARRAY", Symbol.ARRAY );
	 keywordsTable.put( "OF", Symbol.OF );
	 keywordsTable.put( "TRUE", Symbol.TRUE );
	 keywordsTable.put( "FALSE", Symbol.FALSE );
	 keywordsTable.put( "AND", Symbol.AND );
	 keywordsTable.put( "OR", Symbol.OR );
	 keywordsTable.put( "NOT", Symbol.NOT );
	 keywordsTable.put( "MOD", Symbol.MOD );
	 keywordsTable.put( "DIV", Symbol.DIV );

	 return keywordsTable;
      }

}
