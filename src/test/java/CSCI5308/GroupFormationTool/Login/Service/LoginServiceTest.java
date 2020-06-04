package CSCI5308.GroupFormationTool.Login.Service;

import CSCI5308.GroupFormationTool.Login.Repository.MockLoginRepository;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LoginServiceTest {

    MockLoginRepository mock = new MockLoginRepository();
    LoginService service = new LoginService();

    @Test
    void checkLoginTest() {
        assertTrue(mock.checkLogin("B00123456","12345"));
        assertFalse(mock.checkLogin("B0089746","123458"));
    }

    @Test
    void isUserTest() {
        assertTrue(mock.isUser("B00123456"));
        assertFalse(mock.isUser("B00937464"));
    }

    @Test
    void getEmailByBanneridTest() {
        assertEquals("rutikapatel09@gmail.com",mock.getEmailByBannerid("B00123456"));
        assertEquals(service.getEmailByBannerid("B00123456"),mock.getEmailByBannerid("B00123456"));
    }


    @Test
    void insertToForgetPasswordTest() {
        assertTrue(mock.insertToForgetPassword("B00123456","e3Twq6Hyip"));
        assertFalse(mock.insertToForgetPassword("B00123456","fhrshr34h"));
    }

    @Test
    void comparePasswordTest() {
        assertEquals("12345","12345");
        assertNotEquals("123","12345");
    }

    @Test
    void getBannerIdByPassKeyTest() {
        assertEquals("B00123456",mock.getBannerIdByPassKey("e3Twq6Hyip"));
        assertNotEquals("B00156789",mock.getBannerIdByPassKey("e3Twq6Hyip"));
    }

    @Test
    void updatePasswordTest() {
        assertTrue(mock.updatePassword("B00123456","12345"));
        assertFalse(mock.updatePassword("B00765432","12345"));
    }
}