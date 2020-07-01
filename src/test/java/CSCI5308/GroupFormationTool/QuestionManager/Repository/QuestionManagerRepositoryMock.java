package CSCI5308.GroupFormationTool.QuestionManager.Repository;


import CSCI5308.GroupFormationTool.Course.AccessControl.IUserId;
import CSCI5308.GroupFormationTool.QuestionManager.Question;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class QuestionManagerRepositoryMock {
    public List<Question> getQuestionsTest(IUserId user) {
        List<Question> questionList = new ArrayList<Question>();
        if(user.getUserId().equals("B00123456"))
        {
            Question question = new Question();
            question.setQuestionId(1);
            question.setQuestionDescription("QuestionDescription");
            question.setQuestionTopic("QuestionTopic");
            question.setDate(Date.valueOf("2020-12-30"));
            questionList.add(question);

        }
        return questionList;
    }
}

