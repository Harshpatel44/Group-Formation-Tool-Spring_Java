package CSCI5308.GroupFormationTool.AdminPanel.Repository;

import CSCI5308.GroupFormationTool.AdminPanel.AdminInjector;
import CSCI5308.GroupFormationTool.AdminPanel.Model.AssignInstructor;
import CSCI5308.GroupFormationTool.AdminPanel.Model.CreateCourse;
import CSCI5308.GroupFormationTool.AdminPanel.Model.DeleteCourse;
import CSCI5308.GroupFormationTool.AdminPanel.Service.AdminService;
import CSCI5308.GroupFormationTool.Database.StoredProcedure;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.sql.SQLException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class AdminRepositoryTest {


    @Mock
    StoredProcedure storedProcedure;

    @InjectMocks
    public AdminRepository adminRepository = new AdminRepository();

    @Test
    void createCourseRepo() throws SQLException {
        CreateCourse createCourse = new CreateCourse();
        createCourse.setCourseName("testname");
        createCourse.setCourseId("testid");
        assertTrue(createCourse.getCourseName().length()<200);
        assertTrue(createCourse.getCourseId().length()<10);
        assertFalse(createCourse.getCourseName().isEmpty());
        assertFalse(createCourse.getCourseId().isEmpty());
        assertTrue(createCourse.getCourseId() instanceof String);
        assertTrue(createCourse.getCourseName() instanceof String);

    }

    @Test
    void deleteCourseRepo() throws Exception {
        DeleteCourse deleteCourse = new DeleteCourse("test");
        deleteCourse.setSelectedCourseId("testname");
        assertTrue(deleteCourse.getSelectedCourseId().length()<=200);
        assertFalse(deleteCourse.getSelectedCourseId().isEmpty());
        assertTrue(deleteCourse.getSelectedCourseId() instanceof String);
    }

    @Test
    void assignInstructorRepo() {
        AssignInstructor assignInstructor = new AssignInstructor("test");
        assignInstructor.setInstructorId("B00123456");
        assignInstructor.setSelectedInstructorCourseId("testname");

        assertTrue(assignInstructor.getSelectedInstructorCourseId().length()<=200);
        assertFalse(assignInstructor.getSelectedInstructorCourseId().isEmpty());
        assertTrue(assignInstructor.getSelectedInstructorCourseId() instanceof String);

        assertTrue(assignInstructor.getInstructorId().length()<=10);
        assertFalse(assignInstructor.getInstructorId().isEmpty());
        assertTrue(assignInstructor.getInstructorId() instanceof String);

    }

}