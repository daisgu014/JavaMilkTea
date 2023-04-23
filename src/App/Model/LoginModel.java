package App.Model;

import Logic.LoginManagement;

public class LoginModel {
    private LoginManagement logic;

    public LoginModel() {
        this.logic = new LoginManagement();
    }

    public LoginManagement getLogic() {
        return logic;
    }

    public boolean checkLogIn(String username, String pwd) {
        logic.checkLogin(username, pwd);
        int roleId = logic.getRoleId();
        return roleId != -1;
    }
}
