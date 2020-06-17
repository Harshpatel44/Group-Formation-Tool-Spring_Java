package CSCI5308.GroupFormationTool.QuestionEditor.Repository;

import CSCI5308.GroupFormationTool.Database.StoredProcedure;
import CSCI5308.GroupFormationTool.QuestionEditor.AccessControl.IQuestionEditorRepository;

import java.sql.ResultSet;
import java.time.LocalDate;

public class QuestionEditorRepository implements IQuestionEditorRepository {

    @Override
    public boolean SaveTextAndNumericTypeQuestionRepo(String questionText, String questionTitle, String selectedQuestionType,String userId){
        String questionType = changeQuestionTypeName(selectedQuestionType);
        try {
            LocalDate date = LocalDate.now();
            StoredProcedure storedProcedure = new StoredProcedure("SaveQuestionToDB(?,?,?,?,?)");
            storedProcedure.setParameter("uId",userId);
            storedProcedure.setParameter("qTopic", questionText);
            storedProcedure.setParameter("qDesc", questionText);
            storedProcedure.setParameter("qType", questionType);
            storedProcedure.setParameter("dStamp",date.toString());
            storedProcedure.execute();
            storedProcedure.cleanup();
            return true;
        }
        catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public String changeQuestionTypeName(String selectedQuestionType){
        if(selectedQuestionType.equals("Numeric")){
            return "numeric";
        }
        if(selectedQuestionType.equals("Text")){
            return "text";
        }
        if(selectedQuestionType.equals("Multiple Choice, Choose One")){
            return "mcqs";
        }
        if(selectedQuestionType.equals("Multiple Choice, Choose Multiple")){
            return "mcqm";
        }
        return null;
    }

    @Override
    public boolean SaveMcqTypeQuestionRepo(String questionText, String questionTitle, String selectedQuestionType, String options, String ranks, String userId){
        String[] optionList = options.split(",");
        String[] rankList = ranks.split(",");
        Integer qId=0;
        String questionType = changeQuestionTypeName(selectedQuestionType);
        try {
            LocalDate date = LocalDate.now();
            StoredProcedure storedProcedure = new StoredProcedure("SaveQuestionToDB(?,?,?,?,?)");
            storedProcedure.setParameter("uId",userId);
            storedProcedure.setParameter("qTopic", questionTitle);
            storedProcedure.setParameter("qDesc", questionText);
            storedProcedure.setParameter("qType", questionType);
            storedProcedure.setParameter("dStamp",date.toString());
            storedProcedure.execute();
            storedProcedure.cleanup();

            StoredProcedure storedProcedure2 = new StoredProcedure("GetQuestionId(?,?)");
            storedProcedure2.setParameter("uId",userId);
            storedProcedure2.setParameter("qTopic", questionTitle);
            ResultSet resultSet = storedProcedure2.executeWithResults();
            while(resultSet.next()){
                qId = resultSet.getInt("questionId");
            }
            storedProcedure2.cleanup();


            for(int i=0;i<optionList.length;i++){
                StoredProcedure storedProcedure3 = new StoredProcedure("SaveMcqOptionsToDB(?,?,?)");
                storedProcedure3.setParameter("qId",qId.toString());
                storedProcedure3.setParameter("ranks", rankList[i]);
                storedProcedure3.setParameter("optionsDesc", optionList[i]);
                storedProcedure3.execute();
                storedProcedure3.cleanup();
            }



            return true;
        }
        catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
}
