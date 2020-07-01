package CSCI5308.GroupFormationTool.QuestionManager.Service;

import CSCI5308.GroupFormationTool.QuestionManager.IQuestion;
import CSCI5308.GroupFormationTool.QuestionManager.Question;
import CSCI5308.GroupFormationTool.QuestionManager.QuestionManagerRepository;
import CSCI5308.GroupFormationTool.QuestionManager.QuestionManagerService;
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
    public void init() throws Exception {
        MockitoAnnotations.initMocks(this);
        questionManagerRepository = mock(QuestionManagerRepository.class);
        questionManagerService = new QuestionManagerService(questionManagerRepository);
    }

    @Test
    public void getquestionsTestByTopic() throws Exception {
        String userId;
        List<IQuestion> iQuestionList = new ArrayList<IQuestion>();
        IQuestion iQuestion = new Question();
        iQuestion.setQuestionId(1);
        iQuestion.setQuestionDescription("QuestionDescription");
        iQuestion.setQuestionTopic("QuestionTopic");
        iQuestion.setDate(Date.valueOf("2020-12-30"));
        iQuestionList.add(iQuestion);
        userId = "B00123456";
        String sortType = "sortByTopic";
        when(questionManagerRepository.getQuestionsByTopic(userId)).thenReturn(iQuestionList);
        List<IQuestion> returnedList = questionManagerService.getQuestions(userId,sortType);
        assertEquals(returnedList, iQuestionList);
    }
    @Test
    public void getquestionsTestByDate() throws Exception {
        String userId;
        List<IQuestion> iQuestionList = new ArrayList<IQuestion>();
        IQuestion iQuestion = new Question();
        iQuestion.setQuestionId(1);
        iQuestion.setQuestionDescription("QuestionDescription");
        iQuestion.setQuestionTopic("QuestionTopic");
        iQuestion.setDate(Date.valueOf("2020-12-30"));
        iQuestionList.add(iQuestion);
        userId="B00123456";
        String sortType = "sortByDate";
        when(questionManagerRepository.getQuestionsByDate(userId)).thenReturn(iQuestionList);
        List<IQuestion> returnedList = questionManagerService.getQuestions(userId,sortType);
        assertEquals(returnedList, iQuestionList);
    }
    @Test
    public void getquestionsTest() throws Exception {
        String userId;
        List<IQuestion> iQuestionList = new ArrayList<IQuestion>();
        IQuestion iQuestion = new Question();
        iQuestion.setQuestionId(1);
        iQuestion.setQuestionDescription("QuestionDescription");
        iQuestion.setQuestionTopic("QuestionTopic");
        iQuestion.setDate(Date.valueOf("2020-12-30"));
        iQuestionList.add(iQuestion);
        userId="B00123456";
        String sortType = "unsorted";
        when(questionManagerRepository.getQuestions(userId)).thenReturn(iQuestionList);
        List<IQuestion> returnedList = questionManagerService.getQuestions(userId,sortType);
        assertEquals(returnedList, iQuestionList);
    }

    @Test
    public void deleteQuestionTest() throws Exception {
   String userId ="B00100100";
   Integer questionId=1;
   questionManagerService.deleteQuestion(questionId,userId);
    }
}
