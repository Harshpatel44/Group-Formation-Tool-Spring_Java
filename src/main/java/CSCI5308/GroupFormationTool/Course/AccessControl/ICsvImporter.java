package CSCI5308.GroupFormationTool.Course.AccessControl;

import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

public interface ICsvImporter {
	public  Map<Integer,List<String>>  StudentsEnrolledForCourse(String CourseId, MultipartFile filess);
}
