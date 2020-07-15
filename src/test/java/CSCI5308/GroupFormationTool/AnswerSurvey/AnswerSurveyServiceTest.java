package CSCI5308.GroupFormationTool.AnswerSurvey;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AnswerSurveyServiceTest {

    @Test
    void getSurveyQuestionsAndOptions() {
    }

    @Test
    void surveyResponsesArrange() {
        String normalAnswers = "question1Text,22";
        String[] list = normalAnswers.split(",");
        for(int i=0;i<list.length;i++){
            System.out.println(list[i]);
        }

//        String options = "104...multiple : option 1,104...multiple : option 3,107...multiple option 7 : 1,108...multiple option 8 : 1,108...multiple option 8 : 2,110...multiple option 10 : 1";
//        List<String> optionsList = Arrays.asList(options.split(","));
//        System.out.println(normalAnswers.split(","));
    }
}