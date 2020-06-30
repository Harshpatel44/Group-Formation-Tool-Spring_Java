package CSCI5308.GroupFormationTool.Course.Model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class CreateCourseTest {

    @Test
    void getCourseCreateMessage() {
        CreateCourse createCourse = new CreateCourse();
        assertEquals("status here",createCourse.getCourseCreateMessage());
        createCourse.setCourseCreateMessage("Course Created");
        assertEquals("Course Created",createCourse.getCourseCreateMessage());
    }

    @Test
    void setCourseCreateMessage() {
        CreateCourse createCourse = new CreateCourse();
        createCourse.setCourseCreateMessage("Course Created");
        assertEquals("Course Created",createCourse.getCourseCreateMessage());


    }

    @Test
    void getCourseId() {
         CreateCourse createCourse = new CreateCourse();
         assertNull(createCourse.getCourseId());
         createCourse.setCourseId("B00845449");
         assertEquals("B00845449",createCourse.getCourseId());
    }


    @Test
    void setCourseId() {
        CreateCourse createCourse = new CreateCourse();
        createCourse.setCourseId("B00845449");
        assertEquals("B00845449",createCourse.getCourseId());
    }

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
}