package manual;

import java.io.IOException;
import java.util.ArrayList;

import sem.AndExpression;
import sem.AndTest;
import sem.ArithmeticExpression;
import sem.AssignmentStmt;
import sem.BL;
import sem.Call;
import sem.ClassDef;
import sem.Comparison;
import sem.CompoundStmt;
import sem.DefParameter;
import sem.Elif;
import sem.ElseIf;
import sem.ElseIfOptional;
import sem.Expression;
import sem.FileInput;
import sem.FuncDef;
import sem.ID;
import sem.IfStmt;
import sem.MultExpression;
import sem.NM;
import sem.OrExpression;
import sem.ParameterList;
import sem.PrimaryExpression;
import sem.PrintStmt;
import sem.ReturnStmt;
import sem.ST;
import sem.SimpleStmt;
import sem.Statement;
import sem.StmtList;
import sem.Suite;
import sem.UnaryExpression;
import sem.UntilStmt;
import sem.WhileStmt;

/*
 * class Parser
 * 
 * Parses the tokens returned from the lexical analyzer.
 * 
 * Update the parser implementation to parse the pi
 * language according to the grammar provided. 
 */

public class Parser {

	public Lexer lexer; // lexical analyzer
	public Token token; // look ahead token
	public static int lineNum = 1;

	public Parser(Lexer lex) {
		lexer = lex;
	}

	public FileInput parse() throws SyntaxException {

		token = lexer.nextToken();
		ArrayList<Statement> statements = new ArrayList<Statement>();
		while (token != null && token.getTokenType() != Token.EOF) {
			if (!newLine())
				statements.add(statement());

		}

		return new FileInput(statements);
	}

	public boolean newLine() {
		boolean value = match(Token.NEWLINE);
		if (value)
			lineNum++;
		return value;
	}

	public Statement statement() throws SyntaxException {
		Statement s, s1;
		s = stmtList(new ArrayList<SimpleStmt>());
		if (s != null && newLine()) {
			while (token != null && token.getTokenType() == Token.NEWLINE)
				newLine();
			return s;
		} else
			s1 = compoundStmt();
		if (s == null && s1 == null && token.getTokenType() != Token.DEDENT)
			throw new SyntaxException(lineNum, Lexer.charNo - token.getLexeme().length(), token.getLexeme(),
					Lexer.currLine);
		return s1;
	}

	public StmtList stmtList(ArrayList<SimpleStmt> ss) throws SyntaxException {
		SimpleStmt s = simpleStmt();
		if (s != null)
			ss.add(s);
		else
			return null;
		if (match(Token.SM))
			stmtList(ss);

		return new StmtList(ss);
	}

	public SimpleStmt simpleStmt() throws SyntaxException {

		if (token.getTokenType() == Token.ID)
			return assignmentStmt();
		else if (token.getTokenType() == Token.PRINT)
			return printStmt();
		else if (token.getTokenType() == Token.RETURN)
			return returnStmt();
		return null;

	}

	public CompoundStmt compoundStmt() throws SyntaxException {
		if (token.getTokenType() == Token.IF)
			return ifStmt();
		else if (token.getTokenType() == Token.WHILE)
			return whileStmt();
		else if (token.getTokenType() == Token.DEF)
			return funcDef();
		else if (token.getTokenType() == Token.CLASS)
			return classDef();
		else if (token.getTokenType() == Token.UNTIL)
			return untilStmt();
		else
			return null;

	}

	public IfStmt ifStmt() throws SyntaxException {
		if (match(Token.IF)) {
			Expression e = expression();
			if (!match(Token.CL)){
				Warning warning = new Warning(Parser.lineNum, Lexer.errNo,
						"CL missing before suite", Lexer.errLine);
			try {
				MP.writer.write(warning.toString());
				MP.writer.newLine();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
			Suite s = suite();
			ElseIfOptional el = elseIfOptional();
			return new IfStmt(e, s, el);
		}
		throw new SyntaxException(lineNum, Lexer.charNo - token.getLexeme().length(), token.getLexeme(),
				Lexer.currLine);

	}

	public ElseIfOptional elseIfOptional() throws SyntaxException {
		if (match(Token.ELIF)) {
			Expression e = expression();
			if (!match(Token.CL)){
				Warning warning = new Warning(Parser.lineNum, Lexer.errNo,
						"CL missing before suite", Lexer.errLine);
				try {
					MP.writer.write(warning.toString());
					MP.writer.newLine();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
			Suite s = suite();
			ElseIfOptional el = elseIfOptional();
			return new Elif(e, s, el);

		} else if (match(Token.ELSE)) {
			if (!match(Token.CL)){
				Warning warning = new Warning(Parser.lineNum, Lexer.errNo,
						"CL missing before suite", Lexer.errLine);
				try {
					MP.writer.write(warning.toString());
					MP.writer.newLine();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			Suite s = suite();
			return new ElseIf(s);
		}
		return null;
	}

	public Suite suite() throws SyntaxException {
		boolean newLine = newLine();
		if (newLine) {
			if (token.getTokenType() == Token.INDENT) {
				match(Token.INDENT);
				ArrayList<Statement> statements = statements(new ArrayList<Statement>());
				match(Token.DEDENT);
				return new Suite(statements);
			}
		} else
			return new Suite(stmtList(new ArrayList<SimpleStmt>()));

		throw new SyntaxException(lineNum, Lexer.charNo - token.getLexeme().length(), token.getLexeme(),
				Lexer.currLine);
	}

	public ArrayList<Statement> statements(ArrayList<Statement> s)
			throws SyntaxException {
		Statement st = statement();
		if (st != null) {
			s.add(st);
			statements(s);

		}
		return s;
	}
public UntilStmt untilStmt() throws SyntaxException{
	match(Token.UNTIL);
	Expression e = expression();
	if (!match(Token.CL)) {
		Warning warning = new Warning(Parser.lineNum, Lexer.errNo,
				"CL missing before suite", Lexer.errLine);
	try {
		MP.writer.write(warning.toString());
		MP.writer.newLine();
	} catch (IOException e1) {
		e1.printStackTrace();
	}
}
	Suite s = suite();
	if (match(Token.ELSE)) {
		if (match(Token.CL)) {
			return new UntilStmt(e, s, suite());
		}
	}
	return new UntilStmt(e, s, null);

	
}
	public WhileStmt whileStmt() throws SyntaxException {
		match(Token.WHILE);
		Expression e = expression();
		if (!match(Token.CL)) {
			Warning warning = new Warning(Parser.lineNum, Lexer.errNo,
					"CL missing before suite", Lexer.errLine);
		try {
			MP.writer.write(warning.toString());
			MP.writer.newLine();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
		Suite s = suite();
		if (match(Token.ELSE)) {
			if (match(Token.CL)) {
				return new WhileStmt(e, s, suite());
			}
		}
		return new WhileStmt(e, s, null);
	}

	public FuncDef funcDef() throws SyntaxException {
		String t = "";
		if (match(Token.DEF)) {
			t = token.getLexeme();
			if (match(Token.ID))
				if (match(Token.LB)) {
					ParameterList p = parameterList(new ArrayList<DefParameter>());
					if (match(Token.RB))
						if (match(Token.CL))
							return new FuncDef(t, p, suite());
				}
		}
		throw new SyntaxException(lineNum, Lexer.charNo - token.getLexeme().length(), token.getLexeme(),
				Lexer.currLine);
	}

	public ParameterList parameterList(ArrayList<DefParameter> dp)
			throws SyntaxException {
		DefParameter d = defParameter();
		if (d != null) {
			dp.add(d);
			if (match(Token.CM)) {
				return parameterList(dp);
			}
		}
		return new ParameterList(dp);
	}

	public DefParameter defParameter() throws SyntaxException {
		String t = token.getLexeme();
		if (match(Token.ID)) {
			if (match(Token.AO)) {
				return new DefParameter(t, expression());
			}
		}
		return new DefParameter(t, null);
	}

	public ClassDef classDef() throws SyntaxException {
		if (match(Token.CLASS)) {
			String t = token.getLexeme();
			if (match(Token.ID)) {
				if (match(Token.CL)) {
					return new ClassDef(t, suite());
				}
			}
		}
		throw new SyntaxException(lineNum, Lexer.charNo - token.getLexeme().length(), token.getLexeme(),
				Lexer.currLine);
	}

	public AssignmentStmt assignmentStmt() throws SyntaxException {
		ArrayList<String> st = targetList(new ArrayList<String>());
		if (match(Token.AO))
			return new AssignmentStmt(st,
					expressionList(new ArrayList<Expression>()));
		throw new SyntaxException(lineNum, Lexer.charNo - token.getLexeme().length(), token.getLexeme(),
				Lexer.currLine);

	}

	public ArrayList<String> targetList(ArrayList<String> st) {
		String t = token.getLexeme();
		if (match(Token.ID)) {
			st.add(t);
			if (match(Token.CM))
				targetList(st);
		}
		return st;
	}

	public ArrayList<Expression> expressionList(ArrayList<Expression> exp)
			throws SyntaxException {
		Expression e = expression();
		if (e != null) {
			exp.add(e);
			if (match(Token.CM)) {
				try {
					expressionList(exp);
				} catch (SyntaxException se) {
					return exp;
				}
			}
		}
		return exp;
	}

	public PrintStmt printStmt() throws SyntaxException {
		if (match(Token.PRINT))
			return new PrintStmt(expressionList(new ArrayList<Expression>()));
		throw new SyntaxException(lineNum, Lexer.charNo - token.getLexeme().length(), token.getLexeme(),
				Lexer.currLine);
	}

	public ReturnStmt returnStmt() throws SyntaxException {
		if (match(Token.RETURN)) {
			if (token.getTokenType() == Token.NEWLINE){
				Warning warning = new Warning(Parser.lineNum, Lexer.errNo,
						"Return Statement with no Arguments", Lexer.errLine);
				try {
					MP.writer.write(warning.toString());
					MP.writer.newLine();
				} catch (IOException e) {
					e.printStackTrace();
				}
				return new ReturnStmt(new ArrayList<Expression>());
			}
			else
				return new ReturnStmt(
						expressionList(new ArrayList<Expression>()));
		}
		throw new SyntaxException(lineNum, Lexer.charNo - token.getLexeme().length(), token.getLexeme(),
				Lexer.currLine);
	}

	public Expression expression() throws SyntaxException {
		AndTest an = andTest();
		Expression e = expression1(an);
		return e;
	}

	public Expression expression1(AndTest an2) throws SyntaxException {
		if (match(Token.OR)) {
			AndTest an = andTest();
			Expression exp = expression1(an);
			return new Expression(exp, an2);
		}
		return an2;
	}

	public AndTest andTest() throws SyntaxException {
		Comparison c = comparison();
		AndTest an = andTest1(c);
		return an;
	}

	public AndTest andTest1(Comparison c2) throws SyntaxException {
		if (match(Token.AND)) {
			Comparison c = comparison();
			AndTest an = andTest1(c);
			return new AndTest(an, c2);
		}
		return c2;
	}

	public Comparison comparison() throws SyntaxException {
		OrExpression o = orExpression();
		Comparison c = comparison1(o);
		return c;
	}

	public Comparison comparison1(OrExpression o2) throws SyntaxException {
		String t = token.getLexeme();
		if (compOperator()) {
			OrExpression o = orExpression();
			Comparison c = comparison1(o);
			return new Comparison(o2, t, (OrExpression) c);
		}

		return o2;
	}

	public boolean compOperator() {
		return match(Token.LT) || match(Token.GT) || match(Token.EQ)
				|| match(Token.GE) || match(Token.LE) || match(Token.LG)
				|| match(Token.NE) || match(Token.IS) || match(Token.IN);
	}

	public OrExpression orExpression() throws SyntaxException {
		AndExpression an = andExpression();
		OrExpression o = orExpression1(an);

		return o;
	}

	public OrExpression orExpression1(AndExpression an2) throws SyntaxException {
		if (match(Token.LO)) {
			AndExpression an = andExpression();
			OrExpression o = orExpression1(an);

			return new OrExpression(an2, (AndExpression) o);
		}
		return an2;
	}

	public AndExpression andExpression() throws SyntaxException {
		ArithmeticExpression ar = arithmeticExpression();
		AndExpression an = andExpression1(ar);
		return an;
	}

	public AndExpression andExpression1(ArithmeticExpression ar2)
			throws SyntaxException {
		if (match(Token.LA)) {
			ArithmeticExpression ar = arithmeticExpression();
			AndExpression an = andExpression1(ar);
			return new AndExpression(ar2, (ArithmeticExpression) an);
		}

		return ar2;
	}

	public ArithmeticExpression arithmeticExpression() throws SyntaxException {
		MultExpression m = multExpression();
		ArithmeticExpression ar = arithmeticExpression1(m);
		return ar;
	}

	public ArithmeticExpression arithmeticExpression1(MultExpression m2)
			throws SyntaxException {
		String t = token.getLexeme();
		if (match(Token.PO) || match(Token.MO)) {
			MultExpression m = multExpression();
			ArithmeticExpression ar = arithmeticExpression1(m);
			if (ar instanceof MultExpression)
				return new ArithmeticExpression(m2, t, (MultExpression) ar);
			else
				return new ArithmeticExpression(ar, t, m2);
		}
		return m2;
	}

	public MultExpression multExpression() throws SyntaxException {
		UnaryExpression u = unaryExpression(new ArrayList<String>());
		MultExpression m = multExpression1(u);
		return m;
	}

	public MultExpression multExpression1(UnaryExpression u2)
			throws SyntaxException {
		String t = token.getLexeme();
		if (match(Token.TO) || match(Token.DO) || match(Token.DI)
				|| match(Token.MD)) {
			UnaryExpression u = unaryExpression(new ArrayList<String>());
			MultExpression m = multExpression1(u);
			if (m instanceof UnaryExpression)
				return new MultExpression(u2, t, (UnaryExpression) m);
			else
				return new MultExpression(m, t, u2);
		}
		return u2;
	}

	public UnaryExpression unaryExpression(ArrayList<String> op)
			throws SyntaxException {
		String t = token.getLexeme();
		if (match(Token.MO) || match(Token.PO) || match(Token.NO)) {
			op.add(t);
			return unaryExpression(op);
		} else {
			PrimaryExpression p = primary();
			UnaryExpression u = new UnaryExpression(op, p);
			return u;
		}
	}

	public PrimaryExpression primary() throws SyntaxException {

		String t = token.getLexeme();
		boolean parsed = false;
		Call c = null;
		if (parsed = match(Token.ID))
			c = call(t);
		if (parsed && c == null)
			return new ID(t);
		if (match(Token.NM))
			return new NM(t);
		if (match(Token.ST))
			return new ST(t);
		if (match(Token.BL))
			if (t.startsWith("t") || t.startsWith("T"))
				return new BL(true);
			else
				return new BL(false);
		if (c == null)
			throw new SyntaxException(lineNum, Lexer.errNo, token.getLexeme(),
					Lexer.errLine);
		else
			return c;
	}

	public Call call(String t) throws SyntaxException {
		if (match(Token.LB)) {
			ArrayList<Expression> expressions = expressionList(new ArrayList<Expression>());
			if (match(Token.RB))
				return new Call(t, expressions);
		}
		return null;
	}

	public boolean match(int t) {
		if (token != null && token.getTokenType() == t) {
			token = lexer.nextToken();
			return true;
		} else {
			return false;
		}
	}
}