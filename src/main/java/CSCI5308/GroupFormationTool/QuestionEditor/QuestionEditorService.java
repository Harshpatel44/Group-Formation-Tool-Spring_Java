package CSCI5308.GroupFormationTool.QuestionEditor;

import CSCI5308.GroupFormationTool.UserManager.CurrentUser;


public class QuestionEditorService implements IQuestionEditorService {

    public QuestionEditorService(){}
    public QuestionEditorService(QuestionEditorRepository questionEditorRepository,CurrentUser currentUser){
        IQuestionEditorAbstractFactory.instance().setQuestionEditorRepository(questionEditorRepository);
        CurrentUser.instance().setInstance(currentUser);
    }

    private IQuestionEditorRepository questionEditorRepository;

    @Override
    public Boolean saveQuestionServiceForTextAndNumeric(String questionText,String questionTitle,String selectedQuestionType){
        String bannerID = CurrentUser.instance().getBannerId();
        questionEditorRepository = IQuestionEditorAbstractFactory.instance().getQuestionEditorRepository();
        try {
            if(questionEditorRepository.SaveTextAndNumericTypeQuestionRepo(questionText,questionTitle,selectedQuestionType,bannerID)){
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
        questionEditorRepository = IQuestionEditorAbstractFactory.instance().getQuestionEditorRepository();
        if(questionEditorRepository.SaveMcqTypeQuestionRepo(questionText,questionTitle,selectedQuestionType,options,ranks,bannerID)){
            return true;
        }
        else{
            return false;
        }
    }
}
