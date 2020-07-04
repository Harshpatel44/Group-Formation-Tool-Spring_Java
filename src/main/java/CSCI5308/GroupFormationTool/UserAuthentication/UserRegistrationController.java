package CSCI5308.GroupFormationTool.UserAuthentication;

import CSCI5308.GroupFormationTool.Injector;
import CSCI5308.GroupFormationTool.Exceptions.ErrorHelper;
import CSCI5308.GroupFormationTool.Exceptions.ServiceLayerException;

import CSCI5308.GroupFormationTool.PasswordManager.UserPasswordPolicy;
import CSCI5308.GroupFormationTool.PasswordManager.UserPasswordPolicyStatus;
import CSCI5308.GroupFormationTool.UserManager.IUserService;
import CSCI5308.GroupFormationTool.UserManager.User;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Controller
public class UserRegistrationController implements WebMvcConfigurer {

	private IUserService userService;

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/login").setViewName("login");
	}

	@PostMapping("/register")
	public ModelAndView createUser(User user, BindingResult bindingResult) throws Exception {
		userService = Injector.instance().getUserService();
		ModelAndView mv = new ModelAndView();
		mv.addObject("passwordPolicy", UserPasswordPolicy.getInstance());
		try {
			if (bindingResult.hasErrors()) {

				mv.setViewName("signup");
				return mv;
			}
			if (userService.createUser(user)) {
				mv.setViewName("redirect:/login");
				return mv;
			}
		} catch (ServiceLayerException e) {
			if (e.getMapErrors().containsKey("confirmPassword")) {
				if (e.getMapErrors().get("confirmPassword").split(";;").length > 0) {
					String errorPassowrd = e.getMapErrors().get("confirmPassword");
					mv.addObject("unfollowedPolicy", errorPassowrd.split(";;"));
				}
			}
			ErrorHelper.rejectErrors(bindingResult, e.getMapErrors());
			mv.setViewName("signup");
			return mv;
		}
		mv.setViewName("signup");
		return mv;
	}

	@GetMapping("/register")
	public ModelAndView register(User user) throws Exception {
		userService = Injector.instance().getUserService();
		UserPasswordPolicy passwordPolicy = Injector.instance().getUserPasswordPolicyService().getUserPasswordPolicy();
		UserPasswordPolicyStatus passwordPolicyStatus = Injector.instance().getUserPasswordPolicyService().getUserPasswordPolicyStatus();
		ModelAndView mv = new ModelAndView();
		mv.addObject("passwordPolicy", passwordPolicy);
		mv.addObject("userId",user);
		mv.setViewName("signup");
		return mv;
	}
}
