package CSCI5308.GroupFormationTool.QuestionEditor;

import CSCI5308.GroupFormationTool.Course.CourseAbstractFactory;
import CSCI5308.GroupFormationTool.Course.ICourseRepository;
import CSCI5308.GroupFormationTool.Database.DatabaseAbstractFactory;
import CSCI5308.GroupFormationTool.Database.StoredProcedure;
import CSCI5308.GroupFormationTool.UserManager.IUserRepository;
import CSCI5308.GroupFormationTool.UserManager.UserManagerAbstractFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import static CSCI5308.GroupFormationTool.ApplicationConstants.*;

public class QuestionEditorRepository implements IQuestionEditorRepository {

    private static final Logger LOG = LogManager.getLogger();
    private DatabaseAbstractFactory databaseAbstractFactory;
    private IQuestionEditorRepository questionEditorRepository;
    private IUserRepository userRepository;
    private ICourseRepository courseRepository;

    @Override
    public boolean SaveTextAndNumericTypeQuestionRepo(String questionText, String questionTitle, String selectedQuestionType, String userId) {
        String questionType = changeQuestionTypeName(selectedQuestionType);
        databaseAbstractFactory = DatabaseAbstractFactory.instance();
        questionEditorRepository = QuestionEditorAbstractFactory.instance().getQuestionEditorRepository();
        StoredProcedure storedProcedure = null;
        try {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
            LocalDateTime datetime = LocalDateTime.now();
            String time = dtf.format(datetime);
            storedProcedure = databaseAbstractFactory.createStoredProcedure("SaveQuestionToDB(?,?,?,?,?)");
            storedProcedure.setParameter("uId", userId);
            storedProcedure.setParameter("qTopic", questionTitle);
            storedProcedure.setParameter("qDesc", questionText);
            storedProcedure.setParameter("qType", questionType);
            storedProcedure.setParameter("dStamp", time);
            storedProcedure.execute();

            int qID = questionEditorRepository.getQuestionIDFromTopic(questionTitle, time);
            saveQuestionToSurveyQuestions(userId, qID, questionTitle, questionText, questionType, time);
            LOG.info("Operation = Save text and numeric questions for userId " + userId + ", Status = Success");
            return true;
        } catch (Exception e) {
            LOG.error("Operation = Save text and numeric questions for userId " + userId + ", Status = Fail, Error Message=" + e.getMessage());
            e.printStackTrace();
            return false;
        } finally {
            if (storedProcedure != null) {
                storedProcedure.cleanup();
            }

        }
    }

    @Override
    public int getQuestionIDFromTopic(String questionTitle, String dStamp) {
        StoredProcedure storedProcedure = null;
        databaseAbstractFactory = DatabaseAbstractFactory.instance();
        try {
            storedProcedure = databaseAbstractFactory.createStoredProcedure("questionIdFromTopic(?,?)");
            storedProcedure.setParameter("qTopic", questionTitle);
            storedProcedure.setParameter("tm", dStamp);
            ResultSet rs = storedProcedure.executeWithResults();
            while (rs.next()) {
                return rs.getInt("questionId");
            }
            LOG.info("Operation = Save text and numeric questions, get QuestionID for topic function, Status = Success");
        } catch (Exception e) {
            LOG.error("Operation = Save text and numeric questions, get QuestionID for topic function, Status = Fail, Error Message=" + e.getMessage());
            e.printStackTrace();
        } finally {
            if (storedProcedure != null) {
                storedProcedure.cleanup();
            }
        }
        return 0;
    }

    private boolean saveQuestionToSurveyQuestions(String userId, int qId, String questionTitle, String questionText, String questionType, String time) {
        userRepository = UserManagerAbstractFactory.instance().getUserRepository();
        courseRepository = CourseAbstractFactory.instance().getCourseRepository();
        try {
            int roleId = userRepository.getUserRoleIdFromRoleType(instructor);
            ArrayList<String> courseIdList = courseRepository.getCoursesOfSpecificUserRole(userId, roleId);
            for (int i = 0; i < courseIdList.size(); i++) {
                addQuestionToSurveyTable(userId, qId, questionTitle, questionText, questionType, courseIdList.get(i), time);
            }
            LOG.info("Operation = Save questions to survey questions function, Status = Success");
            return true;
        } catch (Exception e) {
            LOG.error("Operation = Save questions to survey questions function, Status = Fail, Error Message=" + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean addQuestionToSurveyTable(String userId, int qId, String questionTitle, String questionText, String questionType, String courseId, String time) {
        StoredProcedure sp = null;
        databaseAbstractFactory = DatabaseAbstractFactory.instance();
        try {
            sp = databaseAbstractFactory.createStoredProcedure("AddQuestionToSurveyTable(?,?,?,?,?,?,?)");
            sp.setParameter("uId", userId);
            sp.setParameter("qId", String.valueOf(qId));
            sp.setParameter("qTopic", questionTitle);
            sp.setParameter("qdesc", questionText);
            sp.setParameter("qtype", questionType);
            sp.setParameter("cId", courseId);
            sp.setParameter("tm", time);
            sp.execute();
            LOG.info("Operation = Save questions to survey table, Status = Success");
            return true;
        } catch (Exception e) {
            LOG.error("Operation = Save questions to survey table, Status = Fail, Error Message=" + e.getMessage());
            e.printStackTrace();
        } finally {
            if (sp != null) {
                sp.cleanup();
            }
        }
        return false;
    }

    private String changeQuestionTypeName(String selectedQuestionType) {
        switch (selectedQuestionType) {
            case numeric:
                return "numeric";
            case text:
                return "text";
            case MCCO:
                return "mcqs";
            case MCCM:
                return "mcqm";
            default:
                return null;
        }
    }

    @Override
    public boolean SaveMcqTypeQuestionRepo(String questionText, String questionTitle, String selectedQuestionType, String options, String ranks, String userId) {
        String[] optionList = options.split(",");
        String[] rankList = ranks.split(",");
        Integer qID = 0;
        StoredProcedure storedProcedure = null;
        StoredProcedure storedProcedure3 = null;
        questionEditorRepository = QuestionEditorAbstractFactory.instance().getQuestionEditorRepository();
        databaseAbstractFactory = DatabaseAbstractFactory.instance();
        String questionType = changeQuestionTypeName(selectedQuestionType);
        try {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
            LocalDateTime datetime = LocalDateTime.now();
            String time = dtf.format(datetime);
            storedProcedure = databaseAbstractFactory.createStoredProcedure("SaveQuestionToDB(?,?,?,?,?)");
            storedProcedure.setParameter("uId", userId);
            storedProcedure.setParameter("qTopic", questionTitle);
            storedProcedure.setParameter("qDesc", questionText);
            storedProcedure.setParameter("qType", questionType);
            storedProcedure.setParameter("dStamp", time);
            storedProcedure.execute();

            try {
                qID = questionEditorRepository.getQuestionIDFromTopic(questionTitle, time);
                saveQuestionToSurveyQuestions(userId, qID, questionTitle, questionText, questionType, time);
                for (int i = 0; i < optionList.length; i++) {
                    storedProcedure3 = databaseAbstractFactory.createStoredProcedure("SaveMcqOptionsToDB(?,?,?)");
                    storedProcedure3.setParameter("qId", qID.toString());
                    storedProcedure3.setParameter("ranks", rankList[i]);
                    storedProcedure3.setParameter("optionsDesc", optionList[i]);
                    storedProcedure3.execute();
                }
                LOG.info("Operation = Save MCQ type question: " + questionTitle + " options, Status = Success");
                return true;
            } catch (Exception e) {
                LOG.error("Operation = Save MCQ type question: " + questionTitle + " options, Status = Fail, Error Message=" + e.getMessage());
                e.printStackTrace();
            } finally {
                if (storedProcedure3 != null) {
                    storedProcedure3.cleanup();
                }
            }
        } catch (Exception e) {
            LOG.error("Operation = Save MCQ type question: " + questionTitle + ", Status = Fail, Error Message=" + e.getMessage());
            e.printStackTrace();
            return false;
        } finally {
            if (storedProcedure != null) {
                storedProcedure.cleanup();
            }
        }
        return false;
    }
}
