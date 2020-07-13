package CSCI5308.GroupFormationTool.AnswerSurvey;

import CSCI5308.GroupFormationTool.Injector;

import java.util.List;

public class DisplaySurveyResponseService implements IDisplaySurveyResponseService {
    public DisplaySurveyResponseService(){}

    public DisplaySurveyResponseService(DisplaySurveyResponseRepository displaySurveyResponseRepository) throws Exception{
        Injector.instance().setDisplaySurveyResponseRepository(displaySurveyResponseRepository);
    }

    @Override
    public List<String> getUsersWhoAnsweredSurvey(String courseId) {
        return Injector.instance().getDisplaySurveyResponseRepository().getUsersWhoAnsweredSurvey(courseId);
    }

    @Override
    public List<ISurveyQuestionOptionsModel> getSurveyResponse(List<String> users, String courseId) {
        return Injector.instance().getDisplaySurveyResponseRepository().getSurveyResponse(users,courseId);
    }
}
