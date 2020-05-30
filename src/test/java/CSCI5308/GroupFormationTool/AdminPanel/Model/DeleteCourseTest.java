package CSCI5308.GroupFormationTool.AdminPanel.Model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DeleteCourseTest {

    @Test
    void getCourseName() {
        CreateCourse createCourse = new CreateCourse();
        assertNull(createCourse.getCourseName());

        createCourse.setCourseName("Advanced Software Developement Concepts");
        assertEquals("Advanced Software Developement Concepts",createCourse.getCourseName());
    }

    @Test
    void setCourseName() {
        CreateCourse createCourse = new CreateCourse();

        createCourse.setCourseName("Advanced Software Developement Concepts");
        assertEquals("Advanced Software Developement Concepts",createCourse.getCourseName());
    }

    @Test
    void getCourseMessage() {
        CreateCourse createCourse = new CreateCourse();
        assertEquals("status here",createCourse.getCourseCreateMessage());

        createCourse.setCourseCreateMessage("Course Deleted");
        assertEquals("Course Deleted",createCourse.getCourseCreateMessage());
    }

    @Test
    void setCourseMessage() {
        CreateCourse createCourse = new CreateCourse();

        createCourse.setCourseCreateMessage("Course Deleted");
        assertEquals("Course Deleted",createCourse.getCourseCreateMessage());
    }
}