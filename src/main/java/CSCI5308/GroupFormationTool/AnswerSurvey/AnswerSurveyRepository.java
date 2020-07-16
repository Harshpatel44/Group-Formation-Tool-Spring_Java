package CSCI5308.GroupFormationTool.AnswerSurvey;

import CSCI5308.GroupFormationTool.Database.StoredProcedure;
import java.sql.ResultSet;
import java.util.*;

public class AnswerSurveyRepository implements IAnswerSurveyRepository {

//    @Override
//    public boolean isSurveyPublished(String courseId) {
//        StoredProcedure surveyAvailable = null;
//        try{
//            surveyAvailable = new StoredProcedure("isSurveyAvailable(?)");
//            surveyAvailable.setParameter(1,courseId);
//            ResultSet rs = surveyAvailable.executeWithResults();
//            if(rs.next()){
//                return true;
//            }
//        }
//        catch (Exception e){
//            e.printStackTrace();
//        }
//        finally {
//            surveyAvailable.cleanup();
//        }
//        return false;
//    }

    @Override
    public List<ISurveyQuestionOptionsModel> getSurveyQuestionsAndOptions(String courseId) {
        StoredProcedure getOptions = null;
        StoredProcedure getSurveyQuestions = null;
        List<ISurveyQuestionOptionsModel> questionsAndOptions = new ArrayList<ISurveyQuestionOptionsModel>();
        try {
            getSurveyQuestions = new StoredProcedure("GetSurveyQuestionByCourse(?)");
            getSurveyQuestions.setParameter(1,courseId);
            ResultSet rs = getSurveyQuestions.executeWithResults();
            while(rs.next()) {
                HashMap<Integer,String> options = new HashMap<>();
                if(rs.getString(6).equals("mcqs") || rs.getString(6).equals("mcqm")) {
                    int questionId = rs.getInt(2);
                    getOptions = new StoredProcedure("GetSurveyQuestionOptions(?)");
                    getOptions.setParameter(1,questionId);
                    ResultSet ors = getOptions.executeWithResults();
                    while(ors.next()) {
                        options.put(ors.getInt("optionRank"),ors.getString("optionDesc"));
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
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            getOptions.cleanup();
            getSurveyQuestions.cleanup();
        }
        return questionsAndOptions;
    }

    @Override
    public void storeSurveyResponses(String bannerId, String courseID, int questionId, String answer) {
        StoredProcedure storeAnswers = null;
        try{
            storeAnswers = new StoredProcedure("InsertIntoSurveyAnswers(?,?,?,?)");
            storeAnswers.setParameter(1,bannerId);
            storeAnswers.setParameter(2,courseID);
            storeAnswers.setParameter(3,questionId);
            storeAnswers.setParameter(4,answer);
            ResultSet rs = storeAnswers.executeWithResults();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        finally {
            storeAnswers.cleanup();
        }
    }
}

