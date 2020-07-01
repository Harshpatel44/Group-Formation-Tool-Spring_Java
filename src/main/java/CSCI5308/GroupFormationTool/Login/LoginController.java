package CSCI5308.GroupFormationTool.Login;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class LoginController{

	@GetMapping("/login")
	public String displaylogin() {
		return "login";
	}

	@GetMapping("/")
	public String getLoginUser(Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication.getPrincipal().toString().equals("admin")) {
			return "redirect:/admin?userId=" + authentication.getPrincipal().toString();
		}
		else if ((authentication instanceof AnonymousAuthenticationToken) == false) {
			return "redirect:/home?userId=" + authentication.getPrincipal().toString();
		}
		else {
			return "login";
		}
	}
}
