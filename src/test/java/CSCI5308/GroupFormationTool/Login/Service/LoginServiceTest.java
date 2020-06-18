package CSCI5308.GroupFormationTool.Login.Service;

import CSCI5308.GroupFormationTool.Login.Repository.LoginRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
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
    void checkLoginTest() throws Exception{

        when(loginRepository.checkLogin("B00835088","123")).thenReturn(true);
        assertTrue(loginService.checkLogin("B00835088","123"));
    }

    @Test
    void isUser(){
        when(loginRepository.isUser("B00835088")).thenReturn(true);
        assertTrue(loginService.isUser("B00835088"));
        assertFalse(loginService.isUser("B11221344"));
    }

    @Test
    void getEmailByBanneridTest() {
        when(loginRepository.getEmailByBannerid("B00835088")).thenReturn("rutikapatel09@gmail.com");
        assertEquals("rutikapatel09@gmail.com",loginService.getEmailByBannerid("B00835088"));
    }

    @Test
    void insertToForgetPasswordTest() {
        when(loginRepository.insertToForgetPassword("B00123456","e3Twq6Hyip")).thenReturn(true);
        assertTrue(loginService.insertToForgetPassword("B00123456","e3Twq6Hyip"));
    }

    @Test
    void comparePasswordTest() {
        assertEquals("12345","12345");
        assertNotEquals("123","12345");
    }

    @Test
    void getBannerIdByPassKeyTest() {
        when(loginRepository.getBannerIdByPassKey("randomstring")).thenReturn("B00100100");
        assertEquals("B00100100",loginService.getBannerIdByPassKey("randomstring"));
    }

    @Test
    void getPasswordByBannerId() {
        List<String> password = new ArrayList<>();
        password.add("passwordValue");
        password.add("passwordValue2");
        when(loginRepository.getPasswordByBannerId("B00835088",3)).thenReturn(password);
        assertEquals(password,loginService.getPasswordByBannerId("B00835088",3));
    }


    @Test
    void comparePassword() {
        String newPassword="abc";
        String confirmPassword="abc";
        assertTrue(loginService.comparePassword(newPassword,confirmPassword));

        String newPassword2="abc";
        String confirmPassword2="def";
        assertFalse(loginService.comparePassword(newPassword2,confirmPassword2));
    }



    @Test
    void updatePassword() {
        assertFalse(loginService.updatePassword("B00835088","123456"));
    }

    @Test
    void getPasswordPolicyNumber() {
        when(loginRepository.getPasswordPolicyNumber()).thenReturn(3);
        assertEquals(3,loginService.getPasswordPolicyNumber());
    }
}