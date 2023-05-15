package DAL;

import Entity.Employee;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginDAO {
    private Database loginAccess = new Database();

    public int checkLogIn(String username, String password){
        int roleId = -1;
        try {
            PreparedStatement preStmt = loginAccess.getPreStmt("""
                    select wp.WorkPositionLVL
                    from Account a join Employee e on a.EmployeeID = e.EmployeeId
                        join WorkPosition wp on wp.PositionId = e.WorkPositionID
                    where a.AccountUsername = ? and a.AccountPassword = ? and ISNULL(a.deleteAt);""");
            preStmt.setString(1, username);
            preStmt.setString(2, password);
            ResultSet rs = preStmt.executeQuery();
            if (rs.next()){
                roleId = rs.getInt(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return roleId;
    }

    public Employee getEmployeeByUsername(String username) {
        Employee e = null;
        WorkPositionDAO positionDAO = new WorkPositionDAO();
        try {
            PreparedStatement preStmt = loginAccess.getPreStmt("""
                select e.EmployeeId , e.EmployeeName, e.EmployeePhone, e.WorkPositionID
                from Account a join Employee e on a.EmployeeID = e.EmployeeId
                where a.AccountUsername = ?;""");
            preStmt.setString(1, username);
            ResultSet rs = preStmt.executeQuery();
            while(rs.next()) {
                e = new Employee(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        positionDAO.get(rs.getInt(4))
                );
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return e;
    }

}
