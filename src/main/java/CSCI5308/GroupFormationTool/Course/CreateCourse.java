package CSCI5308.GroupFormationTool.Course;

public class CreateCourse implements ICreateCourse {
    private String courseId;
    private String courseName;
    private String courseCreateMessage = "status here";

    @Override
    public String getCourseCreateMessage() {
        return courseCreateMessage;
    }

    @Override
    public void setCourseCreateMessage(String courseCreateMessage) {
        this.courseCreateMessage = courseCreateMessage;
    }

    @Override
    public String getCourseId() {
        return courseId;
    }

    @Override
    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    @Override
    public String getCourseName() {
        return courseName;
    }

    @Override
    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }
}
