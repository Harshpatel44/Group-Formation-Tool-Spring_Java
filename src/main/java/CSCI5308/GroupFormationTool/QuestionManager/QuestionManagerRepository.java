package CSCI5308.GroupFormationTool.QuestionManager;

import CSCI5308.GroupFormationTool.Database.DatabaseAbstractFactory;
import CSCI5308.GroupFormationTool.Database.StoredProcedure;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class QuestionManagerRepository implements IQuestionManagerRepository {
    private List<IQuestion> questionList = new ArrayList<IQuestion>();
    private DatabaseAbstractFactory databaseAbstractFactory;
    private static final Logger LOG = LogManager.getLogger();
    @Override
    public List<IQuestion> getQuestions(String userId) {
        databaseAbstractFactory = DatabaseAbstractFactory.instance();
        StoredProcedure sp =null;
        try{
            questionList.clear();
            sp = databaseAbstractFactory.createStoredProcedure("Questions(?)");
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
            LOG.info("Operation = adding question responses , Status = Success ");
        }catch (SQLException throwables) {
            throwables.printStackTrace();
            LOG.error("Operation = adding question responses, Status = Failed, Error Message="+throwables.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            LOG.error("Operation = adding question responses, Status = Failed, Error Message="+e.getMessage());
        }
        finally {
            if(sp!=null){
                sp.cleanup();
            }
        }
        return questionList;
    }

    @Override
    public void deleteQuestion(Integer questionId, String userId) throws Exception {
        databaseAbstractFactory = DatabaseAbstractFactory.instance();
        StoredProcedure sp =  null;
        try{
           sp =databaseAbstractFactory.createStoredProcedure("DeleteQuestion(?,?)");
           sp.setParameter(1,userId);
           sp.setParameter(2,questionId);
           sp.execute();
            LOG.info("Operation = delete Question Id:"+questionId+" , Status = Success ");
        }catch (SQLException throwables) {
            throwables.printStackTrace();
            LOG.error("Operation = delete Question, Status = Failed, Error Message="+throwables.getMessage());
        }
        finally {
            if(sp!=null){
                sp.cleanup();
            }
        }
    }

    @Override
    public List<IQuestion> getQuestionsByTopic(String userId) {
        databaseAbstractFactory = DatabaseAbstractFactory.instance();
        StoredProcedure sp =  null;
        try{
            questionList.clear();
            sp = databaseAbstractFactory.createStoredProcedure("QuestionsByTopic(?)");
            sp.setParameter(1,userId);
            ResultSet rs= sp.executeWithResults();
            while(rs.next()){
                IQuestion temp = QuestionManagerAbstractFactory.instance().getQuestion();
                temp.setDate(rs.getDate("dateStamp"));
                temp.setQuestionTopic(rs.getString("questionTopic"));
                temp.setQuestionDescription(rs.getString("questionDesc"));
                temp.setQuestionId(rs.getInt("questionId"));
                questionList.add(temp);
                LOG.info("Operation = get Question for userId:"+userId+" , Status = Success ");
            }

        }catch (SQLException throwables) {
            throwables.printStackTrace();
            LOG.error("Operation = get Question for userId:"+userId+" , Status = Failed, Error Message="+throwables.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            LOG.error("Operation = get Question for userId:"+userId+" , Status = Failed, Error Message="+e.getMessage());
        }
        finally {
            if(sp!=null){
                sp.cleanup();
            }
        }
        return questionList;
    }

    @Override
    public List<IQuestion> getQuestionsByDate(String userId) throws Exception {
        databaseAbstractFactory = DatabaseAbstractFactory.instance();
        StoredProcedure sp = null;
        try{
            questionList.clear();
            sp = databaseAbstractFactory.createStoredProcedure("QuestionsByDate(?)");
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
            LOG.info("Operation = get Question for userId:"+userId+" , Status = Success ");
        }catch (SQLException throwables) {
            throwables.printStackTrace();
            LOG.error("Operation = get Question for userId:"+userId+" , Status = Failed, Error Message="+throwables.getMessage());
        }
        finally {
            if(sp!=null){
                sp.cleanup();
            }
        }
        return questionList;
    }
}
