package CSCI5308.GroupFormationTool.QuestionEditor;

import java.util.Dictionary;
import java.util.Hashtable;

import static CSCI5308.GroupFormationTool.ApplicationConstants.*;

public class QuestionModel implements IQuestionModel {
    private String questionTitle;
    private String questionText;
    private Dictionary questionTypeList = new Hashtable();
    private String selectedQuestionType;
    private String questionSubmitMessage="Question did not submit successfully...";

    public QuestionModel(){
        this.questionTypeList.put(numeric,numeric);
        this.questionTypeList.put(MCCO,MCCO);
        this.questionTypeList.put(MCCM,MCCM);
        this.questionTypeList.put(text,text);
    }
   @Override
    public String getQuestionSubmitMessage() {
        return questionSubmitMessage;
    }

    @Override
    public void setQuestionSubmitMessage(String questionSubmitMessage) {
        this.questionSubmitMessage = questionSubmitMessage;
    }

    @Override
    public Dictionary getQuestionTypeList() {
        return questionTypeList;
    }

    @Override
    public void setQuestionTypeList(Dictionary questionTypeList) {
        this.questionTypeList = questionTypeList;
    }

    @Override
    public String getSelectedQuestionType() {
        return selectedQuestionType;
    }

    @Override
    public void setSelectedQuestionType(String selectedQuestionType) {
        this.selectedQuestionType = selectedQuestionType;
    }

    @Override
    public String getQuestionTitle() {
        return questionTitle;
    }

    @Override
    public void setQuestionTitle(String questionTitle) {
        this.questionTitle = questionTitle;
    }

    @Override
    public String getQuestionText() {
        return questionText;
    }

    @Override
    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    }
