package CSCI5308.GroupFormationTool.QuestionManager.AccessControl;


import CSCI5308.GroupFormationTool.Course.Model.UserId;
import CSCI5308.GroupFormationTool.QuestionManager.Model.Question;

import java.util.List;
public interface IQuestionManagerService {
    public List<Question> getQuestions(UserId user, String sortType);
    void deleteQuestion(Integer questionId, String userId);

}
