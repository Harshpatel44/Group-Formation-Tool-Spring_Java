package CSCI5308.GroupFormationTool.AnswerSurvey;

public abstract class IAnswerSurveyAbstractFactory {

    private static IAnswerSurveyAbstractFactory instance = null;

    public static IAnswerSurveyAbstractFactory instance(){

        if (instance == null) {
            instance = new AnswerSurveyAbstractFactory() {};
        }
        return instance;
    }

    public abstract IAnswerSurveyRepository getAnswerSurveyRepository();

    public abstract IAnswerSurveyService getAnswerSurveyService();

    public abstract ISurveyQuestionOptionsModel getSurveyQuestionOptionsModel();

    public abstract void setAnswerSurveyRepository(IAnswerSurveyRepository answerSurveyRepository);
}
