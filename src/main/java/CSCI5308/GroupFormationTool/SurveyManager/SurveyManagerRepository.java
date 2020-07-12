package CSCI5308.GroupFormationTool.SurveyManager;

import CSCI5308.GroupFormationTool.Course.CurrentCourse;
import CSCI5308.GroupFormationTool.Database.StoredProcedure;

import CSCI5308.GroupFormationTool.QuestionManager.IQuestion;
import CSCI5308.GroupFormationTool.QuestionManager.Question;
import CSCI5308.GroupFormationTool.UserManager.CurrentUser;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

public class SurveyManagerRepository implements ISurveyManagerRepository{

    private List<IQuestion> AlreadyQuestionList = new ArrayList<IQuestion>();
    private List<IQuestion> NotAddedQuestionList = new ArrayList<IQuestion>();


    @Override
    public void getSurveyQuestions(String courseId) throws Exception {
        try{
            AlreadyQuestionList.clear();
            NotAddedQuestionList.clear();
            String userId = CurrentUser.instance().getBannerId();
            StoredProcedure sp = new StoredProcedure("SurveyQuestions(?,?)");
            sp.setParameter(1,userId);
            sp.setParameter(2,courseId);
            System.out.println(userId);
            System.out.println(courseId);
            ResultSet rs = sp.executeWithResults();
            System.out.println("in repo general after result set");
            while(rs.next()){
                System.out.println("in repo general in while");
                IQuestion temp = new Question();
                temp.setQuestionTopic(rs.getString("questionTopic"));
                temp.setQuestionId(rs.getInt("questionId"));
                temp.setFlag(rs.getInt("flag"));
                System.out.println(temp.getFlag());
                System.out.println(temp.getQuestionId());
                System.out.println(temp.getQuestionTopic());
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
        System.out.println(AlreadyQuestionList);
        return AlreadyQuestionList;
    }

    @Override
    public List<IQuestion> NotAddedSurveyQuestions() throws Exception {
        System.out.println(NotAddedQuestionList);
        return NotAddedQuestionList;
    }

    @Override
    public void AddQuestionToSurvey(Integer questionId) {
        try{
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
            LocalDateTime datetime = LocalDateTime.now();
            System.out.println(dtf.format(datetime));
            String courseId = CurrentCourse.instance().getCurrentCourseId();
            String userId = CurrentUser.instance().getBannerId();
            StoredProcedure sp = new StoredProcedure("AddQuestionToSurvey(?,?,?,?)");
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
    public void RemoveQuestionFromSurvey(Integer questionId) {
        try{
            String courseId = CurrentCourse.instance().getCurrentCourseId();
            String userId = CurrentUser.instance().getBannerId();
            StoredProcedure sp = new StoredProcedure("RemoveQuestionFromSurvey(?,?,?)");
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
    public void PublishSurvey() {
        try{
            String courseId = CurrentCourse.instance().getCurrentCourseId();
            String userId = CurrentUser.instance().getBannerId();
            StoredProcedure sp = new StoredProcedure("AddSurveyToPublish(?,?)");
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
    public boolean checkPublish() {
        boolean isPublished = false;
        try {
            String courseId = CurrentCourse.instance().getCurrentCourseId();
            String userId = CurrentUser.instance().getBannerId();
            StoredProcedure sp = new StoredProcedure("IsSurveyPublished(?,?)");
            sp.setParameter(1, userId);
            sp.setParameter(2, courseId);
            ResultSet rs = sp.executeWithResults();
            if(rs.next())
            {
                isPublished = true;
            }
        }
        catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return isPublished;
    }

    @Override
    public void UnpublishSurvey() {
        try{
            String courseId = CurrentCourse.instance().getCurrentCourseId();
            String userId = CurrentUser.instance().getBannerId();
            StoredProcedure sp = new StoredProcedure("RemoveSurveyFromoPublish(?,?)");
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
