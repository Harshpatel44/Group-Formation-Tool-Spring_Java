package CSCI5308.GroupFormationTool.QuestionEditor.AccessControl;

import CSCI5308.GroupFormationTool.QuestionEditor.Model.QuestionModel;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Dictionary;

public interface IQuestionEditorController {
    public ModelAndView addQuestion(@RequestParam(name="courseId") String courseId, @RequestParam(name="userId") String userId);

    public ModelAndView createQuestion(
                                       @RequestParam(name="courseId") String courseId,
                                       @RequestParam(name="userId") String userId);

    public ModelAndView createOption(QuestionModel questionModel,
                                     @RequestParam(name="courseId") String courseId,
                                     @RequestParam(name="userId") String userId) throws Exception;

    public ModelAndView questionPreview(QuestionModel questionModel,
                                        @RequestParam(name="optionText") String optionText,
                                        @RequestParam(name="optionText") String rankText,
                                        @RequestParam(name="questionText") String questionText,
                                        @RequestParam(name="questionTitle") String questionTitle,
                                        @RequestParam(name="selectedQuestionType") String selectedQuestionType,
                                        @RequestParam(name="courseId") String courseId,
                                        @RequestParam(name="userId") String userId) throws Exception;

    public ModelAndView questionFinish(QuestionModel questionModel,
                                       @RequestParam(name="ranks") String ranks,
                                       @RequestParam(name="options") String options,
                                       @RequestParam(name="questionText") String questionText,
                                       @RequestParam(name="questionTitle") String questionTitle,
                                       @RequestParam(name="selectedQuestionType") String selectedQuestionType,
                                       @RequestParam(name="courseId") String courseId,
                                       @RequestParam(name="userId") String userId) throws Exception;
}
