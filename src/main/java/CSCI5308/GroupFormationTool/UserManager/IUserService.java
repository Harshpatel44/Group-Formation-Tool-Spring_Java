package CSCI5308.GroupFormationTool.UserManager;
import CSCI5308.GroupFormationTool.Exceptions.ServiceLayerException;

public interface IUserService {
	boolean createUser(IUser user) throws ServiceLayerException;
}
