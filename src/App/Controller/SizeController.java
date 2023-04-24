package App.Controller;

import App.Model.SizeModel;
import Entity.Employee;
import Entity.Size;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;

public class SizeController {
    private ArrayList<Size> sizeArrayList;
    private SizeModel sizeModel;
    public SizeController(){
        sizeModel = new SizeModel();
        sizeArrayList = new ArrayList<>();
    }
    public ArrayList<Size> getData(){
        sizeArrayList = sizeModel.getData();
        return sizeArrayList;
    }
    public void setData(){
        sizeArrayList = sizeModel.getData();
    }
    public JTable getDataTable(){
        String[] columns = {"Sign", "Description"};
        JTable table = new JTable();
        DefaultTableModel model = new DefaultTableModel(columns, 0);
        for (Size size : sizeArrayList) {
            Object[] row = {size.getSign(), size.getDescription()};
            model.addRow(row);
        }
        table.setModel(model);
        return table;
    }
    public void InsertSize(Size size){
        try{
            sizeModel.Insert(size);
            JOptionPane.showMessageDialog(null, "Thành công!",
                    "Create Size", JOptionPane.INFORMATION_MESSAGE);
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, "Thất bại!",
                    "Create Size", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    public void UpdateSize(Size size){
        try{
            sizeModel.Update(size);
            JOptionPane.showMessageDialog(null, "Thành công!",
                    "Create Size", JOptionPane.INFORMATION_MESSAGE);
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, "Thất bại!",
                    "Create Size", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
