package CSCI5308.GroupFormationTool.AdminPanel;

import CSCI5308.GroupFormationTool.Course.CourseAbstractFactory;
import CSCI5308.GroupFormationTool.Course.ICreateCourse;
import CSCI5308.GroupFormationTool.Course.IDeleteCourse;
import CSCI5308.GroupFormationTool.UserManager.IInstructor;
import CSCI5308.GroupFormationTool.UserManager.UserManagerAbstractFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

import static CSCI5308.GroupFormationTool.ApplicationConstants.admin;
import static CSCI5308.GroupFormationTool.UserManager.UserManagerAbstractFactory.instance;

@Controller
public class AdminController {

    @GetMapping("/admin")
    public ModelAndView adminPage(HttpServletRequest request) throws Exception {
        ICreateCourse createCourse = CourseAbstractFactory.instance().getCreateCourse();
        IDeleteCourse deleteCourse = CourseAbstractFactory.instance().getDeleteCourse();
        IInstructor instructor = instance().getInstructor();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        ModelAndView mv = new ModelAndView();
        if (authentication.getPrincipal().toString().equals(admin)) {
            try {
                UserManagerAbstractFactory.instance().getUserService().setCurrentUserByBannerID(authentication.getPrincipal().toString());
                Map<String, ?> flashAttribute = RequestContextUtils.getInputFlashMap(request);
                String createMessage = (String) flashAttribute.get("courseCreateMessage");
                String deleteMessage = (String) flashAttribute.get("courseDeleteMessage");
                String assignMessage = (String) flashAttribute.get("instructorAssignMessage");
                if (createMessage != null) {
                    createCourse.setCourseCreateMessage(createMessage);
                }
                if (deleteMessage != null) {
                    deleteCourse.setCourseDeleteMessage(deleteMessage);
                }
                if (assignMessage != null) {
                    instructor.setInstructorAssignMessage(assignMessage);
                }
            } catch (Exception ignored) {
            }
            mv.addObject("createCourse", createCourse);
            mv.addObject("deleteCourse", deleteCourse);
            mv.addObject("assignInstructor", instructor);
            mv.setViewName(admin);
            return mv;
        } else {
            mv.setViewName("redirect:/login");
            return mv;
        }
    }

    @PostMapping("/createCourse")
    public String createCourse(@RequestParam(name = "courseName") String courseName,
                               @RequestParam(name = "courseId") String courseId, RedirectAttributes redirectAttributes) throws Exception {
        ICreateCourse createCourse = CourseAbstractFactory.instance().getCreateCourse();
        createCourse.setCourseName(courseName);
        createCourse.setCourseId(courseId);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.getPrincipal().toString().equals(admin)) {
            CourseAbstractFactory.instance().getCourseService().createCourse(createCourse);
            redirectAttributes.addFlashAttribute("courseCreateMessage", createCourse.getCourseCreateMessage());
            return "redirect:admin";
        } else {
            return "redirect:/login";
        }
    }

    @PostMapping("/deleteCourse")
    public String deleteCourse(@RequestParam(name = "selectedCourseId") String selectedCourseId,
                               RedirectAttributes redirectAttributes) throws Exception {
        IDeleteCourse deleteCourse = CourseAbstractFactory.instance().getDeleteCourse();
        deleteCourse.setSelectedCourseId(selectedCourseId);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.getPrincipal().toString().equals(admin)) {
            CourseAbstractFactory.instance().getCourseService().deleteCourse(deleteCourse);
            redirectAttributes.addFlashAttribute("courseDeleteMessage", deleteCourse.getCourseDeleteMessage());
            return "redirect:admin";
        } else {
            return "redirect:/login";
        }
    }

    @PostMapping("/assignInstructor")
    public String assignInstructor(@RequestParam(name = "selectedInstructorCourseId") String selectedInstructorCourseId,
                                   RedirectAttributes redirectAttributes, @RequestParam(name = "InstructorId") String InstructorId) {
        IInstructor instructor = UserManagerAbstractFactory.instance().getInstructor();
        instructor.setSelectedInstructorCourseId(selectedInstructorCourseId);
        instructor.setInstructorId(InstructorId);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.getPrincipal().toString().equals(admin)) {
            CourseAbstractFactory.instance().getCourseService().assignInstructorForCourse(instructor);
            redirectAttributes.addFlashAttribute("instructorAssignMessage", instructor.getInstructorAssignMessage());
            return "redirect:admin";
        } else {
            return "redirect:/login";
        }
    }
}
