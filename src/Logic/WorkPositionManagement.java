package Logic;

import Entity.WorkPosition;
import DAL.WorkPositionDAO;

import javax.swing.*;
import java.util.ArrayList;

public class WorkPositionManagement
{
    private ArrayList<WorkPosition> workPositions;
    private WorkPositionDAO workPositionDAO = new WorkPositionDAO();
    public WorkPositionManagement(){

    }
    public WorkPosition getWorkPositionById(Integer id){
        return workPositionDAO.get(id);
    }
    public ArrayList<WorkPosition> getData(){
        return workPositionDAO.getAll();
    }
    public WorkPosition Insert(WorkPosition workPosition){
        return workPositionDAO.create(workPosition);
    }
    public void Update(WorkPosition workPosition){
        workPositionDAO.update(workPosition);
    }
}
