package CSCI5308.GroupFormationTool.Controller;

import CSCI5308.GroupFormationTool.AccessControl.IUserController;
import CSCI5308.GroupFormationTool.AccessControl.IUserService;
import CSCI5308.GroupFormationTool.Model.User;
import CSCI5308.GroupFormationTool.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class UserController implements IUserController {

    private UserService userService;

    @PostMapping("/createUser")
    @Override
    public boolean createUser() {
        User user = new User();
        user.setFirstName("padmesh");
        return true;
    }
    
    @GetMapping("/admin")
    @Override
    public ModelAndView adminPage() {
    ModelAndView mv = new ModelAndView();
    mv.setViewName("admin");
    return mv;
    }
}
