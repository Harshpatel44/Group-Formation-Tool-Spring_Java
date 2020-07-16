package CSCI5308.GroupFormationTool.AnswerSurvey;

public class AnswerSurveyAbstractConcrete extends AnswerSurveyAbstractFactory {
    private ISurveyQuestionOptionsModel surveyQuestionOptionsModel;
    private IAnswerSurveyService answerSurveyService ;
    private IAnswerSurveyRepository answerSurveyRepository;

    public ISurveyQuestionOptionsModel getSurveyQuestionOptionsModel(){
        return new SurveyQuestionOptionsModel();
    }

    public IAnswerSurveyRepository getAnswerSurveyRepository() {
        if (answerSurveyRepository == null) {
            answerSurveyRepository = new AnswerSurveyRepository();
        }
        return answerSurveyRepository;
    }

    public IAnswerSurveyService getAnswerSurveyService() {
        if (answerSurveyService == null){
            answerSurveyService = new AnswerSurveyService();
        }
        return answerSurveyService;
    }

    public void setAnswerSurveyRepository(IAnswerSurveyRepository answerSurveyRepository) {
        this.answerSurveyRepository = answerSurveyRepository;
    }
}
