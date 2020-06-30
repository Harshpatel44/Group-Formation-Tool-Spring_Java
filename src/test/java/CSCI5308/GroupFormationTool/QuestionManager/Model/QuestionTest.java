package CSCI5308.GroupFormationTool.QuestionManager.Model;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Date;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class QuestionTest {
    @Test
    public void setQuestionTopicTest(){
        Question question=new Question();
        question.setQuestionTopic("QuetionTopic1");
        assertTrue(question.getQuestionTopic()=="QuetionTopic1");
    }

    @Test
    public void setQuestionDescriptionTest(){
        Question question=new Question();
        question.setQuestionDescription("QuestionDescription1");
        assertTrue(question.getQuestionDescription()=="QuestionDescription1");
    }

    @Test
    public void setDateTest(){
        Question question=new Question();
        question.setDate(Date.valueOf("2020-12-30"));
        assertTrue(question.getDate().equals(Date.valueOf("2020-12-30")));
    }

    @Test
    public void setQuestionId(){
        Question question=new Question();
        question.setQuestionId(2);
        assertTrue(question.getQuestionId()==2);
    }
}
