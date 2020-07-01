package CSCI5308.GroupFormationTool.QuestionManager;

import java.sql.Date;

public interface IQuestion {
    String getQuestionDescription();

    void setQuestionDescription(String questionDescription);

    String getQuestionTopic();

    void setQuestionTopic(String questionTopic);

    Integer getQuestionId();

    void setQuestionId(int questionId);

    Date getDate();

    void setDate(Date date);
}
