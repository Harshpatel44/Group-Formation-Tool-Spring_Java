package CSCI5308.GroupFormationTool.QuestionEditor.Service;

import CSCI5308.GroupFormationTool.QuestionEditor.Repository.QuestionEditorRepository;
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
        when(questionEditorRepository.SaveQuestionRepo(questionText,questionTitle,selectedQuestionType,userId)).thenReturn(true);
        assertEquals("Question submitted successfully",questionEditorService.SaveQuestionServiceForTextAndNumeric(questionText,questionTitle,selectedQuestionType,userId));
    }

    @Test
    void saveQuestionServiceForTextAndNumeric2() throws Exception {
        String questionText="What is your Name?";
        String questionTitle = "About yourself";
        String selectedQuestionType = "abcd";
        String userId="B00000001";
        when(questionEditorRepository.SaveQuestionRepo(questionText,questionTitle,selectedQuestionType,userId)).thenReturn(false);
        assertEquals("Question did not submit successfully",questionEditorService.SaveQuestionServiceForTextAndNumeric(questionText,questionTitle,selectedQuestionType,userId));
    }
}