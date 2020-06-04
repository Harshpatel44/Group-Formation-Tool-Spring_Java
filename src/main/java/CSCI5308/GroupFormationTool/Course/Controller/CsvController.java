package CSCI5308.GroupFormationTool.Course.Controller;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import CSCI5308.GroupFormationTool.Injector;
import CSCI5308.GroupFormationTool.Course.AccessControl.ICsvImporter;
import CSCI5308.GroupFormationTool.Exceptions.ServiceLayerException;
import CSCI5308.GroupFormationTool.UserAuthentication.Model.User;

@Controller
public class CsvController {
	
	private ICsvImporter csvImporter; 
	
	@RequestMapping(value = "/course/uploadcsv", consumes = {"multipart/form-data"})
    public String upload(@RequestParam(name = "file") MultipartFile file, @RequestParam(name = "id") String courseID, Model model) throws ServiceLayerException
    {
//		ModelAndView mav;
		System.out.println("Inside Csv Controller");
		System.out.println("redirect:/login=" + courseID);
		csvImporter = Injector.instance().getCsvImporter();
		Map<Integer,List<String>> results = csvImporter.StudentsEnrolledForCourse(courseID, file);
		
//		mav = new ModelAndView("redirect:/login");
//		mav.addObject("displayresults", true);
		model.addAttribute("success", results.get(1));
		model.addAttribute("failure", results.get(2));
		
		return "courseadmin";
    }
	
	@GetMapping("/course/csv")
	public String register(User user) {
		return "courseadmin";
	}
}