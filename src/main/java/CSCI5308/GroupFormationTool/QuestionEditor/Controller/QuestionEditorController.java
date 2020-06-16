package CSCI5308.GroupFormationTool.QuestionEditor.Controller;

import CSCI5308.GroupFormationTool.QuestionEditor.AccessControl.IQuestionEditorController;
import CSCI5308.GroupFormationTool.QuestionEditor.Model.QuestionModel;
import CSCI5308.GroupFormationTool.QuestionEditor.QuestionEditorInjector;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Collection;
import java.util.HashMap;

@Controller
public class QuestionEditorController implements IQuestionEditorController {

    @Override
    @RequestMapping("/addQuestion")
    public ModelAndView addQuestion(@RequestParam(name="courseId") String courseId,
                                    @RequestParam(name="userId") String userId){
        ModelAndView mv = new ModelAndView("questionEditorHome");

        mv.addObject("courseId",courseId);
        mv.addObject("userId",userId);

        return mv;
    }

    @Override
    @RequestMapping("/createQuestion")
    public ModelAndView createQuestion(
                                        @RequestParam(name="courseId") String courseId,
                                        @RequestParam(name="userId") String userId)
    {
        QuestionModel questionModel = new QuestionModel();
        ModelAndView mv = new ModelAndView("questionEditorCreateQuestion");
        mv.addObject("courseId",courseId);
        mv.addObject("userId",userId);
        mv.addObject("questionModel", questionModel);
        return mv;
    }

    @Override
    @RequestMapping("/createOption")
    public ModelAndView createOption(QuestionModel questionModel,
                                     @RequestParam(name="courseId") String courseId,
                                     @RequestParam(name="userId") String userId)
{

        ModelAndView mv = new ModelAndView();
        if(questionModel.getSelectedQuestionType().equals("Text") || questionModel.getSelectedQuestionType().equals("Numeric")){
            mv.setViewName("questionEditorPreview");
            mv.addObject("options",null);
            mv.addObject("ranks",null);

        }
        else{
            mv.setViewName("questionEditorOption");
        }

        mv.addObject("courseId",courseId);
        mv.addObject("userId",userId);
        mv.addObject("questionModel",questionModel);
        mv.addObject("questionText",questionModel.getQuestionText());
        mv.addObject("questionTitle",questionModel.getQuestionTitle());
        mv.addObject("selectedQuestionType",questionModel.getSelectedQuestionType());
        return mv;
    }

    @Override
    @RequestMapping("/questionPreview")
    public ModelAndView questionPreview(QuestionModel questionModel,
                                        @RequestParam(name="optionText") String optionText,
                                        @RequestParam(name="rankText") String rankText,
                                        @RequestParam(name="questionText") String questionText,
                                        @RequestParam(name="questionTitle") String questionTitle,
                                        @RequestParam(name="selectedQuestionType") String selectedQuestionType,
                                        @RequestParam(name="courseId") String courseId,
                                        @RequestParam(name="userId") String userId) throws Exception
    {
        HashMap<Integer, String> map=QuestionEditorInjector.instance().getQuestionEditorService().arrangeOptionsBasedOnRank(optionText,rankText);
        String[] optionList = optionText.split(",");
        String[] rankList = rankText.split(",");
        ModelAndView mv = new ModelAndView("questionEditorPreview");
        mv.addObject("courseId",courseId);
        mv.addObject("userId",userId);
        mv.addObject("questionModel",questionModel);
        mv.addObject("questionText",questionText);
        mv.addObject("questionTitle",questionTitle);
        mv.addObject("optionListPreview",map.values());
        mv.addObject("options",optionText);
        mv.addObject("ranks",rankText);
        mv.addObject("selectedQuestionType",selectedQuestionType);
        return mv;
    }

    @RequestMapping("/sample")
    public void sample(@RequestParam(name="selectedOptions") String selectedOptions){
        System.out.println(selectedOptions);
    }


    @Override
    @RequestMapping("/questionEditorFinish")
    public ModelAndView questionFinish(QuestionModel questionModel,
                                       @RequestParam(name="ranks") String ranks,
                                       @RequestParam(name="options") String options,
                                       @RequestParam(name="questionText") String questionText,
                                       @RequestParam(name="questionTitle") String questionTitle,
                                       @RequestParam(name="selectedQuestionType") String selectedQuestionType,
                                       @RequestParam(name="courseId") String courseId,
                                       @RequestParam(name="userId") String userId) throws Exception {
        String returnMessage = null;
        if(selectedQuestionType.equals("Text") || selectedQuestionType.equals("Numeric")){
            returnMessage=QuestionEditorInjector.instance().getQuestionEditorService().SaveQuestionServiceForTextAndNumeric(questionText,questionTitle,selectedQuestionType,userId);
        }
        if(selectedQuestionType.equals("Multiple Choice, Choose Multiple") || selectedQuestionType.equals("Multiple Choice, Choose One")){
            returnMessage=QuestionEditorInjector.instance().getQuestionEditorService().saveQuestionForMultipleChoiceService(questionText,questionTitle,selectedQuestionType,options,ranks,userId);
        }
        ModelAndView mv = new ModelAndView("questionEditorFinish");
        mv.addObject("courseId",courseId);
        mv.addObject("userId",userId);
        mv.addObject("message",returnMessage);
        return mv;
    }

}
