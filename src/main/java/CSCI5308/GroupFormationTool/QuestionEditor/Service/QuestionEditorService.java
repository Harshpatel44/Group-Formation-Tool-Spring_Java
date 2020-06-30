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
    public Boolean SaveQuestionServiceForTextAndNumeric(String questionText,String questionTitle,String selectedQuestionType, String userId) throws Exception {
        if(QuestionEditorInjector.instance().getQuestionEditorRepository().SaveTextAndNumericTypeQuestionRepo(questionText,questionTitle,selectedQuestionType,userId)){
            return true;
        }
        else{
            return false;
        }
    }

    @Override
    public Boolean saveQuestionForMultipleChoiceService(String questionText, String questionTitle, String selectedQuestionType, String options, String ranks, String userId) throws Exception {
        if(QuestionEditorInjector.instance().getQuestionEditorRepository().SaveMcqTypeQuestionRepo(questionText,questionTitle,selectedQuestionType,options,ranks,userId)){
            return true;
        }
        else{
            return false;
        }
    }
}
