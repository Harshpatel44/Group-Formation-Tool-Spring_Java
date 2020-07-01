package CSCI5308.GroupFormationTool.Login;

import CSCI5308.GroupFormationTool.Injector;
import CSCI5308.GroupFormationTool.UserAuthentication.IUserNotification;
import CSCI5308.GroupFormationTool.UserAuthentication.UserPasswordPolicy;
import CSCI5308.GroupFormationTool.UserAuthentication.UserPasswordPolicyStatus;
import CSCI5308.GroupFormationTool.UserAuthentication.BCryptEncryption;
import CSCI5308.GroupFormationTool.UserAuthentication.UserService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ForgetPasswordController {
    private IForgetPasswordService service;
    private ILoginService loginService;
    private IUserNotification userNotification;

    @GetMapping("/updateNewPassword")
    public String displayNewPassword() {
        return "newPassword";
    }

    @RequestMapping(value = "/updateNewPassword", method = RequestMethod.POST)
    public String newPassword(@RequestParam("newPassword") String newPassword,
                              @RequestParam("confirmPassword") String confirmPassword, @RequestParam("passKey") String passKey,
                              Model model) {
        boolean matchPassword;
        boolean update;
        String bannerid;
        int passNumber;
        Map<String, String> errors = new HashMap<>();
        UserPasswordPolicy userPasswordPolicy = Injector.instance().getUserRepository().getUserPasswordPolicy();
        UserPasswordPolicyStatus userPasswordPolicystatus = Injector.instance().getUserRepository().getUserPasswordPolicyStatus();
        UserService userService = (UserService) Injector.instance().getUserService();
        List<String> oldPasswords;
        service = Injector.instance().getForgetPasswordService();
        BCryptEncryption encryption = new BCryptEncryption();
        matchPassword = service.comparePassword(newPassword, confirmPassword);
        if (matchPassword == false) {
            model.addAttribute("passKey", passKey);
            model.addAttribute("Error", "Passwords do not match");
            return "newPassword";
        }
        List<String> validationErrors = userService.checkPasswordValidation(newPassword, errors);
        if (validationErrors.size() > 0) {
            UserPasswordPolicy passwordPolicy = UserPasswordPolicy.getInstance();
            model.addAttribute("isError", true);
            model.addAttribute("AllErrors", validationErrors);

            return "newPassword";
        }
        bannerid = service.getBannerIdByPassKey(passKey);
        passNumber = service.getPasswordPolicyNumber();
        oldPasswords = service.getPasswordByBannerId(bannerid,passNumber);
        for (String password : oldPasswords) {
            if (encryption.passwordMatch(newPassword, password)) {
                model.addAttribute("Error", "New password cannot be same as the old password");
                return "newPassword";
            }
        }
        update = service.updatePassword(bannerid, newPassword);
        if (update == false) {
            model.addAttribute("Error", "Error updating the password");
            return "newPassword";
        }
        return "login";
    }

    @GetMapping("/resetPassword")
    public String displayResetPassword() {
        return "forgetPassword";
    }

    @PostMapping("/resetPassword")
    public String resetPassword(@RequestParam("bannerid") String bannerid, Model model) throws MessagingException {
        boolean isUser;
        boolean addUser;
        boolean mailSend;
        String email;
        service = Injector.instance().getForgetPasswordService();
        userNotification = Injector.instance().getUserNotification();
        isUser = loginService.isUser(bannerid);
        if (isUser == false) {
            model.addAttribute("Error", "Not a valid user");
            return "forgetPassword";
        }
        String passKey = service.generatePassKey();
        addUser = service.insertToForgetPassword(bannerid, passKey);
        if (addUser==false) {
            model.addAttribute("Error", "Error adding the user");
            return "forgetPassword";
        }
        email = service.getEmailByBannerid(bannerid);
        mailSend = userNotification.sendUserForgetPasswordLink(email, passKey);
        if (mailSend == false) {
            model.addAttribute("Error", "Error sending the mail");
            return "forgetPassword";
        }
        model.addAttribute("Status", "Mail sent successfully");
        return "forgetPassword";
    }
}
