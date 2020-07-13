package CSCI5308.GroupFormationTool.AnswerSurvey;

import CSCI5308.GroupFormationTool.Database.StoredProcedure;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AnswerSurveyRepository implements IAnswerSurveyRepository {

    @Override
    public boolean SurveyAvailableForUser(String bannerId) {
        try{
            StoredProcedure surveyAvailableForUser = new StoredProcedure("SurveyAvailableForTheUser(?)");
            surveyAvailableForUser.setParameter(1,bannerId);
            ResultSet rs = surveyAvailableForUser.executeWithResults();
            if(rs.next()){
                return true;
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean IsSurveyPublished(String courseId) {
        try{
            StoredProcedure surveyAvailable = new StoredProcedure("isSurveyAvailable(?)");
            surveyAvailable.setParameter(1,courseId);
            ResultSet rs = surveyAvailable.executeWithResults();
            if(rs.next()){
                return true;
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<ISurveyQuestionOptionsModel> getSurveyQuestionsAndOptions(String courseId) {

        List<ISurveyQuestionOptionsModel> questionsAndOptions = new ArrayList<ISurveyQuestionOptionsModel>();
        try {
            StoredProcedure getSurveyQuestions = new StoredProcedure("GetSurveyQuestionByCourse(?)");
            getSurveyQuestions.setParameter(1,courseId);
            ResultSet rs = getSurveyQuestions.executeWithResults();
            System.out.println("Inside Rutika Repo");
            while(rs.next())
            {
                List<String> options = new ArrayList<>();
                if(rs.getString(6).equals("mcqs") || rs.getString(6).equals("mcqm"))
                {
                    int questionId = rs.getInt(2);
                    StoredProcedure getOptions = new StoredProcedure("GetSurveyQuestionOptions(?)");
                    getOptions.setParameter(1,questionId);
                    ResultSet ors = getOptions.executeWithResults();
                    while(ors.next())
                    {
                        options.add(ors.getString(1));
                    }
                }
                ISurveyQuestionOptionsModel question = new SurveyQuestionOptionsModel();
                question.setSurveyCourseId(rs.getString("courseId"));
                question.setSurveyQuestionDescription(rs.getString("questionDesc"));
                question.setSurveyQuestionId(rs.getInt("questionId"));
                question.setSurveyQuestionType(rs.getString("questionType"));
                question.setSurveyQuestionsOptions(options);
                question.setSurveyQuestionTopic(rs.getString("questionTopic"));
                questionsAndOptions.add(question);
            }
            getSurveyQuestions.cleanup();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return questionsAndOptions;
    }
}

