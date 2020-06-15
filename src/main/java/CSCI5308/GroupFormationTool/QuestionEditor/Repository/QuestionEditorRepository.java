package CSCI5308.GroupFormationTool.QuestionEditor.Repository;

import CSCI5308.GroupFormationTool.QuestionEditor.AccessControl.IQuestionEditorRepository;

public class QuestionEditorRepository implements IQuestionEditorRepository {

    @Override
    public boolean CreateQuestionRepo(){
        System.out.println("CreateQuestionRepository");
        return false;
    }
}
