package CSCI5308.GroupFormationTool.SurveyManager;

import CSCI5308.GroupFormationTool.QuestionManager.QuestionManagerAbstractConcrete;
import CSCI5308.GroupFormationTool.QuestionManager.QuestionManagerAbstractFactory;

public abstract class SurveyManagerAbstractFactory {
    private static SurveyManagerAbstractFactory instance = null;

    public static SurveyManagerAbstractFactory instance(){

        if (instance == null) {
            instance = new SurveyManagerAbstractConcrete();
        }
        return instance;
    }

    public abstract ISurveyManagerRepository getSurveyManagerRepository();
    public abstract ISurveyManagerService getSurveyManagerService();
}
