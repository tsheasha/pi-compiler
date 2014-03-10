package sem;

import java.util.*;

public class ArithmeticExpression extends AndExpression {

	ArithmeticExpression arithmetic;
	String operation;
	MultExpression multExpression;

	public ArithmeticExpression(ArithmeticExpression arithmetic,
			String operator, MultExpression multExpression) {
		super();
		this.arithmetic = arithmetic;
		this.operation = operator;
		this.multExpression = multExpression;
	}

	public ArithmeticExpression() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String toString() {
		/*
		 * String result="ArithmeticExpression\n";
		 * result+=Utils.indent(arithmetic.toString(), 1);
		 * result+=Utils.indent(operation, 1);
		 * result+=Utils.indent(multExpression.toString(), 1); return result;
		 */
		String result = "";

		var = "a" + FileInput.numVar++;
		if (arithmetic.var == "")
			if (multExpression.var == "")
				result += var + " = " + arithmetic.toString() + operation
						+ multExpression.toString() + ";\n";
			else
				result += var + " = " + arithmetic.toString() + operation
						+ multExpression.var + ";\n";
		else
			result += var + " = " + arithmetic.var + operation
					+ multExpression.var + ";\n";

		return result;
	}
}
