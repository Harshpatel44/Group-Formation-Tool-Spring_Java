package CSCI5308.GroupFormationTool.AdminPanel.AccessControl;


import CSCI5308.GroupFormationTool.AdminPanel.Model.Instructor;


import java.sql.SQLException;

public interface IInstructorAdminRepository {
    public boolean assignInstructorRepo(Instructor assignInstructor) throws SQLException;
}
