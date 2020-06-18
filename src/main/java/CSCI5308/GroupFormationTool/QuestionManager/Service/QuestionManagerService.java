package CSCI5308.GroupFormationTool.QuestionManager.Service;

import CSCI5308.GroupFormationTool.Course.Model.UserId;
import CSCI5308.GroupFormationTool.Injector;
import CSCI5308.GroupFormationTool.QuestionManager.AccessControl.IQuestionManagerRepository;
import CSCI5308.GroupFormationTool.QuestionManager.AccessControl.IQuestionManagerService;
import CSCI5308.GroupFormationTool.QuestionManager.Model.Question;
import CSCI5308.GroupFormationTool.QuestionManager.Repository.QuestionManagerRepository;

import java.util.List;

public class QuestionManagerService implements IQuestionManagerService {
    private IQuestionManagerRepository questionManagerRepository;

    public QuestionManagerService(){}
    public QuestionManagerService(QuestionManagerRepository questionManagerRepository) {
        Injector.instance().setQuestionManagerRepository(questionManagerRepository);
    }

    @Override
    public List<Question> getQuestions(UserId user,String sortType)
    {
        questionManagerRepository = Injector.instance().getQuestionManagerRepository();
        if (sortType.equals("sortByTopic"))
        {
            return questionManagerRepository.getQuestionsByTopic(user);
        }
        else if (sortType.equals("sortByDate"))
        {
            return questionManagerRepository.getQuestionsByDate(user);
        }
        else
        {
            return questionManagerRepository.getQuestions(user);
        }
    }

    @Override
    public void deleteQuestion(Integer questionId, String userId) {
        questionManagerRepository = Injector.instance().getQuestionManagerRepository();
        questionManagerRepository.deleteQuestion(questionId, userId);
    }

}
