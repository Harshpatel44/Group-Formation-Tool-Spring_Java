package CSCI5308.GroupFormationTool.UserAuthentication.Repository;

import CSCI5308.GroupFormationTool.UserAuthentication.Model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class UserRepositoryTest {


    @Autowired
    private UserRepository userRepository;

//    @Test
//    void createUser() {
//        assertEquals(false,userRepository.createUser(new User()));
//    }
}