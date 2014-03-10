package sem;


public class ST extends PrimaryExpression{
	String value;
	
	public ST(String value)
	{
		this.value=value;
	}
	public String toString()
	{
		return value+"";
	}
}
