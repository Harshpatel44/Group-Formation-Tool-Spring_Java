package CSCI5308.GroupFormationTool.Course.Service;


import CSCI5308.GroupFormationTool.Course.Model.CreateCourse;
import CSCI5308.GroupFormationTool.Course.Model.DeleteCourse;

import CSCI5308.GroupFormationTool.Course.Repository.CourseRepository;
import CSCI5308.GroupFormationTool.Injector;
import CSCI5308.GroupFormationTool.Course.AccessControl.ICourseRepository;
import CSCI5308.GroupFormationTool.Course.AccessControl.ICourseService;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;

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


	@Override
	public Dictionary CoursesWithIdForDropdown() throws SQLException {
		ArrayList<ArrayList<String>> tempCourse;
		ArrayList<String> allCourseIds;
		ArrayList<String> allCourseNames;
		Dictionary allCoursesList = new Hashtable();

		tempCourse = Injector.instance().getCourseRepository().getAllCourses();
		allCourseIds = tempCourse.get(0);
		allCourseNames = tempCourse.get(1);
		try {
			for (int i = 0; i < allCourseIds.size(); i++) {
				allCoursesList.put(allCourseIds.get(i) + " " + allCourseNames.get(i), allCourseIds.get(i));
			}
		}
		catch (Exception e){}
		return allCoursesList;
	}

	@Override
	public boolean CreateCourseService(CreateCourse createCourse) throws Exception {
		ArrayList<String> allCourseNames = Injector.instance().getCourseRepository().getAllCourses().get(1);
		for(int i=0;i<allCourseNames.size();i++){
			if(allCourseNames.get(i).equals(createCourse.getCourseName())){
				createCourse.setCourseCreateMessage("Course name exists");
				return false;
			}
		}
		if(Injector.instance().getCourseRepository().createCourseRepo(createCourse)){
			createCourse.setCourseCreateMessage("Course created");
			return true;
		}
		else{
			createCourse.setCourseCreateMessage("Course id exists");
			return false;
		}
	}
	
	@Override
	public boolean DeleteCourseService(DeleteCourse deleteCourse) throws Exception {
		if(Injector.instance().getCourseRepository().deleteCourseRepo(deleteCourse)){
			deleteCourse.setCourseDeleteMessage("Course deleted");
			deleteCourse.setAllCourseIds(Injector.instance().getCourseRepository().getAllCourses().get(0));
			deleteCourse.setAllCourseNames(Injector.instance().getCourseRepository().getAllCourses().get(1));
			return true;
		}
		else{
			deleteCourse.setCourseDeleteMessage("Course does not exist");
			return false;
		}
	}

}


