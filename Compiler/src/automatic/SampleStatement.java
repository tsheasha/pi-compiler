package automatic;
public class SampleStatement {
	
	String ID;
	String NM;
	public SampleStatement(String iD, String nM) {
		super();
		ID = iD;
		NM = nM;
	}
	public String toString()
	{
		String result="| Statement\n";
		result+="| | "+ID+"\n";
		result+="| | ="+"\n";
		result+="| | "+NM+"\n";
		return result;
		
		
	}

}
