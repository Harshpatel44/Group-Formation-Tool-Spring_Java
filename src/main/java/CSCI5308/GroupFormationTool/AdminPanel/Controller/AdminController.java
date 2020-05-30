package CSCI5308.GroupFormationTool.AdminPanel.Controller;

import CSCI5308.GroupFormationTool.AdminPanel.AccessControl.IAdminController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AdminController implements IAdminController {


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
