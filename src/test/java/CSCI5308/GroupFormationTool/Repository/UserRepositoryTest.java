package CSCI5308.GroupFormationTool.Repository;

import CSCI5308.GroupFormationTool.AccessControl.IUserRepository;
import CSCI5308.GroupFormationTool.Model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UserRepositoryTest {


    @Autowired
    private UserRepository userRepository;

    @Test
    void createUser() {
        assertEquals(false,userRepository.createUser(new User()));
    }
}