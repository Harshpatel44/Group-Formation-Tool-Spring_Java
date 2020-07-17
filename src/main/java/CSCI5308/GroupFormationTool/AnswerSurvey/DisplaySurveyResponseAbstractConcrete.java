package CSCI5308.GroupFormationTool.AnswerSurvey;

public class DisplaySurveyResponseAbstractConcrete extends DisplaySurveyResponseAbstractFactory {
    private IDisplaySurveyResponseService displaySurveyResponseService;
    private IDisplaySurveyResponseRepository displaySurveyResponseRepository;

    public IDisplaySurveyResponseRepository getDisplaySurveyResponseRepository() {
        if (displaySurveyResponseRepository == null) {
            displaySurveyResponseRepository = new DisplaySurveyResponseRepository();
        }
        return displaySurveyResponseRepository;
    }

    public void setDisplaySurveyResponseRepository(IDisplaySurveyResponseRepository displaySurveyResponseRepository) {
        this.displaySurveyResponseRepository = displaySurveyResponseRepository;
    }

    public IDisplaySurveyResponseService getDisplaySurveyResponseService() {
        if (displaySurveyResponseService == null) {
            displaySurveyResponseService = new DisplaySurveyResponseService();
        }
        return displaySurveyResponseService;
    }
}
