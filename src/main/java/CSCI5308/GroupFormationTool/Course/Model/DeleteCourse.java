package CSCI5308.GroupFormationTool.Course.Model;

import CSCI5308.GroupFormationTool.Injector;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;

public class DeleteCourse {
    private String selectedCourseId;
    private String courseDeleteMessage="status here";
    private Dictionary allCoursesList = new Hashtable();
    private ArrayList<String> allCourseIds;
    private ArrayList<String> allCourseNames;

    public DeleteCourse() throws Exception {
        allCoursesList=Injector.instance().getCourseService().CoursesWithIdForDropdown();
    }
    public DeleteCourse(String s){
    }

    public ArrayList<String> getAllCourseIds() {
        return allCourseIds;
    }

    public void setAllCourseIds(ArrayList<String> allCourseIds) {
        this.allCourseIds = allCourseIds;
    }

    public ArrayList<String> getAllCourseNames() {
        return allCourseNames;
    }

    public void setAllCourseNames(ArrayList<String> allCourseNames) {
        this.allCourseNames = allCourseNames;
    }

    public Dictionary getAllCoursesList() {
        return allCoursesList;
    }

    public void setAllCoursesList(Dictionary allCoursesList) {
        this.allCoursesList = allCoursesList;
    }

    public String getSelectedCourseId() {
        return selectedCourseId;
    }

    public void setSelectedCourseId(String selectedCourseId) {
        this.selectedCourseId = selectedCourseId;
    }

    public String getCourseDeleteMessage() {
        return courseDeleteMessage;
    }

    public void setCourseDeleteMessage(String courseDeleteMessage) {
        this.courseDeleteMessage = courseDeleteMessage;
    }
}
