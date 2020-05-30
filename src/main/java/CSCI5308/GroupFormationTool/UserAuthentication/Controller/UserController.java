package CSCI5308.GroupFormationTool.UserAuthentication.Controller;

import CSCI5308.GroupFormationTool.UserAuthentication.AccessControl.IUserController;
import CSCI5308.GroupFormationTool.UserAuthentication.Model.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class UserController implements IUserController {

    @PostMapping("/createUser")
    @Override
    public boolean createUser() {
        User user = new User();
        user.setFirstName("padmesh");
        return true;
    }


}

