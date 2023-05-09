package App.View.CrudGUI;

import App.Controller.AccountController;
import App.Controller.CheckInput;
import Entity.Account;
import Entity.Category;

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
//        RefreshTable();
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
//        JButton add = new JButton("Add") ;
//        JButton edit = new JButton("Edit") ;
//        JButton delete = new JButton("Delete");
        RoundButton add = new RoundButton("Add",Color.decode("#1CA7EC"),Color.decode("#9AD9EA"));
        RoundButton edit = new RoundButton("Edit",Color.decode("#1CA7EC"),Color.decode("#9AD9EA"));
        RoundButton delete = new RoundButton("Delete",Color.decode("#F44336"),Color.decode("#F88279"));
        index = -1;
        key = 0;
        getTable().getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                int selectedRow = getTable().getSelectedRow();
                index = selectedRow;
//                String selectedValue = String.valueOf();
            }
        });
        add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AccountFormAdd accountFormAdd = new AccountFormAdd();
                Object[] message = {accountFormAdd};
                RoundButton btnAccept = new RoundButton("Accept",Color.decode("#1CA7EC"),Color.decode("#9AD9EA"));
                btnAccept.setPreferredSize(new Dimension(100, 30));
                RoundButton btnCancel = new RoundButton("Cancel",Color.decode("#7C8594"),Color.decode("#DDDEE5"));
                btnCancel.setPreferredSize(new Dimension(100, 30));
                btnAccept.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        CheckInput checkInput = new CheckInput();
                        if (checkInput.checkActiveMap(accounts,Integer.parseInt(accountFormAdd.getTfIDEmp().getText()))==false){
                            if (checkInput.checkUsername(accountFormAdd.getTfUsername().getText())==true){
                                if (checkInput.checkPasword(accountFormAdd.getTfPass().getText())==true){
                                    AccountController accountController = new AccountController();
                                    try{
                                        Account account = new Account(
                                                accountFormAdd.getTfUsername().getText(),
                                                accountFormAdd.getTfPass().getText()
                                        );
                                        accountController.InsertAccount(account,accountFormAdd.getTfIDEmp().getText());
                                        int newkey = Integer.parseInt(accountFormAdd.getTfIDEmp().getText());
                                        accounts.put(newkey,account);
                                        //Set new row table
                                        DefaultTableModel model = (DefaultTableModel) getTable().getModel();
                                        model.addRow(new Object[]{
                                                newkey,
                                                accounts.get(newkey).getUsername(),
                                                accounts.get(newkey).getPassword()
                                        });
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
//                JButton btnAccept = new JButton("Accept");
//                JButton btnCancel = new JButton("Cancel");
                RoundButton btnAccept = new RoundButton("Accept",Color.decode("#1CA7EC"),Color.decode("#9AD9EA"));
                btnAccept.setPreferredSize(new Dimension(100, 30));
                RoundButton btnCancel = new RoundButton("Cancel",Color.decode("#7C8594"),Color.decode("#DDDEE5"));
                btnCancel.setPreferredSize(new Dimension(100, 30));
                if(index!=-1){
                    key = Integer.parseInt(String.valueOf(getTable().getValueAt(index, 0)));
                    accountFormUpdate.getTfIDEmp().setText(String.valueOf(key));
                    accountFormUpdate.getTfIDEmp().setEditable(false);
                    accountFormUpdate.getTfUsername().setText(accounts.get(key).getUsername());
                    accountFormUpdate.getTfUsername().setEditable(false);
                    accountFormUpdate.getTfPass().setText(accounts.get(key).getPassword());
                    btnAccept.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            AccountController accountController = new AccountController();
                            try{
                                Account account = new Account(
                                        accountFormUpdate.getTfUsername().getText(),
                                        accountFormUpdate.getTfPass().getText()
                                );
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
                if (index !=-1){
                    int input = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn muốn xóa không?",
                            "Delete Account",
                            JOptionPane.YES_NO_OPTION,
                            JOptionPane.ERROR_MESSAGE
                    );
                    if (input == JOptionPane.OK_OPTION){
                        Account account = new Account(
                                String.valueOf(getTable().getValueAt(index,1)),
                                null
                        );
                        accountController.DeleteAccount(account);
                        ((DefaultTableModel)getTable().getModel()).removeRow(index);
                    }
                }else {
                    JOptionPane.showMessageDialog(null, "Vui lòng chọn tài khoản !",
                            "Delete Account", JOptionPane.INFORMATION_MESSAGE);
                }
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
//    public void RefreshTable(){
//        String[] columns = {"Employee ID","Username","Password"};
//        DefaultTableModel model = new DefaultTableModel(columns,0);
//        for (Map.Entry<Integer, Account> set: accounts.entrySet()){
//            Object[] row = {set.getKey(),set.getValue().getUsername(),set.getValue().getPassword()};
//            model.addRow(row);
//        }
//        getTable().setModel(model);
//    }
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
