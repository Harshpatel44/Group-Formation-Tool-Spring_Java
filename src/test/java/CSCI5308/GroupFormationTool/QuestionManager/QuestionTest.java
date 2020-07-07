package CSCI5308.GroupFormationTool.QuestionManager;

import CSCI5308.GroupFormationTool.QuestionManager.IQuestion;
import CSCI5308.GroupFormationTool.QuestionManager.Question;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Date;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class QuestionTest {
    @Test
    public void setQuestionTopicTest(){
        IQuestion iQuestion =new Question();
        iQuestion.setQuestionTopic("QuetionTopic1");
        assertTrue(iQuestion.getQuestionTopic()=="QuetionTopic1");
    }

    @Test
    public void setQuestionDescriptionTest(){
        IQuestion iQuestion =new Question();
        iQuestion.setQuestionDescription("QuestionDescription1");
        assertTrue(iQuestion.getQuestionDescription()=="QuestionDescription1");
    }

    @Test
    public void setDateTest(){
        IQuestion iQuestion =new Question();
        iQuestion.setDate(Date.valueOf("2020-12-30"));
        assertTrue(iQuestion.getDate().equals(Date.valueOf("2020-12-30")));
    }

    @Test
    public void setQuestionId(){
        IQuestion iQuestion =new Question();
        iQuestion.setQuestionId(2);
        assertTrue(iQuestion.getQuestionId()==2);
    }
}
