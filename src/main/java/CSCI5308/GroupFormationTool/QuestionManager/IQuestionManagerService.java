package CSCI5308.GroupFormationTool.QuestionManager;


import java.util.List;
public interface IQuestionManagerService {
    public List<Question> getQuestions(String userId, String sortType) throws Exception;

    void deleteQuestion(Integer questionId, String userId) throws Exception;


}
