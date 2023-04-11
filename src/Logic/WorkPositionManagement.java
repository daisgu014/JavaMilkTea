package Logic;

import Entity.WorkPosition;
import DAL.WorkPositionDAO;

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
}
