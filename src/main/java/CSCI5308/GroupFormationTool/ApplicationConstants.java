package CSCI5308.GroupFormationTool;

public class ApplicationConstants {

	public static final String applicationUrl = "jdbc:mysql://db-5308.cs.dal.ca:3306/CSCI5308_9_PRODUCTION?useSSL=false&serverTimezone=UTC";
	public static final String dbUser = "CSCI5308_9_PRODUCTION_USER";
	public static final String dbPassword = "CSCI5308_9_PRODUCTION_9914";
	public static final String emailSender = "sdcmaster09@gmail.com";
	public static final String emailSenderPassword = "sdc.master";
	public static final String restLink = "https://group9-production.herokuapp.com/";
	public static final String updatePasswordLink = "updateNewPassword?passKey=";
	public static final String randomKeyForLink = "ABCDEFGHIJKLMNOPQRSTUVWXYZ" + "0123456789"
			+ "abcdefghijklmnopqrstuvxyz";
	public static final String emailPattern = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
			+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

}
