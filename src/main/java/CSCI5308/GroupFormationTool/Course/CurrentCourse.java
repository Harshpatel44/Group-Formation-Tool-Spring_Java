package CSCI5308.GroupFormationTool.Course;

public class CurrentCourse{
    private static CurrentCourse instance = null;
    private String CurrentCourseId;
    private String CurrentCourseName;
    private String CurrentCourseUserRole;

    public static CurrentCourse instance(){
        if(instance==null){
            instance = new CurrentCourse();
        }
        return instance;
    }

    public String getCurrentCourseId() {
        return CurrentCourseId;
    }


    public void setCurrentCourseId(String currentCourseId) {
        CurrentCourseId = currentCourseId;
    }


    public String getCurrentCourseName() {
        return CurrentCourseName;
    }


    public void setCurrentCourseName(String currentCourseName) {
        CurrentCourseName = currentCourseName;
    }


    public String getCurrentCourseUserRole() {
        return CurrentCourseUserRole;
    }


    public void setCurrentCourseUserRole(String currentCourseUserRole) {
        CurrentCourseUserRole = currentCourseUserRole;
    }
}
