package CSCI5308.GroupFormationTool.AccessControl;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

public interface IUserController {

    public boolean createUser();
    public ModelAndView adminPage();
    public ModelAndView createCourse(@RequestParam() String courseId, @RequestParam() String courseName);
}
