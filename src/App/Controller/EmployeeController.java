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
    public void UpdateEmployee(Employee employee){
       try  {
           employeeModel.UpdateEmployee(employee);
           JOptionPane.showMessageDialog(null, "Thành công !",
                   "Update Employee", JOptionPane.INFORMATION_MESSAGE);

       }catch (Exception e){
           System.out.println(e);
           JOptionPane.showMessageDialog(null, "Thất bại !",
                   "Update Employee", JOptionPane.INFORMATION_MESSAGE);
       }
    }
    public Employee InsertEmployee(Employee employee){
        Employee employeeIsert = new Employee();
        try  {
            employeeIsert = employeeModel.InsertEmployee(employee);
            JOptionPane.showMessageDialog(null, "Thành công !",
                    "Create Employee", JOptionPane.INFORMATION_MESSAGE);

        }catch (Exception e){
            System.out.println(e);
            JOptionPane.showMessageDialog(null, "Thất bại !",
                    "Create Employee", JOptionPane.INFORMATION_MESSAGE);
        }
        return employeeIsert;
    }

}
