package sem;

import java.util.*;

public class Expression {
	String var = "";
	Expression expression;
	AndTest andTest;

	public Expression(Expression expression, AndTest andTest) {
		super();
		this.expression = expression;
		this.andTest = andTest;
	}

	public String toString() {
		/*
		 * String result="Expression";
		 * result+=Utils.indent(expression.toString(), 1);
		 * result+=Utils.indent("or", 1);
		 * result+=Utils.indent(andTest.toString(), 1); return result;
		 */
		String result = "";

		var = "a" + FileInput.numVar++;
		if (expression.var == "")
			if (andTest.var == "")
				result += var + " = " + expression.toString() + " or "
						+ andTest.toString() + ";\n";
			else
				result += var + " = " + expression.toString() + " or "
						+ andTest.var + ";\n";
		else
			result += var + " = " + expression.var + " or " + andTest.var
					+ ";\n";

		return result;
	}

	public Expression() {
		super();
		// TODO Auto-generated constructor stub
	}

}
