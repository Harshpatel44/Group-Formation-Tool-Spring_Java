package CSCI5308.GroupFormationTool.Login.Service;

import CSCI5308.GroupFormationTool.Login.Repository.ForgetPasswordRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ForgetPasswordServiceTest {
    public ForgetPasswordRepository forgetPasswordRepository;
    public ForgetPasswordService forgetPasswordService;

    @BeforeEach
    public void init() throws Exception {
        MockitoAnnotations.initMocks(this);
        forgetPasswordRepository = mock(ForgetPasswordRepository.class);
        forgetPasswordService = new ForgetPasswordService(forgetPasswordRepository);
    }

        @Test
        void getEmailByBanneridTest(){
            when(forgetPasswordRepository.getEmailByBannerid("B00835088")).thenReturn("rutikapatel09@gmail.com");
            assertEquals("rutikapatel09@gmail.com",forgetPasswordService.getEmailByBannerid("B00835088"));
        }

        @Test
        void insertToForgetPasswordTest() {
            when(forgetPasswordRepository.insertToForgetPassword("B00123456","e3Twq6Hyip")).thenReturn(true);
            assertTrue(forgetPasswordService.insertToForgetPassword("B00123456","e3Twq6Hyip"));
        }

        @Test
        void comparePasswordTest() {
            assertEquals("12345","12345");
            assertNotEquals("123","12345");
        }

        @Test
        void getBannerIdByPassKeyTest() {
            when(forgetPasswordRepository.getBannerIdByPassKey("randomstring")).thenReturn("B00100100");
            assertEquals("B00100100",forgetPasswordService.getBannerIdByPassKey("randomstring"));
        }

        @Test
        void getPasswordByBannerId() {
            List<String> password = new ArrayList<>();
            password.add("passwordValue");
            password.add("passwordValue2");
            when(forgetPasswordRepository.getPasswordByBannerId("B00835088",3)).thenReturn(password);
            assertEquals(password,forgetPasswordService.getPasswordByBannerId("B00835088",3));
        }

        @Test
        void comparePassword() {
            String newPassword="abc";
            String confirmPassword="abc";
            assertTrue(forgetPasswordService.comparePassword(newPassword,confirmPassword));

            String newPassword2="abc";
            String confirmPassword2="def";
            assertFalse(forgetPasswordService.comparePassword(newPassword2,confirmPassword2));
        }

        @Test
        void updatePassword() {
            assertFalse(forgetPasswordService.updatePassword("B00835088","123456"));
        }

        @Test
        void getPasswordPolicyNumber() {
            when(forgetPasswordRepository.getPasswordPolicyNumber()).thenReturn(3);
            assertEquals(3,forgetPasswordService.getPasswordPolicyNumber());
        }
}
