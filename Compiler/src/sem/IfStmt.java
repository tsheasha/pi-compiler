package sem;

public class IfStmt extends CompoundStmt {

	Expression expression;
	Suite suite;
	ElseIfOptional elseIf;

	public IfStmt(Expression expression, Suite suite, ElseIfOptional elseIf) {
		super();
		this.expression = expression;
		this.suite = suite;
		this.elseIf = elseIf;
	}

	public String toString() {
		/*
		 * String result="IfStmt\n"; result+=Utils.indent(expression.toString(),
		 * 1); result+=Utils.indent(suite.toString(), 1); if(elseIf!=null) {
		 * result+=Utils.indent(elseIf.toString(), 1); } return result;
		 */
		String result = "";
		int label = FileInput.numLab++;
		int currLabNum;
		result += expression.toString();

		if (elseIf != null) {
			elseIf.lab = label;
			currLabNum = FileInput.numLab++;
			result += "IfZ " + expression.var + " Goto L" + currLabNum + ";\n"
					+ suite.toString() + "Goto L" + label + ";\n" + "L"
					+ currLabNum + ":\n" + elseIf.toString() + "L" + label
					+ ":\n";
		} else

			result += "IfZ " + expression.var + " Goto L" + label + ";\n"
					+ suite.toString() + "L" + label + ":\n";

		return result;
	}

}
