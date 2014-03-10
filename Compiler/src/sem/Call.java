package sem;

import java.util.*;

public class Call extends PrimaryExpression {
	String id;
	ArrayList<Expression> expressions;

	public Call(String id, ArrayList<Expression> expressions) {
		this.id = id;
		this.expressions = expressions;
	}

	public String toString() {
		/*
		 * String result="Call "+id+"\n";
		 * 
		 * 
		 * //result+="| ExpressionList\n"; for(int i=0;i<expressions.size();i++)
		 * { result+=Utils.indent(expressions.get(i).toString(),1); }
		 * 
		 * 
		 * return result;
		 */

		String result = "";

		for (int i = 0; i < expressions.size(); i++)
			result += expressions.get(i).toString();

		for (int i = 0; i < expressions.size(); i++)
			result += "push_param " + expressions.get(i).var + ";\n";
		
		var = "a" + FileInput.numVar++;
		return result += var + " = call " + id + ";\n" + "pop_params ;\n";

	}

}
