package sem;

import java.util.*;

public class AssignmentStmt extends SimpleStmt {
	ArrayList<String> IDs;
	ArrayList<Expression> expressions;

	public AssignmentStmt(ArrayList<String> iDs,
			ArrayList<Expression> expressions) {
		super();
		IDs = iDs;
		this.expressions = expressions;
	}

	public String toString() {
		/*
		 * String result="AssignmentStmt\n"; for(int i=0;i<IDs.size();i++) {
		 * result+=Utils.indent(IDs.get(i).toString(), 1); }
		 * result+=Utils.indent("=",1); for(int i=0;i<expressions.size();i++) {
		 * result+=Utils.indent(expressions.get(i).toString(), 1);
		 * 
		 * } return result;
		 */
		String result = "";
		
		for (int i = 0; i < IDs.size(); i++)
			result += IDs.get(i) + " = " + expressions.get(i).toString() + ";\n";
		
		

		return result;

	}
}
