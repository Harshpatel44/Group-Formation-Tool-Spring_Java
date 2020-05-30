package CSCI5308.GroupFormationTool.AdminPanel.AccessControl;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

public interface IAdminController {
    public ModelAndView adminPage();
    public ModelAndView createCourse(@RequestParam() String courseId, @RequestParam() String courseName);
}