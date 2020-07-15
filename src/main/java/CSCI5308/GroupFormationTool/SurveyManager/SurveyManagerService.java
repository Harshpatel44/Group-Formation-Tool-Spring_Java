package CSCI5308.GroupFormationTool.SurveyManager;

import CSCI5308.GroupFormationTool.Injector;
import CSCI5308.GroupFormationTool.QuestionManager.IQuestion;

import java.util.List;

public class SurveyManagerService implements ISurveyManagerService{
    private ISurveyManagerRepository surveyManagerRepository;

    public SurveyManagerService(){}
    public SurveyManagerService(SurveyManagerRepository surveyManagerRepository){
        Injector.instance().setSurveyManagerRepository(surveyManagerRepository);
    }

    @Override
    public void getSurveyQuestions(String courseId) throws Exception {
      surveyManagerRepository = Injector.instance().getSurveyManagerRepository();
      surveyManagerRepository.getSurveyQuestions(courseId);
    }

    @Override
    public List<IQuestion> AlreadyAddedSurveyQuestions() throws Exception {
        surveyManagerRepository = Injector.instance().getSurveyManagerRepository();
        return surveyManagerRepository.AlreadyAddedSurveyQuestions();
    }

    @Override
    public List<IQuestion> NotAddedSurveyQuestions() throws Exception {
        surveyManagerRepository = Injector.instance().getSurveyManagerRepository();
        return surveyManagerRepository.NotAddedSurveyQuestions();
    }

    @Override
    public void AddQuestionToSurvey(Integer questionId) {
        surveyManagerRepository = Injector.instance().getSurveyManagerRepository();
        surveyManagerRepository.AddQuestionToSurvey(questionId);
    }

    @Override
    public void RemoveQuestionFromSurvey(Integer questionId) {
        surveyManagerRepository = Injector.instance().getSurveyManagerRepository();
        surveyManagerRepository.RemoveQuestionFromSurvey(questionId);
    }

    @Override
    public void PublishSurvey() {
        surveyManagerRepository = Injector.instance().getSurveyManagerRepository();
        surveyManagerRepository.PublishSurvey();
    }

    @Override
    public boolean checkPublish() {
        surveyManagerRepository = Injector.instance().getSurveyManagerRepository();
        return surveyManagerRepository.checkPublish();
    }

    @Override
    public void UnpublishSurvey() {
        surveyManagerRepository = Injector.instance().getSurveyManagerRepository();
        surveyManagerRepository.UnpublishSurvey();
    }
}
