package CSCI5308.GroupFormationTool.QuestionEditor.Service;

import CSCI5308.GroupFormationTool.QuestionEditor.Repository.QuestionEditorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;

import java.util.HashMap;

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
        assertEquals("Question submitted successfully",questionEditorService.SaveQuestionServiceForTextAndNumeric(questionText,questionTitle,selectedQuestionType,userId));
    }

    @Test
    void saveQuestionServiceForTextAndNumeric2() throws Exception {
        String questionText="What is your Name?";
        String questionTitle = "About yourself";
        String selectedQuestionType = "abcd";
        String userId="B00000001";
        when(questionEditorRepository.SaveTextAndNumericTypeQuestionRepo(questionText,questionTitle,selectedQuestionType,userId)).thenReturn(false);
        assertEquals("Question did not submit successfully",questionEditorService.SaveQuestionServiceForTextAndNumeric(questionText,questionTitle,selectedQuestionType,userId));
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
        assertEquals("Question submitted successfully",questionEditorService.saveQuestionForMultipleChoiceService(questionText,questionTitle,selectedQuestionType,options,ranks,userId));
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
        assertEquals("Question did not submit successfully",questionEditorService.saveQuestionForMultipleChoiceService(questionText,questionTitle,selectedQuestionType,options,ranks,userId));
    }

    @Test
    void arrangeOptionsBasedOnRank() {
        String optionText="Delhi,Kolkata,Ahmedabad,Vadodara";
        String rankText = "2,1,4,3";

        String[] optionList = optionText.split(",");
        String[] rankList = rankText.split(",");

        HashMap<Integer, String> map = new HashMap<Integer, String>();

        for(int i=0;i<optionList.length;i++){
            map.put(Integer.valueOf(rankList[i]),optionList[i]);
        }
        assertEquals(map,questionEditorService.arrangeOptionsBasedOnRank(optionText,rankText));
    }
}