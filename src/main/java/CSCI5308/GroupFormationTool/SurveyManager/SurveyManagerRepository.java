package CSCI5308.GroupFormationTool.SurveyManager;

import CSCI5308.GroupFormationTool.Course.CurrentCourse;
import CSCI5308.GroupFormationTool.Database.DatabaseAbstractFactory;
import CSCI5308.GroupFormationTool.Database.StoredProcedure;

import CSCI5308.GroupFormationTool.QuestionManager.IQuestion;
import CSCI5308.GroupFormationTool.QuestionManager.Question;
import CSCI5308.GroupFormationTool.QuestionManager.QuestionManagerAbstractFactory;
import CSCI5308.GroupFormationTool.UserManager.CurrentUser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import java.util.logging.FileHandler;

public class SurveyManagerRepository implements ISurveyManagerRepository{
    private DatabaseAbstractFactory databaseAbstractFactory;
    private List<IQuestion> AlreadyQuestionList = new ArrayList<IQuestion>();
    private List<IQuestion> NotAddedQuestionList = new ArrayList<IQuestion>();
    private static final Logger LOG = LogManager.getLogger();

    @Override
    public void getSurveyQuestions() throws Exception {
        databaseAbstractFactory = DatabaseAbstractFactory.instance();
        try{
            AlreadyQuestionList.clear();
            NotAddedQuestionList.clear();
            String userId = CurrentUser.instance().getBannerId();
            String courseId = CurrentCourse.instance().getCurrentCourseId();

            StoredProcedure sp =  databaseAbstractFactory.createStoredProcedure("SurveyQuestions(?,?)");
            sp.setParameter(1,userId);
            sp.setParameter(2,courseId);
            ResultSet rs = sp.executeWithResults();
            while(rs.next()){
                IQuestion temp = QuestionManagerAbstractFactory.instance().getQuestion();
                temp.setQuestionTopic(rs.getString("questionTopic"));
                temp.setQuestionId(rs.getInt("questionId"));
                temp.setFlag(rs.getInt("flag"));
                if(temp.getFlag()==0){
                    NotAddedQuestionList.add(temp);
                }
                else{
                    AlreadyQuestionList.add(temp);
                }
            }
            sp.cleanup();
        }catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<IQuestion> AlreadyAddedSurveyQuestions() throws Exception {
        return AlreadyQuestionList;
    }

    @Override
    public List<IQuestion> NotAddedSurveyQuestions() throws Exception {
        return NotAddedQuestionList;
    }

    @Override
    public void AddQuestionToSurvey(Integer questionId) {
        databaseAbstractFactory = DatabaseAbstractFactory.instance();
        try{
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
            LocalDateTime datetime = LocalDateTime.now();
            String courseId = CurrentCourse.instance().getCurrentCourseId();
            String userId = CurrentUser.instance().getBannerId();
            StoredProcedure sp = databaseAbstractFactory.createStoredProcedure("AddQuestionToSurvey(?,?,?,?)");
            sp.setParameter(1,userId);
            sp.setParameter(2,courseId);
            sp.setParameter(3,questionId);
            sp.setParameter(4,dtf.format(datetime));
            sp.execute();
            sp.cleanup();
        }catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void RemoveQuestionFromSurvey(Integer questionId){
        databaseAbstractFactory = DatabaseAbstractFactory.instance();
        try{
            String courseId = CurrentCourse.instance().getCurrentCourseId();
            String userId = CurrentUser.instance().getBannerId();
            StoredProcedure sp =databaseAbstractFactory.createStoredProcedure("RemoveQuestionFromSurvey(?,?,?)");
            sp.setParameter(1,userId);
            sp.setParameter(2,courseId);
            sp.setParameter(3,questionId);
            sp.execute();
            sp.cleanup();
        }catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void PublishSurvey(){
        databaseAbstractFactory = DatabaseAbstractFactory.instance();
        try{
            String courseId = CurrentCourse.instance().getCurrentCourseId();
            String userId = CurrentUser.instance().getBannerId();
            StoredProcedure sp = databaseAbstractFactory.createStoredProcedure("AddSurveyToPublish(?,?)");
            sp.setParameter(1,userId);
            sp.setParameter(2,courseId);
            sp.execute();
            sp.cleanup();
        }catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean checkPublish(){
        boolean isPublished = false;
        databaseAbstractFactory = DatabaseAbstractFactory.instance();
        try {
            String courseId = CurrentCourse.instance().getCurrentCourseId();
            String userId = CurrentUser.instance().getBannerId();
            StoredProcedure sp =databaseAbstractFactory.createStoredProcedure("IsSurveyPublished(?,?)");
            sp.setParameter(1, userId);
            sp.setParameter(2, courseId);
            ResultSet rs = sp.executeWithResults();
            if(rs.next())
            {
                isPublished = true;
            }
            LOG.info("Operation = CheckPublish, Status = Success ");
        }
        catch (SQLException throwables) {
            LOG.error("Operation = CheckPublish, Status = Failed, Error Message="+throwables.getMessage());
        } catch (Exception e) {
            LOG.error("Operation = CheckPublish, Status = Failed, Error Message="+e.getMessage());
        }
        return isPublished;
    }

    @Override
    public void UnpublishSurvey() {
        databaseAbstractFactory = DatabaseAbstractFactory.instance();
        try{
            String courseId = CurrentCourse.instance().getCurrentCourseId();
            String userId = CurrentUser.instance().getBannerId();
            StoredProcedure sp =databaseAbstractFactory.createStoredProcedure("RemoveSurveyFromoPublish(?,?)");
            sp.setParameter(1,userId);
            sp.setParameter(2,courseId);
            sp.execute();
            sp.cleanup();
        }catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
