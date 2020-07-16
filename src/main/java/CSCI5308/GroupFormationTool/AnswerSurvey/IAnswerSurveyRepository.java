package CSCI5308.GroupFormationTool.AnswerSurvey;

import java.util.List;

public interface IAnswerSurveyRepository {
    public boolean isSurveyPublished(String courseId);
    public List<ISurveyQuestionOptionsModel> getSurveyQuestionsAndOptions(String courseId);
    void storeSurveyResponses(String bannerId,String courseId, int questionId, String answer);

}
