package CSCI5308.GroupFormationTool.QuestionManager.Repository;

import CSCI5308.GroupFormationTool.Database.StoredProcedure;
import CSCI5308.GroupFormationTool.QuestionManager.AccessControl.IQuestionManagerRepository;
import CSCI5308.GroupFormationTool.QuestionManager.Model.Question;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class QuestionManagerRepository implements IQuestionManagerRepository {
    private List<Question> questionList = new ArrayList<Question>();
    @Override
    public List<Question> getQuestions(String userId) {
        try{
            questionList.clear();
            StoredProcedure sp = new StoredProcedure("Questions(?)");
            sp.setParameter(1,userId);
            ResultSet rs= sp.executeWithResults();
            while(rs.next()){
                Question temp = new Question();
                temp.setDate(rs.getDate("dateStamp"));
                temp.setQuestionTopic(rs.getString("questionTopic"));
                temp.setQuestionDescription(rs.getString("questionDesc"));
                temp.setQuestionId(rs.getInt("questionId"));
                questionList.add(temp);
            }
            sp.cleanup();
        }catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return questionList;
    }

    @Override
    public void deleteQuestion(Integer questionId, String userId) {
        try{
           StoredProcedure sp =new StoredProcedure("DeleteQuestion(?,?)");
           sp.setParameter(1,userId);
           sp.setParameter(2,questionId);
           sp.execute();
           sp.cleanup();
        }catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public List<Question> getQuestionsByTopic(String userId) {
        try{
            questionList.clear();
            StoredProcedure sp = new StoredProcedure("QuestionsByTopic(?)");
            sp.setParameter(1,userId);
            ResultSet rs= sp.executeWithResults();
            while(rs.next()){
                Question temp = new Question();
                temp.setDate(rs.getDate("dateStamp"));
                temp.setQuestionTopic(rs.getString("questionTopic"));
                temp.setQuestionDescription(rs.getString("questionDesc"));
                temp.setQuestionId(rs.getInt("questionId"));
                questionList.add(temp);
            }
            sp.cleanup();
        }catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return questionList;
    }

    @Override
    public List<Question> getQuestionsByDate(String userId) {
        try{
            questionList.clear();
            StoredProcedure sp = new StoredProcedure("QuestionsByDate(?)");
            sp.setParameter(1,userId);
            ResultSet rs= sp.executeWithResults();
            while(rs.next()){
                Question temp = new Question();
                temp.setDate(rs.getDate("dateStamp"));
                temp.setQuestionTopic(rs.getString("questionTopic"));
                temp.setQuestionDescription(rs.getString("questionDesc"));
                temp.setQuestionId(rs.getInt("questionId"));
                questionList.add(temp);
            }
            sp.cleanup();
        }catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return questionList;
    }
}
