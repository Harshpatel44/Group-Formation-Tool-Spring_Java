package CSCI5308.GroupFormationTool.AnswerSurvey;

import CSCI5308.GroupFormationTool.Injector;

import java.util.*;

public class AnswerSurveyService implements IAnswerSurveyService {
    public AnswerSurveyService(){}
    public AnswerSurveyService(AnswerSurveyRepository answerSurveyRepository) throws Exception{
        Injector.instance().setAnswerSurveyRepository(answerSurveyRepository);
    }

    @Override
    public List<ISurveyQuestionOptionsModel> getSurveyQuestionsAndOptions(String courseId) {
        return Injector.instance().getAnswerSurveyRepository().getSurveyQuestionsAndOptions(courseId);
    }

    @Override
    public boolean surveyResponses(HashMap<Integer,ArrayList<String>> surveyResponses, String bannerId, String courseId) {
        for(Map.Entry mapElements : surveyResponses.entrySet()){
            Integer questionId = (Integer)mapElements.getKey();
            ArrayList<String> value = (ArrayList<String>) mapElements.getValue();
           for(String answer : value){
               Injector.instance().getAnswerSurveyRepository().storeSurveyResponses(bannerId,courseId,questionId,answer);
           }
        }
        return true;
    }
}
