package CSCI5308.GroupFormationTool.QuestionManager.Service;

import CSCI5308.GroupFormationTool.Course.Model.Course;
import CSCI5308.GroupFormationTool.Course.Model.UserId;
import CSCI5308.GroupFormationTool.QuestionManager.Model.Question;
import CSCI5308.GroupFormationTool.QuestionManager.Repository.QuestionManagerRepositoryMock;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
public class QuestionManagerServiceTest {
    @Mock
    private QuestionManagerRepositoryMock questionManagerRepositoryMock;

    @Test
    public void getquestionsTest(){
        UserId user = new UserId();
        List<Question> questionList = new ArrayList<Question>();
        Question question = new Question();
        question.setQuestionId("1");
        question.setQuestionDescription("QuestionDescription");
        question.setQuestionTopic("QuestionTopic");
        question.setDate("2020-12-30");
        questionList.add(question);
        user.setUserId("B00123456");
        when(questionManagerRepositoryMock.getQuestionsTest(user)).thenReturn(questionList);
        List<Question> returnedList = questionManagerRepositoryMock.getQuestionsTest(user);
        assertEquals(returnedList,questionList);

    }
}
