package CSCI5308.GroupFormationTool.QuestionEditor;

import CSCI5308.GroupFormationTool.Database.StoredProcedure;
import CSCI5308.GroupFormationTool.QuestionEditor.IQuestionEditorRepository;
import java.sql.ResultSet;
import java.time.LocalDate;
import static CSCI5308.GroupFormationTool.ApplicationConstants.numeric;
import static CSCI5308.GroupFormationTool.ApplicationConstants.text;
import static CSCI5308.GroupFormationTool.ApplicationConstants.MCCO;
import static CSCI5308.GroupFormationTool.ApplicationConstants.MCCM;
public class QuestionEditorRepository implements IQuestionEditorRepository {

    @Override
    public boolean SaveTextAndNumericTypeQuestionRepo(String questionText, String questionTitle, String selectedQuestionType,String userId){
        String questionType = changeQuestionTypeName(selectedQuestionType);
        StoredProcedure storedProcedure = null;
        try {
            LocalDate date = LocalDate.now();
            storedProcedure = new StoredProcedure("SaveQuestionToDB(?,?,?,?,?)");
            storedProcedure.setParameter("uId",userId);
            storedProcedure.setParameter("qTopic", questionText);
            storedProcedure.setParameter("qDesc", questionText);
            storedProcedure.setParameter("qType", questionType);
            storedProcedure.setParameter("dStamp",date.toString());
            storedProcedure.execute();

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
        Integer qId=0;
        StoredProcedure storedProcedure = null;
        StoredProcedure storedProcedure2 = null;
        StoredProcedure storedProcedure3 = null;
        String questionType = changeQuestionTypeName(selectedQuestionType);
        try {
            LocalDate date = LocalDate.now();
            storedProcedure = new StoredProcedure("SaveQuestionToDB(?,?,?,?,?)");
            storedProcedure.setParameter("uId",userId);
            storedProcedure.setParameter("qTopic", questionTitle);
            storedProcedure.setParameter("qDesc", questionText);
            storedProcedure.setParameter("qType", questionType);
            storedProcedure.setParameter("dStamp",date.toString());
            storedProcedure.execute();

            try{
                storedProcedure2 = new StoredProcedure("GetQuestionId(?,?)");
                storedProcedure2.setParameter("uId",userId);
                storedProcedure2.setParameter("qTopic", questionTitle);
                ResultSet resultSet = storedProcedure2.executeWithResults();
                while(resultSet.next()){
                    qId = resultSet.getInt("questionId");
                }
                for(int i=0;i<optionList.length;i++){
                    storedProcedure3 = new StoredProcedure("SaveMcqOptionsToDB(?,?,?)");
                    storedProcedure3.setParameter("qId",qId.toString());
                    storedProcedure3.setParameter("ranks", rankList[i]);
                    storedProcedure3.setParameter("optionsDesc", optionList[i]);
                    storedProcedure3.execute();
                    return true;
                }
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                if(storedProcedure2!=null){
                    storedProcedure2.cleanup();
                }
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
