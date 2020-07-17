package CSCI5308.GroupFormationTool.QuestionManager;

import java.sql.Date;

public class Question implements IQuestion {
    private String questionTopic;
    private Integer questionId;
    private String questionDescription;
    private Date date;
    private Integer flag;

    @Override
    public String getQuestionDescription() {
        return questionDescription;
    }

    @Override
    public void setQuestionDescription(String questionDescription) {
        this.questionDescription = questionDescription;
    }

    @Override
    public String getQuestionTopic() {
        return questionTopic;
    }

    @Override
    public void setQuestionTopic(String questionTopic) {
        this.questionTopic = questionTopic;
    }

    @Override
    public Integer getQuestionId() {
        return questionId;
    }

    @Override
    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    @Override
    public Date getDate() {
        return date;
    }

    @Override
    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public Integer getFlag() {
        return flag;
    }

    @Override
    public void setFlag(int flag) {
        this.flag = flag;
    }
}




