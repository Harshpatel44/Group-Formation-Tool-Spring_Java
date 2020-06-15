package CSCI5308.GroupFormationTool.QuestionEditor.Controller;

import CSCI5308.GroupFormationTool.QuestionEditor.AccessControl.IQuestionEditorController;
import CSCI5308.GroupFormationTool.QuestionEditor.Model.QuestionModel;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class QuestionEditorController implements IQuestionEditorController {

    @Override
    @RequestMapping("/addQuestion")
    public ModelAndView addQuestion(@RequestParam(name="courseId") String courseId, @RequestParam(name="userId") String userId){
        ModelAndView mv = new ModelAndView("questionEditorHome");
        mv.addObject("courseId",courseId);
        mv.addObject("userId",userId);
        return mv;
    }

    @Override
    @RequestMapping("/createQuestion")
    public ModelAndView createQuestion(@RequestParam(name="courseId") String courseId, @RequestParam(name="userId") String userId){
        QuestionModel questionModel = new QuestionModel();
        ModelAndView mv = new ModelAndView("questionEditorCreateQuestion");
        mv.addObject("courseId",courseId);
        mv.addObject("userId",userId);
        mv.addObject("questionModel", questionModel);
        return mv;
    }

    @Override
    @RequestMapping("/createOption")
    public ModelAndView createOption(QuestionModel questionModel, @RequestParam(name="courseId") String courseId, @RequestParam(name="userId") String userId) throws Exception {
//        QuestionEditorInjector.instance().getQuestionEditorService().CreateQuestionService();
        System.out.println(questionModel.getSelectedQuestionType());
        ModelAndView mv = new ModelAndView("questionEditorOption");
        mv.addObject("courseId",courseId);
        mv.addObject("userId",userId);
        mv.addObject("questionModel", questionModel);
        return mv;
    }

    @Override
    @RequestMapping("/questionPreview")
    public ModelAndView questionPreview(QuestionModel questionModel, @RequestParam(name="courseId") String courseId, @RequestParam(name="userId") String userId){
        ModelAndView mv = new ModelAndView("questionEditorPreview");
        mv.addObject("courseId",courseId);
        mv.addObject("userId",userId);
        mv.addObject("questionModel", questionModel);
        return mv;
    }

    @Override
    @RequestMapping("/questionEditorFinish")
    public ModelAndView questionFinish(QuestionModel questionModel,@RequestParam(name="courseId") String courseId, @RequestParam(name="userId") String userId){
        ModelAndView mv = new ModelAndView("questionEditorFinish");
        mv.addObject("courseId",courseId);
        mv.addObject("userId",userId);
        mv.addObject("questionModel", questionModel);
        return mv;
    }

}
