package CSCI5308.GroupFormationTool.AdminPanel.Controller;

import CSCI5308.GroupFormationTool.AdminPanel.AccessControl.IAdminController;
import CSCI5308.GroupFormationTool.AdminPanel.AdminInjector;
import CSCI5308.GroupFormationTool.AdminPanel.Model.Instructor;

import CSCI5308.GroupFormationTool.Course.Model.CreateCourse;
import CSCI5308.GroupFormationTool.Course.Model.DeleteCourse;
import CSCI5308.GroupFormationTool.Injector;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
public class AdminController implements IAdminController {

	@GetMapping("/admin")
	@Override
	public ModelAndView adminPage(Model model, HttpServletRequest request) throws Exception {
		CreateCourse createCourse = new CreateCourse();
		DeleteCourse deleteCourse = new DeleteCourse();
		Instructor assignInstructor = new Instructor();
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		ModelAndView mv = new ModelAndView();
		if (authentication.getPrincipal().toString().equals("admin")) {
			try {
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
					assignInstructor.setInstructorAssignMessage(assignMessage);
				}
			} catch (Exception ignored) {
			}
			mv.addObject("createCourse", createCourse);
			mv.addObject("deleteCourse", deleteCourse);
			mv.addObject("assignInstructor", assignInstructor);
			mv.setViewName("admin");
			return mv;
		} else {
			mv.setViewName("redirect:/login");
			return mv;
		}
	}

	@PostMapping("/createCourse")
	@Override
	public String createCourse(CreateCourse createCourse, RedirectAttributes redirectAttributes) throws Exception {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication.getPrincipal().toString().equals("admin")) {
			Injector.instance().getCourseService().CreateCourseService(createCourse);
			redirectAttributes.addFlashAttribute("courseCreateMessage", createCourse.getCourseCreateMessage());
			return "redirect:admin";
		} else {
			return "redirect:/login";
		}
	}

	@PostMapping("/deleteCourse")
	@Override
	public String deleteCourse(DeleteCourse deleteCourse, RedirectAttributes redirectAttributes) throws Exception {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication.getPrincipal().toString().equals("admin")) {
			Injector.instance().getCourseService().DeleteCourseService(deleteCourse);
			redirectAttributes.addFlashAttribute("courseDeleteMessage", deleteCourse.getCourseDeleteMessage());
			return "redirect:admin";
		} else {
			return "redirect:/login";
		}
	}

	@PostMapping("/assignInstructor")
	@Override
	public String assignInstructor(Instructor assignInstructor, RedirectAttributes redirectAttributes)
			throws Exception {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication.getPrincipal().toString().equals("admin")) {
			ModelAndView mv = new ModelAndView();
			AdminInjector.instance().getAdminService().AssignInstructorService(assignInstructor);
			redirectAttributes.addFlashAttribute("instructorAssignMessage",
					assignInstructor.getInstructorAssignMessage());
			return "redirect:admin";
		} else {
			return "redirect:/login";
		}
	}
}
