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
    public AccountGUI(JButton btnAdd, JButton btnUpdate, JButton btnDelete, JButton btnExit, JTable table, String title){
        super(btnAdd,btnUpdate,btnDelete,btnExit,table,title);
    }
    public  AccountGUI(){
        setAccounts();
        Scene();
        SelectTable();
        RefreshTable();
    }
    public void SceneAccount(){

    }
    public void getData(){
        AccountController accountController = new AccountController();
        accounts = accountController.getDataAccount();
    }
    public void setTitleAccount(){
        setTitle("Account");
    }
    public void setDataTable(){
        String[] columns = {"Employee ID","Username","Password"};
        JTable table = new JTable();
        DefaultTableModel model = new DefaultTableModel(columns, 0);
        for (Map.Entry<Integer, Account> set: accounts.entrySet()){
            Object[] row = {set.getKey(),set.getValue().getUsername(),set.getValue().getPassword()};
            model.addRow(row);
        }
        table.setModel(model);
        setTable(table);
    }
    public void setButton(){
        JButton add = new JButton("Add") ;
        JButton edit = new JButton("Edit") ;
        JButton delete = new JButton("Delete");
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
                                        boolean status = accountController.InsertAccount(account,accountFormAdd.getTfIDEmp().getText());
                                        if (status == true){
                                            int key = Integer.parseInt(accountFormAdd.getTfIDEmp().getText());
                                            accounts.put(key,account);
                                            //Set new table
                                            RefreshTable();
                                            JOptionPane.getRootFrame().dispose();
                                        }
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
                JButton btnDelete = new JButton("Delete");
                JButton btnCancel = new JButton("Cancel");
                btnAccept.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        AccountController accountController = new AccountController();
                        try{
                            Account account = new Account(accountFormUpdate.getTfUsername().getText(),accountFormUpdate.getTfPass().getText());
                            boolean checkActive = false;
                            for (Map.Entry<Integer, Account> set: accounts.entrySet()){
                                if (set.getKey() == Integer.parseInt(accountFormUpdate.getTfIDEmp().getText()) && set.getValue().getUsername().equals(accountFormUpdate.getTfUsername().getText())  ){
                                    checkActive = true;
                                }
                            }
                            if (checkActive == true){
                                accountController.UpdateAccount(account);
                                int key = Integer.parseInt(accountFormUpdate.getTfIDEmp().getText());
                                accounts.put(key,account);
                                RefreshTable();
                                JOptionPane.getRootFrame().dispose();
                            }else {
                                JOptionPane.showMessageDialog(null,"Tai khoan khong ung voi ma nhan vien!");
                            }
                        }catch (Exception exception){
                            System.out.println(exception);
                        }
                    }
                });
                btnDelete.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {

                    }
                });
                btnCancel.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        JOptionPane.getRootFrame().dispose(); // Close the dialog
                    }
                });
                Object[] options = {btnAccept,btnDelete,btnCancel};
                int check = JOptionPane.showOptionDialog(null, message, "Update",
                        JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, new ImageIcon(""),
                        options, options[0]);
            }
        });
        delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        JButton exit = new JButton("Exit");
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        setBtnAdd(add);
        setBtnUpdate(edit);
        setBtnDelete(delete);
        setBtnExit(exit);
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
    public void SelectTable(){
        JTable table = getTable();
        table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    int row = table.getSelectedRow();
//                    int col = table.getSelectedColumn();
                    Account account = new Account((String) table.getValueAt(row,1), (String) table.getValueAt(row,2));
                    AccountFormUpdate accountFormUpdate = new AccountFormUpdate();
                    accountFormUpdate.getTfIDEmp().setText(String.valueOf(table.getValueAt(row,0)));
                    accountFormUpdate.getTfIDEmp().setEditable(false);
                    accountFormUpdate.getTfUsername().setText(account.getUsername());
                    accountFormUpdate.getTfUsername().setEditable(false);
                    accountFormUpdate.getTfPass().setText(account.getPassword());

                    Object[] message = {accountFormUpdate};
                    JButton btnAccept = new JButton("Accept");
                    JButton btnCancel = new JButton("Cancel");
                    btnAccept.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            Account newAccount = new Account(accountFormUpdate.getTfUsername().getText(),accountFormUpdate.getTfPass().getText());
                            AccountController accountController = new AccountController();
                            accountController.UpdateAccount(newAccount);
                            table.setValueAt(newAccount.getPassword(),row,2);
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
                }
            }
        });
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
