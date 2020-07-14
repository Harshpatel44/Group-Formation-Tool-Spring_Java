package CSCI5308.GroupFormationTool.AnswerSurvey;

import CSCI5308.GroupFormationTool.AnswerSurvey.ISurveyQuestionOptionsModel;

import java.util.List;

public interface IDisplaySurveyResponseRepository {

    public List<String> getUsersWhoAnsweredSurvey(String courseId);
    public List<ISurveyQuestionOptionsModel> getSurveyResponse(List<String> users, String courseId);
}
