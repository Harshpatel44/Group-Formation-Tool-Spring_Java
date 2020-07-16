package CSCI5308.GroupFormationTool.SurveyManager;

import CSCI5308.GroupFormationTool.QuestionManager.QuestionManagerRepository;

public class SurveyManagerAbstractConcrete extends SurveyManagerAbstractFactory {
    private ISurveyManagerService surveyManagerService;
    private ISurveyManagerRepository surveyManagerRepository;

    @Override
    public ISurveyManagerRepository getSurveyManagerRepository() {
        if (surveyManagerRepository == null) {
            surveyManagerRepository = new SurveyManagerRepository();
        }
        return surveyManagerRepository;
    }

    @Override
    public ISurveyManagerService getSurveyManagerService() {
        if (surveyManagerService == null) {
            surveyManagerService = new SurveyManagerService();
        }
        return surveyManagerService;
    }
}
