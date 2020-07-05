package CSCI5308.GroupFormationTool.Course;

import CSCI5308.GroupFormationTool.Injector;
import CSCI5308.GroupFormationTool.UserManager.CurrentUser;
import CSCI5308.GroupFormationTool.UserManager.IUserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CourseController {
  
	private ICourseService courseService = Injector.instance().getCourseService();
	private IUserService userService = Injector.instance().getUserService();
	private CurrentCourse currentCourse = CurrentCourse.instance();

	@RequestMapping("/course")
	public ModelAndView course(@RequestParam(name="userRole") String userRole,
							   @RequestParam(name="courseId") String courseId,
							   @RequestParam(name="courseName") String courseName){
		currentCourse.setCurrentCourseId(courseId);
		currentCourse.setCurrentCourseName(courseName);
		currentCourse.setCurrentCourseUserRole(userRole);

		ModelAndView model=new ModelAndView("course");
		model.addObject("courseId",courseId);
		model.addObject("userId", CurrentUser.instance().getBannerId());
		model.addObject("checkRole",courseService.roleAllowInstructorAndTA(userRole));
		model.setViewName("course");
		return model;
	}

	@RequestMapping("/course/admin")
	public ModelAndView courseAdmin(@RequestParam(name="checkRole") String checkRole) throws Exception {
		ModelAndView model=new ModelAndView("courseadmin");
		model.addObject("ta",Injector.instance().getTA());
		model.addObject("userId",CurrentUser.instance().getBannerId());
		model.addObject("courseId",CurrentCourse.instance().getCurrentCourseId());
		model.addObject("checkRole",checkRole);
		model.setViewName("courseadmin");
		return model;
	}

	@RequestMapping("/course/admin/addta")
	public ModelAndView addta( @RequestParam(name="taId") String taId) throws Exception {
		courseService = Injector.instance().getCourseService();
		String courseId = CurrentCourse.instance().getCurrentCourseId();
		ModelAndView model=new ModelAndView("courseadmin");
		model.addObject("userId",CurrentUser.instance().getBannerId());
		model.addObject("courseId",courseId);
		model.addObject("result",courseService.addTAForCourse(taId,courseId));
		model.setViewName("courseadmin");
		return model;
	}
}
