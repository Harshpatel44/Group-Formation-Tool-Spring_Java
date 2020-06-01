package CSCI5308.GroupFormationTool.UserAuthentication.Service;


import CSCI5308.GroupFormationTool.UserAuthentication.AccessControl.IPasswordEncryptor;
import CSCI5308.GroupFormationTool.UserAuthentication.AccessControl.IUserRepository;
import CSCI5308.GroupFormationTool.UserAuthentication.AccessControl.IUserService;
import CSCI5308.GroupFormationTool.Injector;
import CSCI5308.GroupFormationTool.UserAuthentication.Model.User;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService {



	private IUserRepository userRepository;
	private IPasswordEncryptor encryptor;

	@Override
	public boolean createUser(User user) {
		userRepository = Injector.instance().getUserRepository();
		encryptor=Injector.instance().getPasswordEncryptor();
		user.setPassword(encryptor.encoder(user.getPassword()));

		boolean success = userRepository.getUserByEmailId(user);

		if(success) {
			success = userRepository.createUser(user);
		}

		return success;
	}
}
