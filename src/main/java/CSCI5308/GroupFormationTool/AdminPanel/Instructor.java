package CSCI5308.GroupFormationTool.AdminPanel;

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
