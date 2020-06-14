package CSCI5308.GroupFormationTool.QuestionManager.Repository;


import CSCI5308.GroupFormationTool.Course.Model.UserId;
import CSCI5308.GroupFormationTool.QuestionManager.Model.Question;

import java.util.ArrayList;
import java.util.List;

public class QuestionManagerRepositoryMock {
    public List<Question> getQuestionsTest(UserId user) {
        List<Question> questionList = new ArrayList<Question>();
        if(user.getUserId().equals("B00123456"))
        {
            Question question = new Question();
            question.setQuestionId("1");
            question.setQuestionDescription("QuestionDescription");
            question.setQuestionTopic("QuestionTopic");
            question.setDate("2020-12-30");
            questionList.add(question);

        }
        return questionList;
    }
}
