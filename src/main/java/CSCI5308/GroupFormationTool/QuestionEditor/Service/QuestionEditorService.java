package CSCI5308.GroupFormationTool.QuestionEditor.Service;

import CSCI5308.GroupFormationTool.QuestionEditor.AccessControl.IQuestionEditorService;
import CSCI5308.GroupFormationTool.QuestionEditor.Model.QuestionModel;
import CSCI5308.GroupFormationTool.QuestionEditor.QuestionEditorInjector;
import CSCI5308.GroupFormationTool.QuestionEditor.Repository.QuestionEditorRepository;


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
}
