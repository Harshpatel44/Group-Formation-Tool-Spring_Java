package CSCI5308.GroupFormationTool.UserAuthentication.Service;

import CSCI5308.GroupFormationTool.UserAuthentication.Model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserService userService;

//    @Test
//    void createUser() {
//        assertEquals(false, userService.createUser(new User()));
//    }
}