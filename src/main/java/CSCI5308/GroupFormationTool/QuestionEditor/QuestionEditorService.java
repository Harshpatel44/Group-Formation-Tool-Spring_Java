package CSCI5308.GroupFormationTool.QuestionEditor;

import CSCI5308.GroupFormationTool.UserManager.CurrentUser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class QuestionEditorService implements IQuestionEditorService {

    private static final Logger LOG = LogManager.getLogger();
    private IQuestionEditorRepository questionEditorRepository;

    public QuestionEditorService() {
    }
    public QuestionEditorService(QuestionEditorRepository questionEditorRepository, CurrentUser currentUser) {
        QuestionEditorAbstractFactory.instance().setQuestionEditorRepository(questionEditorRepository);
        CurrentUser.instance().setInstance(currentUser);
    }

    @Override
    public Boolean saveQuestionServiceForTextAndNumeric(String questionText, String questionTitle, String selectedQuestionType) {
        String bannerID = CurrentUser.instance().getBannerId();
        questionEditorRepository = QuestionEditorAbstractFactory.instance().getQuestionEditorRepository();
        try {
            if (questionEditorRepository.SaveTextAndNumericTypeQuestionRepo(questionText, questionTitle, selectedQuestionType, bannerID)) {
                LOG.info("Operation = Save text and numeric questions function for question: " + questionTitle + ", Status = Success");
                return true;
            } else {
                LOG.warn("Operation = Save text and numeric questions function for question: " + questionTitle + ", Status = Fail, Warning Message = question not saved");
                return false;
            }
        } catch (Exception e) {
            LOG.error("Operation = Save text and numeric questions function for question: " + questionTitle + ", Status = Fail, Error =" + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Boolean saveQuestionForMultipleChoiceService(String questionText, String questionTitle, String selectedQuestionType, String options, String ranks) throws Exception {
        String bannerID = CurrentUser.instance().getBannerId();
        questionEditorRepository = QuestionEditorAbstractFactory.instance().getQuestionEditorRepository();
        if (questionEditorRepository.SaveMcqTypeQuestionRepo(questionText, questionTitle, selectedQuestionType, options, ranks, bannerID)) {
            LOG.info("Operation = Save mcq type questions function for question: " + questionTitle + ", Status = Success");
            return true;
        } else {
            LOG.warn("Operation = Save mcq type questions function for question: " + questionTitle + ", Status = Fail");
            return false;
        }
    }
}
