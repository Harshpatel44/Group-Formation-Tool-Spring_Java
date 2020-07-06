package CSCI5308.GroupFormationTool.QuestionEditor;

import CSCI5308.GroupFormationTool.UserManager.CurrentUser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class QuestionEditorServiceTest {

    public QuestionEditorRepository questionEditorRepository;
    public QuestionEditorService questionEditorService;
    public CurrentUser currentUser;

    @BeforeEach
    void init() throws Exception {
        MockitoAnnotations.initMocks(this);
        questionEditorRepository = mock(QuestionEditorRepository.class);
        currentUser = mock(CurrentUser.class);
        questionEditorService = new QuestionEditorService(questionEditorRepository,currentUser);
    }

    @Test
    void saveQuestionServiceForTextAndNumeric1(){
        String questionText="What is your Name?";
        String questionTitle = "About yourself";
        String selectedQuestionType = "Multiple Choice, Choose Multiple";
        String userId="B00000001";
        when(CurrentUser.instance().getBannerId()).thenReturn("B00000001");
        when(questionEditorRepository.SaveTextAndNumericTypeQuestionRepo(questionText,questionTitle,selectedQuestionType,userId)).thenReturn(true);
        assertEquals(true,questionEditorService.saveQuestionServiceForTextAndNumeric(questionText,questionTitle,selectedQuestionType));
    }

    @Test
    void saveQuestionServiceForTextAndNumeric2() throws Exception {
        String questionText="What is your Name?";
        String questionTitle = "About yourself";
        String selectedQuestionType = "abcd";
        String userId="B00000001";
        when(CurrentUser.instance().getBannerId()).thenReturn("B00000001");
        when(questionEditorRepository.SaveTextAndNumericTypeQuestionRepo(questionText,questionTitle,selectedQuestionType,userId)).thenReturn(false);
        assertEquals(false,questionEditorService.saveQuestionServiceForTextAndNumeric(questionText,questionTitle,selectedQuestionType));
    }

    @Test()
    void saveQuestionServiceForTextAndNumeric3() throws Exception {
        String questionText="What is your Name?";
        String questionTitle = "About yourself";
        String selectedQuestionType = "abcd";
        String userId="B00000001";
        when(CurrentUser.instance().getBannerId()).thenReturn("B00000001");
        when(questionEditorRepository.SaveTextAndNumericTypeQuestionRepo(questionText,questionTitle,selectedQuestionType,userId)).thenThrow(SQLException.class);
        assertEquals(false,questionEditorService.saveQuestionServiceForTextAndNumeric(questionText,questionTitle,selectedQuestionType));
    }

    @Test
    void saveQuestionForMultipleChoiceService1() throws Exception {
        String questionText="What is your Name?";
        String questionTitle = "About yourself";
        String selectedQuestionType = "abcd";
        String userId="B00000001";
        String options="Delhi,Kolkata,Ahmedabad,Vadodara";
        String ranks = "2,1,4,3";
        when(CurrentUser.instance().getBannerId()).thenReturn("B00000001");
        when(questionEditorRepository.SaveMcqTypeQuestionRepo(questionText,questionTitle,selectedQuestionType,options,ranks,userId)).thenReturn(true);
        assertEquals(true,questionEditorService.saveQuestionForMultipleChoiceService(questionText,questionTitle,selectedQuestionType,options,ranks));
    }
    @Test
    void saveQuestionForMultipleChoiceService2() throws Exception {
        String questionText="What is your Name?";
        String questionTitle = "About yourself";
        String selectedQuestionType = "abcd";
        String userId="notAUserId";
        String options="SimpleText";
        String ranks = "2";
        when(CurrentUser.instance().getBannerId()).thenReturn("notAUserId");
        when(questionEditorRepository.SaveMcqTypeQuestionRepo(questionText,questionTitle,selectedQuestionType,options,ranks,userId)).thenReturn(false);
        assertEquals(false,questionEditorService.saveQuestionForMultipleChoiceService(questionText,questionTitle,selectedQuestionType,options,ranks));
    }
}