package CSCI5308.GroupFormationTool.AdminPanel.Model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AssignInstructorTest {

    @Test
    void getInstructorName() {
        CreateCourse createCourse = new CreateCourse();
        assertNull(createCourse.getCourseId());

        createCourse.setCourseId("Harsh Patel");
        assertEquals("Harsh Patel",createCourse.getCourseId());
    }

    @Test
    void setInstructorName() {
        CreateCourse createCourse = new CreateCourse();
        assertNull(createCourse.getCourseId());

        createCourse.setCourseId("Harsh Patel");
        assertEquals("Harsh Patel",createCourse.getCourseId());
    }

    @Test
    void getInstructorCourse() {
        CreateCourse createCourse = new CreateCourse();
        assertNull(createCourse.getCourseId());

        createCourse.setCourseId("Advanced Software Developement Concepts");
        assertEquals("Advanced Software Developement Concepts",createCourse.getCourseId());
    }

    @Test
    void setInstructorCourse() {
        CreateCourse createCourse = new CreateCourse();
        assertNull(createCourse.getCourseId());

        createCourse.setCourseId("Advanced Software Developement Concepts");
        assertEquals("Advanced Software Developement Concepts",createCourse.getCourseId());
    }
}