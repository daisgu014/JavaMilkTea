package Logic;

import Entity.Employee;
import DAL.EmployeeDAO;

import java.util.ArrayList;

public class EmployeeManagement {
    private ArrayList<Employee> employees;
    private EmployeeDAO employeeDAO = new EmployeeDAO();
    public EmployeeManagement(){}
    public void init(){}
    public Employee getEmployeeById(Integer id){
        return employeeDAO.get(id);
    }
}
