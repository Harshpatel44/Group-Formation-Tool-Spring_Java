package CSCI5308.GroupFormationTool.Course.Service;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import org.springframework.web.multipart.MultipartFile;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

import CSCI5308.GroupFormationTool.Injector;
import CSCI5308.GroupFormationTool.Course.AccessControl.ICourseRepository;
import CSCI5308.GroupFormationTool.Course.AccessControl.ICsvImporter;
import CSCI5308.GroupFormationTool.UserAuthentication.AccessControl.IPasswordEncryptor;
import CSCI5308.GroupFormationTool.UserAuthentication.AccessControl.IUserNotification;
import CSCI5308.GroupFormationTool.UserAuthentication.AccessControl.IUserRepository;
import CSCI5308.GroupFormationTool.UserAuthentication.Model.User;

public class CsvImporterService implements ICsvImporter {
	private IUserRepository userRepository;
	private ICourseRepository courseRepository;
	private IPasswordEncryptor encryptor;
	private IUserNotification userNotification;
	private List<String> failureResults;
	private List<String> successfullResults;
	private Map<Integer, List<String>> results;

	@Override
	public Map<Integer, List<String>> StudentsEnrolledForCourse(String courseId, MultipartFile file) {
		try {
			failureResults = new ArrayList<String>();
			successfullResults = new ArrayList<String>();
			results = new HashMap<Integer, List<String>>();
			courseRepository = Injector.instance().getCourseRepository();
			userRepository = Injector.instance().getUserRepository();
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
		results.put(1, successfullResults);
		results.put(2, failureResults);
		return results;
	}

	private void checkTheUserAndEnroll(String[] record, List<String> bannerIds, IUserRepository userRepository,
			String courseId) {
		List<User> successfullUsers = new ArrayList<User>();
		encryptor = Injector.instance().getPasswordEncryptor();
		userNotification = Injector.instance().getUserNotification();
		User user = new User(record[0], record[1], record[2], record[3], encryptor.encoder(record[0]), record[4]);
		if (!bannerIds.contains(user.getBannerId())) {
			boolean success = userRepository.createUser(user);
			if (success) {
				bannerIds.add(user.getBannerId());
				successfullResults.add("User was successfully created" + user.getBannerId());
				try {
					successfullUsers.add(user);
					userNotification.sendUserCredentials(user);
					successfullResults.add("Credentials sent succesfully for user" + user.getBannerId());
					enrollCourse(courseId, user, userRepository);
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
			enrollCourse(courseId, user, userRepository);
		}
	}

	private void enrollCourse(String courseId, User user, IUserRepository userRepository) {
		Boolean ifUserAlredyHasARole = courseRepository.getUserDetailsOnCourse(user, courseId);
		if (!ifUserAlredyHasARole) {
			Boolean enrollCourse = courseRepository.enrollStudentForCourse(user, courseId);
			if (enrollCourse) {
				successfullResults.add("user" + user.getBannerId() + "successfully enrolled for course " + courseId);
			}
		} else {
			failureResults.add("User has multiple roles to the course" + user.getBannerId());
		}
	}
}
