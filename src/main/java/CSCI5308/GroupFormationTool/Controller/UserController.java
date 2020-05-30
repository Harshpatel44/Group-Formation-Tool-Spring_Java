package CSCI5308.GroupFormationTool.Controller;

import CSCI5308.GroupFormationTool.AccessControl.IUserController;
import CSCI5308.GroupFormationTool.Model.User;
import CSCI5308.GroupFormationTool.Service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

//  Author: Harsh Patel
    @GetMapping("/admin")
    @Override
    public ModelAndView adminPage() {
    ModelAndView mv = new ModelAndView();
    mv.setViewName("admin");
    return mv;
    }

//  Author: Harsh Patel
    @PostMapping("/createCourse")
    @Override
    public ModelAndView createCourse(@RequestParam("courseId") String courseId, @RequestParam("courseName") String courseName) {
        return null;
    }
}

