package CSCI5308.GroupFormationTool.QuestionEditor;

public abstract class IQuestionEditorAbstractFactory {

    private static IQuestionEditorAbstractFactory instance = null;

    public static IQuestionEditorAbstractFactory instance(){

        if (instance == null) {
            instance = new QuestionEditorAbstractFactory();
        }
        return instance;
    }

    public abstract IQuestionModel getQuestionModel();

    public abstract IQuestionEditorService getQuestionEditorService();

    public abstract IQuestionEditorRepository getQuestionEditorRepository();

    public abstract IRankFunctionsService getRankFunctionsService();

    public abstract void setQuestionEditorRepository(IQuestionEditorRepository questionEditorRepository);
}
