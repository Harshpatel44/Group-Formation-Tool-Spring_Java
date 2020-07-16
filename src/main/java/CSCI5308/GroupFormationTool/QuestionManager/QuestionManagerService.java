package CSCI5308.GroupFormationTool.QuestionManager;

import CSCI5308.GroupFormationTool.Injector;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

import static CSCI5308.GroupFormationTool.ApplicationConstants.sortByDate;
import static CSCI5308.GroupFormationTool.ApplicationConstants.sortByTopic;

public class QuestionManagerService implements IQuestionManagerService {
    private IQuestionManagerRepository questionManagerRepository;
    private static final Logger LOG = LogManager.getLogger();

    public QuestionManagerService(){}
    public QuestionManagerService(QuestionManagerRepository questionManagerRepository) throws Exception {
        Injector.instance().setQuestionManagerRepository(questionManagerRepository);
    }

    @Override
    public List<IQuestion> getQuestions(String userId, String sortType) throws Exception {
        questionManagerRepository = QuestionManagerAbstractFactory.instance().getQuestionManagerRepository();
        if (sortType.equals(sortByTopic))
        {
            LOG.info("Operation = sorted question by Topic , Status = Success ");
            return questionManagerRepository.getQuestionsByTopic(userId);
        }
        else if (sortType.equals(sortByDate))
        {
            LOG.info("Operation = sorted question by Date , Status = Success ");
            return questionManagerRepository.getQuestionsByDate(userId);
        }
        else
        {
            LOG.info("Operation = unsorted question , Status = Success ");
            return questionManagerRepository.getQuestions(userId);
        }
    }

    @Override
    public void deleteQuestion(Integer questionId, String userId) throws Exception {
        questionManagerRepository = QuestionManagerAbstractFactory.instance().getQuestionManagerRepository();
        questionManagerRepository.deleteQuestion(questionId, userId);
    }
}
