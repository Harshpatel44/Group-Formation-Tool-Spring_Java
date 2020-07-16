package CSCI5308.GroupFormationTool.UserAuthentication;

import CSCI5308.GroupFormationTool.Injector;
import CSCI5308.GroupFormationTool.Exceptions.ErrorHelper;
import CSCI5308.GroupFormationTool.Exceptions.ServiceLayerException;
import CSCI5308.GroupFormationTool.GroupFormmer.GroupFilter;
import CSCI5308.GroupFormationTool.GroupFormmer.GroupFormmerService;
import CSCI5308.GroupFormationTool.GroupFormmer.IGroupFormmerService;
import CSCI5308.GroupFormationTool.PasswordManager.IUserPasswordPolicy;
import CSCI5308.GroupFormationTool.PasswordManager.IUserPasswordPolicyService;
import CSCI5308.GroupFormationTool.PasswordManager.IUserPasswordPolicyStatus;
import CSCI5308.GroupFormationTool.PasswordManager.UserPasswordPolicy;
import CSCI5308.GroupFormationTool.PasswordManager.UserPasswordPolicyStatus;
import CSCI5308.GroupFormationTool.UserManager.IUser;
import CSCI5308.GroupFormationTool.UserManager.IUserService;
import CSCI5308.GroupFormationTool.UserManager.User;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
	public ModelAndView createUser(@RequestParam("firstName") String firstName,
								   @RequestParam("lastName") String lastName,
								   @RequestParam("emailId") String emailId,
								   @RequestParam("contactNumber") String contactNumber,
								   @RequestParam("password") String password,
								   @RequestParam("confirmPassword") String confirmPassword,
								   @RequestParam("bannerId") String bannerId)
								   {
		userService = Injector.instance().getUserService();
		ModelAndView mv = new ModelAndView();
		mv.addObject("passwordPolicy", UserPasswordPolicy.getInstance());
		try {
			IUser iUser = userService.setUser(bannerId,firstName,lastName,emailId,password,contactNumber);
			iUser.setConfirmPassword(confirmPassword);
			if (userService.createUser(iUser)) {
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
			mv.addObject("user",Injector.instance().getUser());
			mv.setViewName("signup");
			return mv;

		}
		mv.setViewName("signup");
		return mv;
	}

	@GetMapping("/register")
	public ModelAndView register() throws Exception {
		// Getting data for singletonClasses UserPasswordPolicy and UserPasswordPolicyStatus
		Injector.instance().getPasswordManagerAbstractFactory().getPasswordPolicyRepository().getUserPasswordPolicy();
        Injector.instance().getPasswordManagerAbstractFactory().getPasswordPolicyRepository().getUserPasswordPolicyStatus();
		IUserPasswordPolicyService iUserPasswordPolicyService = Injector.instance().getPasswordManagerAbstractFactory().getPasswordPolicyService();
		IUserPasswordPolicy passwordPolicy = iUserPasswordPolicyService.getUserPasswordPolicy();
		ModelAndView mv = new ModelAndView();
		mv.addObject("user",Injector.instance().getUser());
		mv.addObject("passwordPolicy", passwordPolicy);
		mv.setViewName("signup");
		return mv;
	}
}
