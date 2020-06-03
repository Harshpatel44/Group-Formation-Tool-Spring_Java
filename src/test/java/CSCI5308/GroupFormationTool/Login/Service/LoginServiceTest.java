package CSCI5308.GroupFormationTool.Login.Service;

import CSCI5308.GroupFormationTool.Login.AccessControl.ILoginRepository;
import CSCI5308.GroupFormationTool.Login.AccessControl.ILoginService;
import CSCI5308.GroupFormationTool.Login.Repository.MockLoginRepository;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LoginServiceTest {

    MockLoginRepository mock = new MockLoginRepository();

    @Test
    void checkLogin()
    {
        assertTrue(mock.checkLogin("B00123456","12345"));
        assertFalse(mock.checkLogin("B0089746","123458"));
    }
}