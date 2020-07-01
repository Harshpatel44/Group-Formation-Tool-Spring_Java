package CSCI5308.GroupFormationTool.QuestionEditor;

import java.util.HashMap;

public interface IQuestionEditorService {

    Boolean SaveQuestionServiceForTextAndNumeric(String questionText, String questionTitle, String selectedQuestionType, String userId) throws Exception;
    Boolean saveQuestionForMultipleChoiceService(String questionText, String questionTitle, String selectedQuestionType, String options, String ranks, String userId) throws Exception;

}
