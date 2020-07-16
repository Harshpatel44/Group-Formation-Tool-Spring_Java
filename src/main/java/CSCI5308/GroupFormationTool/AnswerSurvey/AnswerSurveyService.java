package CSCI5308.GroupFormationTool.AnswerSurvey;
import java.util.*;

public class AnswerSurveyService implements IAnswerSurveyService {
    public AnswerSurveyService(){}
    public AnswerSurveyService(AnswerSurveyRepository answerSurveyRepository) throws Exception{
        IAnswerSurveyAbstractFactory.instance().setAnswerSurveyRepository(answerSurveyRepository);
    }

    @Override
    public List<ISurveyQuestionOptionsModel> getSurveyQuestionsAndOptions(String courseId) {
        return IAnswerSurveyAbstractFactory.instance().getAnswerSurveyRepository().getSurveyQuestionsAndOptions(courseId);
    }

    @Override
    public boolean surveyResponses(HashMap<Integer,ArrayList<String>> surveyResponses, String bannerId, String courseId) {
        for(Map.Entry mapElements : surveyResponses.entrySet()){
            Integer questionId = (Integer)mapElements.getKey();
            ArrayList<String> value = (ArrayList<String>) mapElements.getValue();
           for(String answer : value){
               IAnswerSurveyAbstractFactory.instance().getAnswerSurveyRepository().storeSurveyResponses(bannerId,courseId,questionId,answer);
           }
        }
        return true;
    }

    @Override
    public boolean checkSurveyAvailableForUser(String bannerId) {
        return IAnswerSurveyAbstractFactory.instance().getAnswerSurveyRepository().checkSurveyAvailableForUser(bannerId);
    }
}
