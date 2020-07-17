package CSCI5308.GroupFormationTool.Course;

import CSCI5308.GroupFormationTool.Database.StoredProcedure;
import CSCI5308.GroupFormationTool.UserManager.IInstructor;
import CSCI5308.GroupFormationTool.UserManager.IUser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CourseRepository implements ICourseRepository {
    private static final Logger LOG = LogManager.getLogger();

    private boolean checkIfAlreadyTaInCourse(String taId, String courseId) {
        boolean result = false;
        StoredProcedure checkPresence = null;
        try {
            checkPresence = new StoredProcedure("AlreadyTa(?,?)");
            checkPresence.setParameter(1, courseId);
            checkPresence.setParameter(2, taId);
            ResultSet rs = checkPresence.executeWithResults();
            {
                if (rs.next()) {
                    result = true;
                }
            }
            LOG.info("Operation = check if TA exists in course " + courseId + ", Status = Success");
        } catch (SQLException throwables) {
            LOG.error("Operation = check if TA exists in course " + courseId + ", Status = Fail, Error Message=" + throwables.getMessage());
            throwables.printStackTrace();

        } catch (Exception e) {
            LOG.error("Operation = check if TA exists in course " + courseId + ", Status = Fail, Error Message=" + e.getMessage());
            e.printStackTrace();
        } finally {
            if (checkPresence != null) {
                checkPresence.cleanup();
            }
        }
        return result;
    }

    @Override
    public boolean assignInstructorForCourse(IInstructor instructor) {
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
                LOG.info("Operation = assign instructor for course " + instructor.getSelectedInstructorCourseId() + ", Status = Success");
                return true;
            } catch (Exception e) {
                LOG.error("Operation = assign instructor for course " + instructor.getSelectedInstructorCourseId() + ", Status = Fail, Error Message=" + e.getMessage());
                e.printStackTrace();
                return false;
            } finally {
                if (storedProcedure1 != null) {
                    storedProcedure1.cleanup();
                }
            }
        } catch (Exception e) {
            LOG.error("Operation = assign instructor for course " + instructor.getSelectedInstructorCourseId() + ", Status = Failed, Error Message=" + e.getMessage());
            e.printStackTrace();
            return false;
        } finally {
            if (storedProcedure != null) {
                storedProcedure.cleanup();
            }
        }
    }

    @Override
    public String addTaForCourse(String taId, String courseId) {
        String result = null;
        StoredProcedure storedProcedure = null;
        if (checkIfAlreadyTaInCourse(taId, courseId)) {
            result = "Already user has different role for courseId:" + courseId + ".";
        } else {
            try {
                storedProcedure = new StoredProcedure("InsertTa(?,?)");
                storedProcedure.setParameter(1, courseId);
                storedProcedure.setParameter(2, taId);
                storedProcedure.execute();
                result = "User with Id:" + taId + " is add as a TA for courseId" + courseId + ".";
                LOG.info("Operation = Add TA with id" + taId + " for course" + courseId + ", Status = Success");
            } catch (Exception e) {
                LOG.error("Operation = Add TA with id" + taId + " for course" + courseId + ", Status = Fail, Error Message=" + e.getMessage());
                e.printStackTrace();
            } finally {
                if (storedProcedure != null) {
                    storedProcedure.cleanup();
                }
            }
        }
        return result;
    }


    @Override
    public boolean getUserDetailsOnCourse(IUser iUser, String courseId) {
        StoredProcedure storedProcedure = null;
        try {
            storedProcedure = new StoredProcedure("userByCourse(?,?)");
            storedProcedure.setParameter(1, iUser.getBannerId());
            storedProcedure.setParameter(2, courseId);
            ResultSet results = storedProcedure.executeWithResults();
            if (results != null) {
                if (results.next()) {
                    LOG.info("Operation = " + courseId + " course details for user " + iUser.getBannerId() + ", Status = Success");
                    return true;
                }
            }
        } catch (Exception e) {
            LOG.error("Operation = " + courseId + " course details for user " + iUser.getBannerId() + ", Status = Failed, Error Message=" + e.getMessage());
            e.printStackTrace();
            return false;
        } finally {
            if (storedProcedure != null) {
                storedProcedure.cleanup();
            }
        }
        return false;
    }

    @Override
    public boolean enrollStudentForCourse(IUser user, String courseId) {
        Boolean success = false;
        StoredProcedure storedProcedure = null;
        try {
            storedProcedure = new StoredProcedure("spEnrollStudentForCourse(?, ?)");
            storedProcedure.setParameter(1, user.getBannerId());
            storedProcedure.setParameter(2, courseId);
            storedProcedure.execute();
            success = true;
            LOG.info("Operation = enroll student with id " + user.getBannerId() + " for the course " + courseId + ", Status = Success");
        } catch (SQLException e) {
            LOG.error("Operation = enroll student with id " + user.getBannerId() + " for the course " + courseId + ", Status = Fail");
            e.printStackTrace();
            return false;
        } catch (Exception e) {
            LOG.error("Operation = enroll student with id " + user.getBannerId() + " for the course " + courseId + ", Status = Fail");
            e.printStackTrace();
            return false;
        } finally {
            if (storedProcedure != null) {
                storedProcedure.cleanup();
            }
        }
        return success;
    }

    @Override
    public ArrayList<ArrayList<String>> getAllCourses() {
        ArrayList<String> courseNamesList = new ArrayList<>();
        ArrayList<String> courseIdsList = new ArrayList<>();
        ArrayList<ArrayList<String>> courseNamesWithIdsList = new ArrayList<>();
        StoredProcedure storedProcedure = null;
        try {
            storedProcedure = new StoredProcedure("AllCourses");
            ResultSet result = storedProcedure.executeWithResults();
            while (result.next()) {
                courseNamesList.add(result.getString("courseName"));
                courseIdsList.add(result.getString("courseId"));
            }
            courseNamesWithIdsList.add(courseIdsList);
            courseNamesWithIdsList.add(courseNamesList);
            LOG.info("Operation = Get all courses, Status = Success");
            return courseNamesWithIdsList;
        } catch (Exception e) {
            LOG.error("Operation = Get all courses, Status = Fail, Error Message=" + e.getMessage());
            e.printStackTrace();
        } finally {
            if (storedProcedure != null) {
                storedProcedure.cleanup();
            }
        }
        return courseNamesWithIdsList;
    }


    @Override
    public boolean createCourseRepo(ICreateCourse createCourse) {
        StoredProcedure storedProcedure = null;
        try {
            storedProcedure = new StoredProcedure("CreateCourse(?,?)");
            storedProcedure.setParameter("cId", createCourse.getCourseId());
            storedProcedure.setParameter("cName", createCourse.getCourseName());
            storedProcedure.execute();
            LOG.info("Operation = Create course with id " + createCourse.getCourseId() + ", Status = Success");
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            LOG.error("Operation = Create course with id " + createCourse.getCourseId() + ", Status = Fail, Error Message=" + e.getMessage());
            return false;
        } finally {
            if (storedProcedure != null) {
                storedProcedure.cleanup();
            }
        }
    }

    @Override
    public boolean deleteCourseRepo(IDeleteCourse deleteCourse) {
        StoredProcedure storedProcedure = null;
        try {
            storedProcedure = new StoredProcedure("DeleteCourse(?)");
            storedProcedure.setParameter("cId", deleteCourse.getSelectedCourseId());
            storedProcedure.execute();
            LOG.info("Operation = Delete course of id " + deleteCourse.getSelectedCourseId() + ", Status = Success");
            return true;
        } catch (Exception e) {
            LOG.error("Operation = Delete course of id " + deleteCourse.getSelectedCourseId() + ", Status = Failed, Error Message=" + e.getMessage());
            e.printStackTrace();
            return false;
        } finally {
            if (storedProcedure != null) {
                storedProcedure.cleanup();
            }
        }
    }

    @Override
    public ArrayList<String> getCoursesOfSpecificUserRole(String userId, int roleId) {
        ArrayList<String> courseIdList = new ArrayList<>();
        StoredProcedure storedProcedure = null;
        try {
            storedProcedure = new StoredProcedure("GetCoursesOfSpecificRole(?,?)");
            storedProcedure.setParameter("uId", userId);
            storedProcedure.setParameter("rId", String.valueOf(roleId));
            ResultSet result = storedProcedure.executeWithResults();
            while (result.next()) {
                courseIdList.add(result.getString("courseId"));
            }
            LOG.info("Operation = Get all courses of role " + roleId + ", Status = Pass");
            return courseIdList;
        } catch (Exception e) {
            e.printStackTrace();
            LOG.error("Operation = Get all courses of role " + roleId + ", Status = Fail, Error Message=" + e.getMessage());
        } finally {
            if (storedProcedure != null) {
                storedProcedure.cleanup();
            }
        }
        return courseIdList;
    }
}
