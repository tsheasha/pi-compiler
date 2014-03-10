package manual;
public class SyntaxException extends Exception {
	public SyntaxException( int lineNumber, int charNumber, String foundToken, String line )
	{
	
		super("Syntax Error at line: " + lineNumber + " "+"char: " + charNumber + "\n"+"Found: " + foundToken + "\n"+"In:  "+ line);
	}
}
