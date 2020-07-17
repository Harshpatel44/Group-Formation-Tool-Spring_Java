package CSCI5308.GroupFormationTool.QuestionManager;


import java.util.List;

public interface IQuestionManagerRepository {
    List<IQuestion> getQuestions(String userId);

    void deleteQuestion(Integer questionId, String userId) throws Exception;

    List<IQuestion> getQuestionsByTopic(String userId);

    List<IQuestion> getQuestionsByDate(String userId) throws Exception;
}
