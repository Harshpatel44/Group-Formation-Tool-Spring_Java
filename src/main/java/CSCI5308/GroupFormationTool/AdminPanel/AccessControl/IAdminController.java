package CSCI5308.GroupFormationTool.AdminPanel.AccessControl;
import CSCI5308.GroupFormationTool.AdminPanel.Model.CreateCourse;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

//  Author: Harsh Patel
public interface IAdminController {
    public ModelAndView adminPage(CreateCourse createCourse);
    public ModelAndView createCourse(CreateCourse createCourse);
}