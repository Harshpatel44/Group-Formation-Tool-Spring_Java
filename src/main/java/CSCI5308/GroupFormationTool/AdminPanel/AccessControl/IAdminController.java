package CSCI5308.GroupFormationTool.AdminPanel.AccessControl;
import CSCI5308.GroupFormationTool.AdminPanel.Model.AssignInstructor;
import CSCI5308.GroupFormationTool.AdminPanel.Model.CreateCourse;
import CSCI5308.GroupFormationTool.AdminPanel.Model.DeleteCourse;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

//  Author: Harsh Patel
public interface IAdminController {
    public ModelAndView adminPage(Model model, HttpServletRequest request) throws Exception;
    public String createCourse(CreateCourse createCourse, RedirectAttributes redirectAttributes) throws Exception;
    public String deleteCourse(DeleteCourse deleteCourse, RedirectAttributes redirectAttributes) throws Exception;
    public String assignInstructor(AssignInstructor assignInstructor, RedirectAttributes redirectAttributes) throws Exception;
}