package sem;

import java.util.*;

public class FileInput {
	public static int numLab;
	ArrayList<Statement> statements;
	public static int numVar;

	public FileInput(ArrayList<Statement> statements) {
		super();
		this.statements = statements;
	}

	public String toString() {
		/*
		 * String result="FileInput\n"; for(int i=0;i<statements.size();i++) {
		 * result+=Utils.indent(statements.get(i).toString(), 1); }
		 * 
		 * return result;
		 */
		String result = "";

		for (int i = 0; i < statements.size(); i++)
			result += statements.get(i).toString();

		return result;
	}

}
