package CSCI5308.GroupFormationTool.AnswerSurvey;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DisplaySurveyResponseRepositoryMock implements IDisplaySurveyResponseRepository{
    @Override
    public List<String> getUsersWhoAnsweredSurvey(String courseId) {
        List<String> users = new ArrayList<>();
        users.add("B0835088");
        users.add("B00100100");
        users.add("B00100101");
        return users;
    }

    @Override
    public List<ISurveyQuestionOptionsModel> getSurveyResponse(List<String> users, String courseId) {
        List<ISurveyQuestionOptionsModel> studentResponse = new ArrayList<ISurveyQuestionOptionsModel>();
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
        return studentResponse;
    }

    @Override
    public HashMap<String, HashMap<Integer, ISurveyQuestionOptionsModel>> getSurveyResponse_2(String courseId){
        HashMap<String, HashMap<Integer, ISurveyQuestionOptionsModel>> hashMapHashMap = new HashMap<>();
        return hashMapHashMap;
    }
}
