package CSCI5308.GroupFormationTool.Service;

import CSCI5308.GroupFormationTool.Injector;
import CSCI5308.GroupFormationTool.AccessControl.IUserService;
import CSCI5308.GroupFormationTool.Model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UserServiceTest {

    
    private IUserService userService;

    @Test
    void createUser() {
    	userService = Injector.instance().getUserService();
        assertEquals(true, userService.createUser(new User() {{
        	setPassword("1234567890");
        	setFirstName("Arjun");
        }}));
        
    }
}