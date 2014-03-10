package sem;
import java.util.ArrayList;


public class RaiseStmt extends SimpleStmt{
	
	ArrayList<Expression> expressionList;

	public RaiseStmt(ArrayList<Expression> expressionList) {
		super();
		this.expressionList = expressionList;
	}
	
	public String toString()
	{
		String result="RaiseStmt\n";
		for(int i=0;i<expressionList.size();i++)
		result+=Utils.indent(expressionList.get(i).toString(), 1)+"\n";
		return result;
	}
	

}
