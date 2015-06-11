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

import lexer.*;
import ast.*;
import ast.types.*;
import ast.expressions.*;
import ast.statements.*;
import java.io.*;

public class Main
{

      public static void main(String[] args)
      {
	 char [] input = openFile(args);
	 CompilerParse c = new CompilerParse();
	 c.compile(input);
      }

      public static char[] openFile(String[] args)
      {
	 File file;
	 FileReader stream;
	 int numChRead;
	 char []input = null;
        
	 if ( args.length != 1 )  {
            System.out.println("Usage:\n   Main input output");
            System.out.println("input is the file to be compiled");
            System.out.println("output is the file where the generated code will be stored");
	 }
	 else {
	    file = new File(args[0]);
	    if ( ! file.exists() || ! file.canRead() ) {
	       System.out.println("Either the file " + args[0] + " does not exist or it cannot be read");
	       throw new RuntimeException();
	    }
	    try { 
	       stream = new FileReader(file);  
            } catch ( FileNotFoundException e ) {
	       System.out.println("Something wrong: file does not exist anymore");
	       throw new RuntimeException();
            }
	    // one more character for '\0' at the end that will be added by the
	    // compiler
            input = new char[ (int ) file.length() + 1 ];
            
            try {
	       numChRead = stream.read( input, 0, (int ) file.length() );
            } catch ( IOException e ) {
	       System.out.println("Error reading file " + args[0]);
	       throw new RuntimeException();
            }
                
            /*if ( numChRead != file.length() ) {
	      System.out.println("Read error");
	      throw new RuntimeException();
	      }*/
            try {
	       stream.close();
            } catch ( IOException e ) {
	       System.out.println("Error in handling the file " + args[0]);
	       throw new RuntimeException();
            }
	 }
	 return input;
	     
      }
}
