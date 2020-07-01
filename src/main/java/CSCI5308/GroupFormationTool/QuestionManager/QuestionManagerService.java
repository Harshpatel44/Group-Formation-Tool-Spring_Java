package CSCI5308.GroupFormationTool.QuestionManager;

import CSCI5308.GroupFormationTool.Injector;
import CSCI5308.GroupFormationTool.QuestionManager.IQuestionManagerRepository;
import CSCI5308.GroupFormationTool.QuestionManager.IQuestionManagerService;
import CSCI5308.GroupFormationTool.QuestionManager.Question;
import CSCI5308.GroupFormationTool.QuestionManager.QuestionManagerRepository;

import java.util.List;

import static CSCI5308.GroupFormationTool.ApplicationConstants.sortByDate;
import static CSCI5308.GroupFormationTool.ApplicationConstants.sortByTopic;

public class QuestionManagerService implements IQuestionManagerService {
    private IQuestionManagerRepository questionManagerRepository;

    public QuestionManagerService(){}
    public QuestionManagerService(QuestionManagerRepository questionManagerRepository) throws Exception {
        Injector.instance().setQuestionManagerRepository(questionManagerRepository);
    }

    @Override
    public List<Question> getQuestions(String userId, String sortType) throws Exception {
        questionManagerRepository = Injector.instance().getQuestionManagerRepository();
        if (sortType.equals(sortByTopic))
        {
            return questionManagerRepository.getQuestionsByTopic(userId);
        }
        else if (sortType.equals(sortByDate))
        {
            return questionManagerRepository.getQuestionsByDate(userId);
        }
        else
        {
            return questionManagerRepository.getQuestions(userId);
        }
    }

    @Override
    public void deleteQuestion(Integer questionId, String userId) throws Exception {
        questionManagerRepository = Injector.instance().getQuestionManagerRepository();
        questionManagerRepository.deleteQuestion(questionId, userId);
    }
}
