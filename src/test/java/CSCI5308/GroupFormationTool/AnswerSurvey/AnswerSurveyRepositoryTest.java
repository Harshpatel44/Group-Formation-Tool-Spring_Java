package CSCI5308.GroupFormationTool.AnswerSurvey;

import CSCI5308.GroupFormationTool.Injector;
import org.junit.jupiter.api.BeforeEach;
import static org.mockito.Mockito.mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class AnswerSurveyRepositoryTest {

    private IAnswerSurveyRepository answerSurveyRepository;
    private  IAnswerSurveyService answerSurveyService;

    @BeforeEach
    public void init() throws Exception{
        MockitoAnnotations.initMocks(this);
        answerSurveyRepository = mock(AnswerSurveyRepository.class);
        Injector.instance().setAnswerSurveyRepository(answerSurveyRepository);
    }
}
