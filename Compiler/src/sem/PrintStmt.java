package sem;

import java.util.ArrayList;

public class PrintStmt extends SimpleStmt {

	ArrayList<Expression> expressionList;

	public PrintStmt(ArrayList<Expression> expressionList) {
		super();
		this.expressionList = expressionList;
	}

	public String toString() {
		/*
		 * String result="PrintStmt\n"; if(expressionList!=null) { for(int
		 * i=0;i<expressionList.size();i++)
		 * result+=Utils.indent(expressionList.get(i).toString(), 1); } return
		 * result;
		 */
		String result = "print ";
			int end;
			for (end = 0; end < expressionList.size() - 1; end++)
				result += expressionList.get(end).toString() + ", ";

			result += expressionList.get(end).toString();

		return result += ";\n";

	}

}