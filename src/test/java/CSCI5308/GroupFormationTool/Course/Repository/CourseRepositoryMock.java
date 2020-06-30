package CSCI5308.GroupFormationTool.Course.Repository;

public class CourseRepositoryMock {

    public boolean checkIfUserPresent(String taId){
        boolean result = false;
            if(taId.equals("B00123456")){
                result = true;
            }
        return result;
    }

    private boolean checkIfAlreadyTa(String taId, String courseId) {
        boolean result = false;
                if(taId.equals("B00123456") && courseId.equals("CSCI1")){
                    result = true;
                }
        return result;
    }

    public String addTaTest(String taId, String courseId) {
        String result=null;
        if(checkIfUserPresent(taId))
        {
            if(checkIfAlreadyTa(taId,courseId)){
                result = "Already user is TA of courseId:"+courseId+".";
            }
            else{
                    result = "user with Id:"+taId+" is add as a TA for courseId"+courseId+".";
                }
        }
        else
        {
            result = "No user exist with Id:"+taId+" present in system.";
        }
        return result;
    }
}
