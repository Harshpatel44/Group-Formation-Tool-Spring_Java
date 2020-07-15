package CSCI5308.GroupFormationTool.AnswerSurvey;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.HashMap;
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

    HashMap getSurveyQuestionsOptions();

    void setSurveyQuestionsOptions(HashMap<Integer,String> surveyQuestionsOptions);

    List<String> getSurveyAnswers();

    void setSurveyAnswers(List<String> surveyAnswer);

    String getBannerId();

    void setBannerId(String bannerId);

    void setResponses(ArrayList<String> responses);

    ArrayList<String> getResponses();
}
