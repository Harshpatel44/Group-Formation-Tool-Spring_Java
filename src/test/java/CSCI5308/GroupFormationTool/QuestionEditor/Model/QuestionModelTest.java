package CSCI5308.GroupFormationTool.QuestionEditor.Model;

import org.junit.jupiter.api.Test;
import java.util.Dictionary;
import java.util.Hashtable;
import static org.junit.jupiter.api.Assertions.*;

class QuestionModelTest {

    @Test
    void getQuestionSubmitMessage() {
        QuestionModel questionModel = new QuestionModel();
        assertEquals("Question did not submit successfully...",questionModel.getQuestionSubmitMessage());
    }

    @Test
    void setQuestionSubmitMessage() {
        QuestionModel questionModel = new QuestionModel();
        questionModel.setQuestionSubmitMessage("Question submitted successfully");
        assertEquals("Question submitted successfully",questionModel.getQuestionSubmitMessage());
    }

    @Test
    void getQuestionTypeList() {
        QuestionModel questionModel = new QuestionModel();
        Dictionary questionTypeList = new Hashtable();
        questionTypeList.put("Numeric","Numeric");
        questionTypeList.put("Multiple Choice, Choose One","Multiple Choice, Choose One");
        questionTypeList.put("Multiple Choice, Choose Multiple","Multiple Choice, Choose Multiple");
        questionTypeList.put("Text","Text");
        assertEquals(questionTypeList,questionModel.getQuestionTypeList());
    }

    @Test
    void setQuestionTypeList() {
        QuestionModel questionModel = new QuestionModel();
        Dictionary questionTypeList = new Hashtable();
        questionTypeList.put("Numeric","Numeric");
        questionTypeList.put("Multiple Choice, Choose One","Multiple Choice, Choose One");
        questionTypeList.put("Multiple Choice, Choose Multiple","Multiple Choice, Choose Multiple");
        questionTypeList.put("Text","Text");
        questionModel.setQuestionTypeList(questionTypeList);
        assertEquals(questionTypeList,questionModel.getQuestionTypeList());
    }

    @Test
    void getSelectedQuestionType() {
        QuestionModel questionModel = new QuestionModel();
        assertEquals(null,questionModel.getSelectedQuestionType());
    }

    @Test
    void setSelectedQuestionType() {
        QuestionModel questionModel = new QuestionModel();
        String selectedQuestionType="sample message";
        questionModel.setSelectedQuestionType(selectedQuestionType);
        assertEquals("sample message",questionModel.getSelectedQuestionType());
    }

    @Test
    void getQuestionTitle() {
        QuestionModel questionModel = new QuestionModel();
        assertEquals(null,questionModel.getQuestionTitle());
    }

    @Test
    void setQuestionTitle() {
        QuestionModel questionModel = new QuestionModel();
        String questionTitle="sample title";
        questionModel.setQuestionTitle(questionTitle);
        assertEquals("sample title",questionModel.getQuestionTitle());
    }

    @Test
    void getQuestionText() {
        QuestionModel questionModel = new QuestionModel();
        assertEquals(null,questionModel.getQuestionText());
    }

    @Test
    void setQuestionText() {
        QuestionModel questionModel = new QuestionModel();
        String questionText="sample text";
        questionModel.setQuestionText(questionText);
        assertEquals("sample text",questionModel.getQuestionText());
    }
}