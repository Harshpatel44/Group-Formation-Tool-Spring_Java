package CSCI5308.GroupFormationTool.AdminPanel.Model;


import CSCI5308.GroupFormationTool.AdminPanel.AdminInjector;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;

public class DeleteCourse {
    private String selectedCourseId;
    private String courseDeleteMessage="status here";
    private Dictionary allCoursesList = new Hashtable();
    private ArrayList<ArrayList<String>> tempCourse;
    private ArrayList<String> allCourseIds;
    private ArrayList<String> allCourseNames;

    public DeleteCourse() throws Exception {
        tempCourse = AdminInjector.instance().getAdminRepository().getAllCourses();
        allCourseIds = tempCourse.get(0);
        allCourseNames = tempCourse.get(1);
        try {
            for (int i = 0; i < allCourseIds.size(); i++) {
                allCoursesList.put(allCourseIds.get(i) + " " + allCourseNames.get(i), allCourseIds.get(i));
            }
        }
        catch (Exception e){}
    }
    public DeleteCourse(String s){

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

    public ArrayList<String> getAllCourseNames() {
        return allCourseNames;
    }

    public void setAllCourseNames(ArrayList<String> allCourseNames) {
        this.allCourseNames = allCourseNames;
    }

    public ArrayList<String> getAllCourseIds() {
        return allCourseIds;
    }

    public void setAllCourseIds(ArrayList<String> allCourseIds) {
        this.allCourseIds = allCourseIds;
    }
}
