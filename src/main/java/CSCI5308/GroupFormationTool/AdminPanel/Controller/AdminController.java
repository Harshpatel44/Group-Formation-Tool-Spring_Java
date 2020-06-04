package CSCI5308.GroupFormationTool.AdminPanel.Controller;

import CSCI5308.GroupFormationTool.AdminPanel.AccessControl.IAdminController;
import CSCI5308.GroupFormationTool.AdminPanel.AdminInjector;
import CSCI5308.GroupFormationTool.AdminPanel.Model.AssignInstructor;
import CSCI5308.GroupFormationTool.AdminPanel.Model.CreateCourse;
import CSCI5308.GroupFormationTool.AdminPanel.Model.DeleteCourse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.Map;

@Controller
public class AdminController implements IAdminController {


    //  Author: Harsh Patel
    @GetMapping("/admin")
    @Override
    public ModelAndView adminPage(Model model, HttpServletRequest request) throws Exception {
        CreateCourse createCourse = new CreateCourse();
        DeleteCourse deleteCourse = new DeleteCourse();
        AssignInstructor assignInstructor = new AssignInstructor();

        try {
            Map<String, ?> flashAttribute = RequestContextUtils.getInputFlashMap(request);
            String createMessage = (String) flashAttribute.get("courseCreateMessage");
            String deleteMessage = (String) flashAttribute.get("courseDeleteMessage");
            String assignMessage = (String) flashAttribute.get("instructorAssignMessage");
            if(createMessage!=null){
                createCourse.setCourseCreateMessage(createMessage);
            }
            if (deleteMessage != null) {
                deleteCourse.setCourseDeleteMessage(deleteMessage);
            }
            if(assignMessage!= null){
                assignInstructor.setInstructorAssignMessage(assignMessage);
            }
        }
        catch (Exception ignored){};

        ModelAndView mv = new ModelAndView();
        mv.addObject("createCourse",createCourse);
        mv.addObject("deleteCourse",deleteCourse);
        mv.addObject("assignInstructor",assignInstructor);
        mv.setViewName("admin");
        return mv;
    }


    //  Author: Harsh Patel
    @PostMapping("/createCourse")
    @Override
    public String createCourse(CreateCourse createCourse, RedirectAttributes redirectAttributes) throws Exception {
        System.out.println(redirectAttributes);
        AdminInjector.instance().getAdminService().CreateCourseService(createCourse);
        redirectAttributes.addFlashAttribute("courseCreateMessage",createCourse.getCourseCreateMessage());
        System.out.println(redirectAttributes);
        return "redirect:admin";
    }


    //  Author: Harsh Patel
    @PostMapping("/deleteCourse")
    @Override
    public String deleteCourse(DeleteCourse deleteCourse, RedirectAttributes redirectAttributes) throws Exception {
        AdminInjector.instance().getAdminService().DeleteCourseService(deleteCourse);
        redirectAttributes.addFlashAttribute("courseDeleteMessage",deleteCourse.getCourseDeleteMessage());
        return "redirect:admin";
    }

    //  Author: Harsh Patel
    @PostMapping("/assignInstructor")
    @Override
    public String assignInstructor(AssignInstructor assignInstructor, RedirectAttributes redirectAttributes) throws Exception {
        ModelAndView mv = new ModelAndView();

        AdminInjector.instance().getAdminService().AssignInstructorService(assignInstructor);
        redirectAttributes.addFlashAttribute("instructorAssignMessage",assignInstructor.getInstructorAssignMessage());
        return "redirect:admin";
    }

}
