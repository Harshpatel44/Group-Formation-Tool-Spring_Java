package CSCI5308.GroupFormationTool;

import CSCI5308.GroupFormationTool.AdminPanel.*;
import CSCI5308.GroupFormationTool.Course.*;
import CSCI5308.GroupFormationTool.Login.IForgetPasswordRepository;
import CSCI5308.GroupFormationTool.Login.IForgetPasswordService;
import CSCI5308.GroupFormationTool.Login.ForgetPasswordRepository;
import CSCI5308.GroupFormationTool.Login.ForgetPasswordService;
import CSCI5308.GroupFormationTool.QuestionManager.IQuestionManagerRepository;
import CSCI5308.GroupFormationTool.QuestionManager.IQuestionManagerService;
import CSCI5308.GroupFormationTool.QuestionManager.IQuestionResponsesRepo;
import CSCI5308.GroupFormationTool.QuestionManager.IQuestionResponsesService;
import CSCI5308.GroupFormationTool.QuestionManager.QuestionManagerRepository;
import CSCI5308.GroupFormationTool.QuestionManager.QuestionManagerService;

import CSCI5308.GroupFormationTool.QuestionManager.QuestionResponsesRepo;
import CSCI5308.GroupFormationTool.QuestionManager.QuestionResponsesService;
import CSCI5308.GroupFormationTool.UserAuthentication.IEmailConfiguration;

import CSCI5308.GroupFormationTool.UserAuthentication.IPasswordEncryptor;
import CSCI5308.GroupFormationTool.UserAuthentication.IUserNotification;
import CSCI5308.GroupFormationTool.UserAuthentication.IUserRepository;
import CSCI5308.GroupFormationTool.UserAuthentication.IUserService;
import CSCI5308.GroupFormationTool.Database.DBConfiguration;
import CSCI5308.GroupFormationTool.Database.IDBConfiguration;
import CSCI5308.GroupFormationTool.Login.ILoginRepository;
import CSCI5308.GroupFormationTool.Login.ILoginService;
import CSCI5308.GroupFormationTool.Login.LoginRepository;
import CSCI5308.GroupFormationTool.Login.LoginService;
import CSCI5308.GroupFormationTool.UserAuthentication.UserRepository;
import CSCI5308.GroupFormationTool.UserAuthentication.BCryptEncryption;
import CSCI5308.GroupFormationTool.UserAuthentication.EmailConfiguration;
import CSCI5308.GroupFormationTool.UserAuthentication.UserNotification;
import CSCI5308.GroupFormationTool.UserAuthentication.UserService;

public class Injector {

	private static Injector instance = null;
	private IDBConfiguration dbConfiguration;
	private IUserRepository userRepository;
	private IPasswordEncryptor passwordEncryptor;
	private IUserService userService;
	private ICsvImporter csvImporter;
	private IEmailConfiguration emailConfiguration;
	private IUserNotification userNotification;
	private IHomeRepository homeRepository;
	private IHomeService homeService;
	private ICourseService courseService;
	private ICourseRepository courseRepository;

	private ILoginService loginService;
	private IForgetPasswordService forgetPasswordService;
	private ILoginRepository loginRepository;
	private IForgetPasswordRepository forgetPasswordRepository;

	private IQuestionManagerService questionManagerService;
	private IQuestionManagerRepository questionManagerRepository;
	private IQuestionResponsesService questionResponsesService;
	private IQuestionResponsesRepo questionResponsesRepo;

	private IAdminService adminService;
	private InstructorAdminRepository instructorAdminRepository;
	private IInstructor instructor;

	private ICourse course;
	private ICreateCourse createCourse;
	private IDeleteCourse deleteCourse;
	private ITA ta;
	private IUserRole userRole;

	private Injector() throws Exception {

		dbConfiguration = new DBConfiguration();
		userRepository = new UserRepository();
		passwordEncryptor = new BCryptEncryption();
		userService = new UserService();
		csvImporter = new CsvImporterService();
		emailConfiguration = new EmailConfiguration();
		userNotification = new UserNotification();

		loginService = new LoginService();
		forgetPasswordService = new ForgetPasswordService();
		homeRepository = new HomeRepository();
		homeService = new HomeService();
		courseService = new CourseService();
		courseRepository = new CourseRepository();
		loginRepository = new LoginRepository();
		forgetPasswordRepository =new ForgetPasswordRepository();

		questionManagerRepository = new QuestionManagerRepository();
		questionManagerService = new QuestionManagerService();
		questionResponsesRepo = new QuestionResponsesRepo();
		questionResponsesService = new QuestionResponsesService();


		adminService = new AdminService();
		instructorAdminRepository = new InstructorAdminRepository();
		instructor = new Instructor();

		course = new Course();
		createCourse = new CreateCourse();
		deleteCourse = new DeleteCourse();
		ta = new TA();
		userRole = new UserRole();
	}

	public IInstructor getInstructor() {
		return instructor;
	}

	public void setInstructor(IInstructor instructor) {
		this.instructor = instructor;
	}

	public ICourse getCourse() {
		return course;
	}

	public void setCourse(ICourse course) {
		this.course = course;
	}

	public ICreateCourse getCreateCourse() {
		return createCourse;
	}

	public void setCreateCourse(ICreateCourse createCourse) {
		this.createCourse = createCourse;
	}

	public IDeleteCourse getDeleteCourse() {
		return deleteCourse;
	}

	public void setDeleteCourse(IDeleteCourse deleteCourse) {
		this.deleteCourse = deleteCourse;
	}

	public void setCreateCourse(CreateCourse createCourse) {
		this.createCourse = createCourse;
	}



	public void setDeleteCourse(DeleteCourse deleteCourse) {
		this.deleteCourse = deleteCourse;
	}

	public ITA getTA() {
		return ta;
	}

	public void setTA(ITA ta) {
		this.ta = ta;
	}

	public IUserRole getUserRole() {
		return userRole;
	}

	public void setUserRole(IUserRole userRole) {
		this.userRole = userRole;
	}

	public IAdminService getAdminService() {
		return adminService;
	}

	public void setAdminService(IAdminService adminService) {
		this.adminService = adminService;
	}

	public InstructorAdminRepository getInstructorAdminRepository() {
		return instructorAdminRepository;
	}

	public void setInstructorAdminRepository(InstructorAdminRepository instructorAdminRepository) {
		this.instructorAdminRepository = instructorAdminRepository;
	}

	public IQuestionResponsesService getQuestionResponsesService() {
		return questionResponsesService;
	}

	public void setQuestionResponsesService(IQuestionResponsesService questionResponsesService) {
		this.questionResponsesService = questionResponsesService;
	}

	public IQuestionResponsesRepo getQuestionResponsesRepo() {
		return questionResponsesRepo;
	}

	public void setQuestionResponsesRepo(IQuestionResponsesRepo questionResponsesRepo) {
		this.questionResponsesRepo = questionResponsesRepo;
	}

	public static Injector instance() throws Exception {

		if (instance == null) {
			instance = new Injector();
		}
		return instance;
	}

	public static void setInstance(Injector instance) {
		Injector.instance = instance;
	}

	public void setDbConfiguration(IDBConfiguration dbConfiguration) {
		this.dbConfiguration = dbConfiguration;
	}

	public void setUserRepository(IUserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public void setPasswordEncryptor(IPasswordEncryptor passwordEncryptor) {
		this.passwordEncryptor = passwordEncryptor;
	}

	public void setUserService(IUserService userService) {
		this.userService = userService;
	}

	public void setCsvImporter(ICsvImporter csvImporter) {
		this.csvImporter = csvImporter;
	}

	public void setEmailConfiguration(IEmailConfiguration emailConfiguration) {
		this.emailConfiguration = emailConfiguration;
	}

	public void setUserNotification(IUserNotification userNotification) {
		this.userNotification = userNotification;
	}

	public void setHomeRepository(IHomeRepository homeRepository) {
		this.homeRepository = homeRepository;
	}

	public void setHomeService(IHomeService homeService) {
		this.homeService = homeService;
	}

	public void setCourseService(ICourseService courseService) {
		this.courseService = courseService;
	}

	public void setCourseRepository(ICourseRepository courseRepository) {
		this.courseRepository = courseRepository;
	}

	public void setLoginService(ILoginService loginService) {
		this.loginService = loginService;
	}

	public void setLoginRepository(ILoginRepository loginRepository) {
		this.loginRepository = loginRepository;
	}

	public void setForgetPasswordService(IForgetPasswordService forgetPasswordService) {
		this.forgetPasswordService = forgetPasswordService;
	}

	public void setForgetPasswordRepository(IForgetPasswordRepository forgetPasswordRepository) {
		this.forgetPasswordRepository = forgetPasswordRepository;
	}

	public IUserRepository getUserRepository() {
		return userRepository;
	}

	public IDBConfiguration getDbConfiguration() {
		return dbConfiguration;
	}

	public IPasswordEncryptor getPasswordEncryptor() {
		return passwordEncryptor;
	}

	public IUserService getUserService() {
		return userService;
	}

	public IHomeRepository getHomeRepository() {
		return homeRepository;
	}

	public IHomeService getHomeService() {
		return homeService;
	}

	public ICourseRepository getCourseRepository() {
		return courseRepository;
	}

	public ICourseService getCourseService() {
		return courseService;
	}

	public ICsvImporter getCsvImporter() {
		return csvImporter;
	}

	public IEmailConfiguration getEmailConfiguration() {
		return emailConfiguration;
	}

	public IUserNotification getUserNotification() {
		return userNotification;
	}

	public ILoginService getLoginService() {
		return loginService;
	}

	public ILoginRepository getLoginRepository() {
		return loginRepository;
	}

	public IForgetPasswordService getForgetPasswordService() {
		return forgetPasswordService;
	}

	public IForgetPasswordRepository getForgetPasswordRepository() {
		return forgetPasswordRepository;
	}

	public IQuestionManagerService getQuestionManagerService(){return questionManagerService;}

	public IQuestionManagerRepository getQuestionManagerRepository(){return questionManagerRepository;}

    public void setQuestionManagerRepository(IQuestionManagerRepository questionManagerRepository){this.questionManagerRepository = questionManagerRepository;}
}
