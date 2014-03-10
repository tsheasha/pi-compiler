package sem;

import java.util.*;

public class Suite {

	StmtList list;
	ArrayList<Statement> statements;

	public Suite(StmtList list) {
		this.list = list;
	}

	public Suite(ArrayList<Statement> statements) {
		this.statements = statements;
	}

	public String toString() {
		/*
		 * String result="Suite\n"; if(list!=null) {
		 * result+=Utils.indent(list.toString(), 1); } else { for(int
		 * i=0;i<statements.size();i++) {
		 * result+=Utils.indent(statements.get(i).toString(), 1); } } return
		 * result;
		 */

		String result = "";
		if (list != null)
			result += list.toString();
		else
			for (int i = 0; i < statements.size(); i++)
				result += statements.get(i).toString();

		return result;
	}

}
