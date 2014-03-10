package sem;

import java.util.*;

public class UnaryExpression extends MultExpression {

	public UnaryExpression() {
		super();
		// TODO Auto-generated constructor stub
	}

	ArrayList<String> vars;
	ArrayList<String> operations;
	PrimaryExpression primary;

	public UnaryExpression(ArrayList<String> operations,
			PrimaryExpression primary) {
		vars = new ArrayList<String>();
		this.operations = operations;
		this.primary = primary;
	}

	public String toString() {
		/*
		 * String result=""; for(int i=0;i<operations.size();i++) {
		 * result+=Utils.indent("UnaryExpression\n",i);
		 * result+=Utils.indent(operations.get(i),i)+"\n";
		 * 
		 * 
		 * } result+=Utils.indent(primary.toString(),operations.size()); return
		 * result;
		 */
		String result = "";
		int i;

		if (operations.size() == 0) {
			result += primary.toString();
			this.var = primary.var;
			return result;
		}

		for (i = 0; i < operations.size(); i++)
			vars.add("a" + FileInput.numVar++);

		var = vars.get(operations.size() - 1);
		result += vars.get(0) + " = 0 " + operations.get(0) + primary.var + ";\n";

		for (i = 1; i < operations.size(); i++)
			result += vars.get(i) + " = 0 " + operations.get(i) + vars.get(i - 1)
					+ ";\n";

		return result;
	}
}
