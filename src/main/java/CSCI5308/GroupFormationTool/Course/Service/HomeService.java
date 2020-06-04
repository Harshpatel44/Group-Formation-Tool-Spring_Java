package CSCI5308.GroupFormationTool.Course.Service;


import java.util.List;

import org.springframework.stereotype.Service;

import CSCI5308.GroupFormationTool.Course.AccessControl.IHomeService;
import CSCI5308.GroupFormationTool.Injector;
import CSCI5308.GroupFormationTool.Course.AccessControl.IHomeRepository;
import CSCI5308.GroupFormationTool.Course.Model.Course;
import CSCI5308.GroupFormationTool.Course.Model.UserId;
import CSCI5308.GroupFormationTool.Course.Model.UserRole;

@Service
public class HomeService implements IHomeService {

	public UserRole user;
	private IHomeRepository homeRepository;


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

//	public boolean checkRole(String courseId, String userId)
//	    {
//	    	String role = homeRepository.getRole(courseId, userId);
//
//	    	if(role.equals("Student") || role.equals("TA") || role.equals("Instructor"))
//			{
//				return true;
//			}
//             else{
//             	return false;
//			}

//	    	for(UserRole temp : role) {
//	    		String user = temp.getUserId();
//	    		String course = temp.getCourseId();
//	    		String role1 = temp.getRole();
//
//	            if(courseId.equals(course) && userId.equals(user))
//				{
//
//	            		if(role1 != "Guest")
//		            	{
//		            		return true;
//		            	}
//				}
//
//	        }
//			return false;
//      }
}
	
	
	


