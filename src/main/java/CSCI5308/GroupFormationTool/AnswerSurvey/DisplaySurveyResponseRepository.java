package CSCI5308.GroupFormationTool.AnswerSurvey;

import CSCI5308.GroupFormationTool.Database.StoredProcedure;
import java.util.HashMap;
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

    @Override
    public HashMap<String, HashMap<Integer, ISurveyQuestionOptionsModel>> getSurveyResponse_2(String courseId) {
        List<ISurveyQuestionOptionsModel> studentResponse = new ArrayList<ISurveyQuestionOptionsModel>();
        HashMap<String, HashMap<Integer, ISurveyQuestionOptionsModel>> studentWithQuestionAndAnswer = new HashMap<String, HashMap<Integer, ISurveyQuestionOptionsModel>>();
        HashMap<Integer, ISurveyQuestionOptionsModel> questionMapper = new HashMap<Integer, ISurveyQuestionOptionsModel>();
        try {

            StoredProcedure getSurveyQuestion = new StoredProcedure("GetSurveyQuestionByCourse(?)");
            getSurveyQuestion.setParameter(1, courseId);
            ResultSet question_rs = getSurveyQuestion.executeWithResults();
            while (question_rs.next()) {
                ISurveyQuestionOptionsModel questionAnswer = new SurveyQuestionOptionsModel();
                questionAnswer.setSurveyQuestionDescription(question_rs.getString("questionDesc"));
                questionAnswer.setSurveyQuestionId(question_rs.getInt("questionId"));
                questionAnswer.setSurveyQuestionType(question_rs.getString("questionType"));
                questionAnswer.setSurveyQuestionTopic(question_rs.getString("questionTopic"));
                questionMapper.put(question_rs.getInt("questionId"), questionAnswer);
            }
//			getSurveyQuestion.cleanup();
            StoredProcedure getAnswers = new StoredProcedure("GetSurveyAnswers(?)");
            getAnswers.setParameter(1, courseId);
            ResultSet rs = getAnswers.executeWithResults();

            List<String> questionIDs = new ArrayList<String>();
            while (rs.next()) {
                String userID = rs.getString("userId");
                Integer questionID = rs.getInt("questionId");
                if (studentWithQuestionAndAnswer.containsKey(rs.getString("userId"))) {
                    if (studentWithQuestionAndAnswer.get(userID).containsKey(questionID)) {
                        studentWithQuestionAndAnswer.get(userID).get(questionID).getSurveyAnswers()
                                .add(rs.getString("optionRank"));
                    } else {
                        ISurveyQuestionOptionsModel questionAnswer = questionMapper.get(rs.getInt("questionId"));
                        List<String> answers = new ArrayList<>();
                        answers.add(rs.getString("optionRank"));
                        questionAnswer.setSurveyAnswers(answers);
                        questionAnswer.setBannerId(rs.getString("userId"));
                        studentWithQuestionAndAnswer.get(rs.getString("userId")).put(rs.getInt("questionId"),
                                questionAnswer);

                    }
                } else {

                    ISurveyQuestionOptionsModel questionAnswer = questionMapper.get(rs.getInt("questionId"));
                    List<String> answers = new ArrayList<>();
                    answers.add(rs.getString("optionRank"));
                    questionAnswer.setSurveyAnswers(answers);
                    questionAnswer.setBannerId(rs.getString("userId"));

                    studentWithQuestionAndAnswer.put(rs.getString("userId"),
                            new HashMap<Integer, ISurveyQuestionOptionsModel>() {
                                {
                                    put(rs.getInt("questionId"), questionAnswer);
                                }
                            });
                }
            }
            getAnswers.cleanup();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return studentWithQuestionAndAnswer;
    }

}
