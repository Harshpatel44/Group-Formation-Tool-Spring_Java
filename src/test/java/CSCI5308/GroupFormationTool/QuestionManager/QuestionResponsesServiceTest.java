package CSCI5308.GroupFormationTool.QuestionManager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class QuestionResponsesServiceTest {

    public QuestionResponsesRepo questionResponsesRepo;
    public QuestionResponsesService questionResponsesService;

    @BeforeEach
    public void init() throws Exception {
        MockitoAnnotations.initMocks(this);
        questionResponsesRepo = mock(QuestionResponsesRepo.class);
        questionResponsesService = new QuestionResponsesService(questionResponsesRepo);
    }

    @Test
    void checkIfResponsesPresentService1() throws Exception {
        Integer questionId = 1;
        ArrayList<String> responses = new ArrayList<>();
        when(questionResponsesRepo.getResponsesOfQuestionIdRepo(questionId)).thenReturn(responses);
        assertEquals(false, questionResponsesService.checkIfResponsesPresentService(questionId));
    }

    @Test
    void checkIfResponsesPresentService2() throws Exception {
        Integer questionId = 1;
        ArrayList<String> responses = new ArrayList<>();
        responses.add("response1");
        when(questionResponsesRepo.getResponsesOfQuestionIdRepo(questionId)).thenReturn(responses);
        assertEquals(true, questionResponsesService.checkIfResponsesPresentService(questionId));
    }
}