package CSCI5308.GroupFormationTool.Course;
import java.util.List;

import CSCI5308.GroupFormationTool.UserManager.UserRepository;
import org.springframework.stereotype.Service;
import CSCI5308.GroupFormationTool.Injector;

@Service
public class HomeService implements IHomeService {
	private IHomeRepository homeRepository;
	public HomeService() {}
    public HomeService(HomeRepository homeRepository, UserRepository userRepository){
     Injector.instance().setHomeRepository(homeRepository);
     Injector.instance().setUserRepository(userRepository);
	}


	public List<ICourse> getCourseFromBannerID(String bannerID) throws Exception {
		homeRepository = Injector.instance().getHomeRepository();
		boolean GuestOrNot = Injector.instance().getUserService().checkIfUserIsGuest(bannerID);
		return homeRepository.getCourseFromBannerID(bannerID,GuestOrNot);
	}

	@Override
	public boolean checkRoleOfUser(String bannerID){
		boolean result = Injector.instance().getUserService().checkIfUserIsGuest(bannerID);
		if(result)
		{
			return false;
		}
		else
		{
			return true;
		}
	}
}
	
	
	


