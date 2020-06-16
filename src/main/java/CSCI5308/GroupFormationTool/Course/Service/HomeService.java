package CSCI5308.GroupFormationTool.Course.Service;


import java.util.List;

import CSCI5308.GroupFormationTool.Course.Repository.HomeRepository;
import org.springframework.stereotype.Service;

import CSCI5308.GroupFormationTool.Course.AccessControl.IHomeService;
import CSCI5308.GroupFormationTool.Injector;
import CSCI5308.GroupFormationTool.Course.AccessControl.IHomeRepository;
import CSCI5308.GroupFormationTool.Course.Model.Course;
import CSCI5308.GroupFormationTool.Course.Model.UserId;
import CSCI5308.GroupFormationTool.Course.Model.UserRole;
//Dhruvesh Patel
@Service
public class HomeService implements IHomeService {
	public UserRole user;
	private IHomeRepository homeRepository;
	public HomeService() {}
    public HomeService(HomeRepository homeRepository) {
     Injector.instance().setHomeRepository(homeRepository);
	}




	public List<Course> getCourses(UserId user) {
		homeRepository = Injector.instance().getHomeRepository();
		return homeRepository.getcourse(user);
	}

	@Override
	public boolean checkRole(UserId user) {
		homeRepository = Injector.instance().getHomeRepository();
		boolean result = homeRepository.checkRole(user);
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
	
	
	


