package CSCI5308.GroupFormationTool.UserManager;

public class TA implements ITA {
    private String TaId;

    @Override
    public String getTaId() {
        return TaId;
    }

    @Override
    public void setTaId(String taId) {
        TaId = taId;
    }

}
