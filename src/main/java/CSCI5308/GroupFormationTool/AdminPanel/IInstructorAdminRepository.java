package CSCI5308.GroupFormationTool.AdminPanel;


import java.sql.SQLException;

public interface IInstructorAdminRepository {
    public boolean assignInstructorRepo(Instructor assignInstructor) throws SQLException;
}
