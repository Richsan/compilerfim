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
import ast.*;
import ast.types.*;
import ast.expressions.*;
import ast.statements.*;
import java.io.PrintWriter;
import java.io.FileOutputStream;
import java.util.Hashtable;
import java.util.ArrayList;
import java.io.IOException;

public class CompilerParse
{
      private Hashtable<String, Variable> symbolTable;
      private Lexer lexer;
      private CompilerError error;
	
      public void compile(char []input)
      {
	 FileOutputStream outError;
	 symbolTable = new Hashtable<String, Variable>();
       
         error = new CompilerError();
	 lexer = new Lexer(input, error);
	 error.setLexer(lexer);
        
	 lexer.nextToken();
	 Program prog = null;
	 try{
	    prog = program();
	 } catch(RuntimeException e){
	    System.out.println(e);
	 }

	 try { 
	    outError = new FileOutputStream(prog.getPid()+".c");
	 } catch ( IOException e ) {
	    System.out.println("File " + prog.getPid() + " could not be opened for writing");
	    throw new RuntimeException();
	 }

	 PrintWriter printWriter = new PrintWriter(outError);
	 PW pw = new PW();
	 pw.set(printWriter);


	 prog.genC(pw);
         if ( printWriter.checkError() ) {
	    System.out.println("There was an error in the output");
	 }
      }

      public Program program()
      {
		
	 if(lexer.token != Symbol.PROGRAM)
	    error.signal("\"PROGRAM\" expected");

	 lexer.nextToken();

	 if(lexer.token != Symbol.IDENT)
	    error.signal("Identifier expected");

	 String pid = lexer.stringValue;

	 lexer.nextToken();

	 if(lexer.token != Symbol.SEMICOLON)
	    error.signal("\";\" expected");

	 lexer.nextToken();

	 Program prog = body(pid);

	 if(lexer.token != Symbol.DOT)
	    error.signal("\".\" expected");

	 lexer.nextToken();
	 if ( lexer.token != Symbol.EOF ) 
	    error.signal("EOF expected");

	 return prog;
      }

      public Program body(String pid)
      {
	 VarDecList declist = null;
	 if(lexer.token == Symbol.VAR)
	    declist = dclPart();
		
	 StmtList stmtList = compstmt();

	 return new Program(pid, declist, stmtList);
      }

      public VarDecList dclPart()
      {

	 if(lexer.token != Symbol.VAR)
	    error.signal("expected \"VAR\"");

	 lexer.nextToken();

	 ArrayList<VarList> varList = dcls();

	 return new VarDecList(varList);

      }

      public ArrayList<VarList> dcls()
      {
	 ArrayList<VarList> varList = new ArrayList<VarList>();

	 varList.add(dcl());

	 while(lexer.token == Symbol.IDENT)
	    varList.add(dcl());

	 return varList;
      }

      public VarList dcl()
      {
	 VarList vList;

	 ArrayList<String> varsNames;
	 ArrayList<Variable> vars = new ArrayList<Variable>();
		
	 varsNames = idList();

	 if(lexer.token != Symbol.COLON)
	    error.signal("expected \":\"");

	 lexer.nextToken();

	 TypeComp typeList = type();

	 if(lexer.token != Symbol.SEMICOLON)
	    error.signal("expected \";\"");
		
	 lexer.nextToken();
	 Variable v;
	 for(String name : varsNames)
	 {
	    v = new Variable(name, typeList);
	    symbolTable.put(name,v);
	    vars.add(v);
	 }

	 return new VarList(vars, typeList);
      }

      public ArrayList<String> idList()
      {
	 Hashtable<String,String> tempSymbolTable = new Hashtable<String,String>();
	 ArrayList<String> varsNames = new ArrayList<String>();
	 while(true)
	 {
	    if(lexer.token != Symbol.IDENT)
	       error.signal("expected identifier");

	    varsNames.add(lexer.stringValue);

	    if (tempSymbolTable.get(lexer.stringValue) != null && symbolTable.get(lexer.stringValue) != null)
	       error.signal("Variable " + lexer.stringValue + " has already been declared");
            else
	       tempSymbolTable.put(lexer.stringValue,lexer.stringValue);

	    lexer.nextToken();

	    if(lexer.token != Symbol.COMMA)
	       break;

	    lexer.nextToken();

	 }
						
	 return varsNames;
		
      }

      public TypeComp type()
      {
	 if(lexer.token == Symbol.ARRAY)
	    return arrayType();
	 else
	    return stdType();
      }

      public StdType stdType()
      {
	 if(lexer.token == Symbol.INTEGER)
	 {
	    lexer.nextToken();
	    return StdType.integerType;
	 }
		
	 if(lexer.token == Symbol.REAL)
	 {
	    lexer.nextToken();
	    return StdType.realType;
	 }

	 if(lexer.token == Symbol.CHAR)
	 {
	    lexer.nextToken();
	    return StdType.charType;
	 }

	 if(lexer.token == Symbol.STRING)
	 {
	    lexer.nextToken();
	    return StdType.stringType;
	 }
		
	 error.signal("Type expected");
	 return null;
      }

      public ArrayType arrayType()
      {
	 if(lexer.token != Symbol.ARRAY)
	    error.signal("\"ARRAY\" expected");

	 lexer.nextToken();

	 if(lexer.token != Symbol.LEFTBRACKET)
	    error.signal("\"[\" expected");
	 lexer.nextToken();

	 if(lexer.token != Symbol.NUMBER)
	    error.signal("Number expected");

	 int num1 = lexer.numberValue;

	 lexer.nextToken();

	 if(lexer.token != Symbol.DOUBLEDOT)
	    error.signal("\"..\" expected");

	 lexer.nextToken();

	 if(lexer.token != Symbol.NUMBER)
	    error.signal("Number expected");

	 int num2 = lexer.numberValue;

	 lexer.nextToken();

	 if(lexer.token != Symbol.RIGHTBRACKET)
	    error.signal("\"]\" expected");
		
	 lexer.nextToken();

	 if(lexer.token != Symbol.OF)
	    error.signal("\"OF\" expected");

	 lexer.nextToken();

	 StdType type = stdType();

	 return new ArrayType(type,num1,num2);

      }

      public StmtList compstmt()
      {
	 if(lexer.token != Symbol.BEGIN)
	    error.signal("\"BEGIN\" expected");

	 lexer.nextToken();

	 ArrayList<Statement> slist = stmts();

	 if(lexer.token != Symbol.END)
	    error.signal("\"END\" expected");

	 lexer.nextToken();

	 return new StmtList(slist);
      }

      public ArrayList<Statement> stmts()
      {
	 ArrayList<Statement> slist = new ArrayList<Statement>();
	 while(true)
	 {
	    slist.add(stmt());

	    if(lexer.token != Symbol.SEMICOLON)
	       error.signal("\";\" expected");

	    lexer.nextToken();

	    if(lexer.token != Symbol.WHILE && lexer.token != Symbol.IF
	       && lexer.token != Symbol.IDENT && lexer.token != Symbol.READ
	       && lexer.token != Symbol.WRITE 	&& lexer.token != Symbol.WRITELN
	       && lexer.token != Symbol.BEGIN)
	       break;
	 }

	 return slist;
      }

      public Statement stmt()
      {
	 if(lexer.token == Symbol.WHILE)
	    return whileStmt();
			
	 if(lexer.token == Symbol.IF)
	    return ifStmt();
		
	 if(lexer.token == Symbol.IDENT)
	    return assignStmt();
		
	 if(lexer.token == Symbol.READ)
	    return readStmt();
		
	 if(lexer.token == Symbol.WRITE)
	    return writeStmt();
		
	 if(lexer.token == Symbol.WRITELN)
	    return writelnStmt();
		
	 error.signal("Statement expected");
	 return null;
      }


      public IfStatement ifStmt()
      {
	 if(lexer.token != Symbol.IF)
	    error.signal("\"IF\" expected");

	 lexer.nextToken();

	 Expression e = expr();

	 if(lexer.token != Symbol.THEN)
	    error.signal("\"THEN\" expected");

	 lexer.nextToken();

	 StmtList thenStmts = new StmtList(stmts());
	 StmtList elseStmts = null;

	 if(lexer.token == Symbol.ELSE)
	 {
	    lexer.nextToken();

	    elseStmts = new StmtList(stmts());
	 }

	 if(lexer.token != Symbol.ENDIF)
	    error.signal("\"ENDIF\" expected");		

	 lexer.nextToken();	

	 return new IfStatement(e,thenStmts, elseStmts);
      }

      public WhileStatement whileStmt()
      {
	 if(lexer.token != Symbol.WHILE)
	    error.signal("\"WHILE\" expected");

	 lexer.nextToken();

	 Expression e = expr();

	 if(lexer.token != Symbol.DO)
	    error.signal("\"DO\" expected");

	 lexer.nextToken();

	 StmtList stmList = new StmtList(stmts());

	 if(lexer.token != Symbol.ENDWHILE)
	    error.signal("\"ENDWHILE\" expected");

	 lexer.nextToken();

	 return new WhileStatement(e, stmList);
      }

      public AssignStatement assignStmt()
      {
	 Identifier idnt = vbl();

	 if(lexer.token != Symbol.ASSIGN)
	    error.signal("\":=\" expected");
		
	 lexer.nextToken();

	 Expression e = expr();

	 return new AssignStatement(idnt, e);
      }

      public ReadStatement readStmt()
      {
	 if(lexer.token != Symbol.READ)
	    error.signal("\"READ\" expected");

	 lexer.nextToken();

	 if(lexer.token != Symbol.LEFTPAR)
	    error.signal("\"(\" expected");

	 lexer.nextToken();

	 ArrayList<Identifier> vars = vbList();

	 if(lexer.token != Symbol.RIGHTPAR)
	    error.signal("\")\" expected");

	 lexer.nextToken();

	 return new ReadStatement(vars);
      }

      public WriteStatement writeStmt()
      {
	 if(lexer.token != Symbol.WRITE)
	    error.signal("\"WRITE\" expected");

	 lexer.nextToken();

	 if(lexer.token != Symbol.LEFTPAR)
	    error.signal("\"(\" expected");

	 lexer.nextToken();

	 ArrayList<Expression> eList = exprList();

	 if(lexer.token != Symbol.RIGHTPAR)
	    error.signal("\")\" expected");
		
	 lexer.nextToken();

	 return new WriteStatement(false, eList);
      }

      public WriteStatement writelnStmt()
      {
	 ArrayList<Expression> eList = new ArrayList<Expression>();

	 if(lexer.token != Symbol.WRITELN)
	    error.signal("\"WRITE\" expected");

	 lexer.nextToken();

	 if(lexer.token != Symbol.LEFTPAR)
	    error.signal("\"(\" expected");

	 lexer.nextToken();

	 if(lexer.token == Symbol.PLUS || lexer.token == Symbol.MINUS
	    || lexer.token == Symbol.NOT || lexer.token == Symbol.IDENT
	    || lexer.token == Symbol.STRING)
	    eList = exprList();

	 if(lexer.token != Symbol.RIGHTPAR)
	    error.signal("\")\" expected");
		
	 lexer.nextToken();

	 return new WriteStatement(true, eList);
      }

      public ArrayList<Identifier> vbList()
      {
	 ArrayList<Identifier> varList = new ArrayList<Identifier>();

	 while(true)
	 {
	    varList.add(vbl());

	    if(lexer.token != Symbol.COMMA)
	       break;

	    lexer.nextToken();
	 }

	 return varList;
      }


      public Identifier vbl()
      {
	 if(lexer.token != Symbol.IDENT)
	    error.signal("expected identifier");

	 Variable var = (Variable) symbolTable.get(lexer.stringValue);

	 if (var == null) 
            error.signal("Variable " + lexer.stringValue + " was not declared");

	 lexer.nextToken();

	 if(lexer.token == Symbol.LEFTBRACKET)
	 {
	    if(var.getType().getName() != "ARRAY")
	       error.signal("identifier \""+ var.getName()+ "\" is not an array");

	    lexer.nextToken();
	    Expression index = expr();
	    if(lexer.token != Symbol.RIGHTBRACKET)
	       error.signal("\"]\" expected");

	    lexer.nextToken();

	    return new ArrayIndex(var,index);
	 }

	 return var;
      }

      public ArrayList<Expression> exprList()
      {
	 ArrayList<Expression> exprList = new ArrayList<Expression>();
		
		
	 while(true)
	 {
	    exprList.add(expr());
			
	    if(lexer.token != Symbol.COMMA)
	       break;

	    lexer.nextToken();	
	 }

	 return exprList;
		
      }

      public Expression expr()
      {
				
	 SimExp s = simExp();

	 if(lexer.token == Symbol.EQ || lexer.token == Symbol.LT  ||
	    lexer.token == Symbol.GT || lexer.token == Symbol.LE ||
	    lexer.token == Symbol.GE || lexer.token == Symbol.NEQ)
	 {
	    Symbol sim = lexer.token;
	    lexer.nextToken();
	    return new Expression(s,sim, expr());

	 }

	 return new Expression(s,null,null);
		
      }

      public SimExp simExp()
      {
	 Symbol unary = null;
	 if(lexer.token == Symbol.PLUS || lexer.token == Symbol.MINUS
	    || lexer.token == Symbol.NOT)
	 {
	    unary = lexer.token;
	    lexer.nextToken();
	 }

	 Term term1 = term();

	 ArrayList<AddTerm> addTermlist = new ArrayList<AddTerm>();
	 while(true)
	 {
	    if(lexer.token != Symbol.PLUS && lexer.token != Symbol.MINUS &&
	       lexer.token != Symbol.OR)
	       break;

	    Symbol sim2 = lexer.token;
	    lexer.nextToken();
	    Term term2 = term();

	    addTermlist.add(new AddTerm(sim2, term2));
	 }

	 return new SimExp(unary, term1, addTermlist);
      }

      public Term term()
      {
	 Factor f = factor();
	 ArrayList<MulFactor> mulFactorList = new ArrayList<MulFactor>();
		
	 while(true)
	 {
	    if(lexer.token != Symbol.MULT && lexer.token != Symbol.DIV
	       && lexer.token != Symbol.AND && lexer.token != Symbol.MOD)
	       break;

	    Symbol s = lexer.token;

	    lexer.nextToken();

	    mulFactorList.add(new MulFactor(s,factor()));
	 }

	 return new Term(f,mulFactorList);
      }

      public Factor factor()
      {
	 if(lexer.token == Symbol.IDENT)
	 {
	    return vbl();
			
	 }

	 if(lexer.token == Symbol.NUMBER)
	 {
	    int numb = lexer.numberValue;
	    lexer.nextToken();
	    return new NumberIntExpr(numb);
	 }

	 if(lexer.token == Symbol.REALNUMBER)
	 {
	    double numb = lexer.realNumberValue;
	    lexer.nextToken();
	    return new NumberDoubleExpr(numb);
	 }

	 if(lexer.token == Symbol.STRING)
	 {
	    String text = lexer.stringValue;
	    lexer.nextToken();
	    return new StringExpr(text);
	 }

	 if(lexer.token == Symbol.LEFTPAR)
	 {
	    lexer.nextToken();
	    Expression e = expr();

	    if(lexer.token != Symbol.RIGHTPAR)
	       error.signal("\")\" expected");

	    lexer.nextToken();

	    return new ParExpr(e);
	 }

	 if(lexer.token == Symbol.CHARACTER)
	 {
	    char c = lexer.charValue;
	    lexer.nextToken();

	    return new CharExpr(c);
	 }


	 error.signal("Symbol not expected");
	 return null;
      }

}
