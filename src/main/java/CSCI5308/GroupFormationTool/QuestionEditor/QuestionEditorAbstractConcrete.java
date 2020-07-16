package CSCI5308.GroupFormationTool.QuestionEditor;

public class QuestionEditorAbstractConcrete extends QuestionEditorAbstractFactory {

    private IQuestionModel questionModel;
    private IQuestionEditorService questionEditorService;
    private IQuestionEditorRepository questionEditorRepository;
    private IRankFunctionsService rankFunctionsService;

    public IQuestionModel getQuestionModel(){
        return new QuestionModel();
    }

    public IQuestionEditorService getQuestionEditorService() {
        if (questionEditorService == null) {
            questionEditorService = new QuestionEditorService();
        }
            return questionEditorService;
    }

    public IQuestionEditorRepository getQuestionEditorRepository() {
        if (questionEditorRepository == null) {
            questionEditorRepository = new QuestionEditorRepository();
        }
        return questionEditorRepository;
    }

    public void setQuestionEditorRepository(IQuestionEditorRepository questionEditorRepository){
        this.questionEditorRepository = questionEditorRepository;
    }

    public IRankFunctionsService getRankFunctionsService() {
        if (rankFunctionsService == null) {
            rankFunctionsService = new RankFunctionsService();
        }
        return rankFunctionsService;
    }


}
