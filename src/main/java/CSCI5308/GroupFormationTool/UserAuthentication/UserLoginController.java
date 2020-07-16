package CSCI5308.GroupFormationTool.UserAuthentication;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import static CSCI5308.GroupFormationTool.ApplicationConstants.admin;

@Controller
public class UserLoginController {

    @GetMapping("/login")
    public String displayLogin() {
        return "login";
    }

    @GetMapping("/")
    public String getLoginUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.getPrincipal().toString().equals(admin)) {
            return "redirect:/admin?userId=" + authentication.getPrincipal().toString();
        } else if ((authentication instanceof AnonymousAuthenticationToken) == false) {
            return "redirect:/home?userId=" + authentication.getPrincipal().toString();
        } else {
            return "login";
        }
    }
}
