package CSCI5308.GroupFormationTool.Course;

import CSCI5308.GroupFormationTool.UserManager.IInstructor;
import CSCI5308.GroupFormationTool.UserManager.UserManagerAbstractFactory;
import CSCI5308.GroupFormationTool.UserManager.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;

import static CSCI5308.GroupFormationTool.ApplicationConstants.*;

public class CourseService implements ICourseService {
    private static final Logger LOG = LogManager.getLogger();
    private ICourseRepository courseRepository;

    public CourseService() {
    }

    public CourseService(CourseRepository courseRepository, UserService userService) throws Exception {
        CourseAbstractFactory.instance().setCourseRepository(courseRepository);
        UserManagerAbstractFactory.instance().setUserService(userService);
    }

    @Override
    public boolean roleAllowInstructorAndTA(String userRole) {
        boolean roleCheck;
        if (userRole.equals(student) || userRole.equals(guest)) {
            roleCheck = false;
            LOG.info("Operation = Instructor and TA user check, Status = false");
        } else {
            roleCheck = true;
            LOG.info("Operation = Instructor and TA user check, Status = true");
        }
        return roleCheck;
    }

    @Override
    public boolean roleAllowInstructor(String userRole) {
        boolean flag = false;
        if (userRole.equals(instructor)) {
            flag = true;
            LOG.info("Operation = Instructor check, Status = true");
        }
        return flag;
    }

    @Override
    public boolean assignInstructorForCourse(IInstructor instructor) {
        if (CourseAbstractFactory.instance().getCourseRepository().assignInstructorForCourse(instructor)) {
            instructor.setInstructorAssignMessage("Instructor assigned");
            return true;
        } else {
            instructor.setInstructorAssignMessage("User does not exist or already an instructor");
            return false;
        }
    }

    @Override
    public String addTAForCourse(String taId, String courseId) throws Exception {
        if (UserManagerAbstractFactory.instance().getUserService().checkIfUserExists(taId)) {
            courseRepository = CourseAbstractFactory.instance().getCourseRepository();
            return courseRepository.addTaForCourse(taId, courseId);
        } else {
            return "No user exist with Id:" + taId + " present in system.";
        }
    }


    @Override
    public Dictionary coursesWithIdForDropdown() throws Exception {
        ArrayList<ArrayList<String>> tempCourse;
        ArrayList<String> allCourseIds;
        ArrayList<String> allCourseNames;
        Dictionary allCoursesList = new Hashtable();

        tempCourse = CourseAbstractFactory.instance().getCourseRepository().getAllCourses();
        allCourseIds = tempCourse.get(0);
        allCourseNames = tempCourse.get(1);
        try {
            for (int i = 0; i < allCourseIds.size(); i++) {
                allCoursesList.put(allCourseIds.get(i) + " " + allCourseNames.get(i), allCourseIds.get(i));
            }
            LOG.warn("Operation = Creating courses for dropdown, Status = Fail, Warning =  Course list not generated");
        } catch (Exception e) {
        }
        return allCoursesList;
    }

    @Override
    public boolean createCourse(ICreateCourse createCourse) throws Exception {
        ArrayList<String> allCourseNames = CourseAbstractFactory.instance().getCourseRepository().getAllCourses().get(1);
        for (int i = 0; i < allCourseNames.size(); i++) {
            if (allCourseNames.get(i).equals(createCourse.getCourseName())) {
                createCourse.setCourseCreateMessage("Course name exists");
                LOG.warn("Operation = Create new course with id " + createCourse.getCourseId() + ", Status = Fail, Warning = Course name exists");
                return false;
            }
        }
        if (CourseAbstractFactory.instance().getCourseRepository().createCourseRepo(createCourse)) {
            createCourse.setCourseCreateMessage("Course created");
            LOG.info("Operation = Create new course with id" + createCourse.getCourseId() + ", Status = Fail");
            return true;
        } else {
            createCourse.setCourseCreateMessage("Course id exists");
            LOG.warn("Operation = Create new course with id " + createCourse.getCourseId() + ", Status = Fail, Warning = Course Id exists");
            return false;
        }
    }

    @Override
    public boolean deleteCourse(IDeleteCourse deleteCourse) throws Exception {
        if (CourseAbstractFactory.instance().getCourseRepository().deleteCourseRepo(deleteCourse)) {
            deleteCourse.setCourseDeleteMessage("Course deleted");
            deleteCourse.setAllCourseIds(CourseAbstractFactory.instance().getCourseRepository().getAllCourses().get(0));
            deleteCourse.setAllCourseNames(CourseAbstractFactory.instance().getCourseRepository().getAllCourses().get(1));
            return true;
        } else {
            deleteCourse.setCourseDeleteMessage("Course does not exist");
            LOG.warn("Operation = Delete course with id" + deleteCourse.getSelectedCourseId() + ", Status = Fail, Warning = Course name does not exist");
            return false;
        }
    }
}


