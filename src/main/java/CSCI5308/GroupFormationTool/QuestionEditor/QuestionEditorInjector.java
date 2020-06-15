package CSCI5308.GroupFormationTool.QuestionEditor;


import CSCI5308.GroupFormationTool.AdminPanel.AccessControl.IAdminController;
import CSCI5308.GroupFormationTool.AdminPanel.AccessControl.IAdminRepository;
import CSCI5308.GroupFormationTool.AdminPanel.AccessControl.IAdminService;
import CSCI5308.GroupFormationTool.AdminPanel.Controller.AdminController;
import CSCI5308.GroupFormationTool.AdminPanel.Repository.AdminRepository;
import CSCI5308.GroupFormationTool.AdminPanel.Service.AdminService;
import CSCI5308.GroupFormationTool.QuestionEditor.AccessControl.IQuestionEditorController;
import CSCI5308.GroupFormationTool.QuestionEditor.AccessControl.IQuestionEditorRepository;
import CSCI5308.GroupFormationTool.QuestionEditor.AccessControl.IQuestionEditorService;
import CSCI5308.GroupFormationTool.QuestionEditor.Controller.QuestionEditorController;
import CSCI5308.GroupFormationTool.QuestionEditor.Repository.QuestionEditorRepository;
import CSCI5308.GroupFormationTool.QuestionEditor.Service.QuestionEditorService;

public class QuestionEditorInjector {
    private static QuestionEditorInjector instance = null;
    private IQuestionEditorController questionEditorController;
    private IQuestionEditorService questionEditorService;
    private IQuestionEditorRepository questionEditorRepository;

    private QuestionEditorInjector() throws Exception {
        questionEditorController = new QuestionEditorController();
        questionEditorService = new QuestionEditorService();
        questionEditorRepository = new QuestionEditorRepository();
    }

    public static QuestionEditorInjector instance() throws Exception {
        if(instance==null){
            instance = new QuestionEditorInjector();
        }
        return instance;
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
