package sem;


public class NM extends PrimaryExpression{
	
	String value;
	
	public NM(String value)
	{
		this.value=value;
	}
	public String toString()
	{
		return value+"";
	}

}
