package CSCI5308.GroupFormationTool.QuestionManager;

public class QuestionManagerAbstractConcrete extends QuestionManagerAbstractFactory{
    private IQuestionResponsesService questionResponsesService;
    private IQuestionResponsesRepo questionResponsesRepo;
    private IQuestionManagerRepository questionManagerRepository;
    private IQuestionManagerService questionManagerService;
    @Override
    public IQuestion getQuestion() { return new Question(); }

    @Override
    public IQuestionManagerRepository getQuestionManagerRepository() {
        if (questionManagerRepository == null) {
            questionManagerRepository = new QuestionManagerRepository();
        }
        return questionManagerRepository;
    }

    @Override
    public void setQuestionManagerRepository(IQuestionManagerRepository questionManagerRepository){
        this.questionManagerRepository = questionManagerRepository;
    }

    @Override
    public IQuestionManagerService getQuestionManagerService() {
        if (questionManagerService == null) {
            questionManagerService = new QuestionManagerService();
        }
        return questionManagerService;
    }

    @Override
    public IQuestionResponsesRepo getQuestionResponsesRepo() {
        if (questionResponsesRepo == null) {
            questionResponsesRepo = new QuestionResponsesRepo();
        }
        return questionResponsesRepo;
    }

    @Override
    public void setQuestionResponsesRepo(IQuestionResponsesRepo questionResponsesRepo){
        this.questionResponsesRepo = questionResponsesRepo;
    }

    @Override
    public IQuestionResponsesService getQuestionResponsesService() {
        if (questionResponsesService == null) {
            questionResponsesService = new QuestionResponsesService();
        }
        return questionResponsesService;
    }
}
