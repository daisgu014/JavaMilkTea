package App.Controller;

import App.Model.LoginModel;
import App.View.ScreenGUI.MainGUI;

import javax.swing.*;

import static Main.Main.APP;

public class LoginController {
    private LoginModel model;

    public LoginController() {
        this.model = new LoginModel();
    }

    public LoginModel getModel() {
        return model;
    }

    public void checkLogIn(String username, String pwd) {
        if(model.checkLogIn(username, pwd)){
            APP.getButton(getModel().getLogic().getRoleId());
            MainGUI.currEmployee = getModel().getLogic().getEmployeeByUsername(username);
        }else{
            JOptionPane.showMessageDialog(
                    null,
                    "Login Fail!",
                    "Message",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }
}
