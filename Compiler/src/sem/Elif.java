package sem;

public class Elif extends ElseIfOptional {
	Expression expression;
	Suite suite;
	ElseIfOptional elseIfOptional;

	public Elif(Expression expression, Suite suite,
			ElseIfOptional elseIfOptional) {
		super();
		this.expression = expression;
		this.suite = suite;
		this.elseIfOptional = elseIfOptional;
	}

	public String toString() {
		/*
		 * String result="ElseIfOptional\n";
		 * result+=Utils.indent(expression.toString(),1);
		 * result+=Utils.indent(suite.toString(), 1); if(elseIfOptional!=null)
		 * result+=Utils.indent(elseIfOptional.toString(), 1); return result;
		 */
		String result = "";
		int currLabNum;
		result += expression.toString();

		if (elseIfOptional != null) {
			elseIfOptional.lab = lab;
			currLabNum = FileInput.numLab++;
			result += "IfZ " + expression.var + " GoTo L" + currLabNum + ";\n"
					+ suite.toString() + "GoTo L" + lab + ";\n" + "L"
					+ currLabNum + ":\n" + elseIfOptional.toString();
		} else
			result += "IfZ " + expression.var + " GoTo L" + lab + ";\n"
					+ suite.toString() + "L" + lab + ":\n";

		return result;

	}

}
