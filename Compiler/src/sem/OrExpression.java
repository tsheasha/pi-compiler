package sem;

import java.util.*;

public class OrExpression extends Comparison {

	OrExpression orExpression;
	AndExpression andExpression;

	public OrExpression() {
		super();
		// TODO Auto-generated constructor stub
	}

	public OrExpression(OrExpression orExpression, AndExpression andExpression) {
		super();
		this.orExpression = orExpression;
		this.andExpression = andExpression;
	}

	public String toString() {
		/*
		 * String result="orExpression\n";
		 * result+=Utils.indent(orExpression.toString(), 1);
		 * result+=Utils.indent("|", 1);
		 * result+=Utils.indent(andExpression.toString(), 1); return result;
		 */
		String result = "";

		var = "a" + FileInput.numVar++;
		if(orExpression.var == "")
			if(andExpression.var == "")
				result += var + " = " + orExpression.toString() + " | "
						+ andExpression.toString() + ";\n";
			else
				result += var + " = " + orExpression.toString() + " | "
						+ andExpression.var + ";\n";
		else
			result += var + " = " + orExpression.var + " | "
					+ andExpression.var + ";\n";
		return result;

	}

}
