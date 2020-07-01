package CSCI5308.GroupFormationTool.QuestionManager;


import java.util.List;

public interface IQuestionManagerRepository {
    public List<Question> getQuestions(String userId);
    void deleteQuestion(Integer questionId, String userId) throws Exception;
    public List<Question> getQuestionsByTopic(String userId);
    public List<Question> getQuestionsByDate(String userId) throws Exception;
}
