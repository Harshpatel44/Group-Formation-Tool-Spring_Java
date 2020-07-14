package CSCI5308.GroupFormationTool.AnswerSurvey;

import CSCI5308.GroupFormationTool.Database.StoredProcedure;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class DisplaySurveyResponseRepository implements IDisplaySurveyResponseRepository {

    @Override
    public List<String> getUsersWhoAnsweredSurvey(String courseId) {
        List<String> users = new ArrayList<>();
        try{
            StoredProcedure getUsers = new StoredProcedure("GetUniqueUsers(?)");
            getUsers.setParameter(1,courseId);
            ResultSet rs = getUsers.executeWithResults();
            while(rs.next()){
                users.add(rs.getString(1));
            }
            getUsers.cleanup();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public List<ISurveyQuestionOptionsModel> getSurveyResponse(List<String> users, String courseId) {
        List<ISurveyQuestionOptionsModel> studentResponse = new ArrayList<ISurveyQuestionOptionsModel>();
        try{
            for(String bannerId : users) {
                StoredProcedure getSurveyQuestion = new StoredProcedure("GetSurveyQuestionByCourse(?)");
                getSurveyQuestion.setParameter(1, courseId);
                ResultSet rs = getSurveyQuestion.executeWithResults();
                while (rs.next()) {
                    List<String> userAnswers = new ArrayList<String>();
                    StoredProcedure getAnswers = new StoredProcedure("GetSurveyAnswersByUserId(?,?)");
                    getAnswers.setParameter(1,bannerId);
                    getAnswers.setParameter(2,rs.getInt("questionId"));
                    ResultSet answers = getAnswers.executeWithResults();
                    while(answers.next()){
                        userAnswers.add(answers.getString(1));
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
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return studentResponse;
    }
}
