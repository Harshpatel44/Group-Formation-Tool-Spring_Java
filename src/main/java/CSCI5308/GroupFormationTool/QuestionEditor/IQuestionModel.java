package CSCI5308.GroupFormationTool.QuestionEditor;

import java.util.Dictionary;

public interface IQuestionModel {
    String getQuestionSubmitMessage();

    void setQuestionSubmitMessage(String questionSubmitMessage);

    Dictionary getQuestionTypeList();

    void setQuestionTypeList(Dictionary questionTypeList);

    String getSelectedQuestionType();

    void setSelectedQuestionType(String selectedQuestionType);

    String getQuestionTitle();

    void setQuestionTitle(String questionTitle);

    String getQuestionText();

    void setQuestionText(String questionText);
}
