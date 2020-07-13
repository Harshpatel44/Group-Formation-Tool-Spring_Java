package CSCI5308.GroupFormationTool.AnswerSurvey;

import CSCI5308.GroupFormationTool.QuestionManager.IQuestion;

import java.util.List;

public interface IAnswerSurveyRepository {
    public boolean SurveyAvailableForUser(String bannerId);
    public boolean IsSurveyPublished(String courseId);
    public List<ISurveyQuestionOptionsModel> getSurveyQuestionsAndOptions(String courseId);
}
