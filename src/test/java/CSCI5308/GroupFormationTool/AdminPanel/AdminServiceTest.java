package CSCI5308.GroupFormationTool.AdminPanel;
import CSCI5308.GroupFormationTool.UserManager.IInstructor;
import CSCI5308.GroupFormationTool.UserManager.Instructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


class AdminServiceTest {

    public InstructorAdminRepository adminRepository;
    public AdminService adminService;

    @BeforeEach
    public void init() throws Exception {
        MockitoAnnotations.initMocks(this);
        adminRepository = mock(InstructorAdminRepository.class);
        adminService = new AdminService(adminRepository);
    }

    @Test
    void assignInstructorService() throws Exception {
        IInstructor assignInstructor = new Instructor("test");
        when(adminRepository.assignInstructorRepo(assignInstructor)).thenReturn(true);
        assertTrue(adminService.AssignInstructorService(assignInstructor));
        assertEquals("Instructor assigned",assignInstructor.getInstructorAssignMessage());
        when(adminRepository.assignInstructorRepo(assignInstructor)).thenReturn(false);
        assertFalse(adminService.AssignInstructorService(assignInstructor));
        assertEquals("User does not exist or already an instructor",assignInstructor.getInstructorAssignMessage());
    }
}