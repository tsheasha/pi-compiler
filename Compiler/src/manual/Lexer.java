package manual;

/*
 * Class Lexer
 * 
 * Update the method nextToken() such to the provided
 * specifications of the pi Programming Language.
 * 
 * You are not allowed to use any built it in tokenizer
 * in Java. You are only allowed to scan the input file
 * one character at a time.
 */

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;

public class Lexer {
	private BufferedReader reader;
	private char curr;
	private boolean bracketsIndicator, colonIndicator, integerIndicator,
			strictlygreatIndicator, lessIndicator, newlineIndicator,
			stringIndicator, equalIndicator, strictlylessindicator,
			quoteIndicator, booleanIndicator, greaterequalIndicator,
			divisionIndicator, notequalIndicator = false;
	private int pos, count = 0;
	private String tab = "/t";
	private static final char EOF = (char) (-1);
	private LinkedList<Integer> IndentStack = new LinkedList<Integer>();
	private boolean invalidLiteral;
	private boolean dotAtEnd;
	static int charNo = 0;
	static String currLine = "";
	public static String errLine;
	public static int errNo;

	public Lexer(String inFile) {
		try {
			reader = new BufferedReader(new FileReader(inFile));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		curr = read();
		IndentStack.add(0);

	}

	private char read() {
		try {

			charNo++;
			char c = (char) reader.read();
			currLine += c;
			return c;

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return EOF;
		}
	}

	public boolean isInteger(String s) {
		try {
			Integer.parseInt(s);
		} catch (NumberFormatException e) {
			return false;
		}
		return true;
	}
	public boolean isDouble(String s) {
		try {
			Double.parseDouble(s);
		} catch (NumberFormatException e) {
			return false;
		}
		return true;
	}

	public int getIndent() {
		if ((curr == ' ')) {
			curr = read();
			return getIndent() + 1;
		}
		if ((curr == '\t')) {
			curr = read();
			return getIndent() + 8;
		} else
			return 0;
	}

	public Token nextToken() {
		int state = 0;
		String idBuffer = "";
		int level;
		if (invalidLiteral) {
			Lexer.errNo = Lexer.charNo;
			Lexer.errLine = Lexer.currLine;
			Lexer.currLine = "";
			Lexer.charNo = 0;
			newlineIndicator = true;
			invalidLiteral = false;
			curr = read();
			return new Token(Token.NEWLINE, "NEWLINE");
		}
		if (strictlygreatIndicator || strictlylessindicator || booleanIndicator) {
			strictlylessindicator = false;
			strictlylessindicator = false;
			booleanIndicator = false;
			curr = read();
		}
		if (newlineIndicator) {
			level = getIndent();
			newlineIndicator = false;
			if (level > pos) {
				pos = level;
				count++;
				if (!IndentStack.contains(level))
					IndentStack.add(level);
				return new Token(Token.INDENT, "INDENT");
			} else if (level < pos) {
				if (!IndentStack.contains(level))
					return new Token(Token.ERROR, "TabError");
				pos = level;
				count--;
				return new Token(Token.DEDENT, "DEDENT");
			}
		}
		while (true) {
			switch (state) {

			case 0:
				switch (curr) {
				case EOF:
					if (count > 0) {
						count--;
						return new Token(Token.DEDENT, "DEDENT");
					} else
						return null;
				case ' ':
					curr = read();
					continue;
				case '\t':
					curr = read();
					continue;
				case '\r':
					curr = read();
					continue;
				case '\f':
					curr = read();
					continue;
				case '-':
					curr = read();
					return new Token(Token.MO, "-");
				case '\n':
					Lexer.errNo = Lexer.charNo;
					Lexer.errLine = Lexer.currLine;
					Lexer.currLine = "";
					Lexer.charNo = 0;
					newlineIndicator = true;
					curr = read();
					return new Token(Token.NEWLINE, "NEWLINE");
				case '+':
					curr = read();
					return new Token(Token.PO, "+");
				case '|':
					curr = read();
					return new Token(Token.LO, "|");
				case '&':
					curr = read();
					return new Token(Token.LA, "&");
				case '*':
					curr = read();
					return new Token(Token.TO, "*");
				case '~':
					curr = read();
					return new Token(Token.NO, "~");
				case '%':
					curr = read();
					return new Token(Token.MD, "%");
				case ':':
					if (colonIndicator) {
						colonIndicator = false;
						curr = read();
						return new Token(Token.CL, ":");
					} else {
						curr = read();
						return new Token(Token.ERROR, ":");
					}
				case '(':
					bracketsIndicator = true;
					curr = read();
					return new Token(Token.LB, "(");
				case ')':
					if (bracketsIndicator) {
						curr = read();
						bracketsIndicator = false;
						return new Token(Token.RB, ")");
					} else {
						curr = read();
						return new Token(Token.ERROR, ")");
					}
				case ';':
					curr = read();
					return new Token(Token.SM, ";");
				case ',':
					curr = read();
					return new Token(Token.CM, ",");

				default:
					if (Character.isLetter(curr) || curr == '_') {
						idBuffer = curr + "";
						curr = read();
						state = 1;
						break;
					} else if (curr == '#') {
						curr = read();
						state = 2;
						break;
					} else if (curr == '!') {
						curr = read();
						state = 3;
						notequalIndicator = true;
						break;
					} else if (curr == '=' && !booleanIndicator) {
						curr = read();
						state = 3;
						equalIndicator = true;
						break;
					} else if (curr == '<') {
						curr = read();
						state = 3;
						lessIndicator = true;
						break;
					} else if (isInteger(curr + "")) {
						idBuffer = curr + "";
						curr = read();
						integerIndicator = true;
						state = 1;
						break;
					} else if (curr == '"') {
						stringIndicator = true;
						idBuffer = curr + "";
						curr = read();
						state = 1;
						break;
					} else if (curr == "'".charAt(0) && !stringIndicator) {
						quoteIndicator = true;
						idBuffer = curr + "";
						curr = read();
						state = 1;
						break;
					} else if (curr == '>' && !strictlylessindicator) {
						curr = read();
						state = 3;
						greaterequalIndicator = true;
						break;
					} else if (curr == '/' && !strictlygreatIndicator) {
						curr = read();
						state = 3;
						divisionIndicator = true;
						break;
					} else {
						char temp = curr;
						curr = read();
						Warning warning = new Warning(Parser.lineNum,
								Lexer.charNo, "Invalid Input: " + temp,
								Lexer.currLine);
						try {
							MP.writer.write(warning.toString());
							MP.writer.newLine();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				}
				break;
			case 1:
				if (Character.isLetter(curr) || curr == '_') {
					idBuffer += curr;
					curr = read();
					break;
				} else if (isInteger(curr + "")) {
					idBuffer += curr;
					curr = read();
					break;
				} else if (integerIndicator && curr == '.') {
					idBuffer += curr;
					curr = read();
					break;
				} else if (curr == '"') {
					stringIndicator = false;
					idBuffer += curr;
					curr = read();
					return new Token(Token.ST, idBuffer);
				} else if (curr == "'".charAt(0) && !stringIndicator) {
					quoteIndicator = false;
					idBuffer += curr;
					curr = read();
					return new Token(Token.ST, idBuffer);
				} else if (stringIndicator) {
					if (curr == '\n') {
						Warning warning = new Warning(Parser.lineNum,
								Lexer.charNo, "Invalid String Literal: "
										+ idBuffer, Lexer.currLine);
						try {
							MP.writer.write(warning.toString());
							MP.writer.newLine();
						} catch (IOException e) {
							e.printStackTrace();
						}
						stringIndicator = false;
						invalidLiteral = true;
						return new Token(Token.ST, idBuffer);
					}
					idBuffer += curr;
					curr = read();
					break;
				} else if (quoteIndicator) {
					if (curr == '\n') {
						Warning warning = new Warning(Parser.lineNum,
								Lexer.charNo, "Invalid String Literal: "
										+ idBuffer, Lexer.currLine);
						try {
							MP.writer.write(warning.toString());
							MP.writer.newLine();
						} catch (IOException e) {
							e.printStackTrace();
						}
						quoteIndicator = false;
						invalidLiteral = true;
						return new Token(Token.ST, idBuffer);
					}
					idBuffer += curr;
					curr = read();
					break;
				} else {
					if (isInteger(idBuffer)
							|| isInteger(idBuffer.substring(0,
									idBuffer.length() - 1)) || isDouble(idBuffer)) {
						integerIndicator = false;
						if (idBuffer.charAt(idBuffer.length() - 1) == '.'){
							Warning warning = new Warning(Parser.lineNum,
									Lexer.charNo, "Invalid number format "
											+ idBuffer, Lexer.currLine);
							try {
								MP.writer.write(warning.toString());
								MP.writer.newLine();
							} catch (IOException e) {
								e.printStackTrace();
							}
						}
						return new Token(Token.NM, idBuffer);
					} else if (idBuffer.equals("while")) {
						colonIndicator = true;
						return new Token(Token.WHILE, "while");
					} else if (idBuffer.equals("until")) {
						colonIndicator = true;
						return new Token(Token.UNTIL, "until");
					} else if (idBuffer.equals("if")) {
						colonIndicator = true;
						return new Token(Token.IF, "if");
					} else if (idBuffer.equals("elif")) {
						colonIndicator = true;
						return new Token(Token.ELIF, "elif");

					} else if (idBuffer.equals("class")) {
						colonIndicator = true;
						return new Token(Token.CLASS, "class");
					} else if (idBuffer.equals("return")) {
						return new Token(Token.RETURN, "return");
					} else if (idBuffer.equals("in")) {
						return new Token(Token.IN, "in");
					} else if (idBuffer.equals("is")) {
						return new Token(Token.IS, "is");
					} else if (idBuffer.equals("or")) {
						return new Token(Token.OR, "or");
					} else if (idBuffer.equals("else")) {
						colonIndicator = true;
						return new Token(Token.ELSE, "else");
					} else if (idBuffer.equals("print")) {
						colonIndicator = true;
						return new Token(Token.PRINT, "print");
					} else if (idBuffer.equals("def")) {
						colonIndicator = true;
						return new Token(Token.DEF, "def");
					} else if (idBuffer.equals("and")) {
						return new Token(Token.AND, "and");
					} else if (idBuffer.equals("true")
							|| idBuffer.equals("false")) {
						return new Token(Token.BL, idBuffer);
					} else if (integerIndicator) {
						integerIndicator = false;
						return new Token(Token.ERROR, idBuffer);
					} else
						return new Token(Token.ID, idBuffer);
				}
			case 2:
				if (curr == '\n') {
					Lexer.errNo = Lexer.charNo;
					Lexer.errLine = Lexer.currLine;
					Lexer.currLine = "";
					Lexer.charNo = 0;
					curr = read();
					newlineIndicator = true;
					return new Token(Token.NEWLINE, "NEWLINE");
				} else
					curr = read();

			case 3:
				if (equalIndicator) {
					equalIndicator = false;
					if (curr == '=') {
						booleanIndicator = true;
						return new Token(Token.EQ, "==");
					} else {
						return new Token(Token.AO, "=");
					}
				} else if (lessIndicator) {
					lessIndicator = false;
					if (curr == '=') {
						curr = read();
						return new Token(Token.LE, "<=");
					} else if (curr == '>') {
						booleanIndicator = true;
						return new Token(Token.LG, "<>");
					} else {
						return new Token(Token.LT, "<");
					}
				} else if (greaterequalIndicator) {
					greaterequalIndicator = false;
					booleanIndicator = true;
					if (curr == '=') {
						curr = read();
						return new Token(Token.GE, ">=");
					} else {
						return new Token(Token.GT, ">");
					}
				} else if (notequalIndicator) {
					notequalIndicator = false;
					if (curr == '=') {
						booleanIndicator = true;
						return new Token(Token.NE, "!=");
					} else {
						Warning warning = new Warning(Parser.lineNum,
								Lexer.charNo, "Invalid Input: !",
								Lexer.currLine);
						try {
							MP.writer.write(warning.toString());
							MP.writer.newLine();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				} else if (divisionIndicator) {
					divisionIndicator = false;
					if (curr == '/') {
						strictlygreatIndicator = true;
						return new Token(Token.DI, "//");
					} else {
						return new Token(Token.DO, "/");
					}
				}
			}

		}
	}

}