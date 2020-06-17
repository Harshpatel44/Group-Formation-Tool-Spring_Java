package CSCI5308.GroupFormationTool.Course.Model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class UserIdTest {

    @Test
    public void setUserIdTest(){
        UserId user=new UserId();
        user.setUserId("B00123456");
        assertTrue(user.getUserId()=="B00123456");
    }

}
