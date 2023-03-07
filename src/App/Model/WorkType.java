package App.Model;

public class WorkType {
    private int workTypeId;
    private int workTypeName;


    public WorkType() {
    }

    public WorkType(int workTypeId, int workTypeName) {
        this.workTypeId = workTypeId;
        this.workTypeName = workTypeName;
    }

    public WorkType(int workTypeName) {
        this.workTypeName = workTypeName;
    }

    public int getWorkTypeId() {
        return workTypeId;
    }

    public int getWorkTypeName() {
        return workTypeName;
    }

    public void setWorkTypeName(int workTypeName) {
        this.workTypeName = workTypeName;
    }
}
