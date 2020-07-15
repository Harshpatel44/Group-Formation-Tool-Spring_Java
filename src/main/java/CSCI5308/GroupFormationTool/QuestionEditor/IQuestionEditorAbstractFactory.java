package CSCI5308.GroupFormationTool.QuestionEditor;

import java.time.format.DateTimeFormatter;

public interface IQuestionEditorAbstractFactory {
    IQuestionModel getQuestionModel();

    DateTimeFormatter getDateTimeFormat();
}
