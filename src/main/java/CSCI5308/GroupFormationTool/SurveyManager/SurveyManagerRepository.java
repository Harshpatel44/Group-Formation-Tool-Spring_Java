package CSCI5308.GroupFormationTool.SurveyManager;

import CSCI5308.GroupFormationTool.Course.CurrentCourse;
import CSCI5308.GroupFormationTool.Database.DatabaseAbstractFactory;
import CSCI5308.GroupFormationTool.Database.StoredProcedure;
import CSCI5308.GroupFormationTool.QuestionManager.IQuestion;
import CSCI5308.GroupFormationTool.QuestionManager.QuestionManagerAbstractFactory;
import CSCI5308.GroupFormationTool.UserManager.CurrentUser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class SurveyManagerRepository implements ISurveyManagerRepository {
    private static final Logger LOG = LogManager.getLogger();
    private DatabaseAbstractFactory databaseAbstractFactory;
    private final List<IQuestion> AlreadyQuestionList = new ArrayList<IQuestion>();
    private final List<IQuestion> NotAddedQuestionList = new ArrayList<IQuestion>();

    @Override
    public void getSurveyQuestions() throws Exception {
        StoredProcedure sp = null;
        databaseAbstractFactory = DatabaseAbstractFactory.instance();
        String userId = CurrentUser.instance().getBannerId();
        String courseId = CurrentCourse.instance().getCurrentCourseId();
        try {
            AlreadyQuestionList.clear();
            NotAddedQuestionList.clear();
            sp = databaseAbstractFactory.createStoredProcedure("SurveyQuestions(?,?)");
            sp.setParameter(1, userId);
            sp.setParameter(2, courseId);
            ResultSet rs = sp.executeWithResults();
            while (rs.next()) {
                IQuestion temp = QuestionManagerAbstractFactory.instance().getQuestion();
                temp.setQuestionTopic(rs.getString("questionTopic"));
                temp.setQuestionId(rs.getInt("questionId"));
                temp.setFlag(rs.getInt("flag"));
                if (temp.getFlag() == 0) {
                    NotAddedQuestionList.add(temp);
                    LOG.info("Operation = adding question to NotInSurvey list , Status = Success ");
                } else {
                    AlreadyQuestionList.add(temp);
                    LOG.info("Operation = adding question to InSurvey list , Status = Success ");
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            LOG.error("Operation = survey question list, Status = Failed, Error Message=" + throwables.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            LOG.error("Operation = survey question list, Status = Failed, Error Message=" + e.getMessage());
        } finally {
            if (sp != null) {
                sp.cleanup();
            }
        }
    }

    @Override
    public List<IQuestion> AlreadyAddedSurveyQuestions() throws Exception {
        LOG.info("Operation = Return list for already added survey list , Status = Success ");
        return AlreadyQuestionList;
    }

    @Override
    public List<IQuestion> NotAddedSurveyQuestions() throws Exception {
        LOG.info("Operation = Return list for not added survey list , Status = Success ");
        return NotAddedQuestionList;
    }

    @Override
    public void AddQuestionToSurvey(Integer questionId) {
        StoredProcedure sp = null;
        databaseAbstractFactory = DatabaseAbstractFactory.instance();
        String courseId = CurrentCourse.instance().getCurrentCourseId();
        String userId = CurrentUser.instance().getBannerId();
        try {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
            LocalDateTime datetime = LocalDateTime.now();
            sp = databaseAbstractFactory.createStoredProcedure("AddQuestionToSurvey(?,?,?,?)");
            sp.setParameter(1, userId);
            sp.setParameter(2, courseId);
            sp.setParameter(3, questionId);
            sp.setParameter(4, dtf.format(datetime));
            sp.execute();
            LOG.info("Operation = add question QuestionId:" + questionId + " for CourseID:" + courseId + " and userID:" + userId + ", Status = Success ");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            LOG.error("Operation = add question QuestionId:" + questionId + " for CourseID:" + courseId + " and userID:" + userId + ", Status = Failed, Error Message=" + throwables.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            LOG.error("Operation = add question QuestionId:" + questionId + " for CourseID:" + courseId + " and userID:" + userId + ", Status = Failed, Error Message=" + e.getMessage());
        } finally {
            if (sp != null) {
                sp.cleanup();
            }
        }
    }

    @Override
    public void RemoveQuestionFromSurvey(Integer questionId) {
        StoredProcedure sp = null;
        databaseAbstractFactory = DatabaseAbstractFactory.instance();
        String courseId = CurrentCourse.instance().getCurrentCourseId();
        String userId = CurrentUser.instance().getBannerId();
        try {
            sp = databaseAbstractFactory.createStoredProcedure("RemoveQuestionFromSurvey(?,?,?)");
            sp.setParameter(1, userId);
            sp.setParameter(2, courseId);
            sp.setParameter(3, questionId);
            sp.execute();
            LOG.info("Operation = Remove QuestionId:" + questionId + " for CourseID:" + courseId + " and userID:" + userId + ", Status = Success ");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            LOG.error("Operation = Remove QuestionId:" + questionId + " for CourseID:" + courseId + " and userID:" + userId + ", Status = Failed, Error Message=" + throwables.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            LOG.error("Operation = Remove QuestionId:" + questionId + " for CourseID:" + courseId + " and userID:" + userId + ", Status = Failed, Error Message=" + e.getMessage());
        } finally {
            if (sp != null) {
                sp.cleanup();
            }
        }
    }

    @Override
    public void PublishSurvey() {
        StoredProcedure sp = null;
        databaseAbstractFactory = DatabaseAbstractFactory.instance();
        String courseId = CurrentCourse.instance().getCurrentCourseId();
        String userId = CurrentUser.instance().getBannerId();
        try {
            sp = databaseAbstractFactory.createStoredProcedure("AddSurveyToPublish(?,?)");
            sp.setParameter(1, userId);
            sp.setParameter(2, courseId);
            sp.execute();
            LOG.info("Operation = Publish Survey for CourseID:" + courseId + " and userID:" + userId + ", Status = Success ");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            LOG.error("Operation = Publish Survey for CourseID:" + courseId + " and userID:" + userId + ", Status = Failed, Error Message=" + throwables.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            LOG.error("Operation = Publish Survey for CourseID:" + courseId + " and userID:" + userId + ", Status = Failed, Error Message=" + e.getMessage());
        } finally {
            if (sp != null) {
                sp.cleanup();
            }
        }
    }

    @Override
    public boolean checkPublish() {
        boolean isPublished = false;
        StoredProcedure sp = null;
        databaseAbstractFactory = DatabaseAbstractFactory.instance();
        String courseId = CurrentCourse.instance().getCurrentCourseId();
        String userId = CurrentUser.instance().getBannerId();
        try {
            sp = databaseAbstractFactory.createStoredProcedure("IsSurveyPublished(?,?)");
            sp.setParameter(1, userId);
            sp.setParameter(2, courseId);
            ResultSet rs = sp.executeWithResults();
            if (rs.next()) {
                isPublished = true;
            }
            LOG.info("Operation = CheckPublish for CourseID:" + courseId + " and userID:" + userId + ", Status = Success ");
        } catch (SQLException throwables) {
            LOG.error("Operation = CheckPublish for CourseID:" + courseId + " and userID:" + userId + ", Status = Failed, Error Message=" + throwables.getMessage());
        } catch (Exception e) {
            LOG.error("Operation = CheckPublish for CourseID:" + courseId + " and userID:" + userId + ", Status = Failed, Error Message=" + e.getMessage());
        } finally {
            if (sp != null) {
                sp.cleanup();
            }
        }
        return isPublished;
    }

    @Override
    public void UnpublishSurvey() {
        StoredProcedure sp = null;
        databaseAbstractFactory = DatabaseAbstractFactory.instance();
        String courseId = CurrentCourse.instance().getCurrentCourseId();
        String userId = CurrentUser.instance().getBannerId();
        try {
            sp = databaseAbstractFactory.createStoredProcedure("RemoveSurveyFromoPublish(?,?)");
            sp.setParameter(1, userId);
            sp.setParameter(2, courseId);
            sp.execute();
            LOG.info("Operation = UnPublish for CourseID:" + courseId + " and userID:" + userId + ", Status = Success ");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            LOG.error("Operation = UnPublish for CourseID:" + courseId + " and userID:" + userId + ", Status = Failed, Error Message=" + throwables.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            LOG.error("Operation = UnPublish for CourseID:" + courseId + " and userID:" + userId + ", Status = Failed, Error Message=" + e.getMessage());
        } finally {
            if (sp != null) {
                sp.cleanup();
            }
        }
    }
}
