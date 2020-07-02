package CSCI5308.GroupFormationTool.Course;


import java.util.List;

import CSCI5308.GroupFormationTool.UserManager.IUserRole;
import org.springframework.stereotype.Service;

import CSCI5308.GroupFormationTool.Injector;

@Service
public class HomeService implements IHomeService {
	public IUserRole user;
	private IHomeRepository homeRepository;
	public HomeService() {}
    public HomeService(HomeRepository homeRepository) throws Exception {
     Injector.instance().setHomeRepository(homeRepository);
	}


	public List<ICourse> getCourses(IUserRole userRole) throws Exception {
		homeRepository = Injector.instance().getHomeRepository();
		return homeRepository.getcourse(userRole);
	}

	@Override
	public boolean checkRole(IUserRole userRole) throws Exception {
		homeRepository = Injector.instance().getHomeRepository();
		boolean result = homeRepository.checkRole(userRole);
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
	
	
	


