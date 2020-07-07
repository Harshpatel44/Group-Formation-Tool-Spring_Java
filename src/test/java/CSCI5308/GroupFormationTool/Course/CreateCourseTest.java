package CSCI5308.GroupFormationTool.Course;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class CreateCourseTest {

    @Test
    void getCourseCreateMessage() {
        ICreateCourse createCourse = new CreateCourse();
        assertEquals("status here",createCourse.getCourseCreateMessage());
        createCourse.setCourseCreateMessage("Course Created");
        assertEquals("Course Created",createCourse.getCourseCreateMessage());
    }

    @Test
    void setCourseCreateMessage() {
        ICreateCourse createCourse = new CreateCourse();
        createCourse.setCourseCreateMessage("Course Created");
        assertEquals("Course Created",createCourse.getCourseCreateMessage());
    }

    @Test
    void getCourseId() {
         ICreateCourse createCourse = new CreateCourse();
         assertNull(createCourse.getCourseId());
         createCourse.setCourseId("B00845449");
         assertEquals("B00845449",createCourse.getCourseId());
    }

    @Test
    void setCourseId() {
        ICreateCourse createCourse = new CreateCourse();
        createCourse.setCourseId("B00845449");
        assertEquals("B00845449",createCourse.getCourseId());
    }

    @Test
    void getCourseName() {
        ICreateCourse createCourse = new CreateCourse();
        assertNull(createCourse.getCourseName());
        createCourse.setCourseName("Advanced Software Developement Concepts");
        assertEquals("Advanced Software Developement Concepts",createCourse.getCourseName());
    }

    @Test
    void setCourseName() {
        ICreateCourse createCourse = new CreateCourse();
        createCourse.setCourseName("Advanced Software Developement Concepts");
        assertEquals("Advanced Software Developement Concepts",createCourse.getCourseName());
    }
}