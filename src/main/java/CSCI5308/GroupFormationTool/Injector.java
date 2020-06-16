package CSCI5308.GroupFormationTool;

import CSCI5308.GroupFormationTool.QuestionManager.AccessControl.IQuestionController;
import CSCI5308.GroupFormationTool.QuestionManager.AccessControl.IQuestionManagerRepository;
import CSCI5308.GroupFormationTool.QuestionManager.AccessControl.IQuestionManagerService;
import CSCI5308.GroupFormationTool.QuestionManager.Repository.QuestionManagerRepository;
import CSCI5308.GroupFormationTool.QuestionManager.Service.QuestionManagerService;
import CSCI5308.GroupFormationTool.UserAuthentication.AccessControl.IEmailConfiguration;

import CSCI5308.GroupFormationTool.Course.AccessControl.ICourseRepository;
import CSCI5308.GroupFormationTool.Course.AccessControl.ICourseService;
import CSCI5308.GroupFormationTool.Course.AccessControl.IHomeRepository;
import CSCI5308.GroupFormationTool.Course.AccessControl.IHomeService;
import CSCI5308.GroupFormationTool.Course.Repository.CourseRepository;
import CSCI5308.GroupFormationTool.Course.Repository.HomeRepository;
import CSCI5308.GroupFormationTool.Course.Service.CourseService;
import CSCI5308.GroupFormationTool.Course.Service.HomeService;

import CSCI5308.GroupFormationTool.UserAuthentication.AccessControl.IPasswordEncryptor;
import CSCI5308.GroupFormationTool.UserAuthentication.AccessControl.IUserNotification;
import CSCI5308.GroupFormationTool.UserAuthentication.AccessControl.IUserRepository;
import CSCI5308.GroupFormationTool.UserAuthentication.AccessControl.IUserService;
import CSCI5308.GroupFormationTool.Course.AccessControl.ICsvImporter;
import CSCI5308.GroupFormationTool.Course.Service.CsvImporterService;
import CSCI5308.GroupFormationTool.Database.DBConfiguration;
import CSCI5308.GroupFormationTool.Database.IDBConfiguration;
import CSCI5308.GroupFormationTool.Login.AccessControl.ILoginRepository;
import CSCI5308.GroupFormationTool.Login.AccessControl.ILoginService;
import CSCI5308.GroupFormationTool.Login.Repository.LoginRepository;
import CSCI5308.GroupFormationTool.Login.Service.LoginService;
import CSCI5308.GroupFormationTool.UserAuthentication.Repository.UserRepository;
import CSCI5308.GroupFormationTool.UserAuthentication.Security.BCryptEncryption;
import CSCI5308.GroupFormationTool.UserAuthentication.Service.EmailConfiguration;
import CSCI5308.GroupFormationTool.UserAuthentication.Service.UserNotification;
import CSCI5308.GroupFormationTool.UserAuthentication.Service.UserService;

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
	private ILoginRepository loginRepository;
	private IQuestionManagerService questionManagerService;
	private IQuestionManagerRepository questionManagerRepository;

	private Injector()  {

		dbConfiguration = new DBConfiguration();
		userRepository = new UserRepository();
		passwordEncryptor = new BCryptEncryption();
		userService = new UserService();

		csvImporter = new CsvImporterService();
		emailConfiguration = new EmailConfiguration();
		userNotification = new UserNotification();
		loginService = new LoginService();
		homeRepository = new HomeRepository();
		homeService = new HomeService();
		courseService = new CourseService();
		courseRepository = new CourseRepository();
		loginRepository = new LoginRepository();
		questionManagerRepository = new QuestionManagerRepository();
		questionManagerService = new QuestionManagerService();

	}

	public static Injector instance() {

		if (instance == null) {
			instance = new Injector();
		}
		return instance;
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

	public IQuestionManagerService getQuestionManagerService(){return questionManagerService;}

	public IQuestionManagerRepository getQuestionManagerRepository(){return questionManagerRepository;}

    public void setHomeRepository(IHomeRepository homeRepository){this.homeRepository = homeRepository;}

    public void setQuestionManagerRepository(IQuestionManagerRepository questionManagerRepository){this.questionManagerRepository = questionManagerRepository;}

    public void setCourseRepository(ICourseRepository courseRepository){this.courseRepository = courseRepository;}
}
