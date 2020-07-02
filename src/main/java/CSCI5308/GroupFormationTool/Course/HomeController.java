package CSCI5308.GroupFormationTool.Course;

import CSCI5308.GroupFormationTool.Injector;

import CSCI5308.GroupFormationTool.UserManager.IUserRole;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping
public class HomeController {

	private IHomeService homeService;
	IUserRole userRole = Injector.instance().getUserRole();

	public HomeController() throws Exception {
	}

	@RequestMapping("/home")
	public ModelAndView home() throws Exception {
		ModelAndView model = new ModelAndView();
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if ((authentication instanceof AnonymousAuthenticationToken)) {
			model.setViewName("redirect:/login");
			return model;
		}

		userRole.setUserId(authentication.getPrincipal().toString());
		homeService = Injector.instance().getHomeService();
		model.addObject("userId", userRole.getUserId());
		model.addObject("courses", homeService.getCourses(userRole));
		model.addObject("checkRole", homeService.checkRole(userRole));
		model.setViewName("home");
		return model;
	}
}
