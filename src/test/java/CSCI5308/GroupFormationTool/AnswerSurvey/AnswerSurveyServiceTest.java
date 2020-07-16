package CSCI5308.GroupFormationTool.AnswerSurvey;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@SpringBootTest
class AnswerSurveyServiceTest {
    public AnswerSurveyRepository answerSurveyRepository;
    public AnswerSurveyService answerSurveyService;

    @BeforeEach
    public void init() throws Exception {
        MockitoAnnotations.initMocks(this);
        answerSurveyRepository = mock(AnswerSurveyRepository.class);
        answerSurveyService = new AnswerSurveyService(answerSurveyRepository);
    }

    @Test
    void getSurveyQuestionsAndOptionsTest() {
        List<ISurveyQuestionOptionsModel> questionsWithOptions = new ArrayList<>();
        ISurveyQuestionOptionsModel question = new SurveyQuestionOptionsModel();
        HashMap<Integer,String> options = new HashMap<>();
        options.put(1,"Question Description");
        question.setSurveyCourseId("CSCI1");
        question.setBannerId("B00835088");
        question.setSurveyQuestionDescription("Question description");
        question.setSurveyQuestionId(102);
        question.setSurveyQuestionsOptions(options);
        question.setSurveyQuestionType("mcqs");
        question.setSurveyQuestionTopic("topic 1");
        questionsWithOptions.add(question);

        when(answerSurveyRepository.getSurveyQuestionsAndOptions("CSCI1")).thenReturn(questionsWithOptions);
        List<ISurveyQuestionOptionsModel> returnedList = answerSurveyService.getSurveyQuestionsAndOptions("CSCI1");
        assertEquals(returnedList,questionsWithOptions);

    }

    @Test
    void surveyResponsesArrangeTest() {
        String normalAnswers = "question1Text,22";
        String[] list = normalAnswers.split(",");
        for(int i=0;i<list.length;i++){
            System.out.println(list[i]);
        }

//        String options = "104...multiple : option 1,104...multiple : option 3,107...multiple option 7 : 1,108...multiple option 8 : 1,108...multiple option 8 : 2,110...multiple option 10 : 1";
//        List<String> optionsList = Arrays.asList(options.split(","));
//        System.out.println(normalAnswers.split(","));
    }

    @Test
    void checkSurveyAvailableForUserTest(){
        String bannerId = "b00835088";
        when(answerSurveyRepository.checkSurveyAvailableForUser(bannerId)).thenReturn(false);
        assertFalse(answerSurveyService.checkSurveyAvailableForUser(bannerId));
    }
}