package CSCI5308.GroupFormationTool.QuestionEditor;

public interface IQuestionEditorRepository {
    boolean SaveTextAndNumericTypeQuestionRepo(String questionText, String questionTitle, String selectedQuestionType,String userId);
    boolean SaveMcqTypeQuestionRepo(String questionText, String questionTitle, String selectedQuestionType, String options, String ranks, String userId);
    int getQuestionIDFromTopic(String questionText,String dStamp);
    boolean addQuestionToSurveyTable(String userId, int qId,String questionTitle, String questionText, String courseId,String time);
}
