package CSCI5308.GroupFormationTool.QuestionManager;

import java.util.ArrayList;

public interface IQuestionResponsesRepo {
    ArrayList<String> getResponsesOfQuestionIdRepo(Integer questionId) throws Exception;
}
