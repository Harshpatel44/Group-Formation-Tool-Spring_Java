package CSCI5308.GroupFormationTool.Course.Model;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class UserRoleTest {
    @Test
    public void setCourseIdTest(){
        UserRole user=new UserRole();
        user.setCourseId("CSCI9");
        assertTrue(user.getCourseId()=="CSCI9");
    }
    @Test
    public void setUserIdTest(){
        UserRole user=new UserRole();
        user.setUserId("B00123456");
        assertTrue(user.getUserId()=="B00123456");
    }
    @Test
    public void setRoleIdTest(){
        UserRole user=new UserRole();
        user.setRole("Guest");
        assertTrue(user.getRole()=="Guest");
    }
}
