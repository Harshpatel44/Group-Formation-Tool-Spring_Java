package CSCI5308.GroupFormationTool.AdminPanel.Service;
import CSCI5308.GroupFormationTool.AdminPanel.AccessControl.IAdminRepository;
import CSCI5308.GroupFormationTool.AdminPanel.AdminInjector;
import CSCI5308.GroupFormationTool.AdminPanel.Model.AssignInstructor;
import CSCI5308.GroupFormationTool.AdminPanel.Model.CreateCourse;
import CSCI5308.GroupFormationTool.AdminPanel.Model.DeleteCourse;
import CSCI5308.GroupFormationTool.AdminPanel.Repository.AdminRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


class AdminServiceTest {

    public AdminRepository adminRepository;
    public AdminService adminService;

    @BeforeEach
    public void init() throws Exception {
        MockitoAnnotations.initMocks(this);
        adminRepository = mock(AdminRepository.class);
        adminService = new AdminService(adminRepository);
    }

    @Test
    void createCourseService1() throws Exception {

        CreateCourse createCourse = new CreateCourse();
        createCourse.setCourseName("testname");
        createCourse.setCourseId("testid");

        ArrayList<ArrayList<String>> allCoursesArray = new ArrayList<>();
        ArrayList<String> courseName = new ArrayList<>();
        ArrayList<String> courseId = new ArrayList<>();
        courseId.add("couseid1");
        courseName.add("course1");
        allCoursesArray.add(courseId);
        allCoursesArray.add(courseName);

        when(adminRepository.createCourseRepo(createCourse)).thenReturn(true);
        when(adminRepository.getAllCourses()).thenReturn(allCoursesArray);
        assertTrue(adminService.CreateCourseService(createCourse));

        assertEquals("Course created",createCourse.getCourseCreateMessage());
    }

    @Test
    void createCourseService2() throws Exception {
        CreateCourse createCourse = new CreateCourse();
        createCourse.setCourseName("testname2");
        createCourse.setCourseId("testid");

        ArrayList<String> courseName = new ArrayList<>();
        ArrayList<String> courseId = new ArrayList<>();
        ArrayList<ArrayList<String>> allCoursesArray = new ArrayList<>();
        courseId.add("testid");
        courseName.add("testname");
        allCoursesArray.add(courseId);
        allCoursesArray.add(courseName);
        when(adminRepository.createCourseRepo(createCourse)).thenReturn(false);
        when(adminRepository.getAllCourses()).thenReturn(allCoursesArray);
        assertFalse(adminService.CreateCourseService(createCourse));
        assertEquals("Course id exists",createCourse.getCourseCreateMessage());
    }

    @Test
    void createCourseService3() throws Exception {
        CreateCourse createCourse = new CreateCourse();
        createCourse.setCourseId("testid2");
        createCourse.setCourseName("testname");

        ArrayList<String> courseName = new ArrayList<>();
        ArrayList<String> courseId = new ArrayList<>();
        ArrayList<ArrayList<String>> allCoursesArray = new ArrayList<>();
        courseId.add("testid");
        courseName.add("testname");
        allCoursesArray.add(courseId);
        allCoursesArray.add(courseName);
        when(adminRepository.getAllCourses()).thenReturn(allCoursesArray);
        when(adminRepository.createCourseRepo(createCourse)).thenReturn(true);
        assertEquals(courseName,adminRepository.getAllCourses().get(1));
        assertFalse(adminService.CreateCourseService(createCourse));
        assertEquals("Course name exists",createCourse.getCourseCreateMessage());
    }

    @Test
    void deleteCourseService() throws Exception {
        DeleteCourse deleteCourse = new DeleteCourse("test");
        deleteCourse.setSelectedCourseId("testid");

        Dictionary allCoursesList = new Hashtable();
        allCoursesList.put("testid testname","testid");
        deleteCourse.setAllCoursesList(allCoursesList);

        ArrayList<String> courseName = new ArrayList<>();
        ArrayList<String> courseId = new ArrayList<>();
        ArrayList<ArrayList<String>> allCoursesArray = new ArrayList<>();
        courseId.add("testid");
        courseName.add("testname");
        allCoursesArray.add(courseId);
        allCoursesArray.add(courseName);
        when(adminRepository.getAllCourses()).thenReturn(allCoursesArray);

        when(adminRepository.deleteCourseRepo(deleteCourse)).thenReturn(true);
        assertTrue(adminService.DeleteCourseService(deleteCourse));
        assertEquals("Course deleted",deleteCourse.getCourseDeleteMessage());

        when(adminRepository.deleteCourseRepo(deleteCourse)).thenReturn(false);
        assertFalse(adminService.DeleteCourseService(deleteCourse));
        assertEquals("Course does not exist",deleteCourse.getCourseDeleteMessage());
    }

    @Test
    void assignInstructorService() throws Exception {
        AssignInstructor assignInstructor = new AssignInstructor("test");

        when(adminRepository.assignInstructorRepo(assignInstructor)).thenReturn(true);
        assertTrue(adminService.AssignInstructorService(assignInstructor));
        assertEquals("Instructor assigned",assignInstructor.getInstructorAssignMessage());

        when(adminRepository.assignInstructorRepo(assignInstructor)).thenReturn(false);
        assertFalse(adminService.AssignInstructorService(assignInstructor));
        assertEquals("User does not exist or already an instructor",assignInstructor.getInstructorAssignMessage());
    }
}