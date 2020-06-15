package CSCI5308.GroupFormationTool.QuestionEditor.AccessControl;

import CSCI5308.GroupFormationTool.QuestionEditor.Model.QuestionModel;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

public interface IQuestionEditorController {
    public ModelAndView addQuestion(@RequestParam(name="courseId") String courseId, @RequestParam(name="userId") String userId);
    public ModelAndView createQuestion(@RequestParam(name="courseId") String courseId, @RequestParam(name="userId") String userId);
    public ModelAndView createOption(QuestionModel questionModel, @RequestParam(name="courseId") String courseId, @RequestParam(name="userId") String userId) throws Exception;
    public ModelAndView questionPreview(QuestionModel questionModel,@RequestParam(name="courseId") String courseId, @RequestParam(name="userId") String userId);
    public ModelAndView questionFinish(QuestionModel questionModel, @RequestParam(name="courseId") String courseId, @RequestParam(name="userId") String userId);
}
