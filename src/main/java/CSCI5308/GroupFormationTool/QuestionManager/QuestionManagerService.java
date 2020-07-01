package CSCI5308.GroupFormationTool.QuestionManager;

import CSCI5308.GroupFormationTool.Injector;

import java.util.List;

import static CSCI5308.GroupFormationTool.ApplicationConstants.sortByDate;
import static CSCI5308.GroupFormationTool.ApplicationConstants.sortByTopic;

public class QuestionManagerService implements IQuestionManagerService {
    private IQuestionManagerRepository questionManagerRepository;

    public QuestionManagerService(){}
    public QuestionManagerService(QuestionManagerRepository questionManagerRepository) {
        Injector.instance().setQuestionManagerRepository(questionManagerRepository);
    }

    @Override
    public List<IQuestion> getQuestions(String userId, String sortType)
    {
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
    public void deleteQuestion(Integer questionId, String userId) {
        questionManagerRepository = Injector.instance().getQuestionManagerRepository();
        questionManagerRepository.deleteQuestion(questionId, userId);
    }
}
