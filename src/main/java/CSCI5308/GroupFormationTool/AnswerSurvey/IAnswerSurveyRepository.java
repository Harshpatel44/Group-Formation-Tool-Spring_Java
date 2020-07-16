package CSCI5308.GroupFormationTool.AnswerSurvey;

import java.util.List;

public interface IAnswerSurveyRepository {
    public List<ISurveyQuestionOptionsModel> getSurveyQuestionsAndOptions(String courseId);
    void storeSurveyResponses(String bannerId,String courseId, int questionId, String answer);
    public boolean checkSurveyAvailableForUser(String bannerId);
}
