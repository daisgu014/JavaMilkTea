package App.Model;

import Entity.Employee;
import Entity.Size;
import Entity.WorkPosition;
import Logic.EmployeeManagement;
import Logic.SizeManagement;
import Logic.WorkPositionManagement;

import java.util.ArrayList;

public class EmployeeModel {
    private ArrayList<Employee> employees;
    private EmployeeManagement employeeManagement;
    public EmployeeModel (){
        employeeManagement = new EmployeeManagement();
    }
    public ArrayList<Employee> getData(){
        employees = employeeManagement.getData();
        return employees;
    }
    public ArrayList<WorkPosition> getDataWorkPosition(){
        WorkPositionManagement workPositionManagement = new WorkPositionManagement();
        return workPositionManagement.getData();
    }
    public Employee InsertEmployee(Employee employee){
        return employeeManagement.Insert(employee);
    }
    public void UpdateEmployee(Employee employee){
        employeeManagement.Update(employee);
    }
    public void DeleteEmployee(Employee employee){
        employeeManagement.Delete(employee);
    }

}
