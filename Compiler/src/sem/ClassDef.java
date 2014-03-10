package sem;

public class ClassDef extends CompoundStmt {

	String id;
	Suite suite;

	public ClassDef(String id, Suite suite) {
		super();
		this.id = id;
		this.suite = suite;
	}

	public String toString() {
		/*
		 * String result="ClassDef "+id+"\n"; result+=Utils.indent(id, 1);
		 * return result;
		 */
		return id + ":\n" + suite.toString();
	}
}
