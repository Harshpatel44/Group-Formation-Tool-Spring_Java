package CSCI5308.GroupFormationTool.AdminPanel.Model;

import CSCI5308.GroupFormationTool.AdminPanel.IInstructor;
import CSCI5308.GroupFormationTool.AdminPanel.Instructor;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AssignInstructorTest {

    @Test
    void getInstructorAssignMessage() {
        IInstructor assignInstructor = new Instructor("test");
        assertEquals("status here",assignInstructor.getInstructorAssignMessage());
    }

    @Test
    void setInstructorAssignMessage() {
        IInstructor assignInstructor = new Instructor("test");
        assignInstructor.setInstructorAssignMessage("course deleted");
        assertEquals("course deleted",assignInstructor.getInstructorAssignMessage());
    }

    @Test
    void getInstructorId() {
        IInstructor assignInstructor = new Instructor("test");
        assertEquals(null,assignInstructor.getInstructorId());
        assignInstructor.setInstructorId("B00123456");
        assertEquals("B00123456",assignInstructor.getInstructorId());
    }

    @Test
    void setInstructorId() {
        IInstructor assignInstructor = new Instructor("test");
        assertEquals(null,assignInstructor.getInstructorId());
        assignInstructor.setInstructorId("B00123456");
        assertEquals("B00123456",assignInstructor.getInstructorId());
    }

    @Test
    void getSelectedInstructorCourseId() {
        IInstructor assignInstructor = new Instructor("test");
        assertEquals(null,assignInstructor.getSelectedInstructorCourseId());
        assignInstructor.setSelectedInstructorCourseId("B00123456");
        assertEquals("B00123456",assignInstructor.getSelectedInstructorCourseId());
    }

    @Test
    void setSelectedInstructorCourseId() {
        IInstructor assignInstructor = new Instructor("test");
        assertEquals(null,assignInstructor.getSelectedInstructorCourseId());
        assignInstructor.setSelectedInstructorCourseId("B00123456");
        assertEquals("B00123456",assignInstructor.getSelectedInstructorCourseId());
    }
}