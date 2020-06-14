package CSCI5308.GroupFormationTool.QuestionManager;

import CSCI5308.GroupFormationTool.Injector;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
@Controller
public class QuestionController {

    @RequestMapping("/questionManager")
    public ModelAndView questionManager(@RequestParam(name="courseId") String courseId,  @RequestParam(name="userId") String userId){

        ModelAndView model=new ModelAndView("questionManager");
        model.addObject("courseId",courseId);
        model.addObject("userId",userId);

        model.setViewName("questionManager");
        return model;
    }
}
