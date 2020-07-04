package CSCI5308.GroupFormationTool.UserManager;

public class Instructor implements IInstructor {
    private String InstructorId;
    private String selectedInstructorCourseId;
    private String InstructorAssignMessage="status here";

    public Instructor(){
    }

    public Instructor(String s){}

    @Override
    public String getInstructorAssignMessage() {
        return InstructorAssignMessage;
    }

    @Override
    public void setInstructorAssignMessage(String instructorAssignMessage) {
        this.InstructorAssignMessage = instructorAssignMessage;
    }

    @Override
    public String getInstructorId() {
        return InstructorId;
    }

    @Override
    public void setInstructorId(String instructorId) {
        this.InstructorId = instructorId;
    }

    @Override
    public String getSelectedInstructorCourseId() {
        return selectedInstructorCourseId;
    }

    @Override
    public void setSelectedInstructorCourseId(String selectedInstructorCourseId) {
        this.selectedInstructorCourseId = selectedInstructorCourseId;
    }
}
