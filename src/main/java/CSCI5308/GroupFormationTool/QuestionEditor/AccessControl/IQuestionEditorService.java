package CSCI5308.GroupFormationTool.QuestionEditor.AccessControl;

import CSCI5308.GroupFormationTool.QuestionEditor.Model.QuestionModel;

public interface IQuestionEditorService {


    String SaveQuestionServiceForTextAndNumeric(String questionText, String questionTitle, String selectedQuestionType, String userId) throws Exception;

    String saveQuestionForMultipleChoiceMultiple(String questionText, String questionTitle, String selectedQuestionType, String options, String ranks, String userId) throws Exception;
}
