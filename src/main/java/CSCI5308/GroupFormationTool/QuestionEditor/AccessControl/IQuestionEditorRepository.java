package CSCI5308.GroupFormationTool.QuestionEditor.AccessControl;

public interface IQuestionEditorRepository {

    public boolean SaveTextAndNumericTypeQuestionRepo(String questionText, String questionTitle, String selectedQuestionType,String userId);

    boolean SaveMcqMultipleTypeQuestionRepo(String questionText, String questionTitle, String selectedQuestionType, String options, String ranks, String userId);
}
