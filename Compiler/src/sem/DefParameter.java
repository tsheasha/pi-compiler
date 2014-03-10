package sem;

public class DefParameter {
	String id, var;
	Expression expression;

	public DefParameter(String id, Expression expression) {
		super();
		this.id = id;
		this.expression = expression;
	}

	public String toString() {
		/*
		 * String result=""; int in=0; if(expression!=null) //sure? {
		 * result="DefParameter\n"; in=1; } result+=Utils.indent(id+"\n",in);
		 * if(expression!=null) result+=Utils.indent(expression.toString(), in);
		 * return result;
		 */
		String result = "";

		if (expression != null)
			result += expression.toString() + id + " = " + expression.var
					+ ";\n";

		return result;

	}
}
