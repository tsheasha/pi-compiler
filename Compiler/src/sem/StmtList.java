package sem;

import java.util.*;

public class StmtList extends Statement {

	ArrayList<SimpleStmt> statements;

	public StmtList(ArrayList<SimpleStmt> statements) {
		super();
		this.statements = statements;
	}

	public String toString() {
		/*
		 * if(statements.size()==1) return statements.get(0).toString(); String
		 * result="StmtList\n"; for(int i=0;i<statements.size();i++) {
		 * result+=Utils.indent(statements.get(i).toString(),1); } return
		 * result;
		 */

		String result = "";

		for (int i = 0; i < statements.size(); i++)
			result += statements.get(i).toString();
		return result;

	}

}
