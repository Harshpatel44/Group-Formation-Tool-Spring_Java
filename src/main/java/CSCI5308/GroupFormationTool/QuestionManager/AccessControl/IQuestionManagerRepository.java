package CSCI5308.GroupFormationTool.QuestionManager.AccessControl;


import CSCI5308.GroupFormationTool.Course.Model.UserId;
import CSCI5308.GroupFormationTool.QuestionManager.Model.Question;

import java.util.List;

public interface IQuestionManagerRepository {
    public List<Question> getQuestions(UserId user);

    void deleteQuestion(Integer questionId, String userId);

   public List<Question> getQuestionsByTopic(UserId user);

  public List<Question> getQuestionsByDate(UserId user);
}
