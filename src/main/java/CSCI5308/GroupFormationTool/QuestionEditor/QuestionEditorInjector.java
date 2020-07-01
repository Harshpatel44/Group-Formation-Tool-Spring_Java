package CSCI5308.GroupFormationTool.QuestionEditor;


public class QuestionEditorInjector {
    private static QuestionEditorInjector instance = null;
    private IQuestionEditorService questionEditorService;
    private IQuestionEditorRepository questionEditorRepository;
    private IRankFunctionsService rankFunctionsService;
    public IRankFunctionsService getRankFunctionsService() {
        return rankFunctionsService;
    }

    private QuestionEditorInjector() throws Exception {
        questionEditorService = new QuestionEditorService();
        questionEditorRepository = new QuestionEditorRepository();
        rankFunctionsService = new RankFunctionsService();
    }

    public static QuestionEditorInjector instance() throws Exception {
        if(instance==null){
            instance = new QuestionEditorInjector();
        }
        return instance;
    }

    public void setRankFunctionsService(IRankFunctionsService rankFunctionsService) {
        this.rankFunctionsService = rankFunctionsService;
    }

    public IQuestionEditorService getQuestionEditorService() {
        return questionEditorService;
    }

    public void setQuestionEditorService(IQuestionEditorService questionEditorService) {
        this.questionEditorService = questionEditorService;
    }

    public IQuestionEditorRepository getQuestionEditorRepository() {
        return questionEditorRepository;
    }

    public void setQuestionEditorRepository(IQuestionEditorRepository questionEditorRepository) {
        this.questionEditorRepository = questionEditorRepository;
    }
}
