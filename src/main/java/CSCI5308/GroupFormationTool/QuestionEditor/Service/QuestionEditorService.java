package CSCI5308.GroupFormationTool.QuestionEditor.Service;

import CSCI5308.GroupFormationTool.QuestionEditor.AccessControl.IQuestionEditorService;
import CSCI5308.GroupFormationTool.QuestionEditor.QuestionEditorInjector;
import CSCI5308.GroupFormationTool.QuestionEditor.Repository.QuestionEditorRepository;


public class QuestionEditorService implements IQuestionEditorService {

    public QuestionEditorService(){}

    public QuestionEditorService(QuestionEditorRepository questionEditorRepository) throws Exception {
        QuestionEditorInjector.instance().setQuestionEditorRepository(questionEditorRepository);
    }

    @Override
    public boolean CreateQuestionService() throws Exception {
        System.out.println("CreateQuestionService");
        QuestionEditorInjector.instance().getQuestionEditorRepository().CreateQuestionRepo();
        return false;
    }
}
