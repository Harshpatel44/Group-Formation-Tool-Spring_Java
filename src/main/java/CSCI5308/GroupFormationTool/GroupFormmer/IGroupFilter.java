package CSCI5308.GroupFormationTool.GroupFormmer;

import java.util.ArrayList;
import java.util.HashMap;

public interface IGroupFilter {
	public HashMap<Integer, Boolean> getquestionSimilarOrDissimilar();

	public void setquestionSimilarOrDissimilar(HashMap<Integer, Boolean> questionSimilarOrDissimilar);

	public HashMap<Integer, ArrayList<Integer>> getConsiderLessThanOrGreaterThanX();

	public void setConsiderLessThanOrGreaterThanX(HashMap<Integer, ArrayList<Integer>> considerLessThanOrGreaterThanX);

}
