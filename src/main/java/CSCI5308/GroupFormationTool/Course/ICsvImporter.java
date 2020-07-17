package CSCI5308.GroupFormationTool.Course;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

public interface ICsvImporter {
    Map<Integer, List<String>> StudentsEnrolledForCourse(String CourseId, MultipartFile filess);
}
