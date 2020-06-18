package CSCI5308.GroupFormationTool.Course.Controller;

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
}