package CSCI5308.GroupFormationTool.AdminPanel.Model;

import CSCI5308.GroupFormationTool.AdminPanel.AdminInjector;
import CSCI5308.GroupFormationTool.Injector;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;

public class Instructor {
    private String InstructorId;
    private String selectedInstructorCourseId;
    private String InstructorAssignMessage="status here";

    public Instructor() throws Exception {
    }

    public Instructor(String s){}

    public String getInstructorAssignMessage() {
        return InstructorAssignMessage;
    }

    public void setInstructorAssignMessage(String instructorAssignMessage) {
        this.InstructorAssignMessage = instructorAssignMessage;
    }

    public String getInstructorId() {
        return InstructorId;
    }

    public void setInstructorId(String instructorId) {
        this.InstructorId = instructorId;
    }

    public String getSelectedInstructorCourseId() {
        return selectedInstructorCourseId;
    }

    public void setSelectedInstructorCourseId(String selectedInstructorCourseId) {
        this.selectedInstructorCourseId = selectedInstructorCourseId;
    }
}
