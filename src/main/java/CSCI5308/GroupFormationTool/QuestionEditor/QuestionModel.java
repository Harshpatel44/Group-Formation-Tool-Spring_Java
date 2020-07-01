package CSCI5308.GroupFormationTool.QuestionEditor;

import java.util.Dictionary;
import java.util.Hashtable;

import static CSCI5308.GroupFormationTool.ApplicationConstants.*;

public class QuestionModel {
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

    public String getQuestionSubmitMessage() {
        return questionSubmitMessage;
    }

    public void setQuestionSubmitMessage(String questionSubmitMessage) {
        this.questionSubmitMessage = questionSubmitMessage;
    }

    public Dictionary getQuestionTypeList() {
        return questionTypeList;
    }

    public void setQuestionTypeList(Dictionary questionTypeList) {
        this.questionTypeList = questionTypeList;
    }

    public String getSelectedQuestionType() {
        return selectedQuestionType;
    }

    public void setSelectedQuestionType(String selectedQuestionType) {
        this.selectedQuestionType = selectedQuestionType;
    }

    public String getQuestionTitle() {
        return questionTitle;
    }

    public void setQuestionTitle(String questionTitle) {
        this.questionTitle = questionTitle;
    }

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    }