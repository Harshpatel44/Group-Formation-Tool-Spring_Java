package CSCI5308.GroupFormationTool;

import CSCI5308.GroupFormationTool.AnswerSurvey.*;
import CSCI5308.GroupFormationTool.Course.*;
import CSCI5308.GroupFormationTool.Database.DatabaseAbstractConcrete;
import CSCI5308.GroupFormationTool.Database.DatabaseAbstractFactory;
import CSCI5308.GroupFormationTool.GroupFormmer.*;
import CSCI5308.GroupFormationTool.PasswordManager.*;
import CSCI5308.GroupFormationTool.QuestionEditor.*;
import CSCI5308.GroupFormationTool.QuestionManager.*;

import CSCI5308.GroupFormationTool.SurveyManager.ISurveyManagerRepository;
import CSCI5308.GroupFormationTool.SurveyManager.ISurveyManagerService;
import CSCI5308.GroupFormationTool.SurveyManager.SurveyManagerRepository;
import CSCI5308.GroupFormationTool.SurveyManager.SurveyManagerService;
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
	private DatabaseAbstractFactory databaseAbstractFactory;
	private IDBConfiguration dbConfiguration;

	private IUser user;
	private IInstructor instructor;
	private IUserRepository userRepository;
	private IUserService userService;
	private UserManagerAbstractConcrete userManagerAbstractConcrete;

	private IPasswordEncryptor passwordEncryptor;
	private IEmailConfiguration emailConfiguration;
	private IUserNotification userNotification;
	private ILoginRepository loginRepository;
	private ILoginService loginService;

	private ICourse course;
	private ICreateCourse createCourse;
	private IDeleteCourse deleteCourse;
	private IHomeRepository homeRepository;
	private IHomeService homeService;
	private ICourseService courseService;
	private ICsvImporter csvImporter;
	private ICourseRepository courseRepository;

	private IForgetPasswordService forgetPasswordService;
	private IForgetPasswordRepository forgetPasswordRepository;

	private IQuestion question;
	private IQuestionManagerService questionManagerService;
	private IQuestionManagerRepository questionManagerRepository;
	private IQuestionResponsesService questionResponsesService;
	private IQuestionResponsesRepo questionResponsesRepo;

	private IQuestionModel questionModel;
	private IQuestionEditorService questionEditorService;
	private IQuestionEditorRepository questionEditorRepository;
	private IRankFunctionsService rankFunctionsService;
	private QuestionEditorAbstractFactory questionEditorAbstractFactory;

	private IAnswerSurveyRepository answerSurveyRepository;
	private IAnswerSurveyService answerSurveyService;
	private IDisplaySurveyResponseRepository displaySurveyResponseRepository;
	private IDisplaySurveyResponseService displaySurveyResponseService;

	private IGroupFilter groupFilter;
	private IGroupFormmerRepo grFormmerRepo;
	private IGroupFormmerService groupFormmerService;

	private Injector(){
//		databaseAbstractFactory = new DatabaseAbstractFactory();
//		dbConfiguration = new DBConfiguration();
		answerSurveyRepository = new AnswerSurveyRepository();
		answerSurveyService = new AnswerSurveyService();
		displaySurveyResponseRepository = new DisplaySurveyResponseRepository();
		displaySurveyResponseService = new DisplaySurveyResponseService();

		groupFilter = new GroupFilter();
		grFormmerRepo = new GroupFormmerRepo();
		groupFormmerService = new GroupFormmerService();

		databaseAbstractFactory = new DatabaseAbstractConcrete();
		dbConfiguration = new DBConfiguration();

		user = new User();
		instructor = new Instructor();
		userRepository = new UserRepository();
		userService = new UserService();
		passwordEncryptor = new BCryptEncryption();
//		userManagerAbstractFactory = new UserManagerAbstractFactory();
		userManagerAbstractConcrete = new UserManagerAbstractConcrete();

		userPasswordPolicyRepository = new UserPasswordPolicyRepository();
		userPasswordPolicyService = new UserPasswordPolicyService();

		loginRepository = new UserLoginRepository();
		loginService = new UserLoginService();
		emailConfiguration = new EmailConfiguration();
		userNotification = new UserNotification();


		forgetPasswordRepository =new ForgetPasswordRepository();
		forgetPasswordService = new ForgetPasswordService();

		course = new Course();
		createCourse = new CreateCourse();
		deleteCourse = new DeleteCourse();
		courseService = new CourseService();
		courseRepository = new CourseRepository();
		homeRepository = new HomeRepository();
		homeService = new HomeService();
		csvImporter = new CsvImporterService();

		question = new Question();
		questionManagerRepository = new QuestionManagerRepository();
		questionManagerService = new QuestionManagerService();
		questionResponsesRepo = new QuestionResponsesRepo();
		questionResponsesService = new QuestionResponsesService();

		questionModel = new QuestionModel();
		questionEditorService = new QuestionEditorService();
		questionEditorRepository = new QuestionEditorRepository();
		rankFunctionsService = new RankFunctionsService();
//		questionEditorAbstractFactory = new QuestionEditorAbstractFactory();

		surveyManagerService = new SurveyManagerService();
		surveyManagerRepository = new SurveyManagerRepository();
	}

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

	private ISurveyManagerService surveyManagerService;
	private ISurveyManagerRepository surveyManagerRepository;

	public QuestionEditorAbstractFactory getQuestionEditorAbstractFactory() {
		return questionEditorAbstractFactory;
	}

	public void setQuestionEditorAbstractFactory(QuestionEditorAbstractFactory questionEditorAbstractFactory) {
		this.questionEditorAbstractFactory = questionEditorAbstractFactory;
	}

	public DatabaseAbstractFactory getDatabaseAbstractFactory() {
		return databaseAbstractFactory;
	}

	public void setDatabaseAbstractFactory(DatabaseAbstractFactory databaseAbstractFactory) {
		this.databaseAbstractFactory = databaseAbstractFactory;
	}

	public UserManagerAbstractConcrete getUserManagerAbstractConcrete() {
		return userManagerAbstractConcrete;
	}

	public void setUserManagerAbstractConcrete(UserManagerAbstractConcrete userManagerAbstractConcrete) {
		this.userManagerAbstractConcrete = userManagerAbstractConcrete;
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

	public ISurveyManagerService getSurveyManagerService() { return surveyManagerService; }

	public void setSurveyManagerService(ISurveyManagerService surveyManagerService) { this.surveyManagerService = surveyManagerService; }

	public ISurveyManagerRepository getSurveyManagerRepository() { return surveyManagerRepository; }

	public void setSurveyManagerRepository(ISurveyManagerRepository surveyManagerRepository) { this.surveyManagerRepository = surveyManagerRepository; }
	public IAnswerSurveyService getAnswerSurveyService() {
		return answerSurveyService;
	}

	public void setAnswerSurveyService(IAnswerSurveyService answerSurveyService) {
		this.answerSurveyService = answerSurveyService;
	}

	public IAnswerSurveyRepository getAnswerSurveyRepository() {
		return answerSurveyRepository;
	}

	public void setAnswerSurveyRepository(IAnswerSurveyRepository answerSurveyRepository) {
		this.answerSurveyRepository = answerSurveyRepository;
	}

	public IGroupFilter getGroupFilter() {
		return groupFilter;
	}

	public void setGroupFilter(IGroupFilter groupFilter) {
		this.groupFilter = groupFilter;
	}

	public IGroupFormmerService getGroupFormmerService() {
		return groupFormmerService;
	}

	public void setGroupFormmerService(IGroupFormmerService groupFormmerService) {
		this.groupFormmerService = groupFormmerService;
	}

	public IGroupFormmerRepo getGrFormmerRepo() {
		return grFormmerRepo;
	}

	public void setGrFormmerRepo(IGroupFormmerRepo grFormmerRepo) {
		this.grFormmerRepo = grFormmerRepo;
	}

	public IDisplaySurveyResponseRepository getDisplaySurveyResponseRepository() {
		return displaySurveyResponseRepository;
	}

	public void setDisplaySurveyResponseRepository(IDisplaySurveyResponseRepository displaySurveyResponseRepository) {
		this.displaySurveyResponseRepository = displaySurveyResponseRepository;
	}

	public IDisplaySurveyResponseService getDisplaySurveyResponseService() {
		return displaySurveyResponseService;
	}

	public void setDisplaySurveyResponseService(IDisplaySurveyResponseService displaySurveyResponseService) {
		this.displaySurveyResponseService = displaySurveyResponseService;
	}
}
