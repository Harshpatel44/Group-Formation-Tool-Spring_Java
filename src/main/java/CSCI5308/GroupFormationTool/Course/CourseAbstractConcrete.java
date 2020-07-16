package CSCI5308.GroupFormationTool.Course;

import CSCI5308.GroupFormationTool.QuestionEditor.*;

public class CourseAbstractConcrete extends CourseAbstractFactory {


    private ICourseService courseService;
    private ICourseRepository courseRepository;
    private ICsvImporter csvImporter;
    private IHomeRepository homeRepository;
    private IHomeService homeService;

    @Override
    public ICourse getCourse() { return new Course(); }

    @Override
    public ICreateCourse getCreateCourse() { return new CreateCourse(); }

    @Override
    public IDeleteCourse getDeleteCourse() { return new DeleteCourse(); }

    @Override
    public ICourseRepository getCourseRepository() {
        if (courseRepository == null) {
            courseRepository = new CourseRepository();
        }
        return courseRepository;
    }

    @Override
    public ICourseService getCourseService() {
        if (courseService == null) {
            courseService = new CourseService();
        }
        return courseService;
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
    public IHomeService getHomeService() {
        if (homeService == null) {
            homeService = new HomeService();
        }
        return homeService;
    }
}
