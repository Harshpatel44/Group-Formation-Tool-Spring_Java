package CSCI5308.GroupFormationTool.Course.Model;

import CSCI5308.GroupFormationTool.Course.AccessControl.IUserId;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class UserIdTest {

    @Test
    public void setUserIdTest(){
        IUserId user=new UserId();
        user.setUserId("B00123456");
        assertTrue(user.getUserId()=="B00123456");
    }

}
