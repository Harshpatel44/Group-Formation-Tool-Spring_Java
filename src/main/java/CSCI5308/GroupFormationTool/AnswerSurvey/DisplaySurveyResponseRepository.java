package CSCI5308.GroupFormationTool.AnswerSurvey;

import CSCI5308.GroupFormationTool.Database.StoredProcedure;
import CSCI5308.GroupFormationTool.Injector;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DisplaySurveyResponseRepository implements IDisplaySurveyResponseRepository {

    private static final Logger LOG = LogManager.getLogger();

    @Override
    public List<String> getUsersWhoAnsweredSurvey(String courseId) {
        List<String> users = new ArrayList<>();
        StoredProcedure getUsers = null;
        try{
            getUsers = new StoredProcedure("GetUniqueUsers(?)");
            getUsers.setParameter(1,courseId);
            ResultSet rs = getUsers.executeWithResults();
            while(rs.next()){
                users.add(rs.getString(1));
            }
            getUsers.cleanup();
            LOG.info("Operation = getUsersWhoAnsweredSurvey, Status = Success ");
        }
        catch (SQLException throwables){
            throwables.printStackTrace();
            LOG.error("Operation = getUsersWhoAnsweredSurvey, Status = Failed, Error Message="+throwables.getMessage());
        }
        catch (Exception e){
            e.printStackTrace();
            LOG.error("Operation = getUsersWhoAnsweredSurvey, Status = Failed, Error Message="+e.getMessage());
        }
        finally {
         getUsers.cleanup();
        }
        return users;
    }

    @Override
    public List<ISurveyQuestionOptionsModel> getSurveyResponse(List<String> users, String courseId) {
        List<ISurveyQuestionOptionsModel> studentResponse = new ArrayList<ISurveyQuestionOptionsModel>();
        StoredProcedure getSurveyQuestion = null;
        StoredProcedure getOption = null;
        try{
            for(String bannerId : users) {
                getSurveyQuestion = new StoredProcedure("GetSurveyQuestionByCourse(?)");
                getSurveyQuestion.setParameter(1, courseId);
                ResultSet rs = getSurveyQuestion.executeWithResults();
                while (rs.next()) {
                    List<String> userAnswers = new ArrayList<String>();
                    StoredProcedure getAnswers = new StoredProcedure("GetSurveyAnswersByUserId(?,?)");
                    getAnswers.setParameter(1,bannerId);
                    getAnswers.setParameter(2,rs.getInt("questionId"));
                    ResultSet answers = getAnswers.executeWithResults();

                    while(answers.next()){
                        String questionType = rs.getString("questionType");
                        if( questionType== "mcqs" || questionType == "mcqm") {
                            getOption = new StoredProcedure("GetOptionFromRank(?,?)");
                            getOption.setParameter(1,rs.getInt("questionId"));
                            getOption.setParameter(2, Integer.parseInt(rs.getString("answer")));
                            ResultSet getOptionDesc = getOption.executeWithResults();
                            getOptionDesc.next();
                            userAnswers.add(getOptionDesc.getString("optionsDesc"));
                        }
                        else {
                            userAnswers.add(answers.getString(1));
                        }
                    }
                    getAnswers.cleanup();
                    ISurveyQuestionOptionsModel questionAnswer = new SurveyQuestionOptionsModel();
                    questionAnswer.setSurveyQuestionDescription(rs.getString("questionDesc"));
                    questionAnswer.setSurveyQuestionId(rs.getInt("questionId"));
                    questionAnswer.setSurveyQuestionType(rs.getString("questionType"));
                    questionAnswer.setSurveyQuestionTopic(rs.getString("questionTopic"));
                    questionAnswer.setSurveyAnswers(userAnswers);
                    questionAnswer.setBannerId(bannerId);
                    studentResponse.add(questionAnswer);
                }
                getSurveyQuestion.cleanup();
            }
            LOG.info("Operation = getSurveyResponse, Status = Success ");
        }
        catch (SQLException throwables){
            throwables.printStackTrace();
            LOG.error("Operation = getSurveyResponse, Status = Failed, Error Message="+throwables.getMessage());
        }
        catch (Exception e){
            e.printStackTrace();
            LOG.error("Operation = getSurveyResponse, Status = Failed, Error Message="+e.getMessage());
        }
        finally {
            if(null != getOption){
                getOption.cleanup();
            }
            getSurveyQuestion.cleanup();
        }
        return studentResponse;
    }
}
