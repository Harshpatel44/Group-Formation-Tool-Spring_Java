package CSCI5308.GroupFormationTool.Course.Controller;

import CSCI5308.GroupFormationTool.Course.AccessControl.ICourseService;
import CSCI5308.GroupFormationTool.Course.AccessControl.IHomeController;
import CSCI5308.GroupFormationTool.Course.AccessControl.IHomeService;
import CSCI5308.GroupFormationTool.Injector;
import CSCI5308.GroupFormationTool.Course.Model.UserId;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

//Dhruvesh Patel
@Controller
@RequestMapping
public class HomeController implements IHomeController {

	private IHomeService homeService;
	private ICourseService courseService;
	UserId user = new UserId();

	@RequestMapping("/home")
	public ModelAndView home() {
		ModelAndView model = new ModelAndView();
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if ((authentication instanceof AnonymousAuthenticationToken)) {
			model.setViewName("redirect:/login");
			return model;
		}

		user = new UserId();
		user.setUserId(authentication.getPrincipal().toString());
		homeService = Injector.instance().getHomeService();

		model.addObject("userId", user.getUserId());
		model.addObject("courses", homeService.getCourses(user));
		model.addObject("checkRole", homeService.checkRole(user));
		model.setViewName("home");
		return model;
	}

	/*
	 * @GetMapping("/home") public ModelAndView
	 * listcourse(@RequestParam(name="courseId") String courseId) {
	 * user.setUserId("B00123456"); //System.out.println(userId);
	 * System.out.println(courseId); homeService =
	 * Injector.instance().getHomeService(); ModelAndView modelview = new
	 * ModelAndView("home"); modelview.addObject("userRole", new UserRole());
	 * modelview.addObject("courses", homeService.getCourses(user));
	 * modelview.addObject("homeService", homeService.checkRole("B00123456",
	 * "hjak")); modelview.addObject("userId","B00123456"); return modelview; }
	 */
//
//	@RequestMapping("/home1")
//	public String listcourse(Model model, @RequestParam(name = "courseId")String courseId,@RequestParam(name = "userId")String userId) {
//		user = new UserId();
//		user.setUserId("B00123456");
//		System.out.println(userId);
//		System.out.println(courseId);
//		model.addAttribute("userId",user.getUserId());
//		model.addAttribute("checkRole",homeService.checkRole(courseId,user.getUserId()));
//		model.addAttribute("userRole",new UserRole());
//		model.addAttribute("courses", homeService.getCourses(user));
//		return "redirect:home";
//	}

//    @PostMapping("/home")
//    public ModelAndView course(UserRole userRole, BindingResult result)
//    {
//	    ModelAndView modelview = new ModelAndView();
//	    courseService.setRole(userRole);
//	    modelview.setViewName("course");
//		return modelview;
//
//	}

}