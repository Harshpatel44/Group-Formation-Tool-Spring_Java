package CSCI5308.GroupFormationTool.Login.Controller;

import CSCI5308.GroupFormationTool.Injector;
import CSCI5308.GroupFormationTool.Login.AccessControl.ILoginController;
import CSCI5308.GroupFormationTool.Login.AccessControl.ILoginService;
import CSCI5308.GroupFormationTool.Login.Service.LoginService;
import CSCI5308.GroupFormationTool.UserAuthentication.Security.BCryptEncryption;

import CSCI5308.GroupFormationTool.UserAuthentication.Service.UserService;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class LoginController implements ILoginController {

	private ILoginService service;

	@Override
	@GetMapping("/login")
	public String displaylogin() {
		return "login";
	}

	@Override
	@GetMapping("/")
	public String getLoginUser(Model model) {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		if (authentication.getPrincipal().toString().equals("admin")) {
			return "redirect:/admin?userId=" + authentication.getPrincipal().toString();
		} else if (!(authentication instanceof AnonymousAuthenticationToken)) {
			return "redirect:/home?userId=" + authentication.getPrincipal().toString();
		} else {
			return "login";
		}

	}

	@Override
	@GetMapping("/updateNewPassword")
	public String displayNewPassword() {
		return "newPassword";
	}

	@Override
	@RequestMapping(value = "/updateNewPassword", method = RequestMethod.POST)
	public String newPassword(@RequestParam("newPassword") String newPassword,
							  @RequestParam("confirmPassword") String confirmPassword, @RequestParam("passKey") String passKey,
							  Model model) {
		boolean matchPassword;
		boolean update;
		String bannerid;
		UserService userService = (UserService) Injector.instance().getUserService();
		List<String> oldPasswords;
		service = Injector.instance().getLoginService();
		BCryptEncryption encryption = new BCryptEncryption();

		matchPassword = service.comparePassword(newPassword, confirmPassword);
		if (!matchPassword) {
			model.addAttribute("passKey", passKey);
			model.addAttribute("Error", "Passwords do not match");
			return "newPassword";
		}
		bannerid = service.getBannerIdByPassKey(passKey);
		oldPasswords = service.getPasswordByBannerId(bannerid);



		for(String password : oldPasswords)
		{
			if(encryption.passwordMatch(newPassword,password))
			{
				model.addAttribute("Error","New password cannot be same as the old password");
				return "newPassword";
			}
		}


		update = service.updatePassword(bannerid, newPassword);

		if (!update) {
			model.addAttribute("Error", "Error updating the password");
			return "newPassword";
		}

		return "login";
	}


	@Override
	@GetMapping("/resetPassword")
	public String displayResetPassword() {
		return "forgetPassword";
	}


	@Override
	@PostMapping("/resetPassword")
	public String resetPassword(@RequestParam("bannerid") String bannerid, Model model) throws MessagingException {
		boolean isUser;
		boolean addUser;
		boolean mailSend;
		String email;
		service = Injector.instance().getLoginService();
		isUser = service.isUser(bannerid);
		if (!isUser) {
			model.addAttribute("Error", "Not a valid user");
			return "forgetPassword";
		}

		String passKey = service.generatePassKey();

		addUser = service.insertToForgetPassword(bannerid, passKey);
		if (!addUser) {
			model.addAttribute("Error", "Error adding the user");
			return "forgetPassword";
		}

		email = service.getEmailByBannerid(bannerid);
		mailSend = service.sendMail(email, passKey);
		if (!mailSend) {
			model.addAttribute("Error", "Error sending the mail");
			return "forgetPassword";
		}

		model.addAttribute("Status", "Mail sent successfully");
		return "forgetPassword";
	}
}
