package CSCI5308.GroupFormationTool.AdminPanel.Model;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;

import static org.junit.jupiter.api.Assertions.*;

class AssignInstructorTest {

    @Test
    void getInstructorAssignMessage() {
        Instructor assignInstructor = new Instructor("test");
        assertEquals("status here",assignInstructor.getInstructorAssignMessage());
    }

    @Test
    void setInstructorAssignMessage() {
        Instructor assignInstructor = new Instructor("test");
        assignInstructor.setInstructorAssignMessage("course deleted");
        assertEquals("course deleted",assignInstructor.getInstructorAssignMessage());
    }

    @Test
    void getInstructorId() {
        Instructor assignInstructor = new Instructor("test");
        assertEquals(null,assignInstructor.getInstructorId());
        assignInstructor.setInstructorId("B00123456");
        assertEquals("B00123456",assignInstructor.getInstructorId());
    }

    @Test
    void setInstructorId() {
        Instructor assignInstructor = new Instructor("test");
        assertEquals(null,assignInstructor.getInstructorId());
        assignInstructor.setInstructorId("B00123456");
        assertEquals("B00123456",assignInstructor.getInstructorId());
    }

    @Test
    void getSelectedInstructorCourseId() {
        Instructor assignInstructor = new Instructor("test");
        assertEquals(null,assignInstructor.getSelectedInstructorCourseId());
        assignInstructor.setSelectedInstructorCourseId("B00123456");
        assertEquals("B00123456",assignInstructor.getSelectedInstructorCourseId());
    }

    @Test
    void setSelectedInstructorCourseId() {
        Instructor assignInstructor = new Instructor("test");
        assertEquals(null,assignInstructor.getSelectedInstructorCourseId());
        assignInstructor.setSelectedInstructorCourseId("B00123456");
        assertEquals("B00123456",assignInstructor.getSelectedInstructorCourseId());
    }
}