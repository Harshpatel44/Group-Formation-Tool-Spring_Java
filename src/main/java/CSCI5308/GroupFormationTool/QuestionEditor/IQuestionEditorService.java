package CSCI5308.GroupFormationTool.QuestionEditor;

import java.util.HashMap;

public interface IQuestionEditorService {

    Boolean saveQuestionServiceForTextAndNumeric(String questionText,String questionTitle,String selectedQuestionType);
    Boolean saveQuestionForMultipleChoiceService(String questionText, String questionTitle, String selectedQuestionType, String options, String ranks) throws Exception;

}
