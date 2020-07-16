package CSCI5308.GroupFormationTool.Course;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class DeleteCourseTest {

    public CourseService courseService;
    public DeleteCourse deleteCourse;

    @BeforeEach
    public void init() throws Exception {
        MockitoAnnotations.initMocks(this);
        courseService = mock(CourseService.class);
        deleteCourse = new DeleteCourse(courseService);
    }

//    @Test
//    void getAllCoursesList() throws Exception {
//        IDeleteCourse deleteCourse = new DeleteCourse();
//
//        Dictionary testCoursesList = new Hashtable();
//        testCoursesList.put("testid testname","testid");
//        when(courseService.coursesWithIdForDropdown()).thenReturn(testCoursesList);
//        assertEquals(testCoursesList,deleteCourse.getAllCoursesList());
//    }

//    @Test
//    void setAllCoursesList() throws Exception {
//        IDeleteCourse deleteCourse = new DeleteCourse();
//        Dictionary testCoursesList = new Hashtable();
//        testCoursesList.put("testid testname","testid");
//        deleteCourse.setAllCoursesList(testCoursesList);
//        when(courseService.coursesWithIdForDropdown()).thenReturn(testCoursesList);
//        assertEquals(testCoursesList,deleteCourse.getAllCoursesList());
//    }

    @Test
    void getSelectedCourseId() throws Exception {
        IDeleteCourse deleteCourse = new DeleteCourse();
        assertEquals(null,deleteCourse.getSelectedCourseId());
        deleteCourse.setSelectedCourseId("B00123456");
        assertEquals("B00123456",deleteCourse.getSelectedCourseId());
    }

    @Test
    void setSelectedCourseId() {
        IDeleteCourse deleteCourse = new DeleteCourse();
        assertEquals(null,deleteCourse.getSelectedCourseId());
        deleteCourse.setSelectedCourseId("B00123456");
        assertEquals("B00123456",deleteCourse.getSelectedCourseId());
    }

    @Test
    void getCourseDeleteMessage() {
        IDeleteCourse deleteCourse = new DeleteCourse();
        assertEquals("status here",deleteCourse.getCourseDeleteMessage());
    }

    @Test
    void setCourseDeleteMessage() {
        IDeleteCourse deleteCourse = new DeleteCourse();
        deleteCourse.setCourseDeleteMessage("course deleted");
        assertEquals("course deleted",deleteCourse.getCourseDeleteMessage());
    }

    @Test
    void getAllCourseNames() {
        IDeleteCourse deleteCourse = new DeleteCourse();
        assertEquals(null,deleteCourse.getAllCourseNames());
    }

    @Test
    void setAllCourseNames() {
        IDeleteCourse deleteCourse = new DeleteCourse();
        ArrayList<String> tempCourseNames = new ArrayList<>();
        tempCourseNames.add("tempcourse");
        deleteCourse.setAllCourseNames(tempCourseNames);
        assertEquals(tempCourseNames,deleteCourse.getAllCourseNames());
    }

    @Test
    void getAllCourseIds() {
        IDeleteCourse deleteCourse = new DeleteCourse();
        assertEquals(null,deleteCourse.getSelectedCourseId());
        ArrayList<String> tempCourseId = new ArrayList<>();
    }

    @Test
    void setAllCourseIds() {
        IDeleteCourse deleteCourse = new DeleteCourse();
        ArrayList<String> tempCourseId = new ArrayList<>();
        tempCourseId.add("tempcourse1");
        deleteCourse.setAllCourseIds(tempCourseId);
        assertEquals(tempCourseId,deleteCourse.getAllCourseIds());
    }
}