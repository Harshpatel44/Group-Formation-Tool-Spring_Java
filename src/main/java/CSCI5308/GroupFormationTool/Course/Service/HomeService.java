package CSCI5308.GroupFormationTool.Course.Service;


import java.util.List;

import CSCI5308.GroupFormationTool.Course.AccessControl.IUserId;
import CSCI5308.GroupFormationTool.Course.Repository.HomeRepository;
import org.springframework.stereotype.Service;

import CSCI5308.GroupFormationTool.Course.AccessControl.IHomeService;
import CSCI5308.GroupFormationTool.Injector;
import CSCI5308.GroupFormationTool.Course.AccessControl.IHomeRepository;
import CSCI5308.GroupFormationTool.Course.Model.Course;
import CSCI5308.GroupFormationTool.Course.Model.UserRole;

@Service
public class HomeService implements IHomeService {
	public UserRole user;
	private IHomeRepository homeRepository;
	public HomeService() {}
    public HomeService(HomeRepository homeRepository) {
     Injector.instance().setHomeRepository(homeRepository);
	}


	public List<Course> getCourses(IUserId iUserId) {
		homeRepository = Injector.instance().getHomeRepository();
		return homeRepository.getcourse(iUserId);
	}

	@Override
	public boolean checkRole(IUserId iUserId) {
		homeRepository = Injector.instance().getHomeRepository();
		boolean result = homeRepository.checkRole(iUserId);
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
	
	
	


