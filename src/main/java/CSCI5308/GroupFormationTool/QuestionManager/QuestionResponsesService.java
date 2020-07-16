package CSCI5308.GroupFormationTool.QuestionManager;

import CSCI5308.GroupFormationTool.Injector;
import CSCI5308.GroupFormationTool.QuestionManager.IQuestionResponsesService;
import CSCI5308.GroupFormationTool.QuestionManager.QuestionResponsesRepo;

import java.sql.SQLException;
import java.util.ArrayList;

public class QuestionResponsesService implements IQuestionResponsesService {
    public QuestionResponsesService(){}
    public QuestionResponsesService(QuestionResponsesRepo questionResponsesRepo) throws Exception {
        Injector.instance().setQuestionResponsesRepo(questionResponsesRepo);
    }

    @Override
    public boolean checkIfResponsesPresentService(Integer questionId) throws Exception {
        ArrayList<String> responses= QuestionManagerAbstractFactory.instance().getQuestionResponsesRepo().getResponsesOfQuestionIdRepo(questionId);
        if(responses.size()==0){
            return false;
        }
        else{
            return true;
        }
    }
}
