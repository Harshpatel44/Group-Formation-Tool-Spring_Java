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
    public boolean storeSurveyResponses(HashMap<Integer,ArrayList<String>> surveyResponses) {
        // Start your code from here. SurveyResponses variable contains all the responses.
        return false;
    }
}
