package CSCI5308.GroupFormationTool.SurveyManager;

import CSCI5308.GroupFormationTool.QuestionManager.IQuestion;

import java.util.List;

public class SurveyManagerService implements ISurveyManagerService {
    private ISurveyManagerRepository surveyManagerRepository;

    public SurveyManagerService() {
    }

    public SurveyManagerService(SurveyManagerRepository surveyManagerRepository) {
        SurveyManagerAbstractFactory.instance().setSurveyManagerRepository(surveyManagerRepository);
    }

    @Override
    public void getSurveyQuestions() throws Exception {
        surveyManagerRepository = SurveyManagerAbstractFactory.instance().getSurveyManagerRepository();
        surveyManagerRepository.getSurveyQuestions();
    }

    @Override
    public List<IQuestion> AlreadyAddedSurveyQuestions() throws Exception {
        surveyManagerRepository = SurveyManagerAbstractFactory.instance().getSurveyManagerRepository();
        return surveyManagerRepository.AlreadyAddedSurveyQuestions();
    }

    @Override
    public List<IQuestion> NotAddedSurveyQuestions() throws Exception {
        surveyManagerRepository = SurveyManagerAbstractFactory.instance().getSurveyManagerRepository();
        return surveyManagerRepository.NotAddedSurveyQuestions();
    }

    @Override
    public void AddQuestionToSurvey(Integer questionId) {
        surveyManagerRepository = SurveyManagerAbstractFactory.instance().getSurveyManagerRepository();
        surveyManagerRepository.AddQuestionToSurvey(questionId);
    }

    @Override
    public void RemoveQuestionFromSurvey(Integer questionId) {
        surveyManagerRepository = SurveyManagerAbstractFactory.instance().getSurveyManagerRepository();
        surveyManagerRepository.RemoveQuestionFromSurvey(questionId);
    }

    @Override
    public void PublishSurvey() {
        surveyManagerRepository = SurveyManagerAbstractFactory.instance().getSurveyManagerRepository();
        surveyManagerRepository.PublishSurvey();
    }

    @Override
    public boolean checkPublish() {
        surveyManagerRepository = SurveyManagerAbstractFactory.instance().getSurveyManagerRepository();
        return surveyManagerRepository.checkPublish();
    }

    @Override
    public void UnpublishSurvey() {
        surveyManagerRepository = SurveyManagerAbstractFactory.instance().getSurveyManagerRepository();
        surveyManagerRepository.UnpublishSurvey();
    }
}
