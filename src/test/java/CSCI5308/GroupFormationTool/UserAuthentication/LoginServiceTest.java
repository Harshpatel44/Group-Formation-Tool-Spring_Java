package CSCI5308.GroupFormationTool.UserAuthentication;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class LoginServiceTest {

    public UserLoginRepository userLoginRepository;
    public UserLoginService userLoginService;

    @BeforeEach
    public void init() throws Exception {
        MockitoAnnotations.initMocks(this);
        userLoginRepository = mock(UserLoginRepository.class);
        userLoginService = new UserLoginService(userLoginRepository);
    }

    @Test
    void checkLoginTest() throws Exception {
        when(userLoginRepository.checkIfUserIsAuthenticated("B00835088","123")).thenReturn(true);
        assertTrue(userLoginService.checkIfUserIsAuthenticated("B00835088","123"));
    }

}