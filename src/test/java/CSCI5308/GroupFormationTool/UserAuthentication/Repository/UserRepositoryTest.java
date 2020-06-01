package CSCI5308.GroupFormationTool.UserAuthentication.Repository;

import CSCI5308.GroupFormationTool.Injector;
import CSCI5308.GroupFormationTool.UserAuthentication.AccessControl.IUserRepository;
import CSCI5308.GroupFormationTool.UserAuthentication.Model.User;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class UserRepositoryTest {

	private IUserRepository userRepository;

	@Test
	void createUser() {
		userRepository = Injector.instance().getUserRepository();
		assertEquals(true, userRepository.createUser(new User() {
			{
				setPassword("1234567890");
				setFirstName("Arjun");
			}
		}));
    }
}