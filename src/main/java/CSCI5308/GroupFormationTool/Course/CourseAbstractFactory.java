package CSCI5308.GroupFormationTool.Course;

public abstract class CourseAbstractFactory {
    private static CourseAbstractFactory instance = null;

    public static CourseAbstractFactory instance(){

        if (instance == null) {
            instance = new CourseAbstractConcrete();
        }
        return instance;
    }

    public abstract ICourse getCourse();
    public abstract ICourseRepository getCourseRepository();
    public abstract ICourseService getCourseService();
    public abstract ICreateCourse getCreateCourse();
    public abstract ICsvImporter getCsvImporter();
    public abstract IDeleteCourse getDeleteCourse();
    public abstract IHomeRepository getHomeRepository();
    public abstract IHomeService getHomeService();

}
