package CSCI5308.GroupFormationTool.QuestionManager;


import java.util.List;

public interface IQuestionManagerRepository {
    public List<IQuestion> getQuestions(String userId);
    void deleteQuestion(Integer questionId, String userId) throws Exception;
    public List<IQuestion> getQuestionsByTopic(String userId);
    public List<IQuestion> getQuestionsByDate(String userId) throws Exception;
}
