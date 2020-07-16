package CSCI5308.GroupFormationTool.PasswordManager;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import CSCI5308.GroupFormationTool.UserAuthentication.IPasswordEncryptor;
import CSCI5308.GroupFormationTool.UserAuthentication.IUserNotification;
import CSCI5308.GroupFormationTool.UserAuthentication.UserAuthenticationAbstractFactory;
import CSCI5308.GroupFormationTool.UserManager.UserManagerAbstractFactory;

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
        service = UserPasswordManagerAbstractFactory.instance().getForgetPasswordService();
        IPasswordEncryptor encryption = UserAuthenticationAbstractFactory.instance().getBCryptEncryption();
        matchPassword = service.comparePassword(newPassword, confirmPassword);
        if (matchPassword == false) {
            model.addAttribute("passKey", passKey);
            model.addAttribute("Error", "Passwords do not match");
            return "newPassword";
        }
        iUserPasswordPolicyService = UserPasswordManagerAbstractFactory.instance().getPasswordPolicyService();
		// Getting data for singletonClasses UserPasswordPolicy and UserPasswordPolicyStatus
		UserPasswordManagerAbstractFactory.instance().getPasswordPolicyRepository().getUserPasswordPolicy();
        UserPasswordManagerAbstractFactory.instance().getPasswordPolicyRepository().getUserPasswordPolicyStatus();
        
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
        service = UserPasswordManagerAbstractFactory.instance().getForgetPasswordService();
        userNotification = UserAuthenticationAbstractFactory.instance().getUserNotification();
        isUser = UserManagerAbstractFactory.instance().getUserService().checkIfUserExists(bannerID);
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
