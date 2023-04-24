package App.Controller;

import App.Model.WorkPositionModel;
import App.View.CrudGUI.CrudGUI;
import App.View.CrudGUI.WorkPositionGUI;
import Entity.Size;
import Entity.WorkPosition;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;

public class WorkPositionController extends CrudGUI {
    private ArrayList<WorkPosition> workPositions;
    private WorkPositionModel workPositionModel;
    public WorkPositionController (){
        workPositionModel = new WorkPositionModel();
    }
    public ArrayList<WorkPosition> getWorkPositions(){
        workPositions =  workPositionModel.getData();
        return  workPositions;
    }
    public JTable getDataTable(){
        String[] columns = {"ID", "Name","Level"};
        JTable table = new JTable();
        DefaultTableModel model = new DefaultTableModel(columns, 0);
        for (WorkPosition workPosition : workPositions) {
            Object[] row = {workPosition.getPositionId(),workPosition.getName(),workPosition.getPositionLvl()};
            model.addRow(row);
        }
        table.setModel(model);
        return table;
    }
    public WorkPosition InsertPosition(WorkPosition workPosition){
        WorkPosition workPositionInsert = new WorkPosition();
        try  {
            workPositionInsert = workPositionModel.Insert(workPosition);
            JOptionPane.showMessageDialog(null, "Thành công !",
                    "WorkPoisition", JOptionPane.INFORMATION_MESSAGE);

        }catch (Exception e){
            System.out.println(e);
            JOptionPane.showMessageDialog(null, "Thất bại !",
                    "WorkPoisition", JOptionPane.INFORMATION_MESSAGE);
        }
        return workPositionInsert;
    }
    public void UpdatePosition(WorkPosition workPosition){
        try  {
            workPositionModel.Update(workPosition);
            JOptionPane.showMessageDialog(null, "Thành công !",
                    "WorkPoisition", JOptionPane.INFORMATION_MESSAGE);

        }catch (Exception e){
            System.out.println(e);
            JOptionPane.showMessageDialog(null, "Thất bại !",
                    "WorkPoisition", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
