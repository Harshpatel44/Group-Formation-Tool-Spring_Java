package CSCI5308.GroupFormationTool.QuestionManager;

import CSCI5308.GroupFormationTool.Database.DatabaseAbstractFactory;
import CSCI5308.GroupFormationTool.Database.StoredProcedure;
import CSCI5308.GroupFormationTool.QuestionManager.IQuestionResponsesRepo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class QuestionResponsesRepo implements IQuestionResponsesRepo {
    private DatabaseAbstractFactory databaseAbstractFactory;
    private static final Logger LOG = LogManager.getLogger();
    @Override
    public ArrayList<String> getResponsesOfQuestionIdRepo(Integer questionId) throws Exception {
        ArrayList<String> responses = new ArrayList<>();
        databaseAbstractFactory = DatabaseAbstractFactory.instance();
        StoredProcedure sp = null;
        try{
            sp = databaseAbstractFactory.createStoredProcedure("getResponsesByQuestionId(?)");
            sp.setParameter(1,questionId);
            ResultSet rs= sp.executeWithResults();
            while(rs.next()){
                responses.add(rs.getString("responses"));
                LOG.info("Operation = adding question responses , Status = Success ");
            }
        }catch (SQLException throwables) {
            throwables.printStackTrace();
            LOG.error("Operation = survey question list, Status = Failed, Error Message="+throwables.getMessage());
        }
        finally {
            if(sp!=null){
                sp.cleanup();
            }
        }
        return responses;
    }
}
