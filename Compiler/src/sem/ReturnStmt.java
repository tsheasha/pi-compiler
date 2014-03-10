package sem;

import java.util.ArrayList;

public class ReturnStmt extends SimpleStmt {

	ArrayList<Expression> expressionList;

	public ReturnStmt(ArrayList<Expression> expressionList) {
		super();
		this.expressionList = expressionList;
	}

	public String toString() {
		/*
		 * String result="ReturnStmt\n"; for(int
		 * i=0;i<expressionList.size();i++)
		 * result+=Utils.indent(expressionList.get(i).toString(), 1)+"\n";
		 * return result;
		 */

		String result = "return ";

		if (expressionList.size() > 0) {
			for (int i = 0; i < expressionList.size(); i++)
				result += expressionList.get(i).toString();
			
			int end;
			for (end = 0; end < expressionList.size() - 1; end++)
				result += expressionList.get(end).var + ",";

			result += expressionList.get(end).var;

		} 

		return result += ";\n";

	}
}
