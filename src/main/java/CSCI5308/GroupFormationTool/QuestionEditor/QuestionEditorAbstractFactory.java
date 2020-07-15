package CSCI5308.GroupFormationTool.QuestionEditor;

public class QuestionEditorAbstractFactory implements IQuestionEditorAbstractFactory {

    @Override
    public IQuestionModel getQuestionModel(){
        return new QuestionModel();
    }

}
