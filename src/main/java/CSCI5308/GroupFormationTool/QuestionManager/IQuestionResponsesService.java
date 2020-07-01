package CSCI5308.GroupFormationTool.QuestionManager;

import java.sql.SQLException;

public interface IQuestionResponsesService {
    boolean checkIfResponsesPresentService(Integer questionId) throws Exception;
}
