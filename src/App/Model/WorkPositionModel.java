package App.Model;

import Entity.WorkPosition;
import Logic.WorkPositionManagement;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class WorkPositionModel {
    private ArrayList<WorkPosition> workPositions;
    private WorkPositionManagement workPositionManagement;
    public  WorkPositionModel (){
        workPositionManagement = new WorkPositionManagement();
    }
    public ArrayList<WorkPosition> getData(){
        workPositions = workPositionManagement.getData();
        return workPositions;
    }
    public WorkPosition Insert( WorkPosition workPosition){
        return workPositionManagement.Insert(workPosition);
    }
    public void Update(WorkPosition workPosition){
        workPositionManagement.Update(workPosition);
    }
    public void Delete(WorkPosition workPosition){
        workPositionManagement.Delete(workPosition);
    }
}
