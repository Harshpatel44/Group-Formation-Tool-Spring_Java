package CSCI5308.GroupFormationTool.QuestionManager;

import CSCI5308.GroupFormationTool.Injector;
import CSCI5308.GroupFormationTool.QuestionManager.IQuestionResponsesService;
import CSCI5308.GroupFormationTool.QuestionManager.QuestionResponsesRepo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;
import java.util.ArrayList;

public class QuestionResponsesService implements IQuestionResponsesService {
    private static final Logger LOG = LogManager.getLogger();
    public QuestionResponsesService(){}
    public QuestionResponsesService(QuestionResponsesRepo questionResponsesRepo) throws Exception {
        QuestionManagerAbstractFactory.instance().setQuestionResponsesRepo(questionResponsesRepo);
    }

    @Override
    public boolean checkIfResponsesPresentService(Integer questionId) throws Exception {
        ArrayList<String> responses= QuestionManagerAbstractFactory.instance().getQuestionResponsesRepo().getResponsesOfQuestionIdRepo(questionId);
        if(responses.size()==0){
            LOG.warn("Operation = no response present , Status = Success" );
            return false;

        }
        else{
            LOG.info("Operation = response present , Status = Success" );
            return true;
        }
    }
}
