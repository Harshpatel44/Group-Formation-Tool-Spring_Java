package CSCI5308.GroupFormationTool.QuestionEditor;

public interface IQuestionEditorRepository {
    public boolean SaveTextAndNumericTypeQuestionRepo(String questionText, String questionTitle, String selectedQuestionType,String userId);
    boolean SaveMcqTypeQuestionRepo(String questionText, String questionTitle, String selectedQuestionType, String options, String ranks, String userId);
}
