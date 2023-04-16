package App.View.CrudGUI;

import Entity.Account;

import javax.swing.*;
import javax.swing.plaf.basic.BasicArrowButton;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AccountForm extends Form{
    private JLabel lbTitle;
    private JButton btnAccept;
    private JButton btnCancel;
    private JTextField tfUsername;
    private JPasswordField tfPass;
    private Account account;
    public AccountForm(JLabel lbTitle, JButton btnAccept, JButton btnCancel, JTextField tfUsername,JPasswordField tfPass){
        super(lbTitle,btnAccept,btnCancel);
        this.tfUsername = tfUsername;
        this.tfPass = tfPass;
    }

    public JTextField getTfUsername() {
        return tfUsername;
    }

    public void setTfUsername(JTextField tfUsername) {
        this.tfUsername = tfUsername;
    }

    public JPasswordField getTfPass() {
        return tfPass;
    }

    public void setTfPass(JPasswordField tfPass) {
        this.tfPass = tfPass;
    }

    public AccountForm(){

    }
    public JPanel pnRows( JLabel jLabel,JTextField textField){
        JPanel pn = new JPanel();
        pn.setPreferredSize(new Dimension(480,50));
        jLabel.setPreferredSize(new Dimension(200,30));
        textField.setPreferredSize(new Dimension(200,30));
        pn.add(jLabel);
        pn.add(textField);
        return pn;
    }
    @Override
    public JPanel pnPnContainer(){
        JPanel pn = new JPanel();
        pn.setPreferredSize(new Dimension(490,300));
        pn.setLayout(new FlowLayout());
        pn.setBorder(BorderFactory.createLineBorder(Color.red));
        JLabel lbUsername = new JLabel("Username ",SwingConstants.CENTER);
//        JTextField tfUsername = new JTextField();
//        setTfUsername(new JTextField());
        pn.add(pnRows(lbUsername,getTfUsername()));
        JLabel lbPass = new JLabel("Password ",SwingConstants.CENTER);
//        JPasswordField tfPass = new JPasswordField();
//        setTfPass(new JPasswordField());
        pn.add(pnRows(lbPass,getTfPass()));
        return pn;
    }
    public void SceneAccount(Account account){
        setLbTitle(new JLabel("Account",SwingConstants.CENTER));
        setBtnAccept(new JButton("Accept"));
        setBtnCancel(new JButton("Exit"));
        setTfUsername(new JTextField(account.getUsername()));
        setTfPass(new JPasswordField(account.getPassword()));
        getBtnAccept().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(getTfUsername().getText());
            }
        });
        getBtnCancel().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        SceneForm();
    }
    public static void main(String[] args) {
//        JLabel lbTitle = new JLabel("Hihi",SwingConstants.CENTER);
//        JButton btnAccept = new JButton("Accept");
//        JButton btnCancel = new JButton("Cancel");
        AccountForm accountForm = new AccountForm();
//        accountForm.SceneAccount();
    }
}
