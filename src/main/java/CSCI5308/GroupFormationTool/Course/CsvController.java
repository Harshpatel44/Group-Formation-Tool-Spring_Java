package CSCI5308.GroupFormationTool.Course;

import java.util.List;
import java.util.Map;

import CSCI5308.GroupFormationTool.UserManager.CurrentUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;


@Controller
public class CsvController {
	
	private ICsvImporter csvImporter; 
	
	@RequestMapping(value = "/course/uploadcsv", consumes = {"multipart/form-data"})
    public String upload(@RequestParam(name = "file") MultipartFile file,
						 Model model)
    {
		csvImporter = CourseAbstractFactory.instance().getCsvImporter();
		String courseID = CurrentCourse.instance().getCurrentCourseId();
		Map<Integer,List<String>> results = csvImporter.StudentsEnrolledForCourse(courseID, file);
		model.addAttribute("success", results.get(1));
		model.addAttribute("failure", results.get(2));
		model.addAttribute("userId", CurrentUser.instance().getBannerId());
		model.addAttribute("courseId",courseID);
		return "courseadmin";
    }
	
	@GetMapping("/course/csv")
	public String register() {
		return "courseadmin";
	}
}
