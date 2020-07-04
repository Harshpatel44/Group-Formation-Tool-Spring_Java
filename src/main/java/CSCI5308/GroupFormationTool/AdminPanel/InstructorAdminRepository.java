package CSCI5308.GroupFormationTool.AdminPanel;

import CSCI5308.GroupFormationTool.Database.StoredProcedure;
import CSCI5308.GroupFormationTool.UserManager.IInstructor;

import java.sql.ResultSet;

public class InstructorAdminRepository implements IInstructorAdminRepository {

    @Override
    public boolean assignInstructorRepo(IInstructor instructor) throws Exception {
        StoredProcedure storedProcedure = null;
        StoredProcedure storedProcedure1 = null;
        try {
            storedProcedure = new StoredProcedure("GetInstructorRole");
            ResultSet resultSet = storedProcedure.executeWithResults();
            resultSet.next();
            int instructorRoleId = resultSet.getInt("roleId");
            try {
                storedProcedure1 = new StoredProcedure("AssignInstructor(?,?,?)");
                storedProcedure1.setParameter(1, instructor.getInstructorId());
                storedProcedure1.setParameter(2, String.valueOf(instructorRoleId));
                storedProcedure1.setParameter(3, instructor.getSelectedInstructorCourseId());
                storedProcedure1.execute();
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
            finally {
                if(storedProcedure1 != null){
                    storedProcedure1.cleanup();
                }
            }
        }
        catch (Exception e){
            e.printStackTrace();
            return false;
        }
        finally {
            if(storedProcedure!=null){
                storedProcedure.cleanup();
            }
        }
    }
}

