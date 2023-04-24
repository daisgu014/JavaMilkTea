package Logic;

import DAL.LoginDAO;
import Entity.Employee;

public class LoginManagement {
    private LoginDAO loginDAO;
    private int roleId;

    public LoginManagement() {
        this.loginDAO = new LoginDAO();
    }

    public int getRoleId() {
        return roleId;
    }

    public void checkLogin(String username, String pwd) {
        roleId = loginDAO.checkLogIn(username, pwd);
    }

    public Employee getEmployeeByUsername(String username) {
        return loginDAO.getEmployeeByUsername(username);
    }

}
