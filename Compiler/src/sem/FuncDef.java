package sem;

import java.util.*;

public class FuncDef extends CompoundStmt {
	String id;
	ParameterList list;
	Suite suite;

	public FuncDef(String id, ParameterList list, Suite suite) {
		super();
		this.list = list;
		this.id = id;
		this.suite = suite;
	}

	public String toString() {
		/*
		 * String result="FuncDef "+id+"\n";
		 * result+=Utils.indent(list.toString(), 1);
		 * result+=Utils.indent(suite.toString(), 1); return result;
		 */
		return id + ":\n" + "begin_func;\n" + list.toString()
				+ suite.toString() + "end_func;\n";
	}

}
