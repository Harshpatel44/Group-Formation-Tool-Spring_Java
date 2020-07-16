package CSCI5308.GroupFormationTool.UserAuthentication;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class LoginServiceTest {

    public ILoginRepository userLoginRepository;
    public ILoginService userLoginService;

    @BeforeEach
    public void init() throws Exception {
        MockitoAnnotations.initMocks(this);
        userLoginRepository = mock(UserLoginRepository.class);
        UserAuthenticationAbstractFactory.instance().setLoginRepository(userLoginRepository);
        userLoginService = UserAuthenticationAbstractFactory.instance().getLoginService();
    }

    @Test
    void checkLoginTest() throws Exception {
        when(userLoginRepository.checkIfUserIsAuthenticated("B00835088","123")).thenReturn(true);
        assertTrue(userLoginService.checkIfUserIsAuthenticated("B00835088","123"));
    }

//    @Test
//    void isUser() throws Exception {
//        when(loginRepository.isUser("B00835088")).thenReturn(true);
//        assertTrue(loginService.isUser("B00835088"));
//        assertFalse(loginService.isUser("B11221344"));
//    }
}