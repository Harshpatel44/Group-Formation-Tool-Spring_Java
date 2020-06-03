package CSCI5308.GroupFormationTool.Login.Controller;

import CSCI5308.GroupFormationTool.Login.Service.LoginService;
import CSCI5308.GroupFormationTool.UserAuthentication.Security.BCryptEncryption;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController{

    @GetMapping("/login")
    public String displaylogin()
    {
        return "login";
    }

    @PostMapping("/login")
    public String getLoginUser(@RequestParam("bannerId") String bannerid,
                               @RequestParam("password") String password,
                               Model model) {

        boolean isValid = false;
        LoginService service = new LoginService();
        isValid = service.checkLogin(bannerid, password);
        if(isValid)
        {
            return "redirect:/home?userId="+bannerid;
        }
        else
        {
            model.addAttribute("Error","Invalid username or password");
            return "login";
        }
    }
}
