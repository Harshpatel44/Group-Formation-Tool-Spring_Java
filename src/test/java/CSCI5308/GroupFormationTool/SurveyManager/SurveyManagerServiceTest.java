package CSCI5308.GroupFormationTool.SurveyManager;

import CSCI5308.GroupFormationTool.QuestionManager.IQuestion;
import CSCI5308.GroupFormationTool.QuestionManager.Question;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
public class SurveyManagerServiceTest {
    public SurveyManagerService surveyManagerService;
    public SurveyManagerRepository surveyManagerRepository;

    @BeforeEach
    public void init() throws Exception {
        MockitoAnnotations.initMocks(this);
        surveyManagerRepository = mock(SurveyManagerRepository.class);
        surveyManagerService = new SurveyManagerService(surveyManagerRepository);
    }

    @Test
    public void AlreadyAddedSurveyQuestionsTest() throws Exception {
        List<IQuestion> iQuestionList = new ArrayList<IQuestion>();
        IQuestion iQuestion = new Question();
        iQuestion.setQuestionId(1);
        iQuestion.setQuestionTopic("QuestionTopic");
        iQuestion.setFlag(1);
        iQuestionList.add(iQuestion);
        when(surveyManagerRepository.AlreadyAddedSurveyQuestions()).thenReturn(iQuestionList);
        List<IQuestion> returnedList = surveyManagerRepository.AlreadyAddedSurveyQuestions();
        assertEquals(returnedList, iQuestionList);
    }

    @Test
    public void NotAddedSurveyQuestionsTest() throws Exception {
        List<IQuestion> iQuestionList = new ArrayList<IQuestion>();
        IQuestion iQuestion = new Question();
        iQuestion.setQuestionId(1);
        iQuestion.setQuestionTopic("QuestionTopic");
        iQuestion.setFlag(0);
        iQuestionList.add(iQuestion);
        when(surveyManagerRepository.NotAddedSurveyQuestions()).thenReturn(iQuestionList);
        List<IQuestion> returnedList = surveyManagerRepository.NotAddedSurveyQuestions();
        assertEquals(returnedList, iQuestionList);
    }

    @Test
    public void checkPublishTestIfPublished() throws Exception {
        boolean publish = true;
        when(surveyManagerRepository.checkPublish()).thenReturn(true);
        boolean returned = surveyManagerRepository.checkPublish();
        assertEquals(returned, publish);
    }

    @Test
    public void checkPublishTestIfNotPublished() throws Exception {
        boolean publish = false;
        when(surveyManagerRepository.checkPublish()).thenReturn(false);
        boolean returned = surveyManagerRepository.checkPublish();
        assertEquals(returned, publish);
    }

    @Test
    public void getSurveyQuestionsTest() throws Exception {
        surveyManagerService.getSurveyQuestions();
    }

    @Test
    public void AddQuestionToSurveyTest() throws Exception {
        Integer questionId = 1;
        surveyManagerService.AddQuestionToSurvey(questionId);
    }

    @Test
    public void RemoveQuestionFromSurveyTest() throws Exception {
        Integer questionId = 1;
        surveyManagerService.RemoveQuestionFromSurvey(questionId);
    }

    @Test
    public void PublishSurveyTest() throws Exception {
        surveyManagerService.PublishSurvey();
    }

    @Test
    public void UnpublishSurveyTest() throws Exception {
        surveyManagerService.UnpublishSurvey();
    }

}

