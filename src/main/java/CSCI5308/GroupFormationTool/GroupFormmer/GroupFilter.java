package CSCI5308.GroupFormationTool.GroupFormmer;

import java.util.ArrayList;
import java.util.HashMap;

public class GroupFilter implements IGroupFilter {
	private HashMap<Integer, Boolean> questionSimilarOrDissimilar;
	private HashMap<Integer, ArrayList<Integer>> considerLessThanOrGreaterThanX;

	public GroupFilter() {

	}

	public GroupFilter(HashMap<Integer, Boolean> questionSimilarOrDissimilar,
			HashMap<Integer, ArrayList<Integer>> considerLessThanOrGreaterThanX) {
		super();
		this.questionSimilarOrDissimilar = questionSimilarOrDissimilar;
		this.setConsiderLessThanOrGreaterThanX(considerLessThanOrGreaterThanX);

	}

	@Override
	public HashMap<Integer, Boolean> getquestionSimilarOrDissimilar() {
		return questionSimilarOrDissimilar;
	}

	@Override
	public void setquestionSimilarOrDissimilar(HashMap<Integer, Boolean> questionSimilarOrDissimilar) {

		this.questionSimilarOrDissimilar = questionSimilarOrDissimilar;
	}

	public HashMap<Integer, ArrayList<Integer>> getConsiderLessThanOrGreaterThanX() {
		return considerLessThanOrGreaterThanX;
	}

	public void setConsiderLessThanOrGreaterThanX(HashMap<Integer, ArrayList<Integer>> considerLessThanOrGreaterThanX) {
		this.considerLessThanOrGreaterThanX = considerLessThanOrGreaterThanX;
	}
}
