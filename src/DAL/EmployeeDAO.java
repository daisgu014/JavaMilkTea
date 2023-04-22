package DAL;

import Entity.Account;
import Entity.Employee;
import Entity.WorkPosition;
import Logic.WorkPositionManagement;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class EmployeeDAO extends DAO<Employee> {
    Database database = new Database();
    WorkPositionManagement workPositionManagement = new WorkPositionManagement();
    @Override
    public ArrayList<Employee> getAll() {
        ArrayList<Employee> employeeArrayList = new ArrayList<>();
        try {
            PreparedStatement prSt = database.getPreStmt("SELECT emp.EmployeeId,emp.EmployeeName,emp.EmployeePhone,wp.PositionId,wp.WorkPositionName,wp.WorkPositionLVL\n" +
                    "FROM employee as emp ,workposition as wp \n" +
                    "WHERE emp.WorkPositionID = wp.PositionId");
            ResultSet rs = prSt.executeQuery();
            while (rs.next()) {
                WorkPosition workPosition = new WorkPosition(rs.getInt(4),rs.getString(5),rs.getInt(6));
                Employee employee = new Employee(rs.getInt(1),rs.getString(2),rs.getString(3),workPosition);
                employeeArrayList.add(employee);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return employeeArrayList;
    }

    @Override
    public Employee get(int id) {
        Employee employee = new Employee();
        PreparedStatement preparedStatement = database.getPreStmt("select * from Employee where EmployeeId=?");
        try {
            preparedStatement.setInt(1,id);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
            employee.setEmployeeId(rs.getInt(1));
            employee.setEmployeeName(rs.getString(2));
            employee.setEmployeePhone(rs.getString(3));
            employee.setWorkPosition(workPositionManagement.getWorkPositionById(rs.getInt(4)));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return employee;
    }

    @Override
    public Employee create(Employee employee) {
        return null;
    }


    @Override
    public void update(Employee employee) {

    }

    @Override
    public void delete(Employee employee) {

    }

    @Override
    public void deleteById(int id) {

    }
}
