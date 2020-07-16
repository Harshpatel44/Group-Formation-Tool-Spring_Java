package CSCI5308.GroupFormationTool.AnswerSurvey;

public abstract class DisplaySurveyResponseAbstractFactory {

    private static DisplaySurveyResponseAbstractFactory instance = null;

    public static DisplaySurveyResponseAbstractFactory instance(){

        if (instance == null) {
            instance = new DisplaySurveyResponseAbstractConcrete(){};
        }
        return instance;
    }

    public abstract IDisplaySurveyResponseRepository getDisplaySurveyResponseRepository();

    public abstract IDisplaySurveyResponseService getDisplaySurveyResponseService();

    public abstract void setDisplaySurveyResponseRepository(IDisplaySurveyResponseRepository displaySurveyResponseRepository);
}
