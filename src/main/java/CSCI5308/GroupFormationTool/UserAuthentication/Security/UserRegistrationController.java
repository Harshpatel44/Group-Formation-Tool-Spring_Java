package CSCI5308.GroupFormationTool.UserAuthentication.Security;

import CSCI5308.GroupFormationTool.UserAuthentication.AccessControl.IUserService;
import CSCI5308.GroupFormationTool.Injector;
import CSCI5308.GroupFormationTool.UserAuthentication.Model.User;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.validation.Valid;

@Controller
public class UserRegistrationController implements WebMvcConfigurer {

	private IUserService userService;

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/login").setViewName("login");
	}


	@PostMapping("/register")
	public String createUser(@Valid User user, BindingResult bindingResult ) {
		userService = Injector.instance().getUserService();
		if(bindingResult.hasErrors()) {

			return "signup";
		}
		else {   	

			if(userService.createUser(user)) {
				return "redirect:/login";
			}
			else {
				
			    bindingResult.reject("emailExists", "Email id already exists. Please login or register with a new email id");
				return "signup";
			}
		}
	}

	@GetMapping("/register")
	public String register(User user) {
		return "signup";
	}

}
