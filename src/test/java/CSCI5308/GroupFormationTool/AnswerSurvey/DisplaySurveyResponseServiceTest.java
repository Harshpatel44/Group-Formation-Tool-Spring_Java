package CSCI5308.GroupFormationTool.AnswerSurvey;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class DisplaySurveyResponseServiceTest {
    public IDisplaySurveyResponseRepository displaySurveyResponseRepository;
    public IDisplaySurveyResponseService displaySurveyResponseService;

    @BeforeEach
    public void init() throws Exception {
        MockitoAnnotations.initMocks(this);
        displaySurveyResponseRepository = mock(DisplaySurveyResponseRepository.class);
        displaySurveyResponseService = new DisplaySurveyResponseService();
        DisplaySurveyResponseAbstractFactory.instance().setDisplaySurveyResponseRepository(displaySurveyResponseRepository);
    }

    @Test
    void getUsersWhoAnsweredSurvey() {
        List<String> users = new ArrayList<>();
        users.add("B0835088");
        users.add("B00100100");
        users.add("B00100101");

        when(displaySurveyResponseRepository.getUsersWhoAnsweredSurvey("CSCI1")).thenReturn(users);
        List<String> returnedList = displaySurveyResponseService.getUsersWhoAnsweredSurvey("CSCI1");
        assertEquals(returnedList,users);
    }

    @Test
    void getSurveyResponse() {
        List<ISurveyQuestionOptionsModel> studentResponse = new ArrayList<ISurveyQuestionOptionsModel>();
        List<String> users = new ArrayList<>();
        users.add("b00835088");
        users.add("b00100100");
        List<String> userAnswers = new ArrayList<String>();
        userAnswers.add("option 1");
        userAnswers.add("option 2");
        ISurveyQuestionOptionsModel questionAnswer = new SurveyQuestionOptionsModel();
        questionAnswer.setSurveyQuestionDescription("Question Description");
        questionAnswer.setSurveyQuestionId(104);
        questionAnswer.setSurveyQuestionType("mcqm");
        questionAnswer.setSurveyQuestionTopic("Question topic");
        questionAnswer.setSurveyAnswers(userAnswers);
        questionAnswer.setBannerId("b00835088");
        studentResponse.add(questionAnswer);

        when(displaySurveyResponseRepository.getSurveyResponse(users,"CSCI1")).thenReturn(studentResponse);
        List<ISurveyQuestionOptionsModel> returnedList = displaySurveyResponseService.getSurveyResponse(users,"CSCI1");
        assertEquals(returnedList,studentResponse);
    }
}