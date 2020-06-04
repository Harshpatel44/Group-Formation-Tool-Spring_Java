package CSCI5308.GroupFormationTool.AdminPanel.Repository;

import CSCI5308.GroupFormationTool.AdminPanel.AccessControl.IAdminRepository;
import CSCI5308.GroupFormationTool.AdminPanel.Model.AssignInstructor;
import CSCI5308.GroupFormationTool.AdminPanel.Model.CreateCourse;
import CSCI5308.GroupFormationTool.AdminPanel.Model.DeleteCourse;
import CSCI5308.GroupFormationTool.AdminPanel.Service.AdminService;
import CSCI5308.GroupFormationTool.Database.StoredProcedure;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

//  Author: Harsh Patel
public class AdminRepository implements IAdminRepository {


    @Override
    public boolean createCourseRepo(CreateCourse createCourse) throws SQLException {
        try {
            StoredProcedure storedProcedure = new StoredProcedure("CreateCourse(?,?)");
            System.out.println(storedProcedure);
            storedProcedure.setParameter("cId", createCourse.getCourseId());
            storedProcedure.setParameter("cName", createCourse.getCourseName());
            System.out.println(storedProcedure);
            storedProcedure.execute();
            storedProcedure.cleanup();
            return true;
        }
        catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteCourseRepo(DeleteCourse deleteCourse) {
        try {
            System.out.println(deleteCourse.getSelectedCourseId());
            StoredProcedure storedProcedure = new StoredProcedure("DeleteCourse(?)");
            storedProcedure.setParameter("cId", deleteCourse.getSelectedCourseId());
            storedProcedure.execute();
            storedProcedure.cleanup();
            return true;
        }
        catch (Exception e){
           e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean assignInstructorRepo(AssignInstructor assignInstructor) throws SQLException {
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

    @Override
    public ArrayList<ArrayList<String>> getAllCourses() throws SQLException{
        ArrayList<String> courseNamesList = new ArrayList<>();
        ArrayList<String> courseIdsList = new ArrayList<>();
        ArrayList<ArrayList<String>> courseNamesWithIdsList = new ArrayList<>();

        StoredProcedure storedProcedure = new StoredProcedure("AllCourses");
        ResultSet result = storedProcedure.executeWithResults();

        while(result.next()){
            courseNamesList.add(result.getString("courseName"));
            courseIdsList.add(result.getString("courseId"));
        }
        storedProcedure.cleanup();

        courseNamesWithIdsList.add(courseIdsList);
        courseNamesWithIdsList.add(courseNamesList);

        return courseNamesWithIdsList;
    }

}

