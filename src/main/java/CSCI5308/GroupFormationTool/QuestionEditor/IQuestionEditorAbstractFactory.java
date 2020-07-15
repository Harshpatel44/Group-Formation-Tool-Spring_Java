package CSCI5308.GroupFormationTool.QuestionEditor;

import java.time.format.DateTimeFormatter;

public interface IQuestionEditorAbstractFactory {
    QuestionModel getQuestionModel();

    DateTimeFormatter getDateTimeFormat();
}
