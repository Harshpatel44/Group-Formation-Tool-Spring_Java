package CSCI5308.GroupFormationTool.GroupFormmer;

import CSCI5308.GroupFormationTool.AnswerSurvey.ISurveyQuestionOptionsModel;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public interface IGroupFormmerService {

    HashMap<Integer, ArrayList<String>> FormGroups(String courseID, int teamSize);

    boolean saveGroupFormula(IGroupFilter groupFilter, String courseID);

    List<ISurveyQuestionOptionsModel> getSurveyQuestionsForGroupFormula(String courseId);

    IGroupFilter createGroupFilterHashMap(HttpServletRequest req);
}
