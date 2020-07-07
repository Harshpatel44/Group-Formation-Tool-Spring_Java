package CSCI5308.GroupFormationTool.QuestionManager;


import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class QuestionManagerRepositoryMock {

    public List<IQuestion> getQuestionsTest(String bannerID) {
        List<IQuestion> questionList = new ArrayList<IQuestion>();
        if(bannerID.equals("B00123456"))
        {
            IQuestion iQuestion = new Question();
            iQuestion.setQuestionId(1);
            iQuestion.setQuestionDescription("QuestionDescription");
            iQuestion.setQuestionTopic("QuestionTopic");
            iQuestion.setDate(Date.valueOf("2020-12-30"));
            questionList.add(iQuestion);

        }
        return questionList;
    }
}

