package CSCI5308.GroupFormationTool.AnswerSurvey;

public class DisplaySurveyResponseAbstractFactory extends IDisplaySurveyResponseAbstractFactory {
    private IDisplaySurveyResponseService displaySurveyResponseService ;
    private IDisplaySurveyResponseRepository displaySurveyResponseRepository;

    public IDisplaySurveyResponseRepository getDisplaySurveyResponseRepository() {
        if (displaySurveyResponseRepository == null) {
            displaySurveyResponseRepository = new DisplaySurveyResponseRepository();
        }
        return displaySurveyResponseRepository;
    }

    public IDisplaySurveyResponseService getDisplaySurveyResponseService() {
        if (displaySurveyResponseService == null){
            displaySurveyResponseService = new DisplaySurveyResponseService();
        }
        return displaySurveyResponseService;
    }

    public void setDisplaySurveyResponseRepository(IDisplaySurveyResponseRepository displaySurveyResponseRepository) {
        this.displaySurveyResponseService = displaySurveyResponseService;
    }
}
