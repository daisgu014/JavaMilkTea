package App.View.CrudGUI;

import javax.swing.*;
import java.awt.*;

public class AccountFormUpdate extends FormDialog {
    private JTextField tfUsername;
    private JPasswordField tfPass;
    private JTextField tfIDEmp;

    public AccountFormUpdate(JLabel lbTitle,JTextField tfUsername, JPasswordField tfPass, JTextField tfIDEmp) {
        super(lbTitle);
        this.tfUsername = tfUsername;
        this.tfPass = tfPass;
        this.tfIDEmp = tfIDEmp;
    }
    public AccountFormUpdate(){
        SceneForm();
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

    public JTextField getTfIDEmp() {
        return tfIDEmp;
    }

    public void setTfIDEmp(JTextField tfIDEmp) {
        this.tfIDEmp = tfIDEmp;
    }
//    public JPanel pnRows(JLabel jLabel, JTextField textField){
//        JPanel pn = new JPanel();
//        pn.setPreferredSize(new Dimension(480,50));
//        jLabel.setPreferredSize(new Dimension(200,30));
//        textField.setPreferredSize(new Dimension(200,30));
//        pn.add(jLabel);
//        pn.add(textField);
//        return pn;
//    }
    @Override
    public JPanel pnContainer(){
        JPanel pn = new JPanel();
        pn.setPreferredSize(new Dimension(490,300));
        pn.setLayout(new FlowLayout());
        pn.setBorder(new RoundedBorder(20));
//        pn.setBorder(BorderFactory.createLineBorder(Color.decode("#1CA7EC")));

//        pn.setBackground(Color.decode("#9AD9EA"));
        JLabel lbIDEmp = new JLabel("Employee ID: ",SwingConstants.CENTER);
        pn.add(pnRows(lbIDEmp,getTfIDEmp()));
        JLabel lbUsername = new JLabel("Username: ",SwingConstants.CENTER);
        pn.add(pnRows(lbUsername,getTfUsername()));
        JLabel lbPass = new JLabel("Password: ",SwingConstants.CENTER);
        pn.add(pnRows(lbPass,getTfPass()));
        return pn;
    }
    @Override
    public void SceneForm(){
        setPreferredSize(new Dimension(500,600));
        setLayout(new FlowLayout());
        setLbTitle( new JLabel("Update Account",SwingConstants.CENTER));
        setTfUsername(new JTextField());
        setTfPass(new JPasswordField());
        setTfIDEmp(new JTextField());
        add(pnTitle());
        add(pnContainer());
        setVisible(true);
    }
    public static void main(String[] args) {
        AccountFormUpdate accountFormUpdate = new AccountFormUpdate();
        Object[] message = {accountFormUpdate};
        int check = JOptionPane.showConfirmDialog(null,message,"Update",JOptionPane.OK_OPTION,JOptionPane.QUESTION_MESSAGE,
                new ImageIcon(""));
        if (check == JOptionPane.OK_OPTION){
            System.out.println(accountFormUpdate.getTfUsername().getText());
        }
    }
}
