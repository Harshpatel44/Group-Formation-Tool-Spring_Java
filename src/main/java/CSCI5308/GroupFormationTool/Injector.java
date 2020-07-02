package CSCI5308.GroupFormationTool;

import CSCI5308.GroupFormationTool.AdminPanel.AdminService;
import CSCI5308.GroupFormationTool.AdminPanel.IAdminService;
import CSCI5308.GroupFormationTool.AdminPanel.InstructorAdminRepository;
import CSCI5308.GroupFormationTool.Login.IForgetPasswordRepository;
import CSCI5308.GroupFormationTool.Login.IForgetPasswordService;
import CSCI5308.GroupFormationTool.Login.ForgetPasswordRepository;
import CSCI5308.GroupFormationTool.Login.ForgetPasswordService;
import CSCI5308.GroupFormationTool.QuestionEditor.IQuestionModel;
import CSCI5308.GroupFormationTool.QuestionEditor.QuestionModel;
import CSCI5308.GroupFormationTool.QuestionManager.*;

import CSCI5308.GroupFormationTool.UserAuthentication.*;

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
import CSCI5308.GroupFormationTool.Login.ILoginRepository;
import CSCI5308.GroupFormationTool.Login.ILoginService;
import CSCI5308.GroupFormationTool.Login.LoginRepository;
import CSCI5308.GroupFormationTool.Login.LoginService;

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
    private IUser user;
	private IQuestion question;
	private IQuestionModel questionModel;


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
		user = new User();
		question = new Question();
		questionModel = new QuestionModel();
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

	public IUser getUser() { return user; }

	public void setUser(IUser user) { this.user = user; }

	public void setQuestionManagerRepository(IQuestionManagerRepository questionManagerRepository){this.questionManagerRepository = questionManagerRepository;}

	public IQuestion getQuestion() { return question; }

	public void setQuestion(IQuestion question) { this.question = question; }

	public IQuestionModel getQuestionModel() { return questionModel; }

	public void setQuestionModel(IQuestionModel questionModel) { this.questionModel = questionModel; }
}
