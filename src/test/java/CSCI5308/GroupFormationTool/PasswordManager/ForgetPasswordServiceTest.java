package CSCI5308.GroupFormationTool.PasswordManager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ForgetPasswordServiceTest {
    public IForgetPasswordRepository forgetPasswordRepository;
    public IForgetPasswordService forgetPasswordService;

    @BeforeEach
    public void init() throws Exception {
        MockitoAnnotations.initMocks(this);
        forgetPasswordRepository = mock(ForgetPasswordRepository.class);
        UserPasswordManagerAbstractFactory.instance().setForgetPasswordRepository(forgetPasswordRepository);
        forgetPasswordService = UserPasswordManagerAbstractFactory.instance().getForgetPasswordService();
    }


    @Test
    void getEmailByBanneridTest() throws Exception {

        when(forgetPasswordRepository.getEmailByBannerID("B00835088")).thenReturn("rutikapatel09@gmail.com");
        assertEquals("rutikapatel09@gmail.com", forgetPasswordService.getEmailByBannerID("B00835088"));
    }

    @Test
    void insertToForgetPasswordTest() throws Exception {
        when(forgetPasswordRepository.insertToForgetPassword("B00123456", "e3Twq6Hyip")).thenReturn(true);
        assertTrue(forgetPasswordService.insertToForgetPassword("B00123456", "e3Twq6Hyip"));
    }

    @Test
    void comparePasswordTest() {
        assertEquals("12345", "12345");
        assertNotEquals("123", "12345");
    }

    @Test
    void getBannerIdByPassKeyTest() throws Exception {
        when(forgetPasswordRepository.getBannerIDByPassKey("randomstring")).thenReturn("B00100100");
        assertEquals("B00100100", forgetPasswordService.getBannerIDByPassKey("randomstring"));
    }

    @Test
    void getPasswordByBannerId() throws Exception {
        List<String> password = new ArrayList<>();
        password.add("passwordValue");
        password.add("passwordValue2");
        when(forgetPasswordRepository.getPasswordByBannerID("B00835088", 3)).thenReturn(password);
        assertEquals(password, forgetPasswordService.getPasswordByBannerID("B00835088", 3));
    }

    @Test
    void comparePassword() {
        String newPassword = "abc";
        String confirmPassword = "abc";
        assertTrue(forgetPasswordService.comparePassword(newPassword, confirmPassword));

        String newPassword2 = "abc";
        String confirmPassword2 = "def";
        assertFalse(forgetPasswordService.comparePassword(newPassword2, confirmPassword2));
    }

    @Test
    void updatePassword() throws Exception {
        assertFalse(forgetPasswordService.updatePassword("B00835088", "123456"));
    }

    @Test
    void getPasswordPolicyNumber() throws Exception {
        when(forgetPasswordRepository.getPasswordPolicyNumber()).thenReturn(3);
        assertEquals(3, forgetPasswordService.getPasswordPolicyNumber());
    }
}
