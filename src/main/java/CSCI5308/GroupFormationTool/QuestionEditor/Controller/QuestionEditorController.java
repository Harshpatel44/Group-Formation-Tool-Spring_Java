package CSCI5308.GroupFormationTool.QuestionEditor.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class QuestionEditorController {

    @RequestMapping("/addQuestion")
    public ModelAndView addQuestion(@RequestParam(name="courseId") String courseId, @RequestParam(name="userId") String userId){
        ModelAndView mv = new ModelAndView("questionEditorHome");
        mv.addObject("courseId",courseId);
        mv.addObject("userId",userId);
        return mv;
    }

    @RequestMapping("/createQuestion")
    public ModelAndView createQuestion(@RequestParam(name="courseId") String courseId, @RequestParam(name="userId") String userId){
        ModelAndView mv = new ModelAndView("questionEditorCreateQuestion");
        mv.addObject("courseId",courseId);
        mv.addObject("userId",userId);
        return mv;
    }

    @RequestMapping("/createOption")
    public ModelAndView createOption(@RequestParam(name="courseId") String courseId, @RequestParam(name="userId") String userId){
        ModelAndView mv = new ModelAndView("questionEditorOption");
        mv.addObject("courseId",courseId);
        mv.addObject("userId",userId);
        return mv;
    }

    @RequestMapping("/questionPreview")
    public ModelAndView questionPreview(@RequestParam(name="courseId") String courseId, @RequestParam(name="userId") String userId){
        ModelAndView mv = new ModelAndView("questionEditorPreview");
        mv.addObject("courseId",courseId);
        mv.addObject("userId",userId);
        return mv;
    }

    @RequestMapping("/questionEditorFinish")
    public ModelAndView questionFinish(@RequestParam(name="courseId") String courseId, @RequestParam(name="userId") String userId){
        ModelAndView mv = new ModelAndView("questionEditorFinish");
        mv.addObject("courseId",courseId);
        mv.addObject("userId",userId);
        return mv;
    }

}
