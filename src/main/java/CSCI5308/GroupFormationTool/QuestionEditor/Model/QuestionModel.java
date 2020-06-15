package CSCI5308.GroupFormationTool.QuestionEditor.Model;

import java.util.Dictionary;
import java.util.HashMap;
import java.util.Hashtable;

public class QuestionModel {
    private String questionTitle;
    private String questionText;
    private Dictionary questionTypeList = new Hashtable();
    private String selectedQuestionType;

    public QuestionModel(){
        this.questionTypeList.put("Numeric","numeric");
        this.questionTypeList.put("Multiple Choice, Choose One","mcqs");
        this.questionTypeList.put("Multiple Choise, Choose Multiple","mcqm");
        this.questionTypeList.put("Text","text");
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
