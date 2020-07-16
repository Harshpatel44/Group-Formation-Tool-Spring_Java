package CSCI5308.GroupFormationTool.AnswerSurvey;

import CSCI5308.GroupFormationTool.Database.StoredProcedure;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class AnswerSurveyRepository implements IAnswerSurveyRepository {

    private static final Logger LOG = LogManager.getLogger();

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
                if(rs.getString("questionType").equals("mcqs") || rs.getString("questionType").equals("mcqm")) {
                    int questionId = rs.getInt(2);
                    getOptions = new StoredProcedure("GetSurveyQuestionOptions(?)");
                    getOptions.setParameter(1,questionId);
                    ResultSet ors = getOptions.executeWithResults();
                    while(ors.next()) {
                        options.put(ors.getInt("optionRank"),ors.getString("optionsDesc"));
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
            LOG.info("Operation = getSurveyQuestionsAndOptions, Status = Success ");
        }
        catch(SQLException throwables){
            throwables.printStackTrace();
            LOG.error("Operation = getSurveyQuestionsAndOptions, Status = Failed, Error Message="+throwables.getMessage());
        }
        catch (Exception e) {
            e.printStackTrace();
            LOG.error("Operation = getSurveyQuestionsAndOptions, Status = Failed, Error Message="+e.getMessage());
        }
        finally {
            if(null != getOptions) {
                getOptions.cleanup();
            }
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
            LOG.info("Operation = storeSurveyResponses, Status = Success");
        }
        catch(SQLException throwables){
            throwables.printStackTrace();
            LOG.error("Operation = storeSurveyResponses, Status = Failed, Error Message="+throwables.getMessage());
        }
        catch (Exception e){
            e.printStackTrace();
            LOG.error("Operation = storeSurveyResponses, Status = Failed, Error Message="+e.getMessage());
        }
        finally {
            storeAnswers.cleanup();
        }
    }

    @Override
    public boolean checkSurveyAvailableForUser(String bannerId) {
        StoredProcedure checkSurveyAvailableForUser = null;
        try{
            checkSurveyAvailableForUser = new StoredProcedure("SurveyAvailableForTheUser(?)");
            checkSurveyAvailableForUser.setParameter(1,bannerId);
            ResultSet rs = checkSurveyAvailableForUser.executeWithResults();
            if(rs.next()){
                return false;
            }
            LOG.info("Operation = checkSurveyAvailableForUser, Status = Success");
        }
        catch (SQLException throwables){
            throwables.printStackTrace();
            LOG.error("Operation = checkSurveyAvailableForUser, Status = Failed, Error Message="+throwables.getMessage());
        }
        catch (Exception e){
            e.printStackTrace();
            LOG.error("Operation = checkSurveyAvailableForUser, Status = Failed, Error Message="+e.getMessage());        }
        finally {
            checkSurveyAvailableForUser.cleanup();
        }
        return true;
    }
}

