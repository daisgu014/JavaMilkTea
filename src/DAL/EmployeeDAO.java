package DAL;

import App.Model.Employee;
import Logic.AccountManagement;
import Logic.WorkPositionManagement;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class EmployeeDAO extends DAO<Employee> {
    Database database = new Database();
    WorkPositionManagement workPositionManagement = new WorkPositionManagement();
    @Override
    public ArrayList<Employee> getAll() {
        return null;
    }

    @Override
    public Employee get(int id) {
        Employee employee = new Employee();
        PreparedStatement preparedStatement = database.getPreStmt("select * from Employee where id=?");
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
    public int create(Employee employee) {
        return 0;
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
