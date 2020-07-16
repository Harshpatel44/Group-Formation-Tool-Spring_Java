package CSCI5308.GroupFormationTool.AnswerSurvey;

public abstract class IDisplaySurveyResponseAbstractFactory {

    private static IDisplaySurveyResponseAbstractFactory instance = null;

    public static IDisplaySurveyResponseAbstractFactory instance(){

        if (instance == null) {
            instance = new DisplaySurveyResponseAbstractFactory(){};
        }
        return instance;
    }

    public abstract IDisplaySurveyResponseRepository getDisplaySurveyResponseRepository();

    public abstract IDisplaySurveyResponseService getDisplaySurveyResponseService();

    public abstract void setDisplaySurveyResponseRepository(IDisplaySurveyResponseRepository displaySurveyResponseRepository);
}
