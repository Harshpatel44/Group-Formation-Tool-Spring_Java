package CSCI5308.GroupFormationTool.AdminPanel;

import CSCI5308.GroupFormationTool.AdminPanel.IInstructorAdminRepository;
import CSCI5308.GroupFormationTool.AdminPanel.Instructor;
import CSCI5308.GroupFormationTool.Database.StoredProcedure;

import java.sql.ResultSet;
import java.sql.SQLException;

public class InstructorAdminRepository implements IInstructorAdminRepository {

    @Override
    public boolean assignInstructorRepo(Instructor assignInstructor) throws SQLException {
        StoredProcedure storedProcedure = new StoredProcedure("GetInstructorRole");
        ResultSet resultSet = storedProcedure.executeWithResults();
        resultSet.next();
        int instructorRoleId = resultSet.getInt("roleId");
        storedProcedure.cleanup();
        try{
            StoredProcedure storedProcedure2 = new StoredProcedure("AssignInstructor(?,?,?)");
            storedProcedure2.setParameter(1,assignInstructor.getInstructorId());
            storedProcedure2.setParameter(2, String.valueOf(instructorRoleId));
            storedProcedure2.setParameter(3,assignInstructor.getSelectedInstructorCourseId());
            storedProcedure2.execute();
            storedProcedure2.cleanup();
            return true;
        }
        catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
}

