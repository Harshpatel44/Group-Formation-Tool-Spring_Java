package CSCI5308.GroupFormationTool.AdminPanel;


import java.sql.SQLException;

public interface IInstructorAdminRepository {
    boolean assignInstructorRepo(IInstructor instructor) throws SQLException;
}
