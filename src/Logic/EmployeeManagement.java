package Logic;

import App.View.CrudGUI.EmployeeGUI;
import Entity.Employee;
import DAL.EmployeeDAO;
import com.mysql.cj.x.protobuf.MysqlxCrud;

import java.util.ArrayList;

public class EmployeeManagement {
    private ArrayList<Employee> employees;
    private EmployeeDAO employeeDAO = new EmployeeDAO();
    public EmployeeManagement(){}
    public void init(){}
    public Employee getEmployeeById(Integer id){
        return employeeDAO.get(id);
    }
    public ArrayList<Employee> getData(){
        return employeeDAO.getAll();
    }
    public Employee Insert(Employee employee){
        return employeeDAO.create(employee);
    }
    public  void Update(Employee employee){
        employeeDAO.update(employee);
    }
    public void Delete(Employee employee) {
        employeeDAO.delete(employee);
    }
}
