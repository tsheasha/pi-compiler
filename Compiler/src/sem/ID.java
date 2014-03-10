package sem;


public class ID extends PrimaryExpression{
	
	String value;
	
	public ID(String value)
	{
		this.value=value;
	}
	
	public String toString()
	{
		return value+"";
	}

}
