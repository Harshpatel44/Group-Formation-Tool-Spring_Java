package CSCI5308.GroupFormationTool.AnswerSurvey;

import java.util.HashMap;
import java.util.List;

public interface IDisplaySurveyResponseRepository {

    List<String> getUsersWhoAnsweredSurvey(String courseId);

    List<ISurveyQuestionOptionsModel> getSurveyResponse(List<String> users, String courseId);

    HashMap<String, HashMap<Integer, ISurveyQuestionOptionsModel>> getSurveyResponse_2(String courseId);
}
