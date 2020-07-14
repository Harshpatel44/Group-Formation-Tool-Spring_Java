package CSCI5308.GroupFormationTool.AnswerSurvey;

import java.util.List;

public interface IAnswerSurveyService {
    public List<ISurveyQuestionOptionsModel> getSurveyQuestionsAndOptions(String courseId);
}
