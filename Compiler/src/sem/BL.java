package sem;


public class BL extends PrimaryExpression{
	
	boolean value;
	
	public BL(boolean value)
	{
		this.value=value;
	}
	public String toString()
	{
		return value+"";
	}

}
