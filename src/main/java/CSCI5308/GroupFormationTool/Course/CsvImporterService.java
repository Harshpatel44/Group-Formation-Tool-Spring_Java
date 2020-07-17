package CSCI5308.GroupFormationTool.Course;

import CSCI5308.GroupFormationTool.UserAuthentication.IPasswordEncryptor;
import CSCI5308.GroupFormationTool.UserAuthentication.IUserNotification;
import CSCI5308.GroupFormationTool.UserAuthentication.UserAuthenticationAbstractFactory;
import CSCI5308.GroupFormationTool.UserManager.IUser;
import CSCI5308.GroupFormationTool.UserManager.IUserRepository;
import CSCI5308.GroupFormationTool.UserManager.UserManagerAbstractFactory;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.*;

public class CsvImporterService implements ICsvImporter {
    private IUserRepository userRepository;
    private ICourseRepository courseRepository;
    private IPasswordEncryptor encryptor;
    private IUserNotification userNotification;
    private List<String> failureResults;
    private List<String> successfulResults;
    private Map<Integer, List<String>> results;

    @Override
    public Map<Integer, List<String>> StudentsEnrolledForCourse(String courseId, MultipartFile file) {
        try {
            failureResults = new ArrayList<>();
            successfulResults = new ArrayList<>();
            results = new HashMap<>();
            courseRepository = CourseAbstractFactory.instance().getCourseRepository();
            userRepository = UserManagerAbstractFactory.instance().getUserRepository();
            List<String> bannerIds = userRepository.getAllBannerIds();
            Reader reader = new InputStreamReader(file.getInputStream());
            CSVReader csvReader = new CSVReaderBuilder(reader).withSkipLines(1).build();
            List<String[]> records = csvReader.readAll();
            Iterator<String[]> iter = records.iterator();
            while (iter.hasNext()) {
                String[] record = iter.next();
                checkTheUserAndEnroll(record, bannerIds, userRepository, courseId);
            }
        } catch (IOException e) {
            failureResults.add("Failure reading uploaded file: " + e.getMessage());

        } catch (Exception e) {
            failureResults.add("Failure parsing CSV file: " + e.getMessage());
        }
        results.put(1, successfulResults);
        results.put(2, failureResults);
        return results;
    }

    private void checkTheUserAndEnroll(String[] record, List<String> bannerIds, IUserRepository userRepository,
                                       String courseId) throws Exception {
        List<IUser> successfulUsers = new ArrayList<>();
        encryptor = UserAuthenticationAbstractFactory.instance().getBCryptEncryption();
        userNotification = UserAuthenticationAbstractFactory.instance().getUserNotification();
        IUser user = UserManagerAbstractFactory.instance().getUserService().setUser(record[0], record[1], record[2], record[3], encryptor.encoder(record[0]), record[4]);
        boolean flag = bannerIds.contains(user.getBannerId());
        if (flag == false) {
            boolean success = userRepository.createUser(user);
            if (success) {
                bannerIds.add(user.getBannerId());
                successfulResults.add("User was successfully created" + user.getBannerId());
                try {
                    successfulUsers.add(user);
                    userNotification.sendUserCredentials(user);
                    successfulResults.add("Credentials sent successfully for user" + user.getBannerId());
                    enrollCourse(courseId, user);
                } catch (AddressException e) {
                    failureResults.add("Failed to Send the mail for the user" + user.getBannerId());
                    e.printStackTrace();
                } catch (MessagingException e) {
                    failureResults.add("Failed to Send the mail for the user" + user.getBannerId());
                    e.printStackTrace();
                }
            } else {
                failureResults.add("Failed to create the user" + user.getBannerId());
            }
        } else {
            enrollCourse(courseId, user);
        }
    }

    private void enrollCourse(String courseId, IUser user) throws Exception {
        Boolean ifUserAlreadyHasARole = courseRepository.getUserDetailsOnCourse(user, courseId);

        if (ifUserAlreadyHasARole == false) {
            Boolean enrollCourse = courseRepository.enrollStudentForCourse(user, courseId);
            if (enrollCourse) {
                successfulResults.add("user" + user.getBannerId() + "successfully enrolled for course " + courseId);
            }
        } else {
            failureResults.add("User has multiple roles to the course" + user.getBannerId());
        }
    }
}
