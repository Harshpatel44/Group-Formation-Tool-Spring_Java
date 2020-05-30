package CSCI5308.GroupFormationTool.AdminPanel.Model;


public class DeleteCourse {
    public String courseName;
    public String courseMessage="status here";

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseMessage() {
        return courseMessage;
    }

    public void setCourseMessage(String courseMessage) {
        this.courseMessage = courseMessage;
    }
}
