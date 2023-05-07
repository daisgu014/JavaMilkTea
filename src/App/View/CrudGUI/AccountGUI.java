package App.View.CrudGUI;

import App.Controller.AccountController;
import App.Controller.CheckInput;
import Entity.Account;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class AccountGUI extends CrudGUI {
    private HashMap<Integer, Account> accounts;
    private AccountController accountController;
    private int index;
    private int key;
    public AccountGUI(JButton btnAdd, JButton btnUpdate, JButton btnDelete, JTable table, String title){
        super(btnAdd,btnUpdate,btnDelete,table,title);
    }
    public  AccountGUI(){
        accountController = new AccountController();
        setAccounts();
        Scene();
        RefreshTable();
    }
    public void SceneAccount(){

    }
    public void getData(){
        accounts = accountController.getDataAccount();
    }
    public void setTitleAccount(){
        setTitle("Account");
    }
    public void setDataTable(){
        setTable(accountController.getDataTable());
    }
    public void setButton(){
        JButton add = new JButton("Add") ;
        JButton edit = new JButton("Edit") ;
        JButton delete = new JButton("Delete");
        index = -1;
        key = 0;
        getTable().getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                int selectedRow = getTable().getSelectedRow();
                String selectedValue = String.valueOf(getTable().getValueAt(selectedRow, 0));
                key = Integer.parseInt(selectedValue);
                index = selectedRow;
            }
        });

        add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AccountFormAdd accountFormAdd = new AccountFormAdd();
                Object[] message = {accountFormAdd};
                JButton btnAccept = new JButton("Add");
                JButton btnCancel = new JButton("Cancel");
                btnAccept.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        CheckInput checkInput = new CheckInput();
                        if (checkInput.checkActiveMap(accounts,Integer.parseInt(accountFormAdd.getTfIDEmp().getText()))==false){
                            if (checkInput.checkUsername(accountFormAdd.getTfUsername().getText())==true){
                                if (checkInput.checkPasword(accountFormAdd.getTfPass().getText())==true){
                                    AccountController accountController = new AccountController();
                                    try{
                                        Account account = new Account(accountFormAdd.getTfUsername().getText(),accountFormAdd.getTfPass().getText());
                                        accountController.InsertAccount(account,accountFormAdd.getTfIDEmp().getText());
                                        int newkey = Integer.parseInt(accountFormAdd.getTfIDEmp().getText());
                                        accounts.put(newkey,account);
                                        //Set new table
                                        DefaultTableModel model = (DefaultTableModel) getTable().getModel();
                                        model.addRow(new Object[]{newkey,accounts.get(newkey).getUsername(),accounts.get(newkey).getPassword()});
                                        JOptionPane.getRootFrame().dispose();
                                    }catch (Exception exception){
                                        System.out.println(exception);
                                    }
                                }else{
                                    JOptionPane.showMessageDialog(null, "Mật khẩu từ 6 đến 12 ký tự",
                                            "Create Account", JOptionPane.INFORMATION_MESSAGE);
                                }
                            }else {
                                JOptionPane.showMessageDialog(null, "Tài khoản từ 6 đến 12 ký tự",
                                        "Create Account", JOptionPane.INFORMATION_MESSAGE);
                            }
                        }else {
                            JOptionPane.showMessageDialog(null, "Nhân viên đã có tài khoản",
                                    "Create Account", JOptionPane.INFORMATION_MESSAGE);
                        }
                    }
                });

                btnCancel.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        JOptionPane.getRootFrame().dispose(); // Close the dialog
                    }
                });
                Object[] options = {btnAccept,btnCancel};
                int check = JOptionPane.showOptionDialog(null, message, "Create Account",
                        JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, new ImageIcon(""),
                        options, options[0]);
            }
        });
        edit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AccountFormUpdate accountFormUpdate = new AccountFormUpdate();
                Object[] message = {accountFormUpdate};
                JButton btnAccept = new JButton("Accept");
                JButton btnCancel = new JButton("Cancel");
                if(index!=-1){
                    accountFormUpdate.getTfIDEmp().setText(String.valueOf(key));
                    accountFormUpdate.getTfUsername().setText(accounts.get(key).getUsername());
                    accountFormUpdate.getTfPass().setText(accounts.get(key).getPassword());
                    btnAccept.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            AccountController accountController = new AccountController();
                            try{
                                Account account = new Account(accountFormUpdate.getTfUsername().getText(),accountFormUpdate.getTfPass().getText());
                                accountController.UpdateAccount(account);
                                int newkey = Integer.parseInt(accountFormUpdate.getTfIDEmp().getText());
                                accounts.put(newkey,account);
//                                System.out.println(index);
//                                System.out.println(accounts.get(key).getPassword());
                                getTable().setValueAt(accounts.get(newkey).getPassword(),index,2);
                                JOptionPane.getRootFrame().dispose();
                            }catch (Exception exception){
                                System.out.println(exception);
                            }
                        }
                    });
                    btnCancel.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            JOptionPane.getRootFrame().dispose(); // Close the dialog
                        }
                    });
                    Object[] options = {btnAccept,btnCancel};
                    int check = JOptionPane.showOptionDialog(null, message, "Update",
                            JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, new ImageIcon(""),
                            options, options[0]);
                }else {
                    JOptionPane.showMessageDialog(null, "Vui lòng chọn tài khoản !",
                            "Update Account", JOptionPane.INFORMATION_MESSAGE);
                }

            }
        });
        delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        setBtnAdd(add);
        setBtnUpdate(edit);
        setBtnDelete(delete);
    }
    public HashMap<Integer, Account> getAccounts() {
        return accounts;
    }
    public void setAccounts(){
        getData();
        setTitleAccount();
        setDataTable();
        setButton();
    }
    public void RefreshTable(){
        String[] columns = {"Employee ID","Username","Password"};
        DefaultTableModel model = new DefaultTableModel(columns,0);
        for (Map.Entry<Integer, Account> set: accounts.entrySet()){
            Object[] row = {set.getKey(),set.getValue().getUsername(),set.getValue().getPassword()};
            model.addRow(row);
        }
        getTable().setModel(model);
    }
    public static void main(String[] args) {
        JFrame jFrame = new JFrame();
        jFrame.setSize(1280,800);
        AccountGUI accountGUI = new AccountGUI();
        jFrame.add(accountGUI);
        jFrame.setLocationRelativeTo(null);
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jFrame.setVisible(true);
    }
}
