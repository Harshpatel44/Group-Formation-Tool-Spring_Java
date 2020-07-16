package CSCI5308.GroupFormationTool.AnswerSurvey;

import java.util.List;

public interface IDisplaySurveyResponseService {
    List<String> getUsersWhoAnsweredSurvey(String courseId);

    List<ISurveyQuestionOptionsModel> getSurveyResponse(List<String> users, String courseId);
}
