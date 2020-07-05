package CSCI5308.GroupFormationTool;

import CSCI5308.GroupFormationTool.Course.*;
import CSCI5308.GroupFormationTool.PasswordManager.*;
import CSCI5308.GroupFormationTool.QuestionEditor.*;
import CSCI5308.GroupFormationTool.QuestionManager.*;

import CSCI5308.GroupFormationTool.UserAuthentication.*;

import CSCI5308.GroupFormationTool.UserAuthentication.IPasswordEncryptor;
import CSCI5308.GroupFormationTool.UserAuthentication.IUserNotification;
import CSCI5308.GroupFormationTool.UserManager.IUserRepository;
import CSCI5308.GroupFormationTool.UserManager.IUserService;
import CSCI5308.GroupFormationTool.Course.ICourseRepository;
import CSCI5308.GroupFormationTool.Course.ICourseService;
import CSCI5308.GroupFormationTool.Course.IHomeRepository;
import CSCI5308.GroupFormationTool.Course.IHomeService;
import CSCI5308.GroupFormationTool.Course.CourseRepository;
import CSCI5308.GroupFormationTool.Course.HomeRepository;
import CSCI5308.GroupFormationTool.Course.CourseService;
import CSCI5308.GroupFormationTool.Course.HomeService;

import CSCI5308.GroupFormationTool.Course.ICsvImporter;
import CSCI5308.GroupFormationTool.Course.CsvImporterService;
import CSCI5308.GroupFormationTool.Database.DBConfiguration;
import CSCI5308.GroupFormationTool.Database.IDBConfiguration;
import CSCI5308.GroupFormationTool.UserAuthentication.ILoginRepository;
import CSCI5308.GroupFormationTool.UserAuthentication.ILoginService;
import CSCI5308.GroupFormationTool.UserManager.UserRepository;
import CSCI5308.GroupFormationTool.UserAuthentication.BCryptEncryption;
import CSCI5308.GroupFormationTool.UserAuthentication.EmailConfiguration;
import CSCI5308.GroupFormationTool.UserAuthentication.UserNotification;
import CSCI5308.GroupFormationTool.UserManager.UserService;
import CSCI5308.GroupFormationTool.UserManager.*;

public class Injector {

	private static Injector instance = null;
	private IDBConfiguration dbConfiguration;
	private IUserRepository userRepository;
	private IPasswordEncryptor passwordEncryptor;
	private IUserService userService;
	private IEmailConfiguration emailConfiguration;
	private IUserNotification userNotification;

	private IHomeRepository homeRepository;
	private IHomeService homeService;
	private ICourseService courseService;
	private ICsvImporter csvImporter;
	private ICourseRepository courseRepository;

	private ILoginService loginService;
	private IForgetPasswordService forgetPasswordService;
	private ILoginRepository loginRepository;
	private IForgetPasswordRepository forgetPasswordRepository;

	private IQuestion question;
	private IQuestionModel questionModel;
	private IQuestionManagerService questionManagerService;
	private IQuestionManagerRepository questionManagerRepository;
	private IQuestionResponsesService questionResponsesService;
	private IQuestionResponsesRepo questionResponsesRepo;

	private IInstructor instructor;

	private ICourse course;
	private ICreateCourse createCourse;
	private IDeleteCourse deleteCourse;
	private IUser user;

	private IQuestionEditorService questionEditorService;
	private IQuestionEditorRepository questionEditorRepository;
	private IRankFunctionsService rankFunctionsService;

	public IUserPasswordPolicyService getUserPasswordPolicyService() {
		return userPasswordPolicyService;
	}

	public void setUserPasswordPolicyService(IUserPasswordPolicyService userPasswordPolicyService) {
		this.userPasswordPolicyService = userPasswordPolicyService;
	}

	public IUserPasswordPolicyRepository getUserPasswordPolicyRepository() {
		return userPasswordPolicyRepository;
	}

	public void setUserPasswordPolicyRepository(IUserPasswordPolicyRepository userPasswordPolicyRepository) {
		this.userPasswordPolicyRepository = userPasswordPolicyRepository;
	}

	private IUserPasswordPolicyService userPasswordPolicyService;
	private IUserPasswordPolicyRepository userPasswordPolicyRepository;

	private Injector(){

		dbConfiguration = new DBConfiguration();
		userRepository = new UserRepository();
		passwordEncryptor = new BCryptEncryption();
		userService = new UserService();
		csvImporter = new CsvImporterService();
		emailConfiguration = new EmailConfiguration();
		userNotification = new UserNotification();
		loginService = new UserLoginService();
		forgetPasswordService = new ForgetPasswordService();
		homeRepository = new HomeRepository();
		homeService = new HomeService();
		courseService = new CourseService();
		courseRepository = new CourseRepository();
		loginRepository = new UserLoginRepository();
		forgetPasswordRepository =new ForgetPasswordRepository();
		questionManagerRepository = new QuestionManagerRepository();
		questionManagerService = new QuestionManagerService();
		questionResponsesRepo = new QuestionResponsesRepo();
		questionResponsesService = new QuestionResponsesService();
		user = new User();

		instructor = new Instructor();

		course = new Course();
		createCourse = new CreateCourse();
		deleteCourse = new DeleteCourse();
		
		question = new Question();
		questionModel = new QuestionModel();

		userPasswordPolicyRepository = new UserPasswordPolicyRepository();
		userPasswordPolicyService = new UserPasswordPolicyService();

		questionEditorService = new QuestionEditorService();
		questionEditorRepository = new QuestionEditorRepository();
		rankFunctionsService = new RankFunctionsService();
	}

	public IRankFunctionsService getRankFunctionsService() {
		return rankFunctionsService;
	}

	public void setRankFunctionsService(IRankFunctionsService rankFunctionsService) {
		this.rankFunctionsService = rankFunctionsService;
	}

	public IQuestionEditorService getQuestionEditorService() {
		return questionEditorService;
	}

	public void setQuestionEditorService(IQuestionEditorService questionEditorService) {
		this.questionEditorService = questionEditorService;
	}

	public IQuestionEditorRepository getQuestionEditorRepository() {
		return questionEditorRepository;
	}

	public void setQuestionEditorRepository(IQuestionEditorRepository questionEditorRepository) {
		this.questionEditorRepository = questionEditorRepository;
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

	public static Injector instance(){

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

	public IUser getUser() { return user; }

	public void setUser(IUser user) { this.user = user; }

	public void setQuestionManagerRepository(IQuestionManagerRepository questionManagerRepository){this.questionManagerRepository = questionManagerRepository;}

	public IQuestion getQuestion() { return question; }

	public void setQuestion(IQuestion question) { this.question = question; }

	public IQuestionModel getQuestionModel() { return questionModel; }

	public void setQuestionModel(IQuestionModel questionModel) { this.questionModel = questionModel; }
}
