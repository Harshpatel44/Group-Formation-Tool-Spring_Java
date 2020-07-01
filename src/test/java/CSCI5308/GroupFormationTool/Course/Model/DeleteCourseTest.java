package CSCI5308.GroupFormationTool.Course.Model;

import CSCI5308.GroupFormationTool.Course.DeleteCourse;
import CSCI5308.GroupFormationTool.Course.IDeleteCourse;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DeleteCourseTest {


    @Test
    void getAllCoursesList() throws Exception {
        IDeleteCourse deleteCourse = new DeleteCourse("test");

        Dictionary testCoursesList = new Hashtable();
        assertEquals(testCoursesList,deleteCourse.getAllCoursesList());

        testCoursesList.put("testid testname","testid");
        deleteCourse.setAllCoursesList(testCoursesList);

        assertEquals(testCoursesList,deleteCourse.getAllCoursesList());

    }

    @Test
    void setAllCoursesList() {
        IDeleteCourse deleteCourse = new DeleteCourse("test");

        Dictionary testCoursesList = new Hashtable();
        assertEquals(testCoursesList,deleteCourse.getAllCoursesList());

        testCoursesList.put("testid testname","testid");
        deleteCourse.setAllCoursesList(testCoursesList);

        assertEquals(testCoursesList,deleteCourse.getAllCoursesList());
    }

    @Test
    void getSelectedCourseId() {
        IDeleteCourse deleteCourse = new DeleteCourse("test");
        assertEquals(null,deleteCourse.getSelectedCourseId());
        deleteCourse.setSelectedCourseId("B00123456");
        assertEquals("B00123456",deleteCourse.getSelectedCourseId());
    }

    @Test
    void setSelectedCourseId() {
        IDeleteCourse deleteCourse = new DeleteCourse("test");
        assertEquals(null,deleteCourse.getSelectedCourseId());
        deleteCourse.setSelectedCourseId("B00123456");
        assertEquals("B00123456",deleteCourse.getSelectedCourseId());
    }

    @Test
    void getCourseDeleteMessage() {
        IDeleteCourse deleteCourse = new DeleteCourse("test");
        assertEquals("status here",deleteCourse.getCourseDeleteMessage());
    }

    @Test
    void setCourseDeleteMessage() {
        IDeleteCourse deleteCourse = new DeleteCourse("test");
        deleteCourse.setCourseDeleteMessage("course deleted");
        assertEquals("course deleted",deleteCourse.getCourseDeleteMessage());
    }

    @Test
    void getAllCourseNames() {
        IDeleteCourse deleteCourse = new DeleteCourse("test");
        assertEquals(null,deleteCourse.getAllCourseNames());
    }

    @Test
    void setAllCourseNames() {
        IDeleteCourse deleteCourse = new DeleteCourse("test");
        ArrayList<String> tempCourseNames = new ArrayList<>();
        tempCourseNames.add("tempcourse");
        deleteCourse.setAllCourseNames(tempCourseNames);
        assertEquals(tempCourseNames,deleteCourse.getAllCourseNames());
    }

    @Test
    void getAllCourseIds() {
        IDeleteCourse deleteCourse = new DeleteCourse("test");
        assertEquals(null,deleteCourse.getSelectedCourseId());
        ArrayList<String> tempCourseId = new ArrayList<>();
    }

    @Test
    void setAllCourseIds() {
        IDeleteCourse deleteCourse = new DeleteCourse("test");
        ArrayList<String> tempCourseId = new ArrayList<>();
        tempCourseId.add("tempcourse1");
        deleteCourse.setAllCourseIds(tempCourseId);
        assertEquals(tempCourseId,deleteCourse.getAllCourseIds());
    }
}