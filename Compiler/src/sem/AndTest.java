package sem;

public class AndTest extends Expression {

	AndTest andTest;
	Comparison comparison;

	public AndTest() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AndTest(AndTest andTest, Comparison comparison) {
		super();
		this.andTest = andTest;
		this.comparison = comparison;
	}

	public String toString() {
		/*
		 * String result="AndTest\n"; result+=Utils.indent(andTest.toString(),
		 * 1); result+=Utils.indent("and", 1);
		 * result+=Utils.indent(comparison.toString(), 1); return result;
		 */
		String result = "";

		var = "a" + FileInput.numVar++;
		if(andTest.var == "")
			if(comparison.var =="")
				result += var + "=" + andTest.toString() + " and "
						+ comparison.toString() + ";\n";
			else
				result += var + "=" + andTest.toString() + " and "
						+ comparison.var + ";\n";
		else
			result += var + "=" + andTest.var + " and "
					+ comparison.var + ";\n";
		

		return result;
	}

	public AndTest(Expression expression, AndTest andTest) {
		super(expression, andTest);
		// TODO Auto-generated constructor stub
	}

}
