package CSCI5308.GroupFormationTool;

public class ApplicationConstants {

    public static final String applicationUrl = "jdbc:mysql://db-5308.cs.dal.ca:3306/CSCI5308_9_DEVINT?useSSL=false&serverTimezone=UTC";
    public static final String dbUser = "CSCI5308_9_DEVINT_USER";
    public static final String dbPassword = "CSCI5308_9_DEVINT_9017";
    public static final String emailSender = "sdcmaster09@gmail.com";
    public static final String emailSenderPassword = "sdc.master";
    public static final Integer emailPort = 587;
    public static final String emailHost = "smtp.gmail.com";
    public static final String restLink = "https://group9-develop.herokuapp.com/";
    public static final String updatePasswordLink = "updateNewPassword?passKey=";
    public static final String randomKeyForLink = "ABCDEFGHIJKLMNOPQRSTUVWXYZ" + "0123456789" + "abcdefghijklmnopqrstuvxyz";
    public static final String emailPattern = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    public static final String sortByDate = "sortByDate";
    public static final String sortByTopic = "sortByTopic";
    public static final String numeric = "Numeric";
    public static final String text = "Text";
    public static final String MCCM = "Multiple Choice, Choose Multiple";
    public static final String MCCO = "Multiple Choice, Choose One";
    public static final String guest = "Guest";
    public static final String instructor = "instructor";
    public static final String student = "student";
    public static final String teachingAssistant = "teachingAssistant";
    public static final String admin = "admin";
    public static final String user = "USER";
    public static final String badCredentialsException = "1000";
    public static final String noUserFoundException = "1001";
    public static final String userNotificationSubject = "Login Credentials for GroupFormation Tool";
    public static final String userNotificationBody = "Welcome to the GroupFormation Tool\n Your Login credentials are as follows: \n";
    public static final String passwordResetSubject = "Password Reset Link";
}
