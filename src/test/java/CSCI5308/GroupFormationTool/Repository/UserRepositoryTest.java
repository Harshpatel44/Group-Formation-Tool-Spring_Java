package CSCI5308.GroupFormationTool.Repository;

import CSCI5308.GroupFormationTool.Injector;
import CSCI5308.GroupFormationTool.AccessControl.IUserRepository;
import CSCI5308.GroupFormationTool.Model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;

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