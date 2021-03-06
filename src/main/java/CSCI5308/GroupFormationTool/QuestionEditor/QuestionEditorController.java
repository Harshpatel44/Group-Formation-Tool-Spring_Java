package CSCI5308.GroupFormationTool.QuestionEditor;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;

import static CSCI5308.GroupFormationTool.ApplicationConstants.*;

@Controller
public class QuestionEditorController {

    private QuestionEditorAbstractFactory questionEditorAbstractFactory;
    private IRankFunctionsService rankFunctionsService;
    private IQuestionEditorService questionEditorService;

    @RequestMapping("/addQuestion")
    public ModelAndView addQuestion() {
        ModelAndView mv = new ModelAndView("questionEditorHome");

        return mv;
    }

    @RequestMapping("/createQuestion")
    public ModelAndView createQuestion() {
        IQuestionModel questionModel = QuestionEditorAbstractFactory.instance().getQuestionModel();
        ModelAndView mv = new ModelAndView("questionEditorCreateQuestion");
        mv.addObject("questionModel", questionModel);
        return mv;
    }

    @RequestMapping("/createOption")
    public ModelAndView createOption(QuestionModel questionModel) {
        ModelAndView mv = new ModelAndView();
        if (questionModel.getSelectedQuestionType().equals(text)
                || questionModel.getSelectedQuestionType().equals(numeric)) {
            mv.setViewName("questionEditorPreview");
            mv.addObject("options", null);
            mv.addObject("ranks", null);
        } else {
            mv.setViewName("questionEditorOption");
        }
        mv.addObject("questionModel", questionModel);
        mv.addObject("questionText", questionModel.getQuestionText());
        mv.addObject("questionTitle", questionModel.getQuestionTitle());
        mv.addObject("selectedQuestionType", questionModel.getSelectedQuestionType());
        return mv;
    }

    @RequestMapping(value = "/questionPreview")
    public ModelAndView questionPreview(QuestionModel questionModel,
                                        @RequestParam(name = "optionText") String optionText, @RequestParam(name = "rankText") String rankText,
                                        @RequestParam(name = "questionText") String questionText,
                                        @RequestParam(name = "questionTitle") String questionTitle,
                                        @RequestParam(name = "selectedQuestionType") String selectedQuestionType) {
        questionEditorAbstractFactory = QuestionEditorAbstractFactory.instance();
        rankFunctionsService = questionEditorAbstractFactory.getRankFunctionsService();
        HashMap<Integer, String> map = rankFunctionsService.arrangeOptionsBasedOnRank(optionText, rankText);
        String[] optionList = optionText.split(",");
        String[] rankList = rankText.split(",");
        ModelAndView mv = new ModelAndView("questionEditorPreview");
        mv.addObject("questionModel", questionModel);
        mv.addObject("questionText", questionText);
        mv.addObject("questionTitle", questionTitle);
        mv.addObject("optionListPreview", map.values());
        mv.addObject("options", optionText);
        mv.addObject("ranks", rankText);
        mv.addObject("selectedQuestionType", selectedQuestionType);
        return mv;
    }

    @RequestMapping("/questionEditorFinish")
    public ModelAndView questionFinish(QuestionModel questionModel, @RequestParam(name = "ranks") String ranks,
                                       @RequestParam(name = "options") String options, @RequestParam(name = "questionText") String questionText,
                                       @RequestParam(name = "questionTitle") String questionTitle,
                                       @RequestParam(name = "selectedQuestionType") String selectedQuestionType)
            throws Exception {
        boolean result;
        String returnMessage = null;
        questionEditorAbstractFactory = QuestionEditorAbstractFactory.instance();
        questionEditorService = questionEditorAbstractFactory.getQuestionEditorService();
        if (selectedQuestionType.equals(text) || selectedQuestionType.equals(numeric)) {
            result = questionEditorService.saveQuestionServiceForTextAndNumeric(questionText, questionTitle, selectedQuestionType);
            if (result) {
                returnMessage = "Question submitted successfully";
            } else {
                returnMessage = "Question did not submit successfully";
            }
        }
        if (selectedQuestionType.equals(MCCM)
                || selectedQuestionType.equals(MCCO)) {
            result = questionEditorService.saveQuestionForMultipleChoiceService(questionText, questionTitle, selectedQuestionType, options, ranks);
            if (result) {
                returnMessage = "Question submitted successfully";
            } else {
                returnMessage = "Question did not submit successfully";
            }
        }
        ModelAndView mv = new ModelAndView("questionEditorFinish");
        mv.addObject("message", returnMessage);
        return mv;
    }
}
