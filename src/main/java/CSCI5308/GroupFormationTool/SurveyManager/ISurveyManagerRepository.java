package CSCI5308.GroupFormationTool.SurveyManager;

import CSCI5308.GroupFormationTool.QuestionManager.IQuestion;

import java.util.List;

public interface ISurveyManagerRepository {
    void getSurveyQuestions() throws Exception;

    List<IQuestion> AlreadyAddedSurveyQuestions() throws Exception;

    List<IQuestion> NotAddedSurveyQuestions() throws Exception;

    void AddQuestionToSurvey(Integer questionId);

    void RemoveQuestionFromSurvey(Integer questionId);

    void PublishSurvey();

    boolean checkPublish();

    void UnpublishSurvey();
}
