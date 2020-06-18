package CSCI5308.GroupFormationTool.QuestionEditor.Service;

import CSCI5308.GroupFormationTool.QuestionEditor.AccessControl.IQuestionEditorService;
import CSCI5308.GroupFormationTool.QuestionEditor.QuestionEditorInjector;
import CSCI5308.GroupFormationTool.QuestionEditor.Repository.QuestionEditorRepository;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import static java.util.stream.Collectors.*;
import static java.util.Map.Entry.*;


public class QuestionEditorService implements IQuestionEditorService {

    public QuestionEditorService(){}

    public QuestionEditorService(QuestionEditorRepository questionEditorRepository) throws Exception {
        QuestionEditorInjector.instance().setQuestionEditorRepository(questionEditorRepository);
    }



    @Override
    public String SaveQuestionServiceForTextAndNumeric(String questionText,String questionTitle,String selectedQuestionType, String userId) throws Exception {
        if(QuestionEditorInjector.instance().getQuestionEditorRepository().SaveTextAndNumericTypeQuestionRepo(questionText,questionTitle,selectedQuestionType,userId)){

            return "Question submitted successfully";
        }
        else{
            return "Question did not submit successfully";
        }
    }

    @Override
    public String saveQuestionForMultipleChoiceService(String questionText, String questionTitle, String selectedQuestionType, String options, String ranks, String userId) throws Exception {
        if(QuestionEditorInjector.instance().getQuestionEditorRepository().SaveMcqTypeQuestionRepo(questionText,questionTitle,selectedQuestionType,options,ranks,userId)){
            return "Question submitted successfully";
        }
        else{
            return "Question did not submit successfully";
        }
    }


    public HashMap<Integer, String> arrangeOptionsBasedOnRank(String optionText, String rankText){

        String[] optionList = optionText.split(",");
        String[] rankList = rankText.split(",");

        HashMap<Integer, String> map = new HashMap<Integer, String>();

        for(int i=0;i<optionList.length;i++){
            map.put(Integer.valueOf(rankList[i]),optionList[i]);
        }

        Map<Integer, String> sorted = map
                .entrySet()
                .stream()
                .sorted(comparingByKey())
                .collect(
                        toMap(e -> e.getKey(), e -> e.getValue(),
                                (e1, e2) -> e2, LinkedHashMap::new));
    System.out.println(map);
    return map;
    }

}
