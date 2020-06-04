package CSCI5308.GroupFormationTool.UserAuthentication.Controller;

import CSCI5308.GroupFormationTool.UserAuthentication.AccessControl.IUserService;
import CSCI5308.GroupFormationTool.Injector;
import CSCI5308.GroupFormationTool.Exceptions.ErrorHelper;
import CSCI5308.GroupFormationTool.Exceptions.ServiceLayerException;
import CSCI5308.GroupFormationTool.UserAuthentication.Model.User;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
	public String createUser(User user,BindingResult bindingResult) {
		userService = Injector.instance().getUserService();
		try {
			if(bindingResult.hasErrors()) {

				return "new_signup";
			}


			if(userService.createUser(user)) {
				return "redirect:/login";
			}

		}
		catch (ServiceLayerException e) {
			ErrorHelper.rejectErrors(bindingResult, e.getMapErrors());
			return "signup";
		}
		return "signup";
	}

	@GetMapping("/register")
	public String register(User user) {
		return "signup";
	}

}
