package App.Model;

import Entity.Employee;
import Entity.Size;
import Logic.EmployeeManagement;
import Logic.SizeManagement;

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
}
