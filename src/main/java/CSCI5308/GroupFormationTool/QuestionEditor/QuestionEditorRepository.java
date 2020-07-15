package CSCI5308.GroupFormationTool.QuestionEditor;

import CSCI5308.GroupFormationTool.Course.ICourseRepository;
import CSCI5308.GroupFormationTool.Database.IDatabaseAbstractFactory;
import CSCI5308.GroupFormationTool.Database.StoredProcedure;
import CSCI5308.GroupFormationTool.Injector;
import CSCI5308.GroupFormationTool.UserManager.IUserRepository;

import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import static CSCI5308.GroupFormationTool.ApplicationConstants.*;


public class QuestionEditorRepository implements IQuestionEditorRepository {

    private IDatabaseAbstractFactory databaseAbstractFactory = Injector.instance().getDatabaseAbstractFactory();
    private IQuestionEditorAbstractFactory questionEditorAbstractFactory = Injector.instance().getQuestionEditorAbstractFactory();
    private IQuestionEditorRepository questionEditorRepository = Injector.instance().getQuestionEditorRepository();
    private IUserRepository userRepository = Injector.instance().getUserRepository();
    private ICourseRepository courseRepository = Injector.instance().getCourseRepository();

    @Override
    public boolean SaveTextAndNumericTypeQuestionRepo(String questionText, String questionTitle, String selectedQuestionType,String userId){
        String questionType = changeQuestionTypeName(selectedQuestionType);
        StoredProcedure storedProcedure = null;
        try {
            DateTimeFormatter dtf = questionEditorAbstractFactory.getDateTimeFormat();
            LocalDateTime datetime = LocalDateTime.now();
            String time = dtf.format(datetime);
            storedProcedure = databaseAbstractFactory.createStoredProcedure("SaveQuestionToDB(?,?,?,?,?)");
            storedProcedure.setParameter("uId",userId);
            storedProcedure.setParameter("qTopic", questionTitle);
            storedProcedure.setParameter("qDesc", questionText);
            storedProcedure.setParameter("qType", questionType);
            storedProcedure.setParameter("dStamp",time);
            storedProcedure.execute();

            int qID = questionEditorRepository.getQuestionIDFromTopic(questionTitle,time);
            saveQuestionToSurveyQuestions(userId, qID, questionTitle, time);
            return true;
        }
        catch (Exception e){
            e.printStackTrace();
            return false;
        }finally {
            if(storedProcedure!=null){
                storedProcedure.cleanup();
            }

        }
    }

    @Override
    public int getQuestionIDFromTopic(String questionTitle,String dStamp){
        StoredProcedure storedProcedure = null;
        try{
            storedProcedure = databaseAbstractFactory.createStoredProcedure("questionIdFromTopic(?,?)");
            storedProcedure.setParameter("qTopic",questionTitle);
            storedProcedure.setParameter("tm",dStamp);
            ResultSet rs = storedProcedure.executeWithResults();
            while(rs.next()){
                return rs.getInt("questionId");
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(storedProcedure!=null){
                storedProcedure.cleanup();
            }
        }
        return 0;
    }

    private boolean saveQuestionToSurveyQuestions(String userId, int qId, String questionTitle, String time){
        try {
            int roleId = userRepository.getUserRoleIdFromRoleType(instructor);
            ArrayList<String> courseIdList = courseRepository.getCoursesOfSpecificUserRole(userId, roleId);
            for(int i = 0;i<courseIdList.size();i++){
                addQuestionToSurveyTable(userId, qId, questionTitle, courseIdList.get(i), time);
            }
            return true;
        }
        catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean addQuestionToSurveyTable(String userId, int qId, String questionTitle, String courseId,String time){
        StoredProcedure sp = null;
        try{
            sp = databaseAbstractFactory.createStoredProcedure("AddQuestionToSurveyTable(?,?,?,?,?)");
            sp.setParameter("uId",userId);
            sp.setParameter("qId",String.valueOf(qId));
            sp.setParameter("qTopic",questionTitle);
            sp.setParameter("cId",courseId);
            sp.setParameter("tm",time);
            sp.execute();
            return true;
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(sp!=null){
                sp.cleanup();
            }
        }
        return false;
    }

    private String changeQuestionTypeName(String selectedQuestionType){
        switch (selectedQuestionType){
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
    public boolean SaveMcqTypeQuestionRepo(String questionText, String questionTitle, String selectedQuestionType, String options, String ranks, String userId){
        String[] optionList = options.split(",");
        String[] rankList = ranks.split(",");
        Integer qID = 0;
        StoredProcedure storedProcedure = null;
        StoredProcedure storedProcedure3 = null;
        String questionType = changeQuestionTypeName(selectedQuestionType);
        try {
            DateTimeFormatter dtf = questionEditorAbstractFactory.getDateTimeFormat();
            LocalDateTime datetime = LocalDateTime.now();
            String time = dtf.format(datetime);
            storedProcedure = databaseAbstractFactory.createStoredProcedure("SaveQuestionToDB(?,?,?,?,?)");
            storedProcedure.setParameter("uId",userId);
            storedProcedure.setParameter("qTopic", questionTitle);
            storedProcedure.setParameter("qDesc", questionText);
            storedProcedure.setParameter("qType", questionType);
            storedProcedure.setParameter("dStamp",time);
            storedProcedure.execute();

            try{
                qID = questionEditorRepository.getQuestionIDFromTopic(questionTitle,time);
                saveQuestionToSurveyQuestions(userId, qID, questionTitle, time);
                for(int i=0;i<optionList.length;i++){
                    storedProcedure3 = new StoredProcedure("SaveMcqOptionsToDB(?,?,?)");
                    storedProcedure3.setParameter("qId",qID.toString());
                    storedProcedure3.setParameter("ranks", rankList[i]);
                    storedProcedure3.setParameter("optionsDesc", optionList[i]);
                    storedProcedure3.execute();
                }
                return true;
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                if(storedProcedure3!=null){
                    storedProcedure3.cleanup();
                }
            }
        }
        catch (Exception e){
            e.printStackTrace();
            return false;
        }finally {
            if(storedProcedure!=null){
                storedProcedure.cleanup();
            }
        }
        return false;
    }
}
