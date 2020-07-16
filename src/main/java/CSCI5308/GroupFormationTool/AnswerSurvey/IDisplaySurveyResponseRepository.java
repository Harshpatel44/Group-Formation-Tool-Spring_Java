package CSCI5308.GroupFormationTool.AnswerSurvey;

import java.util.HashMap;
import java.util.List;

public interface IDisplaySurveyResponseRepository {

    public List<String> getUsersWhoAnsweredSurvey(String courseId);
    public List<ISurveyQuestionOptionsModel> getSurveyResponse(List<String> users, String courseId);
    public HashMap<String, HashMap<Integer, ISurveyQuestionOptionsModel>> getSurveyResponse_2(String courseId);
}
