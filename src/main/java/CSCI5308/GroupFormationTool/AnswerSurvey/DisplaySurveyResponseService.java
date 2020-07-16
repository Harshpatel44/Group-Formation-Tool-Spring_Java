package CSCI5308.GroupFormationTool.AnswerSurvey;


import java.util.List;

public class DisplaySurveyResponseService implements IDisplaySurveyResponseService {
    public DisplaySurveyResponseService(){}

    public DisplaySurveyResponseService(DisplaySurveyResponseRepository displaySurveyResponseRepository) throws Exception{
        IDisplaySurveyResponseAbstractFactory.instance().setDisplaySurveyResponseRepository(displaySurveyResponseRepository);
    }

    @Override
    public List<String> getUsersWhoAnsweredSurvey(String courseId) {
        return IDisplaySurveyResponseAbstractFactory.instance().getDisplaySurveyResponseRepository().getUsersWhoAnsweredSurvey(courseId);
    }

    @Override
    public List<ISurveyQuestionOptionsModel> getSurveyResponse(List<String> users, String courseId) {
        return IDisplaySurveyResponseAbstractFactory.instance().getDisplaySurveyResponseRepository().getSurveyResponse(users,courseId);
    }
}
