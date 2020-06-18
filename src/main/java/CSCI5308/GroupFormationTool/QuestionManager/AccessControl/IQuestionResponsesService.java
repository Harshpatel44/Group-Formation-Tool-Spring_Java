package CSCI5308.GroupFormationTool.QuestionManager.AccessControl;

import java.sql.SQLException;

public interface IQuestionResponsesService {
    boolean checkIfResponsesPresentService(Integer questionId) throws SQLException;
}
