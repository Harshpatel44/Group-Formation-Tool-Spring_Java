package CSCI5308.GroupFormationTool.Course.Service;

import CSCI5308.GroupFormationTool.Course.Repository.CourseRepository;
import CSCI5308.GroupFormationTool.Injector;
import CSCI5308.GroupFormationTool.Course.AccessControl.ICourseRepository;
import CSCI5308.GroupFormationTool.Course.AccessControl.ICourseService;
//Dhruvesh Patel
public class CourseService implements ICourseService {
   private ICourseRepository courseRepository;

   public CourseService(){}
   public CourseService(CourseRepository courseRepository){
   	Injector.instance().setCourseRepository(courseRepository);
   }

	@Override
	public boolean checkRole(String userType) {
		boolean roleCheck = true;
		if(userType.equals("student") || userType.equals("Guest")){
			roleCheck=false;
		}
		else{
			roleCheck = true;
		}
		return roleCheck;
	}

	@Override
	public boolean checkUserType(String userType) {
		boolean user = false;
		if(userType.equals("instructor"))
		{
			user = true;
		}
		return user;
	}

	@Override
	public String addTa(String taId, String courseId) {
		courseRepository = Injector.instance().getCourseRepository();
		return courseRepository.addTa(taId,courseId);
	}
}


