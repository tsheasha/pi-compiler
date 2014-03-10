package sem;

import java.util.*;

public class MultExpression extends ArithmeticExpression {
	MultExpression multExpression;
	String operation;
	UnaryExpression unaryExpression;

	public MultExpression(MultExpression multExpression, String operation,
			UnaryExpression unaryExpression) {
		super();
		this.multExpression = multExpression;
		this.operation = operation;
		this.unaryExpression = unaryExpression;
	}

	public MultExpression() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String toString() {
		/*
		 * String result="MultExpression\n";
		 * result+=Utils.indent(multExpression.toString(), 1);
		 * result+=Utils.indent(operation, 1);
		 * result+=Utils.indent(unaryExpression.toString(), 1); return result;
		 */
		String result = "";

		var = "a" + FileInput.numVar++;
		if (multExpression.var == "")
			if (unaryExpression.var == "")
				result += var + " = " + multExpression.toString() + operation
						+ unaryExpression.toString() + ";\n";
			else
				result += var + " = " + multExpression.toString() + operation
						+ unaryExpression.var + ";\n";
		else
			result += var + " = " + multExpression.var + operation
					+ unaryExpression.var + ";\n";

		return result;

	}

}
