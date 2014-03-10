package automatic;
public class Warning {
	
	int lineNo;
	int charNo;
	String message;
	String line;
	public Warning(int lineNo, int charNo, String message, String line)
	{
		this.lineNo=lineNo;
		this.charNo=charNo;
		this.message=message;
		this.line=line;
	}
	
	public String toString()
	{
		String s="WARNING at line "+lineNo+" char "+charNo+":\n"+
				message+"\n"+
			"In: "+line+"\n";
		return s;

	}

}
