package CSCI5308.GroupFormationTool.AnswerSurvey;

import java.util.List;

public class SurveyQuestionOptionsModel implements ISurveyQuestionOptionsModel {
    private Integer surveyQuestionId;
    private String surveyQuestionTopic;
    private String surveyQuestionDescription;
    private String surveyQuestionType;
    private String surveyCourseId;
    private List<String> surveyQuestionsOptions;
    private List<String> surveyAnswer;
    private String bannerId;

    public SurveyQuestionOptionsModel() {
    }

    @Override
    public Integer getSurveyQuestionId() {
        return this.surveyQuestionId;
    }

    @Override
    public void setSurveyQuestionId(int surveyQuestionId) {
        this.surveyQuestionId = surveyQuestionId;
    }

    @Override
    public String getSurveyQuestionTopic() {
        return this.surveyQuestionTopic;
    }

    @Override
    public void setSurveyQuestionTopic(String surveyQuestionTopic) {
        this.surveyQuestionTopic = surveyQuestionTopic;
    }

    @Override
    public String getSurveyQuestionDescription() {
        return this.surveyQuestionDescription;
    }

    @Override
    public void setSurveyQuestionDescription(String surveyQuestionDescription) {
        this.surveyQuestionDescription = surveyQuestionDescription;
    }

    @Override
    public String getSurveyQuestionType() {
        return this.surveyQuestionType;
    }

    @Override
    public void setSurveyQuestionType(String surveyQuestionType) {
        this.surveyQuestionType = surveyQuestionType;

    }

    @Override
    public String getSurveyCourseId() {
        return this.surveyCourseId;
    }

    @Override
    public void setSurveyCourseId(String courseId) {
        this.surveyCourseId = courseId;
    }

    @Override
    public List<String> getSurveyQuestionsOptions() {
        return this.surveyQuestionsOptions;
    }

    @Override
    public void setSurveyQuestionsOptions(List<String> surveyQuestionsOptions) {
        this.surveyQuestionsOptions = surveyQuestionsOptions;
    }

    @Override
    public List<String> getSurveyAnswers() {
        return this.surveyAnswer;
    }

    @Override
    public void setSurveyAnswers(List<String> surveyAnswer) {
        this.surveyAnswer = surveyAnswer;
    }

    @Override
    public String getBannerId() {
        return this.bannerId;
    }

    @Override
    public void setBannerId(String bannerId) {
        this.bannerId = bannerId;
    }
}
