package CSCI5308.GroupFormationTool.QuestionManager;

import CSCI5308.GroupFormationTool.Database.DatabaseAbstractFactory;
import CSCI5308.GroupFormationTool.Database.StoredProcedure;
import CSCI5308.GroupFormationTool.QuestionManager.IQuestionResponsesRepo;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class QuestionResponsesRepo implements IQuestionResponsesRepo {
    private DatabaseAbstractFactory databaseAbstractFactory;
    @Override
    public ArrayList<String> getResponsesOfQuestionIdRepo(Integer questionId) throws Exception {
        ArrayList<String> responses = new ArrayList<>();
        databaseAbstractFactory = DatabaseAbstractFactory.instance();
        StoredProcedure sp = databaseAbstractFactory.createStoredProcedure("getResponsesByQuestionId(?)");
        try{
            sp.setParameter(1,questionId);
            ResultSet rs= sp.executeWithResults();
            while(rs.next()){
                responses.add(rs.getString("responses"));
            }
        }catch (SQLException throwables) {
            throwables.printStackTrace();
            sp.cleanup();
        }
        finally {
            sp.cleanup();
        }
        return responses;
    }
}
