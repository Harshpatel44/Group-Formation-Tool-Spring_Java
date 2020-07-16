package CSCI5308.GroupFormationTool.Course;

public class CourseAbstractConcrete extends CourseAbstractFactory {


    private ICourseService courseService;
    private ICourseRepository courseRepository;
    private ICsvImporter csvImporter;
    private IHomeRepository homeRepository;
    private IHomeService homeService;

    @Override
    public ICourse getCourse() {
        return new Course();
    }

    @Override
    public ICreateCourse getCreateCourse() {
        return new CreateCourse();
    }

    @Override
    public IDeleteCourse getDeleteCourse() {
        return new DeleteCourse();
    }

    @Override
    public ICourseRepository getCourseRepository() {
        if (courseRepository == null) {
            courseRepository = new CourseRepository();
        }
        return courseRepository;
    }

    @Override
    public void setCourseRepository(ICourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Override
    public ICourseService getCourseService() {
        if (courseService == null) {
            courseService = new CourseService();
        }
        return courseService;
    }

    @Override
    public void setCourseService(ICourseService courseService) {
        this.courseService = courseService;
    }

    @Override
    public ICsvImporter getCsvImporter() {
        if (csvImporter == null) {
            csvImporter = new CsvImporterService();
        }
        return csvImporter;
    }

    @Override
    public IHomeRepository getHomeRepository() {
        if (homeRepository == null) {
            homeRepository = new HomeRepository();
        }
        return homeRepository;
    }

    @Override
    public void setHomeRepository(IHomeRepository homeRepository) {
        this.homeRepository = homeRepository;
    }

    @Override
    public IHomeService getHomeService() {
        if (homeService == null) {
            homeService = new HomeService();
        }
        return homeService;
    }
}
