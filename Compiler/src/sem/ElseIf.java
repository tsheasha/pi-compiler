package sem;

public class ElseIf extends ElseIfOptional {

	Suite suite;

	public ElseIf(Suite suite) {
		super();
		this.suite = suite;
	}

	public String toString() {
		/*
		 * String result="ElseIfOptional\n";
		 * result+=Utils.indent(suite.toString(), 1); return result;
		 */
		String result = suite.toString(); 
		return result;
	}
}
