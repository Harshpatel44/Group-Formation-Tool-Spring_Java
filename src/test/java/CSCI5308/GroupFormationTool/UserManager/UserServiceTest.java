package CSCI5308.GroupFormationTool.UserManager;


import CSCI5308.GroupFormationTool.PasswordManager.UserPasswordPolicy;
import CSCI5308.GroupFormationTool.PasswordManager.UserPasswordPolicyStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static CSCI5308.GroupFormationTool.ApplicationConstants.admin;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
public class UserServiceTest {
    private IUserService userService;
    private IUserRepository userRepository;
    private CurrentUser currentUser;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
        userRepository = mock(UserRepository.class);
        currentUser = mock(CurrentUser.class);
        UserManagerAbstractFactory.instance().setUserRepository(userRepository);
        CurrentUser.setInstance(currentUser);
        UserPasswordPolicy.setInstance(2, 23, 1, 1, 1, "@#");
        UserPasswordPolicyStatus.setInstance(1, 1, 1, 1, 1, 1);
        userService = UserManagerAbstractFactory.instance().getUserService();
    }


    @Test
    public void creaUserCorrectDetails() throws Exception {
        IUser user = UserMockDB.setDefault();
        when(userRepository.createUser(user)).thenReturn(true);
        assertEquals(true, userRepository.createUser(user));
    }


    @Test
    public void setUserTest() {
        IUser iUser = new User();
        iUser.setFirstName("firstName");
        iUser.setLastName("lastName");
        iUser.setPassword("password");
        iUser.setBannerId("bannerId");
        iUser.setContactNumber("contactNumber");
        iUser.setEmailId("emailId");

        IUser iUser1 = userService.setUser("bannerId", "firstName", "lastName", "emailId", "password", "contactNumber");
        assertEquals("firstName", iUser.getFirstName());
        assertEquals("lastName", iUser.getLastName());
        assertEquals("password", iUser.getPassword());
        assertEquals("bannerId", iUser.getBannerId());
        assertEquals("contactNumber", iUser.getContactNumber());
        assertEquals("emailId", iUser.getEmailId());
    }

    @Test
    public void checkIfUserExistsTest() {

        when(userRepository.checkIfUserExists("123456789")).thenReturn(true);
        assertEquals(true, userRepository.checkIfUserExists("123456789"));
        when(userRepository.checkIfUserExists("123456789")).thenReturn(false);
        assertEquals(false, userRepository.checkIfUserExists("123456789"));
    }

    @Test
    public void setUserByBannerIdTest() {
        IUser iUser = new User();
        iUser.setFirstName("firstName");
        iUser.setLastName("lastName");
        iUser.setPassword("password");
        iUser.setBannerId("bannerId");
        iUser.setContactNumber("contactNumber");
        iUser.setEmailId("emailId");

        when(userRepository.setUserByBannerId("bannerId", iUser)).thenReturn(iUser);
        assertEquals(iUser, userRepository.setUserByBannerId("bannerId", iUser));
    }

    @Test
    public void getAllBannerIdsTest() {
        List<String> bannerIDs = new ArrayList<>();
        bannerIDs.add("banner1");
        bannerIDs.add("banner2");

        when(userRepository.getAllBannerIds()).thenReturn(bannerIDs);
        assertEquals(bannerIDs, userRepository.getAllBannerIds());
    }

    @Test
    public void checkUserRoleForCourseTest() {
        String bannerID = "bannerId";
        String courseID = "courseID";

        when(userRepository.checkUserRoleForCourse(bannerID, courseID)).thenReturn("Instructor");
        assertEquals("Instructor", userRepository.checkUserRoleForCourse(bannerID, courseID));
    }

    @Test // when user is guest
    public void checkRoleTestGuest() {
        String bannerID = "B23456789";

        when(userRepository.checkIfUserIsGuest(bannerID)).thenReturn(true);
        assertTrue(userRepository.checkIfUserIsGuest(bannerID));
    }

    @Test // when user is Not a guest
    public void checkRoleTestNotGuest() throws Exception {
        String bannerID = "B23456789";

        when(userRepository.checkIfUserIsGuest(bannerID)).thenReturn(false);
        assertFalse(userService.checkIfUserIsGuest(bannerID));
    }

    public void setCurrentUserByBannerIDTest1() {

        userService.setCurrentUserByBannerID(admin);
        when(CurrentUser.instance().getBannerId()).thenReturn(admin);
        when(CurrentUser.instance().getLastName()).thenReturn(admin);
        when(CurrentUser.instance().getFirstName()).thenReturn(admin);
        assertEquals(admin, CurrentUser.instance().getBannerId());
        assertEquals(admin, CurrentUser.instance().getLastName());
        assertEquals(admin, CurrentUser.instance().getFirstName());
    }

    @Test
    public void setCurrentUserByBannerIDTest2() {
        IUser iUser1 = new User();
        IUser iUser = new User();
        iUser.setFirstName("firstName");
        iUser.setLastName("lastName");
        iUser.setPassword("password");
        iUser.setBannerId("B23456789");
        iUser.setContactNumber("contactNumber");
        iUser.setEmailId("emailId");

        when(userRepository.setUserByBannerId("B23456789", iUser1)).thenReturn(iUser);

        when(CurrentUser.instance().getBannerId()).thenReturn(iUser.getBannerId());
        when(CurrentUser.instance().getLastName()).thenReturn(iUser.getLastName());
        when(CurrentUser.instance().getFirstName()).thenReturn(iUser.getFirstName());

        assertEquals(iUser.getBannerId(), CurrentUser.instance().getBannerId());
        assertEquals(iUser.getFirstName(), CurrentUser.instance().getFirstName());
        assertEquals(iUser.getLastName(), CurrentUser.instance().getLastName());
    }
}
