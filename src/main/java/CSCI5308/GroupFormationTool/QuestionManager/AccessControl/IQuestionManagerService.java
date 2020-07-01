package CSCI5308.GroupFormationTool.QuestionManager.AccessControl;


import CSCI5308.GroupFormationTool.Course.AccessControl.IUserId;
import CSCI5308.GroupFormationTool.QuestionManager.Model.Question;

import java.util.List;
public interface IQuestionManagerService {
    public List<Question> getQuestions(String userId, String sortType);

    void deleteQuestion(Integer questionId, String userId);


}
