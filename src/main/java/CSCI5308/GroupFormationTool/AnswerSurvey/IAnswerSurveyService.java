package CSCI5308.GroupFormationTool.AnswerSurvey;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public interface IAnswerSurveyService {
    List<ISurveyQuestionOptionsModel> getSurveyQuestionsAndOptions(String courseId);
    boolean surveyResponses(HashMap<Integer,ArrayList<String>> surveyResponses,String bannerId,String courseID);
    public boolean checkSurveyAvailableForUser(String bannerId);

}
