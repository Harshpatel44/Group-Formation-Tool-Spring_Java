package CSCI5308.GroupFormationTool.AnswerSurvey;

public abstract class AnswerSurveyAbstractFactory {

    private static AnswerSurveyAbstractFactory instance = null;

    public static AnswerSurveyAbstractFactory instance() {

        if (instance == null) {
            instance = new AnswerSurveyAbstractConcrete() {
            };
        }
        return instance;
    }

    public abstract IAnswerSurveyRepository getAnswerSurveyRepository();

    public abstract void setAnswerSurveyRepository(IAnswerSurveyRepository answerSurveyRepository);

    public abstract IAnswerSurveyService getAnswerSurveyService();

    public abstract ISurveyQuestionOptionsModel getSurveyQuestionOptionsModel();
}
