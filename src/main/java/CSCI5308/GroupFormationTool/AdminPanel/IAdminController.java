package CSCI5308.GroupFormationTool.AdminPanel;
import CSCI5308.GroupFormationTool.Course.CreateCourse;
import CSCI5308.GroupFormationTool.Course.DeleteCourse;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;

public interface IAdminController {
    public ModelAndView adminPage(Model model, HttpServletRequest request) throws Exception;
    public String createCourse(CreateCourse createCourse, RedirectAttributes redirectAttributes) throws Exception;
    public String deleteCourse(DeleteCourse deleteCourse, RedirectAttributes redirectAttributes) throws Exception;
    public String assignInstructor(Instructor assignInstructor, RedirectAttributes redirectAttributes) throws Exception;
}