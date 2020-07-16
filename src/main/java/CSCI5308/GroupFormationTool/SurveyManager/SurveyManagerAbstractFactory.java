package CSCI5308.GroupFormationTool.SurveyManager;

public abstract class SurveyManagerAbstractFactory {
    private static SurveyManagerAbstractFactory instance = null;

    public static SurveyManagerAbstractFactory instance() {

        if (instance == null) {
            instance = new SurveyManagerAbstractConcrete();
        }
        return instance;
    }

    public abstract ISurveyManagerRepository getSurveyManagerRepository();

    public abstract void setSurveyManagerRepository(ISurveyManagerRepository surveyManagerRepository);

    public abstract ISurveyManagerService getSurveyManagerService();
}
