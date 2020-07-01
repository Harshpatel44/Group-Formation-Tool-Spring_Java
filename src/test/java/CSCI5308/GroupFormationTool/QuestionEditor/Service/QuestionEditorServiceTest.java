package CSCI5308.GroupFormationTool.QuestionEditor.Service;

import CSCI5308.GroupFormationTool.QuestionEditor.QuestionEditorRepository;
import CSCI5308.GroupFormationTool.QuestionEditor.QuestionEditorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class QuestionEditorServiceTest {

    public QuestionEditorRepository questionEditorRepository;
    public QuestionEditorService questionEditorService;


    @BeforeEach
    void init() throws Exception {
        MockitoAnnotations.initMocks(this);
        questionEditorRepository = mock(QuestionEditorRepository.class);
        questionEditorService = new QuestionEditorService(questionEditorRepository);
    }

    @Test
    void saveQuestionServiceForTextAndNumeric1() throws Exception {
        String questionText="What is your Name?";
        String questionTitle = "About yourself";
        String selectedQuestionType = "Multiple Choice, Choose Multiple";
        String userId="B00000001";
        when(questionEditorRepository.SaveTextAndNumericTypeQuestionRepo(questionText,questionTitle,selectedQuestionType,userId)).thenReturn(true);
        assertEquals(true,questionEditorService.SaveQuestionServiceForTextAndNumeric(questionText,questionTitle,selectedQuestionType,userId));
    }

    @Test
    void saveQuestionServiceForTextAndNumeric2() throws Exception {
        String questionText="What is your Name?";
        String questionTitle = "About yourself";
        String selectedQuestionType = "abcd";
        String userId="B00000001";
        when(questionEditorRepository.SaveTextAndNumericTypeQuestionRepo(questionText,questionTitle,selectedQuestionType,userId)).thenReturn(false);
        assertEquals(false,questionEditorService.SaveQuestionServiceForTextAndNumeric(questionText,questionTitle,selectedQuestionType,userId));
    }

    @Test
    void saveQuestionForMultipleChoiceService1() throws Exception {
        String questionText="What is your Name?";
        String questionTitle = "About yourself";
        String selectedQuestionType = "abcd";
        String userId="B00000001";
        String options="Delhi,Kolkata,Ahmedabad,Vadodara";
        String ranks = "2,1,4,3";
        when(questionEditorRepository.SaveMcqTypeQuestionRepo(questionText,questionTitle,selectedQuestionType,options,ranks,userId)).thenReturn(true);
        assertEquals(true,questionEditorService.saveQuestionForMultipleChoiceService(questionText,questionTitle,selectedQuestionType,options,ranks,userId));
    }
    @Test
    void saveQuestionForMultipleChoiceService2() throws Exception {
        String questionText="What is your Name?";
        String questionTitle = "About yourself";
        String selectedQuestionType = "abcd";
        String userId="notAUserId";
        String options="SimpleText";
        String ranks = "2";
        when(questionEditorRepository.SaveMcqTypeQuestionRepo(questionText,questionTitle,selectedQuestionType,options,ranks,userId)).thenReturn(false);
        assertEquals(false,questionEditorService.saveQuestionForMultipleChoiceService(questionText,questionTitle,selectedQuestionType,options,ranks,userId));
    }
}