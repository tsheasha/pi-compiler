package sem;


public class Utils {
	
	public static String indent(String s,int level)
	{
		String result="";
		String []list=s.split("\n");
		for(int i=0;i<list.length;i++)
		{
			for(int j=0;j<level;j++)
				result+="| ";
			result+=list[i]+"\n";
		}
		return result;
	}

}
