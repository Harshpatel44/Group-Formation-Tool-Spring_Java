package CSCI5308.GroupFormationTool.AdminPanel.Controller;

import CSCI5308.GroupFormationTool.AdminPanel.AccessControl.IAdminController;
import CSCI5308.GroupFormationTool.AdminPanel.Model.CreateCourse;
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
    public ModelAndView adminPage(CreateCourse createCourse) {
        ModelAndView mv = new ModelAndView();
        mv.addObject(createCourse);
        mv.setViewName("admin");
        return mv;
    }


    //  Author: Harsh Patel
    @PostMapping("/createCourse")
    @Override
    public ModelAndView createCourse(CreateCourse createCourse) {

        ModelAndView mv = new ModelAndView();
        mv.setViewName("admin");
        return mv;
    }

}
