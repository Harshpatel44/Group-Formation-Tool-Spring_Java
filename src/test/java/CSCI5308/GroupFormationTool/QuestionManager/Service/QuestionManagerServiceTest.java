package CSCI5308.GroupFormationTool.QuestionManager.Service;

import CSCI5308.GroupFormationTool.Course.Model.UserId;
import CSCI5308.GroupFormationTool.QuestionManager.Model.Question;
import CSCI5308.GroupFormationTool.QuestionManager.Repository.QuestionManagerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
public class QuestionManagerServiceTest {
    public QuestionManagerService questionManagerService;
    public QuestionManagerRepository questionManagerRepository;
    @BeforeEach
    public void init(){
        MockitoAnnotations.initMocks(this);
        questionManagerRepository = mock(QuestionManagerRepository.class);
        questionManagerService = new QuestionManagerService(questionManagerRepository);
    }

    @Test
    public void getquestionsTestByTopic(){
        UserId user = new UserId();
        List<Question> questionList = new ArrayList<Question>();
        Question question = new Question();
        question.setQuestionId(1);
        question.setQuestionDescription("QuestionDescription");
        question.setQuestionTopic("QuestionTopic");
        question.setDate(Date.valueOf("2020-12-30"));
        questionList.add(question);
        user.setUserId("B00123456");
        String sortType = "sortByTopic";
        when(questionManagerRepository.getQuestionsByTopic(user)).thenReturn(questionList);
        List<Question> returnedList = questionManagerService.getQuestions(user,sortType);
        assertEquals(returnedList,questionList);
    }
    @Test
    public void getquestionsTestByDate(){
        UserId user = new UserId();
        List<Question> questionList = new ArrayList<Question>();
        Question question = new Question();
        question.setQuestionId(1);
        question.setQuestionDescription("QuestionDescription");
        question.setQuestionTopic("QuestionTopic");
        question.setDate(Date.valueOf("2020-12-30"));
        questionList.add(question);
        user.setUserId("B00123456");
        String sortType = "sortByDate";
        when(questionManagerRepository.getQuestionsByDate(user)).thenReturn(questionList);
        List<Question> returnedList = questionManagerService.getQuestions(user,sortType);
        assertEquals(returnedList,questionList);
    }
    @Test
    public void getquestionsTest(){
        UserId user = new UserId();
        List<Question> questionList = new ArrayList<Question>();
        Question question = new Question();
        question.setQuestionId(1);
        question.setQuestionDescription("QuestionDescription");
        question.setQuestionTopic("QuestionTopic");
        question.setDate(Date.valueOf("2020-12-30"));
        questionList.add(question);
        user.setUserId("B00123456");
        String sortType = "unsorted";
        when(questionManagerRepository.getQuestions(user)).thenReturn(questionList);
        List<Question> returnedList = questionManagerService.getQuestions(user,sortType);
        assertEquals(returnedList,questionList);
    }

    @Test
    public void deleteQuestionTest(){
   String userId ="B00100100";
   Integer questionId=1;
   questionManagerService.deleteQuestion(questionId,userId);
    }
}
