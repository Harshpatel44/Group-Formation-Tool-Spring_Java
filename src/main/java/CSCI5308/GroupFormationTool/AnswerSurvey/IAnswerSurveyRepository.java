package CSCI5308.GroupFormationTool.AnswerSurvey;

import java.util.List;

public interface IAnswerSurveyRepository {
    List<ISurveyQuestionOptionsModel> getSurveyQuestionsAndOptions(String courseId);

    void storeSurveyResponses(String bannerId, String courseId, int questionId, String answer);

    boolean checkSurveyAvailableForUser(String bannerId);

    List<ISurveyQuestionOptionsModel> getSurveyQuestionsForGroupFormula(String courseId);
}
