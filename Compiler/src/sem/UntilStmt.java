package sem;

public class UntilStmt extends CompoundStmt {

	Expression expression;
	Suite suite;
	Suite elseSuite;

	public UntilStmt(Expression expression, Suite suite, Suite elseSuite) {
		super();
		this.expression = expression;
		this.suite = suite;
		this.elseSuite = elseSuite;
	}

	public String toString() {
		/*
		 * String result="WhileStmt\n";
		 * result+=Utils.indent(expression.toString(), 1);
		 * result+=Utils.indent(suite.toString(), 1); if(elseSuite!=null) {
		 * result+=Utils.indent(elseSuite.toString(), 1); } return result;
		 */
		String result = "";
		int label = FileInput.numLab++;
		int currLabNum = FileInput.numLab++;

		result += "L" + label + ":\n" + expression.toString() + "IfZ "
				+ expression.var + " Goto L" + currLabNum + ";\n";
		if (elseSuite != null)
			result += elseSuite.toString() + "Goto L" + FileInput.numLab + ";\n";
				result += "L"+ currLabNum + ":\n" + suite.toString() + "Goto L" + label + ";\n"
						+ "\nL" + FileInput.numLab + ":\n";


		return result;
	}

}
