package CSCI5308.GroupFormationTool.QuestionEditor.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class QuestionEditorController {

    @RequestMapping("/addQuestion")
    public ModelAndView createQuestion(@RequestParam(name="courseId") String courseId, @RequestParam(name="userId") String userId){
        ModelAndView mv = new ModelAndView("questionEditor");
        mv.addObject("courseId",courseId);
        mv.addObject("userId",userId);
        return mv;
    }
}
