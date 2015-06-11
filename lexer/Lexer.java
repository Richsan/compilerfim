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
import java.lang.StringBuffer;


public class Lexer
{

      public Lexer( char []input, CompilerError error)
      {
	 this.input = input;
	 input[input.length - 1] = '\0';
	 lineNumber = 1;
	 tokenPos = 0;
	 this.error = error;
      }

      public Symbol token;
      public String stringValue;
      public int numberValue;
      public double realNumberValue;
      public char charValue;

      private int tokenPos;
      private int lastTokenPos; // para erro

      private char []input;

      private int lineNumber;

      private CompilerError error;

      static private Hashtable<String,Symbol> keywordsTable = KeywordTable.createTable();
      private static final int MaxValueInteger = 32768;
      private static final double MaxValueDouble = 1.79769e308;

	
      public int getLineNumber()
      {
	 return lineNumber;
      }

      public String getStringValue() 
      {
	 return stringValue;
      }
    
      public int getNumberValue() 
      {
	 return numberValue;
      }
    
      public char getCharValue()
      {
	 return charValue;
      }

      //para imprimir linha com erro
      public String getCurrentLine()
      {
	 int i = lastTokenPos;
	 if ( i == 0 ) 
	    i = 1; 
	 else 
	    if ( i >= input.length )
	       i = input.length;
	            
	 StringBuffer line = new StringBuffer();
	 // go to the beginning of the line
	 while ( i >= 1 && input[i] != '\n' )
	    i--;
	 if ( input[i] == '\n' )
	    i++;
	 // go to the end of the line putting it in variable line
	 while ( input[i] != '\0' && input[i] != '\n' && input[i] != '\r' ) 
	 {
	    line.append( input[i] );
	    i++;
	 }
	 return line.toString();
      }

      public void nextToken()
      {
	 jumpEmptyChars();

	 if ( input[tokenPos] == '\0') 
	 {   
	    token = Symbol.EOF;
	    return;
	 }

	 if(jumpComments())
	    return;

	 if(nextLetters())
	    return;

	 if(nextNumber())
	    return;

	 nextSymbol();

	 lastTokenPos = tokenPos - 1;
      }

      //jump \n, space, \t, etc
      private void jumpEmptyChars()
      {
	 char ch;
	        
	 while (  (ch = input[tokenPos]) == ' ' || ch == '\r' ||
		  ch == '\t' || ch == '\n')
	 {
	    // count the number of lines
	    if ( ch == '\n')
	       lineNumber++;

	    tokenPos++;
	 }
      }

      private boolean jumpComments()
      {
	 if ( input[tokenPos] == '{')
	 {
	    // comment found
	    while ( input[tokenPos] != '}')
	    {
	       if(input[tokenPos] == '\0')
		  error.signal("End of file without }");
		       
	       tokenPos++;

	    }
	    tokenPos++;
	    nextToken();

	    return true;
	 }

	 return false;
      }

      private boolean nextLetters()
      {
	 char ch = input[tokenPos];
		 

	 if ( Character.isLetter( ch ))
	 {
	    // get an identifier or keyword
	    StringBuffer ident = new StringBuffer();
	    while ( Character.isLetter( input[tokenPos] )  || Character.isDigit(input[tokenPos]))
	    {
	       ident.append(input[tokenPos]);
	       tokenPos++;
	    }

	    stringValue = ident.toString();

	    // if identStr is in the list of keywords, it is a keyword !
	    Symbol value = keywordsTable.get(stringValue);

	    if ( value == null ) 
	       token = Symbol.IDENT;
	    else 
	       token = value;

		   
	    return true;
	 }

	 return false;
      }

      private boolean nextString()
      {
	 char ch = input[tokenPos];

	 if(ch == '\'')
	 {
	    tokenPos++;
	    StringBuffer ident = new StringBuffer();
	    while (input[tokenPos] != '\'' )
	    {
	       if(input[tokenPos] == '\0')
		  error.signal("End of file without close string");

	       ident.append(input[tokenPos]);
	       tokenPos++;
	    }
	    tokenPos++;
		     	
	    stringValue = ident.toString();
	    token = Symbol.STRING;
    
	    return true;
	 }

	 return false;
      }

      private boolean nextNumber()
      {
	 char ch = input[tokenPos];

	 if ( Character.isDigit( ch ) )
	 {
	    // get a number
	    StringBuffer number = new StringBuffer();
	    while ( Character.isDigit( input[tokenPos] ) )
	    {
	       number.append(input[tokenPos]);
	       tokenPos++;
	    }

	    if(input[tokenPos] == '.' && Character.isDigit(input[tokenPos+1]))
	    {
	       number.append(input[tokenPos]);
	       tokenPos++;
	       token = Symbol.REALNUMBER;

	       while ( Character.isDigit( input[tokenPos] ) )
	       {
		  number.append(input[tokenPos]);
		  tokenPos++;
	       }

	       try {
		  realNumberValue = Double.valueOf(number.toString()).doubleValue();
	       } catch ( NumberFormatException e ) {
		  error.signal("Number out of limits");
	       }

	       if ( numberValue >= MaxValueDouble )
		  error.signal("Number out of limits");
		                
	       return true;

	    }

	    token = Symbol.NUMBER;

	    try {
	       numberValue = Integer.valueOf(number.toString()).intValue();
	    } catch ( NumberFormatException e ) {
	       error.signal("Number out of limits");
	    }

	    if ( numberValue >= MaxValueInteger )
	       error.signal("Number out of limits");
	                
	    return true;
	 }

	 return false;
      }

      private void nextSymbol()
      {
	 char ch = input[tokenPos];

	 tokenPos++;

	 switch ( ch ) {
	 case '+' :
	    token = Symbol.PLUS;
	    break;
		 
	 case '-' :
	    token = Symbol.MINUS;
	    break;
		 
	 case '*' :
	    token = Symbol.MULT;
	    break;
		 
	 case '/' :
	    token = Symbol.DIV;
	    break;
		 
	 case '<' :
	    if ( input[tokenPos] == '=' )
	    {
	       tokenPos++;
	       token = Symbol.LE;
	    }
	    else if ( input[tokenPos] == '>' )
	    {
	       tokenPos++;
	       token = Symbol.NEQ;
	    }
	    else 
	       token = Symbol.LT;
	    break;
		 
	 case '>' :
	    if ( input[tokenPos] == '=' )
	    {
	       tokenPos++;
	       token = Symbol.GE;
	    }
	    else
	       token = Symbol.GT;
	    break;
		 
	 case '=' :
	    token = Symbol.EQ;
	    break;
		 
	 case '(' :
	    token = Symbol.LEFTPAR;
	    break;
		 
	 case ')' :
	    token = Symbol.RIGHTPAR;
	    break;
		 
	 case ',' :
	    token = Symbol.COMMA;
	    break;
		 
	 case ';' :
	    token = Symbol.SEMICOLON;
	    break;
		 
	 case ':' :
	    if(input[tokenPos] == '=')
	    {
	       token = Symbol.ASSIGN;
	       tokenPos++;
	    }
	    else
	       token = Symbol.COLON;
	    break;

	 case '[' :
	    token = Symbol.LEFTBRACKET;
	    break;

	 case ']' :
	    token = Symbol.RIGHTBRACKET;
	    break;

	 case '.' :
	    if(input[tokenPos] == '.')
	    {
	       token = Symbol.DOUBLEDOT;
	       tokenPos++;
	    }
	    else
	       token = Symbol.DOT;
	    break;
		 
	 case '\'' : 
	    if(input[tokenPos+1] != '\'')
	    {
	       tokenPos--;
	       nextString();
	    }	
	    else
	    {	
	       token = Symbol.CHARACTER;
	       charValue = input[tokenPos];
	       tokenPos++;
	       if ( input[tokenPos] != '\'' ) 
		  error.signal("Illegal literal character" + input[tokenPos-1] );
	       tokenPos++;
	    }
	    break;
		 
	 default :
	    error.signal("Invalid Character: '" + ch + "'");
	 }
      }
}
