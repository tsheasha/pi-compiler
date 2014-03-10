package sem;

import java.util.*;

public class AndExpression extends OrExpression {

	AndExpression andExpression;
	ArithmeticExpression arithmeticExpression;

	public AndExpression() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AndExpression(AndExpression andExpression,
			ArithmeticExpression arithmeticExpression) {
		super();
		this.andExpression = andExpression;
		this.arithmeticExpression = arithmeticExpression;
	}

	public String toString() {
		/*
		 * String result="AndExpression\n";
		 * result+=Utils.indent(andExpression.toString(), 1);
		 * result+=Utils.indent("&", 1);
		 * result+=Utils.indent(arithmeticExpression.toString(), 1); return
		 * result;
		 */
		String result = "";

		var = "a" + FileInput.numVar++;
		if(andExpression.var == "")
			if(arithmeticExpression.var == "")
				result += var + "=" + andExpression.toString() + "&"
						+ arithmeticExpression.toString() + ";\n";
			else
				result += var + "=" + andExpression.toString() + "&"
						+ arithmeticExpression.var + ";\n";
		else
			result += var + "=" + andExpression.var + "&"
					+ arithmeticExpression.var + ";\n";


		return result;
	}

}
