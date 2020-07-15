package CSCI5308.GroupFormationTool.Course;

import CSCI5308.GroupFormationTool.Injector;

import CSCI5308.GroupFormationTool.UserManager.CurrentUser;
import CSCI5308.GroupFormationTool.UserManager.IUserService;
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
	private IUserService iUserService;
	public HomeController(){
	}

	@RequestMapping("/home")
	public ModelAndView home() throws Exception {
		ModelAndView model = new ModelAndView();
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if ((authentication instanceof AnonymousAuthenticationToken)) {
			model.setViewName("redirect:/login");
			return model;
		}
		homeService = Injector.instance().getHomeService();
		iUserService = Injector.instance().getUserService();
		iUserService.setCurrentUserByBannerID(authentication.getPrincipal().toString());
		String bannerID = CurrentUser.instance().getBannerId();

		model.addObject("userId", bannerID);
		model.addObject("courses", homeService.getCourseFromBannerID(bannerID));
		model.addObject("checkRole", homeService.checkRoleOfUser(bannerID));
		model.setViewName("home");
		return model;
	}
}
