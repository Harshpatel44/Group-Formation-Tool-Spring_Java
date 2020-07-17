package CSCI5308.GroupFormationTool.QuestionManager;

public abstract class QuestionManagerAbstractFactory {
    private static QuestionManagerAbstractFactory instance = null;

    public static QuestionManagerAbstractFactory instance() {

        if (instance == null) {
            instance = new QuestionManagerAbstractConcrete();
        }
        return instance;
    }

    public abstract IQuestion getQuestion();

    public abstract IQuestionManagerRepository getQuestionManagerRepository();

    public abstract void setQuestionManagerRepository(IQuestionManagerRepository questionManagerRepository);

    public abstract IQuestionManagerService getQuestionManagerService();

    public abstract IQuestionResponsesRepo getQuestionResponsesRepo();

    public abstract void setQuestionResponsesRepo(IQuestionResponsesRepo questionResponsesRepo);

    public abstract IQuestionResponsesService getQuestionResponsesService();
}

