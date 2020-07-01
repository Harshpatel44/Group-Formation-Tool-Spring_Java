package CSCI5308.GroupFormationTool.AdminPanel;

public interface IInstructor {
    String getInstructorAssignMessage();

    void setInstructorAssignMessage(String instructorAssignMessage);

    String getInstructorId();

    void setInstructorId(String instructorId);

    String getSelectedInstructorCourseId();

    void setSelectedInstructorCourseId(String selectedInstructorCourseId);
}
