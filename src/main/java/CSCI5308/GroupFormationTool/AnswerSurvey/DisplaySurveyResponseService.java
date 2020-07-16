package CSCI5308.GroupFormationTool.AnswerSurvey;


import java.util.List;

public class DisplaySurveyResponseService implements IDisplaySurveyResponseService {
    public DisplaySurveyResponseService() {
    }


    @Override
    public List<String> getUsersWhoAnsweredSurvey(String courseId) {
        return DisplaySurveyResponseAbstractFactory.instance().getDisplaySurveyResponseRepository().getUsersWhoAnsweredSurvey(courseId);
    }

    @Override
    public List<ISurveyQuestionOptionsModel> getSurveyResponse(List<String> users, String courseId) {
        return DisplaySurveyResponseAbstractFactory.instance().getDisplaySurveyResponseRepository().getSurveyResponse(users, courseId);
    }
}
