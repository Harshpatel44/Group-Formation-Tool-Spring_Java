package CSCI5308.GroupFormationTool.Course;

import CSCI5308.GroupFormationTool.Injector;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;

public class DeleteCourse implements IDeleteCourse {
    private String selectedCourseId;
    private String courseDeleteMessage="status here";
    private Dictionary allCoursesList = new Hashtable();
    private ArrayList<String> allCourseIds;
    private ArrayList<String> allCourseNames;

    public DeleteCourse(ICourseService courseService) throws Exception {
        Injector.instance().setCourseService(courseService);
    }
    public DeleteCourse(){ }

    @Override
    public ArrayList<String> getAllCourseIds() {
        return allCourseIds;
    }

    @Override
    public void setAllCourseIds(ArrayList<String> allCourseIds) {
        this.allCourseIds = allCourseIds;
    }

    @Override
    public ArrayList<String> getAllCourseNames() {
        return allCourseNames;
    }

    @Override
    public void setAllCourseNames(ArrayList<String> allCourseNames) {
        this.allCourseNames = allCourseNames;
    }

    @Override
    public Dictionary getAllCoursesList() throws Exception {
        allCoursesList=Injector.instance().getCourseService().coursesWithIdForDropdown();
        return allCoursesList;
    }

    @Override
    public void setAllCoursesList(Dictionary allCoursesList) {

        this.allCoursesList = allCoursesList;
    }

    @Override
    public String getSelectedCourseId() {
        return selectedCourseId;
    }

    @Override
    public void setSelectedCourseId(String selectedCourseId) {
        this.selectedCourseId = selectedCourseId;
    }

    @Override
    public String getCourseDeleteMessage() {
        return courseDeleteMessage;
    }

    @Override
    public void setCourseDeleteMessage(String courseDeleteMessage) {
        this.courseDeleteMessage = courseDeleteMessage;
    }
}
