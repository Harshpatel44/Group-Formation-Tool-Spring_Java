package CSCI5308.GroupFormationTool.AdminPanel.Model;

import CSCI5308.GroupFormationTool.AdminPanel.AdminInjector;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;

public class AssignInstructor {
    private String InstructorId;
    private String selectedInstructorCourseId;
    private String InstructorAssignMessage="status here";
    private Dictionary allCoursesList = new Hashtable();
    private ArrayList<String> allCourseIds;
    private ArrayList<String> allCourseNames;
    private ArrayList<ArrayList<String>> tempCourse;

    public AssignInstructor() throws Exception {
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

    public AssignInstructor(String s){}

    public Dictionary getAllCoursesList() {
        return allCoursesList;
    }

    public void setAllCoursesList(Dictionary allCoursesList) {
        this.allCoursesList = allCoursesList;
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

    public String getInstructorAssignMessage() {
        return InstructorAssignMessage;
    }

    public void setInstructorAssignMessage(String instructorAssignMessage) {
        this.InstructorAssignMessage = instructorAssignMessage;
    }

    public String getInstructorId() {
        return InstructorId;
    }

    public void setInstructorId(String instructorId) {
        this.InstructorId = instructorId;
    }

    public String getSelectedInstructorCourseId() {
        return selectedInstructorCourseId;
    }

    public void setSelectedInstructorCourseId(String selectedInstructorCourseId) {
        this.selectedInstructorCourseId = selectedInstructorCourseId;
    }

}
