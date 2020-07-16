package CSCI5308.GroupFormationTool.AnswerSurvey;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AnswerSurveyRepositoryMock {

    public List<ISurveyQuestionOptionsModel> getSurveyQuestionsAndOptions(String courseId) {
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

        return questionsWithOptions;
    }
}
