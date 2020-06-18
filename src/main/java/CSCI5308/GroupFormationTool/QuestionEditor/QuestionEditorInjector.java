package CSCI5308.GroupFormationTool.QuestionEditor;


import CSCI5308.GroupFormationTool.QuestionEditor.AccessControl.IQuestionEditorController;
import CSCI5308.GroupFormationTool.QuestionEditor.AccessControl.IQuestionEditorRepository;
import CSCI5308.GroupFormationTool.QuestionEditor.AccessControl.IQuestionEditorService;
import CSCI5308.GroupFormationTool.QuestionEditor.AccessControl.IRankFunctionsService;
import CSCI5308.GroupFormationTool.QuestionEditor.Controller.QuestionEditorController;
import CSCI5308.GroupFormationTool.QuestionEditor.Repository.QuestionEditorRepository;
import CSCI5308.GroupFormationTool.QuestionEditor.Service.QuestionEditorService;
import CSCI5308.GroupFormationTool.QuestionEditor.Service.RankFunctionsService;

public class QuestionEditorInjector {
    private static QuestionEditorInjector instance = null;
    private IQuestionEditorController questionEditorController;
    private IQuestionEditorService questionEditorService;
    private IQuestionEditorRepository questionEditorRepository;
    private IRankFunctionsService rankFunctionsService;
    public IRankFunctionsService getRankFunctionsService() {
        return rankFunctionsService;
    }

    private QuestionEditorInjector() throws Exception {
        questionEditorController = new QuestionEditorController();
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

    public IQuestionEditorController getQuestionEditorController() {
        return questionEditorController;
    }

    public void setQuestionEditorController(IQuestionEditorController questionEditorController) {
        this.questionEditorController = questionEditorController;
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
