package CSCI5308.GroupFormationTool.Login;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class LoginServiceTest {

    public LoginRepository loginRepository;
    public LoginService loginService;

    @BeforeEach
    public void init() throws Exception {
        MockitoAnnotations.initMocks(this);
        loginRepository = mock(LoginRepository.class);
        loginService = new LoginService(loginRepository);
    }

    @Test
    void checkLoginTest() throws Exception {
        when(loginRepository.checkLogin("B00835088","123")).thenReturn(true);
        assertTrue(loginService.checkLogin("B00835088","123"));
    }

    @Test
    void isUser() throws Exception {
        when(loginRepository.isUser("B00835088")).thenReturn(true);
        assertTrue(loginService.isUser("B00835088"));
        assertFalse(loginService.isUser("B11221344"));
    }
}