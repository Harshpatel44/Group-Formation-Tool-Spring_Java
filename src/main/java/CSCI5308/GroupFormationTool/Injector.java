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

	private IPasswordEncryptor passwordEncryptor;
	private IEmailConfiguration emailConfiguration;
	private IUserNotification userNotification;
	private ILoginRepository loginRepository;
	private ILoginService loginService;

	private IForgetPasswordService forgetPasswordService;
	private IForgetPasswordRepository forgetPasswordRepository;

	private Injector(){



		userPasswordPolicyRepository = new UserPasswordPolicyRepository();
		userPasswordPolicyService = new UserPasswordPolicyService();

		loginRepository = new UserLoginRepository();
		loginService = new UserLoginService();
		emailConfiguration = new EmailConfiguration();
		userNotification = new UserNotification();


		forgetPasswordRepository =new ForgetPasswordRepository();
		forgetPasswordService = new ForgetPasswordService();


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



	public static Injector instance(){

		if (instance == null) {
			instance = new Injector();
		}
		return instance;
	}

	public static void setInstance(Injector instance) {
		Injector.instance = instance;
	}


	public void setPasswordEncryptor(IPasswordEncryptor passwordEncryptor) {
		this.passwordEncryptor = passwordEncryptor;
	}


	public void setEmailConfiguration(IEmailConfiguration emailConfiguration) {
		this.emailConfiguration = emailConfiguration;
	}

	public void setUserNotification(IUserNotification userNotification) {
		this.userNotification = userNotification;
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

	public IPasswordEncryptor getPasswordEncryptor() {
		return passwordEncryptor;
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


}
