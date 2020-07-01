package CSCI5308.GroupFormationTool.Course;

import java.util.ArrayList;
import java.util.Dictionary;

public interface IDeleteCourse {
    ArrayList<String> getAllCourseIds();

    void setAllCourseIds(ArrayList<String> allCourseIds);

    ArrayList<String> getAllCourseNames();

    void setAllCourseNames(ArrayList<String> allCourseNames);

    Dictionary getAllCoursesList();

    void setAllCoursesList(Dictionary allCoursesList);

    String getSelectedCourseId();

    void setSelectedCourseId(String selectedCourseId);

    String getCourseDeleteMessage();

    void setCourseDeleteMessage(String courseDeleteMessage);
}
