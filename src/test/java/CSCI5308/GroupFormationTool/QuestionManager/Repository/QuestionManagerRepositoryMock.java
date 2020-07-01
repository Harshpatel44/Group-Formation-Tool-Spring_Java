package CSCI5308.GroupFormationTool.QuestionManager.Repository;


import CSCI5308.GroupFormationTool.Course.IUserRole;
import CSCI5308.GroupFormationTool.QuestionManager.IQuestion;
import CSCI5308.GroupFormationTool.QuestionManager.Question;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class QuestionManagerRepositoryMock {
    public List<Question> getQuestionsTest(IUserRole user) {
        List<Question> questionList = new ArrayList<IQuestion>();
        if(user.getUserId().equals("B00123456"))
        {
            IQuestion iQuestion = new Question();
            iQuestion.setQuestionId(1);
            iQuestion.setQuestionDescription("QuestionDescription");
            iQuestion.setQuestionTopic("QuestionTopic");
            iQuestion.setDate(Date.valueOf("2020-12-30"));
            iQuestionList.add(iQuestion);

        }
        return iQuestionList;
    }
}

