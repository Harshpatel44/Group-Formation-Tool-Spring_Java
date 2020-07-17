package CSCI5308.GroupFormationTool.GroupFormmer;

import java.util.ArrayList;
import java.util.HashMap;

public interface IGroupFilter {
    HashMap<Integer, Boolean> getquestionSimilarOrDissimilar();

    void setquestionSimilarOrDissimilar(HashMap<Integer, Boolean> questionSimilarOrDissimilar);

    HashMap<Integer, ArrayList<Integer>> getConsiderLessThanOrGreaterThanX();

    void setConsiderLessThanOrGreaterThanX(HashMap<Integer, ArrayList<Integer>> considerLessThanOrGreaterThanX);

}
