package CSCI5308.GroupFormationTool.AdminPanel.Model;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;

import static org.junit.jupiter.api.Assertions.*;

class AssignInstructorTest {

    @Test
    void getAllCoursesList() {
        AssignInstructor assignInstructor = new AssignInstructor("test");
        Dictionary testCoursesList = new Hashtable();
        assertEquals(testCoursesList,assignInstructor.getAllCoursesList());

        testCoursesList.put("testid testname","testid");
        assignInstructor.setAllCoursesList(testCoursesList);
        assertEquals(testCoursesList,assignInstructor.getAllCoursesList());
    }

    @Test
    void setAllCoursesList() {
        AssignInstructor assignInstructor = new AssignInstructor("test");
        Dictionary testCoursesList = new Hashtable();
        assertEquals(testCoursesList,assignInstructor.getAllCoursesList());

        testCoursesList.put("testid testname","testid");
        assignInstructor.setAllCoursesList(testCoursesList);
        assertEquals(testCoursesList,assignInstructor.getAllCoursesList());
    }

    @Test
    void getAllCourseIds() {
        AssignInstructor assignInstructor = new AssignInstructor("test");
        assertEquals(null,assignInstructor.getAllCourseIds());
        ArrayList<String> tempCourseId = new ArrayList<>();
    }

    @Test
    void setAllCourseIds() {
        AssignInstructor assignInstructor = new AssignInstructor("test");
        ArrayList<String> tempCourseId = new ArrayList<>();
        tempCourseId.add("tempcourse1");
        assignInstructor.setAllCourseIds(tempCourseId);
        assertEquals(tempCourseId,assignInstructor.getAllCourseIds());
    }

    @Test
    void getAllCourseNames() {
        AssignInstructor assignInstructor = new AssignInstructor("test");
        assertEquals(null,assignInstructor.getAllCourseNames());
    }

    @Test
    void setAllCourseNames() {
        AssignInstructor assignInstructor = new AssignInstructor("test");
        ArrayList<String> tempCourseNames = new ArrayList<>();
        tempCourseNames.add("tempcourse");
        assignInstructor.setAllCourseNames(tempCourseNames);
        assertEquals(tempCourseNames,assignInstructor.getAllCourseNames());
    }

    @Test
    void getInstructorAssignMessage() {
        AssignInstructor assignInstructor = new AssignInstructor("test");
        assertEquals("status here",assignInstructor.getInstructorAssignMessage());
    }

    @Test
    void setInstructorAssignMessage() {
        AssignInstructor assignInstructor = new AssignInstructor("test");
        assignInstructor.setInstructorAssignMessage("course deleted");
        assertEquals("course deleted",assignInstructor.getInstructorAssignMessage());
    }

    @Test
    void getInstructorId() {
        AssignInstructor assignInstructor = new AssignInstructor("test");
        assertEquals(null,assignInstructor.getInstructorId());
        assignInstructor.setInstructorId("B00123456");
        assertEquals("B00123456",assignInstructor.getInstructorId());
    }

    @Test
    void setInstructorId() {
        AssignInstructor assignInstructor = new AssignInstructor("test");
        assertEquals(null,assignInstructor.getInstructorId());
        assignInstructor.setInstructorId("B00123456");
        assertEquals("B00123456",assignInstructor.getInstructorId());
    }

    @Test
    void getSelectedInstructorCourseId() {
        AssignInstructor assignInstructor = new AssignInstructor("test");
        assertEquals(null,assignInstructor.getSelectedInstructorCourseId());
        assignInstructor.setSelectedInstructorCourseId("B00123456");
        assertEquals("B00123456",assignInstructor.getSelectedInstructorCourseId());
    }

    @Test
    void setSelectedInstructorCourseId() {
        AssignInstructor assignInstructor = new AssignInstructor("test");
        assertEquals(null,assignInstructor.getSelectedInstructorCourseId());
        assignInstructor.setSelectedInstructorCourseId("B00123456");
        assertEquals("B00123456",assignInstructor.getSelectedInstructorCourseId());
    }
}