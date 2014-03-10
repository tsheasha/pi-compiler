package sem;

import java.util.ArrayList;

public class ParameterList {
	public ArrayList<DefParameter> defParameters;

	public ParameterList(ArrayList<DefParameter> defParameters) {
		super();
		this.defParameters = defParameters;
	}

	public String toString() {
		/*
		 * String result=""; if(defParameters.size()>0) {
		 * result="ParameterList\n"; for(int i=0;i<defParameters.size();i++) {
		 * result+=Utils.indent(defParameters.get(i).toString(),1); } } return
		 * result;
		 */
		String result = "";

		for (int i = 0; i < defParameters.size(); i++)
			result += defParameters.get(i).toString();

		return result;
	}

}
