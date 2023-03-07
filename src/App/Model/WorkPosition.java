package App.Model;

public class WorkPosition {
    private int workPositionId;
    private String workPositionName;
    private String workPositionLVL;

    public WorkPosition() {
    }

    public WorkPosition(int workPositionId, String workPositionName, String workPositionLVL) {
        this.workPositionId = workPositionId;
        this.workPositionName = workPositionName;
        this.workPositionLVL = workPositionLVL;
    }

    public WorkPosition(String workPositionName, String workPositionLVL) {
        this.workPositionName = workPositionName;
        this.workPositionLVL = workPositionLVL;
    }

    public int getWorkPositionId() {
        return workPositionId;
    }

    public void setWorkPositionId(int workPositionId) {
        this.workPositionId = workPositionId;
    }

    public String getWorkPositionName() {
        return workPositionName;
    }

    public void setWorkPositionName(String workPositionName) {
        this.workPositionName = workPositionName;
    }

    public String getWorkPositionLVL() {
        return workPositionLVL;
    }

    public void setWorkPositionLVL(String workPositionLVL) {
        this.workPositionLVL = workPositionLVL;
    }
}
