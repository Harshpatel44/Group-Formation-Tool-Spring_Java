package CSCI5308.GroupFormationTool.QuestionEditor.AccessControl;

import java.util.HashMap;

public interface IRankFunctionsService {
    HashMap<Integer, String> arrangeOptionsBasedOnRank(String optionText,String rankText);
}
