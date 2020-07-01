package CSCI5308.GroupFormationTool.QuestionManager.AccessControl;


import CSCI5308.GroupFormationTool.Course.AccessControl.IUserId;
import CSCI5308.GroupFormationTool.QuestionManager.Model.Question;

import java.util.List;

public interface IQuestionManagerRepository {
    public List<Question> getQuestions(String userId);
    void deleteQuestion(Integer questionId, String userId);
    public List<Question> getQuestionsByTopic(String userId);
    public List<Question> getQuestionsByDate(String userId);
}
