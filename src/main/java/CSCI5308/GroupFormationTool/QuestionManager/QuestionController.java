package CSCI5308.GroupFormationTool.QuestionManager;

import CSCI5308.GroupFormationTool.Course.CurrentCourse;
import CSCI5308.GroupFormationTool.UserManager.CurrentUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class QuestionController {
    String sortType = "unsorted";
    private IQuestionManagerService questionManagerService;

    @RequestMapping("/questionManager")
    public ModelAndView questionManager() {
        String bannerID = CurrentUser.instance().getBannerId();
        String courseId = CurrentCourse.instance().getCurrentCourseId();
        ModelAndView model = new ModelAndView("questionManager");
        model.addObject("userId", bannerID);
        model.addObject("courseId", courseId);
        model.setViewName("questionManager");
        return model;
    }

    @RequestMapping("/questionList")
    public ModelAndView questionList() throws Exception {
        ModelAndView model = new ModelAndView("questionList");
        questionManagerService = QuestionManagerAbstractFactory.instance().getQuestionManagerService();
        String bannerID = CurrentUser.instance().getBannerId();
        model.addObject("prompt", false);
        model.addObject("questions", questionManagerService.getQuestions(bannerID, sortType));
        return model;
    }

    @RequestMapping("/sortQuestionByDate")
    public ModelAndView sortByDate() {
        ModelAndView model = new ModelAndView("questionList");
        sortType = "sortByDate";
        model.addObject("prompt", false);
        model.setViewName("redirect:/questionList");
        return model;
    }

    @RequestMapping("/sortQuestionByTopic")
    public ModelAndView sortByTopic() {
        ModelAndView model = new ModelAndView("questionList");
        sortType = "sortByTopic";
        model.addObject("prompt", false);
        model.setViewName("redirect:/questionList");
        return model;
    }

    @RequestMapping("/deleteQuestion")
    public ModelAndView deleteQuestion(@RequestParam(name = "selectedQuestionId") Integer questionId) throws Exception {
        ModelAndView model = new ModelAndView("questionList");
        questionManagerService = QuestionManagerAbstractFactory.instance().getQuestionManagerService();
        String bannerID = CurrentUser.instance().getBannerId();
        questionManagerService.deleteQuestion(questionId, bannerID);
        model.addObject("questions", questionManagerService.getQuestions(bannerID, sortType));
        model.setViewName("redirect:/questionList");
        return model;
    }

    @RequestMapping("/checkResponses")
    public ModelAndView checkResponses(@RequestParam(name = "questionId") Integer questionId) throws Exception {
        String bannerID = CurrentUser.instance().getBannerId();
        ModelAndView model = new ModelAndView("questionList");
        model.addObject("selectedQuestionId", questionId);
        boolean prompt = QuestionManagerAbstractFactory.instance().getQuestionResponsesService().checkIfResponsesPresentService(questionId);
        if (prompt == false) {
            model.setViewName("redirect:/deleteQuestion");
        } else {
            model.addObject("prompt", prompt);
            model.setViewName("questionList");
        }
        model.addObject("questions", questionManagerService.getQuestions(bannerID, sortType));
        return model;
    }
}
