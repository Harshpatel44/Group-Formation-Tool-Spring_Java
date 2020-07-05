package CSCI5308.GroupFormationTool.QuestionEditor;

import CSCI5308.GroupFormationTool.QuestionEditor.IQuestionEditorService;
import CSCI5308.GroupFormationTool.QuestionEditor.QuestionEditorInjector;
import CSCI5308.GroupFormationTool.QuestionEditor.QuestionEditorRepository;
import CSCI5308.GroupFormationTool.UserManager.CurrentUser;


public class QuestionEditorService implements IQuestionEditorService {

    public QuestionEditorService(){}
    public QuestionEditorService(QuestionEditorRepository questionEditorRepository) throws Exception {
        QuestionEditorInjector.instance().setQuestionEditorRepository(questionEditorRepository);
    }

    @Override
    public Boolean saveQuestionServiceForTextAndNumeric(String questionText,String questionTitle,String selectedQuestionType){
        String bannerID = CurrentUser.instance().getBannerId();
        try {
            if(QuestionEditorInjector.instance().getQuestionEditorRepository().SaveTextAndNumericTypeQuestionRepo(questionText,questionTitle,selectedQuestionType,bannerID)){
                return true;
            }
            else{
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Boolean saveQuestionForMultipleChoiceService(String questionText, String questionTitle, String selectedQuestionType, String options, String ranks) throws Exception {
        String bannerID = CurrentUser.instance().getBannerId();
        if(QuestionEditorInjector.instance().getQuestionEditorRepository().SaveMcqTypeQuestionRepo(questionText,questionTitle,selectedQuestionType,options,ranks,bannerID)){
            return true;
        }
        else{
            return false;
        }
    }
}
