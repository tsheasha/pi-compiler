package automatic;
import java.util.ArrayList;


public class SampleProgram {
	
	ArrayList<SampleStatement> statements;

	public SampleProgram(ArrayList<SampleStatement> statements) {
		super();
		this.statements = statements;
	}
	public String toString()
	{
		String result="Program\n";
		for(int i=0;i<statements.size();i++)
			result+=statements.get(i);
		return result;
	}
	

}
