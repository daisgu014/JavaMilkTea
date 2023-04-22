package App.Controller;

import App.Model.EmployeeModel;
import App.Model.SizeModel;
import Entity.Employee;
import Entity.Size;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;

public class EmployeeController {
    private ArrayList<Employee> employeeArrayList;
    private EmployeeModel employeeModel;
    private JTable table;
    public EmployeeController(){
        employeeModel = new EmployeeModel();
        employeeArrayList = new ArrayList<>();
        setData();
    }
    public ArrayList<Employee> getData(){
        employeeArrayList = employeeModel.getData();
        return employeeArrayList;
    }
    public void setData(){
        employeeArrayList = employeeModel.getData();
    }
    public JTable getDataTable(){
        String[] columns = {"ID","Name", "Phone","WorkPosition"};
        table = new JTable();
        DefaultTableModel model = new DefaultTableModel(columns, 0);
        for (Employee employee : employeeArrayList) {
            Object[] row = {employee.getEmployeeId(),employee.getEmployeeName(),employee.getEmployeePhone(),employee.getWorkPosition().getName()};
            model.addRow(row);
        }
        table.setModel(model);
        return table;
    }
}
