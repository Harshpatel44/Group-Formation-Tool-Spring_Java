package CSCI5308.GroupFormationTool.QuestionEditor;

import java.time.format.DateTimeFormatter;

public class QuestionEditorAbstractFactory implements IQuestionEditorAbstractFactory {

    @Override
    public QuestionModel getQuestionModel(){
        return new QuestionModel();
    }

    @Override
    public DateTimeFormatter getDateTimeFormat(){
        return DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
    }
}
