package CSCI5308.GroupFormationTool.AdminPanel.Model;

import org.springframework.stereotype.Component;

@Component
public class CreateCourse {
    public String courseId;
    public String courseName;
    public String courseCreateMessage = "status here";

    public String getCourseCreateMessage() {
        return courseCreateMessage;
    }

    public void setCourseCreateMessage(String courseCreateMessage) {
        this.courseCreateMessage = courseCreateMessage;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String couseName) {
        this.courseName = couseName;
    }
}
