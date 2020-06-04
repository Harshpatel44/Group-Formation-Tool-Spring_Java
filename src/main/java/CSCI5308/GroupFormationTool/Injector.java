package CSCI5308.GroupFormationTool;

import CSCI5308.GroupFormationTool.Course.AccessControl.ICourseRepository;
import CSCI5308.GroupFormationTool.Course.AccessControl.ICourseService;
import CSCI5308.GroupFormationTool.Course.AccessControl.IHomeRepository;
import CSCI5308.GroupFormationTool.Course.AccessControl.IHomeService;
import CSCI5308.GroupFormationTool.Course.Repository.CourseRepository;
import CSCI5308.GroupFormationTool.Course.Repository.HomeRepository;
import CSCI5308.GroupFormationTool.Course.Service.CourseService;
import CSCI5308.GroupFormationTool.Course.Service.HomeService;
import CSCI5308.GroupFormationTool.UserAuthentication.AccessControl.IPasswordEncryptor;
import CSCI5308.GroupFormationTool.UserAuthentication.AccessControl.IUserRepository;
import CSCI5308.GroupFormationTool.UserAuthentication.AccessControl.IUserService;
import CSCI5308.GroupFormationTool.Database.DBConfiguration;
import CSCI5308.GroupFormationTool.Database.IDBConfiguration;
import CSCI5308.GroupFormationTool.UserAuthentication.Repository.UserRepository;
import CSCI5308.GroupFormationTool.UserAuthentication.Security.BCryptEncryption;
import CSCI5308.GroupFormationTool.UserAuthentication.Service.UserService;

public class Injector {

	private static Injector instance = null;

	private IDBConfiguration dbConfiguration;
	private IUserRepository userRepository;
	private IPasswordEncryptor passwordEncryptor;
	private IUserService userService;
	private IHomeRepository homeRepository;
	private IHomeService homeService;
	private ICourseService courseService;
	private ICourseRepository courseRepository;
	
	private Injector() {

		dbConfiguration = new DBConfiguration();
		userRepository = new UserRepository();
		passwordEncryptor = new BCryptEncryption();
		userService = new UserService();
		homeRepository = new HomeRepository();
		homeService = new HomeService();
		courseService = new CourseService();
		courseRepository = new CourseRepository();
	}

	public static Injector instance() {

		if(instance==null) {
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


}
