package CSCI5308.GroupFormationTool.AnswerSurvey;

import java.util.List;

public interface ISurveyQuestionOptionsModel {

    Integer getSurveyQuestionId();

    void setSurveyQuestionId(int surveyQuestionId);

    String getSurveyQuestionTopic();

    void setSurveyQuestionTopic(String surveyQuestionTopic);

    String getSurveyQuestionDescription();

    void setSurveyQuestionDescription(String surveyQuestionDescription);

    String getSurveyQuestionType();

    void setSurveyQuestionType(String surveyQuestionType);

    String getSurveyCourseId();

    void setSurveyCourseId (String courseId);

    List<String> getSurveyQuestionsOptions();

    void setSurveyQuestionsOptions(List<String> surveyQuestionsOptions);

    List<String> getSurveyAnswers();

    void setSurveyAnswers(List<String> surveyAnswer);

    String getBannerId();

    void setBannerId(String bannerId);
}
