package App.View;

import App.Model.CRUDForm;

import javax.swing.*;
import java.awt.*;

public class AccountForm extends CRUDForm {
    private JLabel lbID;
    private JTextField tfID;
    private JLabel lbUsername;
    private JTextField tfUsername;
    private JLabel lbPassword;
    private JTextField tfPassword;

    AccountForm(){

    }
    public void renderForm(String txt1, String txt2,Object[][] rows, Object[] colums){
        this.getFrom(txt1,txt2,rows,colums);
        lbID = new JLabel("Employee ID: ",SwingConstants.CENTER);
        lbID.setBounds(0,90,125,40);
        lbID.setFont(new Font("Arial",Font.BOLD,15));
        tfID = new JTextField(2);
        tfID.setBounds(125,90,240,40);
        tfID.setFont(new Font("Arial",Font.BOLD,15));
        lbUsername = new JLabel("Username: ",SwingConstants.CENTER);
        lbUsername.setBounds(0,180,125,40);
        lbUsername.setFont(new Font("Arial",Font.BOLD,15));
        tfUsername = new JTextField(2);
        tfUsername.setBounds(125,180,240,40);
        tfUsername.setFont(new Font("Arial",Font.BOLD,15));
        lbPassword = new JLabel("Password: ",SwingConstants.CENTER);
        lbPassword.setBounds(0,270,125,40);
        lbPassword.setFont(new Font("Arial",Font.BOLD,15));
        tfPassword = new JTextField(2);
        tfPassword.setBounds(125,270,240,40);
        tfPassword.setFont(new Font("Arial",Font.TRUETYPE_FONT,15));
        this.getPn().add(lbID);
        this.getPn().add(tfID);
        this.getPn().add(lbUsername);
        this.getPn().add(tfUsername);
        this.getPn().add(lbPassword);
        this.getPn().add(tfPassword);
    }
    public static void main(String[] args) {
        AccountForm ac = new AccountForm();
        Object columns[] = {"Name", "Designation", "Salary"};
        Object rows[][] = {{"Adithya", "Content Developer", 25000},
                {"Jai", "SME", 30000},
                {"Chaitanya", "Java Engineer", 45000},
                {"Ramesh", "Scala Developer", 40000},
                {"Ravi", "SAP  Consultant", 70000}};
        ac.renderForm("Account","Detail",rows,columns);
//        ac.getFrom("Account","Detail");
//        JLabel lb = new JLabel("Hello");
//        lb.setBounds(0,100,400,50);
//        ac.getPn().add(lb);

    }
}
