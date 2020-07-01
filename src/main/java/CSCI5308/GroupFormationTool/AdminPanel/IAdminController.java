package CSCI5308.GroupFormationTool.AdminPanel;
import CSCI5308.GroupFormationTool.Course.ICreateCourse;
import CSCI5308.GroupFormationTool.Course.IDeleteCourse;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;

public interface IAdminController {
    public ModelAndView adminPage(Model model, HttpServletRequest request) throws Exception;
    public String createCourse(ICreateCourse createCourse, RedirectAttributes redirectAttributes) throws Exception;
    public String deleteCourse(IDeleteCourse deleteCourse, RedirectAttributes redirectAttributes) throws Exception;
    public String assignInstructor(IInstructor instructor, RedirectAttributes redirectAttributes) throws Exception;
}