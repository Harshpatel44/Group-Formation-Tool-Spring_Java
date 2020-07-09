package CSCI5308.GroupFormationTool.SurveyManager;

import CSCI5308.GroupFormationTool.QuestionManager.IQuestion;

import java.util.List;

public interface ISurveyManagerRepository {
    public void getSurveyQuestions(String CourseId) throws Exception;
    public List<IQuestion> AlreadyAddedSurveyQuestions() throws Exception;
    public List<IQuestion> NotAddedSurveyQuestions() throws Exception;

    void AddQuestionToSurvey(Integer questionId);
    void RemoveQuestionFromSurvey(Integer questionId);
    void PublishSurvey();

    boolean checkPublish();
    void UnpublishSurvey();
}
