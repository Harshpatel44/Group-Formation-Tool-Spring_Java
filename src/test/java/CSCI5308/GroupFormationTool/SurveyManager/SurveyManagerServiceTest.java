package CSCI5308.GroupFormationTool.SurveyManager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.Mockito.mock;

@SpringBootTest
public class SurveyManagerServiceTest {
    public SurveyManagerService surveyManagerService;
    public SurveyManagerRepository surveyManagerRepository;

    @BeforeEach
    public void init() throws Exception {
        MockitoAnnotations.initMocks(this);
        surveyManagerRepository = mock(SurveyManagerRepository.class);
        surveyManagerService = new SurveyManagerService(surveyManagerRepository);
    }
@Test
    public void getSurveyQuestionsTest() throws Exception {

}


}

