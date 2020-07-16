package CSCI5308.GroupFormationTool.QuestionEditor;

public abstract class QuestionEditorAbstractFactory {

    private static QuestionEditorAbstractFactory instance = null;

    public static QuestionEditorAbstractFactory instance(){

        if (instance == null) {
            instance = new QuestionEditorAbstractConcrete();
        }
        return instance;
    }

    public abstract IQuestionModel getQuestionModel();

    public abstract IQuestionEditorService getQuestionEditorService();

    public abstract IQuestionEditorRepository getQuestionEditorRepository();

    public abstract IRankFunctionsService getRankFunctionsService();

    public abstract void setQuestionEditorRepository(IQuestionEditorRepository questionEditorRepository);
}
