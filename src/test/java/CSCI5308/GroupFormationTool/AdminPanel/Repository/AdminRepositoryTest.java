package CSCI5308.GroupFormationTool.AdminPanel.Repository;

import CSCI5308.GroupFormationTool.AdminPanel.IInstructor;
import CSCI5308.GroupFormationTool.AdminPanel.Instructor;
import CSCI5308.GroupFormationTool.AdminPanel.InstructorAdminRepository;
import CSCI5308.GroupFormationTool.Database.StoredProcedure;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;

class AdminRepositoryTest {

    @Mock
    StoredProcedure storedProcedure;

    @InjectMocks
    public InstructorAdminRepository adminRepository = new InstructorAdminRepository();


    @Test
    void assignInstructorRepo() {
        IInstructor assignInstructor = new Instructor("test");
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