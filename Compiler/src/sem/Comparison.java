package sem;

import java.util.*;

public class Comparison extends AndTest {

	Comparison comparison;
	String operation;
	OrExpression orExpression;

	public Comparison(Comparison comparison, String operation,
			OrExpression orExpression) {
		super();
		this.comparison = comparison;
		this.operation = operation;
		this.orExpression = orExpression;
	}

	public String toString() {
		/*
		 * String result="Comparison\n";
		 * result+=Utils.indent(comparison.toString(), 1);
		 * result+=Utils.indent(" " + operation + " ", 1);
		 * result+=Utils.indent(orExpression.toString(), 1); return result;
		 */
		String result = "";

		var = "a" + FileInput.numVar++;
		if (comparison.var == "")
			if (orExpression.var == "")
				result += var + " = " + comparison.toString() + " " + operation + " "
						+ orExpression.toString() + ";\n";
			else
				result += var + " = " + comparison.toString() + " " + operation + " "
						+ orExpression.var + ";\n";
		else
			result += var + " = " + comparison.var + " " + operation + " "
					+ orExpression.var + ";\n";

		return result;
	}

	public Comparison() {
		super();
		// TODO Auto-generated constructor stub
	}

}
