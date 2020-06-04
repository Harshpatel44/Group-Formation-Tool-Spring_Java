package CSCI5308.GroupFormationTool.Login.Controller;

import CSCI5308.GroupFormationTool.Login.Service.LoginService;
import CSCI5308.GroupFormationTool.UserAuthentication.Security.BCryptEncryption;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;

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

    @GetMapping("/updateNewPassword")
    public String displayNewPassword()
    {
        return "newPassword";
    }


    @RequestMapping(value = "/updateNewPassword",method = RequestMethod.POST)
    public String newPassword(@RequestParam("newPassword") String newPassword,
                              @RequestParam("confirmPassword") String confirmPassword,
                              @RequestParam("passKey") String passKey,
                              Model model)
    {
        boolean matchPassword;
        boolean update;
        String bannerid;
        LoginService service = new LoginService();

        matchPassword = service.comparePassword(newPassword,confirmPassword);
        if(!matchPassword)
        {
            model.addAttribute("passKey",passKey);
            model.addAttribute("Error","Passwords do not match");
            return "newPassword" ;
        }
        bannerid = service.getBannerIdByPassKey(passKey);
        update = service.updatePassword(bannerid,newPassword);

        if(!update)
        {
            model.addAttribute("Error","Error updating the password");
            return "newPassword" ;
        }

        return "login";
    }


    @GetMapping("/resetPassword")
    public String displayResetPassword()
    {
        return "forgetPassword";
    }


    @PostMapping("/resetPassword")
    public String resetPassword(@RequestParam("bannerid") String bannerid, Model model) throws MessagingException {
        boolean isUser;
        boolean addUser;
        boolean mailSend;
        String email;
        LoginService service = new LoginService();
        isUser = service.isUser(bannerid);
        if(!isUser)
        {
            model.addAttribute("Error","Not a valid user");
            return "forgetPassword";
        }

        String passKey = service.generatePassKey();

        addUser = service.insertToForgetPassword(bannerid,passKey);
        if(!addUser)
        {
            model.addAttribute("Error","Error adding the user");
            return "forgetPassword";
        }

        email = service.getEmailByBannerid(bannerid);
        mailSend = service.sendMail(email,passKey);
        if(!mailSend)
        {
            model.addAttribute("Error","Error sending the mail");
            return "forgetPassword";
        }

        model.addAttribute("Status","Mail sent successfully");
        return "forgetPassword";
    }

}
