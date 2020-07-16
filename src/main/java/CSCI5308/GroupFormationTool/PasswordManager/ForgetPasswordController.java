package CSCI5308.GroupFormationTool.PasswordManager;

import CSCI5308.GroupFormationTool.Injector;
import CSCI5308.GroupFormationTool.UserAuthentication.IPasswordEncryptor;
import CSCI5308.GroupFormationTool.UserAuthentication.IUserNotification;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class ForgetPasswordController {
    private IForgetPasswordService service;
    private IUserNotification userNotification;
    private IUserPasswordPolicyService iUserPasswordPolicyService;

    @GetMapping("/updateNewPassword")
    public String displayNewPassword() {
        return "newPassword";
    }

    @RequestMapping(value = "/updateNewPassword", method = RequestMethod.POST)
    public String newPassword(@RequestParam("newPassword") String newPassword,
                              @RequestParam("confirmPassword") String confirmPassword, @RequestParam("passKey") String passKey,
                              Model model) throws Exception {
        boolean matchPassword;
        boolean update;
        String bannerID;
        int passNumber;
        
        Map<String, String> errors = new HashMap<>();
        List<String> oldPasswords;
        service = Injector.instance().getPasswordManagerAbstractFactory().getForgetPasswordService();
        IPasswordEncryptor encryption = Injector.instance().getAuthenticationAbstractFactory().getBCryptEncryption();
        matchPassword = service.comparePassword(newPassword, confirmPassword);
        if (matchPassword == false) {
            model.addAttribute("passKey", passKey);
            model.addAttribute("Error", "Passwords do not match");
            return "newPassword";
        }
        iUserPasswordPolicyService = Injector.instance().getPasswordManagerAbstractFactory().getPasswordPolicyService();
		// Getting data for singletonClasses UserPasswordPolicy and UserPasswordPolicyStatus
		Injector.instance().getPasswordManagerAbstractFactory().getPasswordPolicyRepository().getUserPasswordPolicy();
        Injector.instance().getPasswordManagerAbstractFactory().getPasswordPolicyRepository().getUserPasswordPolicyStatus();
        
        List<String> validationErrors = iUserPasswordPolicyService.checkPasswordValidation(newPassword, errors);
        if (validationErrors.size() > 0) {
            model.addAttribute("isError", true);
            model.addAttribute("AllErrors", validationErrors);
            return "newPassword";
        }
        bannerID = service.getBannerIDByPassKey(passKey);
        passNumber = service.getPasswordPolicyNumber();
        oldPasswords = service.getPasswordByBannerID(bannerID,passNumber);
        for (String password : oldPasswords) {
            if (encryption.passwordMatch(newPassword, password)) {
                model.addAttribute("Error", "New password cannot be same as the old password");
                return "newPassword";
            }
        }
        update = service.updatePassword(bannerID, newPassword);
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
    public String resetPassword(@RequestParam("bannerID") String bannerID, Model model) throws Exception {
        boolean isUser;
        boolean addUser;
        boolean mailSend;
        String email;
        service = Injector.instance().getPasswordManagerAbstractFactory().getForgetPasswordService();
        userNotification = Injector.instance().getAuthenticationAbstractFactory().getUserNotification();
        isUser = Injector.instance().getUserService().checkIfUserExists(bannerID);
        if (isUser == false) {
            model.addAttribute("Error", "Not a valid user");
            return "forgetPassword";
        }
        String passKey = service.generatePassKey();
        addUser = service.insertToForgetPassword(bannerID, passKey);
        if (addUser==false) {
            model.addAttribute("Error", "Error adding the user");
            return "forgetPassword";
        }
        email = service.getEmailByBannerID(bannerID);
        mailSend = userNotification.sendUserForgetPasswordLink(email, passKey);
        if (mailSend == false) {
            model.addAttribute("Error", "Error sending the mail");
            return "forgetPassword";
        }
        model.addAttribute("Status", "Mail sent successfully");
        return "forgetPassword";
    }
}
