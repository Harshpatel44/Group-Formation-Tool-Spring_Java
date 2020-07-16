package CSCI5308.GroupFormationTool.QuestionManager;

import CSCI5308.GroupFormationTool.Database.DatabaseAbstractFactory;
import CSCI5308.GroupFormationTool.Database.StoredProcedure;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class QuestionManagerRepository implements IQuestionManagerRepository {
    private List<IQuestion> questionList = new ArrayList<IQuestion>();
    private DatabaseAbstractFactory databaseAbstractFactory;

    @Override
    public List<IQuestion> getQuestions(String userId) {
        databaseAbstractFactory = DatabaseAbstractFactory.instance();
        try{
            questionList.clear();
            StoredProcedure sp = databaseAbstractFactory.createStoredProcedure("Questions(?)");
            sp.setParameter(1,userId);
            ResultSet rs= sp.executeWithResults();
            while(rs.next()){
                IQuestion temp = QuestionManagerAbstractFactory.instance().getQuestion();
                temp.setDate(rs.getDate("dateStamp"));
                temp.setQuestionTopic(rs.getString("questionTopic"));
                temp.setQuestionDescription(rs.getString("questionDesc"));
                temp.setQuestionId(rs.getInt("questionId"));
                questionList.add(temp);
            }
            sp.cleanup();
        }catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return questionList;
    }

    @Override
    public void deleteQuestion(Integer questionId, String userId) throws Exception {
        databaseAbstractFactory = DatabaseAbstractFactory.instance();
        try{
           StoredProcedure sp =databaseAbstractFactory.createStoredProcedure("DeleteQuestion(?,?)");
           sp.setParameter(1,userId);
           sp.setParameter(2,questionId);
           sp.execute();
           sp.cleanup();
        }catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public List<IQuestion> getQuestionsByTopic(String userId) {
        databaseAbstractFactory = DatabaseAbstractFactory.instance();
        try{
            questionList.clear();
            StoredProcedure sp = databaseAbstractFactory.createStoredProcedure("QuestionsByTopic(?)");
            sp.setParameter(1,userId);
            ResultSet rs= sp.executeWithResults();
            while(rs.next()){
                IQuestion temp = QuestionManagerAbstractFactory.instance().getQuestion();
                temp.setDate(rs.getDate("dateStamp"));
                temp.setQuestionTopic(rs.getString("questionTopic"));
                temp.setQuestionDescription(rs.getString("questionDesc"));
                temp.setQuestionId(rs.getInt("questionId"));
                questionList.add(temp);
            }
            sp.cleanup();
        }catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return questionList;
    }

    @Override
    public List<IQuestion> getQuestionsByDate(String userId) throws Exception {
        databaseAbstractFactory = DatabaseAbstractFactory.instance();
        try{
            questionList.clear();
            StoredProcedure sp = databaseAbstractFactory.createStoredProcedure("QuestionsByDate(?)");
            sp.setParameter(1,userId);
            ResultSet rs= sp.executeWithResults();
            while(rs.next()){
                IQuestion temp = QuestionManagerAbstractFactory.instance().getQuestion();
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
